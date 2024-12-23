package modelo.dao.funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controle.entidade.conexao.ConexaoBD;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.funcionario.Funcionario;

public class FuncionarioDAO {

	public boolean login(String cpf, String senha) {
		String sql = "SELECT * FROM funcionario WHERE cpf = ? AND senha = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cpf);
			stmt.setString(2, senha);

			ResultSet rs = stmt.executeQuery();
			return rs.next(); // Retorna true se houver um funcionário com as credenciais corretas

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String nomeFuncionario(String cpf) {
		String sqlNome = "SELECT nome FROM funcionario WHERE cpf = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sqlNome)) {

			stmt.setString(1, cpf);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) { // se tiver um funcionario com esse cpf,
				return rs.getString("nome"); // retorna o nome do funcionario
			} else {
				return "Nenhum"; // não retorna nada
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro ao consultar";
		}
	}

	public boolean funcionarioAdm(String cpf) {
		String sqlNome = "SELECT adm FROM funcionario WHERE cpf = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sqlNome)) {

			stmt.setString(1, cpf);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getBoolean("adm");
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean cadastrarFuncionario(Funcionario funcionario) {
		String sqlContato = "INSERT INTO contato (email, telefone) VALUES (?, ?)";
		String sqlFuncionario = "INSERT INTO funcionario (cpf, senha, nome, contato_id, adm) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtContato = conn.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario)) {

			stmtContato.setString(1, funcionario.getContato().getEmail());
			stmtContato.setString(2, funcionario.getContato().getTelefone());
			stmtContato.executeUpdate();

			ResultSet generatedKeys = stmtContato.getGeneratedKeys();
			if (generatedKeys.next()) {
				int contatoId = generatedKeys.getInt(1);
				stmtFuncionario.setString(1, funcionario.getCpf());
				stmtFuncionario.setString(2, funcionario.getSenhaFuncionario());
				stmtFuncionario.setString(3, funcionario.getNome());
				stmtFuncionario.setInt(4, contatoId);
				stmtFuncionario.setBoolean(5, funcionario.isAdm());
				stmtFuncionario.executeUpdate();
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean verificaCpfExistente(String cpf) {
		String sqlFuncionarioCPF = "SELECT cpf FROM funcionario WHERE cpf = ?";
		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionarioCPF)) {

			stmtFuncionario.setString(1, cpf);
			ResultSet rs = stmtFuncionario.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean verificaTelefoneExistente(String telefone) {
		String sqlFuncionarioContatoTelefone = "SELECT c.telefone FROM contato c "
				+ "JOIN funcionario f ON c.id_contato = f.contato_id " + "WHERE c.telefone = ?";
		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtContato = conn.prepareStatement(sqlFuncionarioContatoTelefone)) {

			stmtContato.setString(1, telefone);
			ResultSet rs = stmtContato.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean verificaEmailExistente(String email) {
		String sqlFuncionarioContatoEmail = "SELECT c.email FROM contato c "
				+ "JOIN funcionario f ON c.id_contato = f.contato_id " + "WHERE c.email = ?";
		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtContato = conn.prepareStatement(sqlFuncionarioContatoEmail)) {

			stmtContato.setString(1, email);
			ResultSet rs = stmtContato.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluirFuncionario(String cpf) {
		String sql = "DELETE FROM funcionario WHERE cpf = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, cpf);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Funcionario> listarFuncionarios() {

		List<Funcionario> funcionarios = new ArrayList<>();
		String sqlSelect = "SELECT f.*, c.email, c.telefone " + "FROM funcionario f "
				+ "JOIN contato c ON f.contato_id = c.id_contato";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmt = conn.prepareStatement(sqlSelect);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				Contato contato = new Contato();

				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setSenhaFuncionario(rs.getString("senha"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setAdm(rs.getBoolean("adm"));

				contato.setId(rs.getInt("contato_id"));
				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				funcionario.setContato(contato);
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return funcionarios;
	}

	public Funcionario carregarDadosFuncionario(String cpf) {
		Funcionario funcionario = null;
		String sql = "SELECT nome, cpf, senha, email, telefone,id_contato " + "FROM funcionario "
				+ "JOIN contato ON contato_id = id_contato " + "WHERE cpf = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setSenhaFuncionario(rs.getString("senha"));

				Contato contato = new Contato();
				contato.setId(rs.getInt("id_contato"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				funcionario.setContato(contato);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}

	public boolean editarFuncionario(Funcionario f) {
		String sqlFuncionario = "UPDATE funcionario SET nome = ?, senha = ?, adm = ? WHERE cpf = ?";
		String sqlContato = "UPDATE contato SET email = ?, telefone = ? WHERE id_contato = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtContato = conn.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario)) {

			stmtFuncionario.setString(1, f.getNome());
			stmtFuncionario.setString(2, f.getSenhaFuncionario());
			stmtFuncionario.setBoolean(3, f.isAdm());
			stmtFuncionario.setString(4, f.getCpf());
			stmtFuncionario.executeUpdate();
			int rowsAffectedFuncionario = stmtFuncionario.executeUpdate();

			stmtContato.setString(1, f.getContato().getEmail());
			stmtContato.setString(2, f.getContato().getTelefone());
			stmtContato.setInt(3, f.getContato().getId());
			stmtContato.executeUpdate();
			int rowsAffectedContato = stmtContato.executeUpdate();

			// Verifica se as atualizações foram bem-sucedidas
			return rowsAffectedFuncionario > 0 || rowsAffectedContato > 0;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public void deletarFuncionario(int id) {
		String delFuncionario = "DELETE * FROM funcionario WHERE cpf = ?";
	}

}

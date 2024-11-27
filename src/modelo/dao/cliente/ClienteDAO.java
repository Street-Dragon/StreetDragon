package modelo.dao.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controle.entidade.conexao.ConexaoBD;
import modelo.dao.genericdao.GenericDAO;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.cliente.Cliente;
import modelo.entidade.pessoa.funcionario.Funcionario;

public class ClienteDAO extends GenericDAO {

	public boolean cadastrarCliente(Cliente cliente) {
		String sqlContato = "INSERT INTO contato (email, telefone) VALUES (?, ?)";
		String sqlCliente = "INSERT INTO cliente (cpf,nome, contato_id) VALUES (?, ?, ?)";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtContato = conn.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente)) {

			stmtContato.setString(1, cliente.getContato().getEmail());
			stmtContato.setString(2, cliente.getContato().getTelefone());
			stmtContato.executeUpdate();

			ResultSet generatedKeys = stmtContato.getGeneratedKeys();
			if (generatedKeys.next()) {
				int contatoId = generatedKeys.getInt(1);
				stmtCliente.setString(1, cliente.getCpf());
				stmtCliente.setString(2, cliente.getNome());
				stmtCliente.setInt(3, contatoId);
				stmtCliente.executeUpdate();
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editarCliente(Cliente cliente) {
		String sqlCliente = "UPDATE cliente SET nome = ? WHERE cpf = ?";
		String sqlContato = "UPDATE contato SET email = ?, telefone = ? WHERE id_contato = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtContato = conn.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente)) {

			stmtCliente.setString(1, cliente.getNome());
			stmtCliente.setString(2, cliente.getCpf());
			stmtCliente.executeUpdate();
			int rowsAffectedCliente = stmtCliente.executeUpdate();

			stmtContato.setString(1, cliente.getContato().getEmail());
			stmtContato.setString(2, cliente.getContato().getTelefone());
			stmtContato.setInt(3, cliente.getContato().getId());
			stmtContato.executeUpdate();
			int rowsAffectedContato = stmtContato.executeUpdate();

			// Verifica se as atualizações foram bem-sucedidas
			return rowsAffectedCliente > 0 || rowsAffectedContato > 0;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public boolean excluirCliente(String cpf) {
		String sqlExcluir = "DELETE FROM cliente WHERE cpf = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmt = conn.prepareStatement(sqlExcluir)) {
			stmt.setString(1, cpf);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Cliente> listarClientes() {

		List<Cliente> clientes = new ArrayList<>();
		String sqlCliente = "SELECT cliente.*, contato.email, contato.telefone " + "FROM cliente "
				+ "JOIN contato ON cliente.contato_id = contato.id_contato";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmt = conn.prepareStatement(sqlCliente);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Cliente cliente = new Cliente();
				Contato contato = new Contato();

				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setNumeroCompras(rs.getString("numero_compras"));

				contato.setId(rs.getInt("contato_id"));
				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				cliente.setContato(contato);

				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}

	public Cliente carregarDadosCliente(String cpf) {
		Cliente cliente = null;
		String sqlCliente = "SELECT nome, cpf, email, telefone, numero_compras, id_contato " + "FROM cliente "
				+ "JOIN contato ON cliente.contato_id = contato.id_contato " + "WHERE cpf = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmt = conn.prepareStatement(sqlCliente)) {

			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNumeroCompras(rs.getString("numero_compras"));

				Contato contato = new Contato();
				contato.setId(rs.getInt("id_contato"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				cliente.setContato(contato);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	public boolean verificaCpfExistente(String cpf) {
		String sqlClienteCPF = "SELECT cpf FROM cliente WHERE cpf = ?";
		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtCliente = conn.prepareStatement(sqlClienteCPF)) {

			stmtCliente.setString(1, cpf);
			ResultSet rs = stmtCliente.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean verificaTelefoneExistente(String telefone) {
		String sqlClienteContatoTelefone = "SELECT co.telefone FROM contato co "
				+ "JOIN cliente cl ON co.id_contato = cl.contato_id " + "WHERE co.telefone = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtContato = conn.prepareStatement(sqlClienteContatoTelefone)) {

			stmtContato.setString(1, telefone);
			ResultSet rs = stmtContato.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
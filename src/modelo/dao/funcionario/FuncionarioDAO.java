package modelo.dao.funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controle.entidade.conexao.ConexaoBD;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.funcionario.Funcionario;

public class FuncionarioDAO {
    public boolean login(String cpf, String senha) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ? AND senha = ?";
        
        try (Connection conn = ConexaoBD.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se houver um funcionário com as credenciais corretas
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    // Mano pra que q serve isso?
    public String nomeFuncionario(String cpf) {
    	String sqlNome = "SELECT nome FROM funcionario WHERE cpf = ?";
    	
    	try (Connection conn = ConexaoBD.getConexaoMySQL();
    			PreparedStatement stmt = conn.prepareStatement(sqlNome)) {
                
               stmt.setString(1, cpf);
               
               ResultSet rs = stmt.executeQuery(); 
               if (rs.next()) {		// se tiver um funcionario com esse cpf,
                   return rs.getString("nome"); // retorna o nome do funcionario
               } else {
                   return "Nenhum"; // não retorna nada
               }
               
           } catch (SQLException e) {
               e.printStackTrace();
               return "Erro ao consultar";
           }
    }
    
    
    public boolean cadastrarFuncionario(Funcionario funcionario) {
        String sqlContato = "INSERT INTO contato (email, telefone) VALUES (?, ?)";
        String sqlFuncionario = "INSERT INTO funcionario (cpf, senha, nome, contato_id) VALUES (?, ?, ?, ?)";

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
        String sqlFuncionarioContatoTelefone = "SELECT c.telefone FROM contato c " +
                                    "JOIN funcionario f ON c.id_contato = f.contato_id " +
                                    "WHERE c.telefone = ?";
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
        String sqlFuncionarioContatoEmail = "SELECT c.email FROM contato c " +
                                 "JOIN funcionario f ON c.id_contato = f.contato_id " +
                                 "WHERE c.email = ?";
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

    //Falta os outros conteúdos eu tô com preguiça mi deixa
    public List<Funcionario> listarFuncionarios() {
		List<Funcionario> funcionarios = new ArrayList<>();
		String sqlSelect = "SELECT f.*, c.email, c.telefone FROM funcionario f "
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

}

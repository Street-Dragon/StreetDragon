package modelo.dao.funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controle.entidade.conexao.ConexaoBD;
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

    public List<String> verificaCpfsExistentes(List<String> cpfs) {
        String sqlFuncionarioCPF = "SELECT cpf FROM funcionario WHERE cpf = ?";
        List<String> cpfsExistentes = new ArrayList<>();
        
        try (Connection conn = ConexaoBD.getConexaoMySQL();
             PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionarioCPF)) {
             
            for (String cpf : cpfs) {
                stmtFuncionario.setString(1, cpf);
                ResultSet rs = stmtFuncionario.executeQuery();
                if (rs.next()) {
                    cpfsExistentes.add(cpf);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cpfsExistentes;
    }

    public List<String> verificaTelefonesExistentes(List<String> telefones) {
        String sqlFuncionarioTelefone = "SELECT telefone FROM funcionario WHERE telefone = ?";
        List<String> telefonesExistentes = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexaoMySQL();
             PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionarioTelefone)) {

            for (String telefone : telefones) {
                stmtFuncionario.setString(1, telefone);
                ResultSet rs = stmtFuncionario.executeQuery();
                if (rs.next()) {
                    telefonesExistentes.add(telefone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return telefonesExistentes;
    }

    public List<String> verificaEmailsExistentes(List<String> emails) {
        String sqlFuncionarioEmail = "SELECT email FROM funcionario WHERE email = ?";
        List<String> emailsExistentes = new ArrayList<>();
        
        try (Connection conn = ConexaoBD.getConexaoMySQL();
             PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionarioEmail)) {
             
            for (String email : emails) {
                stmtFuncionario.setString(1, email);
                ResultSet rs = stmtFuncionario.executeQuery();
                if (rs.next()) {
                    emailsExistentes.add(email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emailsExistentes;
    }

    
}

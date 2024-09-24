package modelo.dao.funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controle.entidade.conexao.ConexaoBD;
import modelo.entidade.pessoa.funcionario.Funcionario;

public class FuncionarioDAO {
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM funcionarios WHERE username = ? AND password = ?";
        
        try (Connection conn = ConexaoBD.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se houver um funcionário com as credenciais corretas
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (nome, cpf, email, telefone, senha, adm) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getContato().getEmail()); 
            stmt.setString(4, funcionario.getContato().getTelefone());
            stmt.setString(5, funcionario.getSenhaFuncionario());
            stmt.setBoolean(6, funcionario.isAdm());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}

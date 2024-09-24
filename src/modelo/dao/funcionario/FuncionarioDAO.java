package modelo.dao.funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controle.entidade.conexao.ConexaoBD;

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
}

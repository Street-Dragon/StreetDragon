package modelo.dao.fornecedor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.dao.genericdao.GenericDAO;
import modelo.entidade.pessoa.fornecedor.Fornecedor;



public class FornecedorDAO extends GenericDAO {
	
    public void cadastrarFornecedor(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO fornecedor (nome, Cnpj, endereco_CEP, rua) VALUES (?, ?, ?, ?)";
        save(sql, fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getCep(), fornecedor.getRua());
    }

    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {
        String sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, rua = ?, endereco_CEP = ? WHERE idFornecedores = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getRua()); 
            stmt.setInt(4, fornecedor.getCep()); 
            stmt.setInt(5, fornecedor.getId()); 
            stmt.executeUpdate();
        }
    }
    
    
    public void excluirFornecedor(int id) throws SQLException {
        String sql = "DELETE FROM fornecedor WHERE idFornecedores = ?";
        delete(sql, id);
    }
    
   
public Fornecedor buscarFornecedorPorId(int id) throws SQLException {
        
        String sql = "SELECT * FROM fornecedor WHERE idFornecedores = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
           
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(rs.getInt("idFornecedores"));
                    fornecedor.setNome(rs.getString("nome"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedor.setRua(rs.getString("rua"));
                    fornecedor.setCep(rs.getInt("endereco_CEP"));
                    
                    return fornecedor;
                } else {
                    
                    return null;
                }
            }
        }
    }
    
    
    
    //validações:
    
    
    public boolean possuiProdutos(int idFornecedor) throws SQLException {
        String sql = "SELECT COUNT(*) FROM produto WHERE idFornecedores = ?";
        try (Connection conn = getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
            stmt.setInt(1, idFornecedor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    
    public boolean cnpjExiste(String cnpj) throws SQLException {
    	String sql = "SELECT COUNT(*) FROM fornecedor WHERE cnpj = ?";
    	 try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/streetdragon", "root", "aluno");
    	         PreparedStatement stmt = conn.prepareStatement(sql)) {
    	        stmt.setString(1, cnpj);
    	        try (ResultSet rs = stmt.executeQuery()) {
    	            if (rs.next()) {
    	                return rs.getInt(1) > 0;
    	            }
    	        }
    	    }
    	    return false; 
    	}
    	

    
    
    
    
    //15 de Novembto de 1888
    public List<Fornecedor> listarFornecedores() throws SQLException {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("idFornecedores"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setCep(rs.getInt("endereco_cep"));
                fornecedor.setRua(rs.getString("rua"));
                fornecedores.add(fornecedor);
            }
        }

        return fornecedores;
    }


	}
    
   
   

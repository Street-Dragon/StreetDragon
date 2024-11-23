package modelo.dao.fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.dao.genericdao.GenericDAO;
import modelo.entidade.pessoa.fornecedor.Fornecedor;
import modelo.entidade.pessoa.funcionario.Funcionario;

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

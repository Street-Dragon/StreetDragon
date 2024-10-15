package modelo.dao.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controle.entidade.conexao.ConexaoBD;
import modelo.entidade.produto.Produto;

public class ProdutoDAO {
	  public String nomeProduto(String nomeProduto) {
		  
    	String sqlNomeP = "SELECT nome FROM produto WHERE nomeProduto = ?";
    	
    	try (Connection conn = ConexaoBD.getConexaoMySQL();
    			PreparedStatement stmt = conn.prepareStatement(sqlNomeP)) {
                
               stmt.setString(1, nomeProduto);
               
               ResultSet rs = stmt.executeQuery(); 
               if (rs.next()) {		// se tiver um produto com esse nome,
                   return rs.getString("nomeProduto"); // retorna o nome do produto
               } else {
                   return "Nenhum"; // não retorna nada
               }
               
           } catch (SQLException e) {
               e.printStackTrace();
               return "Erro ao consultar";
           }
    }
    
    
    public boolean cadastrarProduto(Produto produto) {
    	String sqlFornecedor = "SELECT nome FROM Fornecedor WHERE nome = ?";
        String sqlProduto= "INSERT INTO produto (nomeProduto, material, categoria, variacao, valor, quantEstoque, tamanho, fornecedor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexaoMySQL();
        		
             PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto);
        	 PreparedStatement stmtFornecedor = conn.prepareStatement(sqlFornecedor, Statement.RETURN_GENERATED_KEYS)){
        	
        	stmtFornecedor.setString(1, produto.getFornecedor().getNome());
        	stmtFornecedor.executeUpdate();
	        ResultSet generatedKeys = stmtFornecedor.getGeneratedKeys();
            int fornecedorId = generatedKeys.getInt(1);
            
            stmtProduto.setString(1, produto.getNomeProduto());
            stmtProduto.setString(2, produto.getMaterial());
            stmtProduto.setString(3, produto.getCategoria());
            stmtProduto.setString(4, produto.getVariacao());
            stmtProduto.setFloat(5, produto.getValor());
            stmtProduto.setInt(6, produto.getQuantEstoque());
            stmtProduto.setString(7, String.valueOf(produto.getTamanho()));
            stmtProduto.setInt (8, fornecedorId);
            stmtProduto.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

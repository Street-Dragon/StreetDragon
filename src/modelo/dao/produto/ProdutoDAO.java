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
        String sqlProduto= "INSERT INTO produto (nome, material, categoria, variacao, valor, estoque, tamanho) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexaoMySQL();
        		
             PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto);
        	 PreparedStatement stmtFornecedor = conn.prepareStatement(sqlFornecedor)){
        	
        	/*stmtFornecedor.setString(1, produto.getFornecedor().getNome());
	        ResultSet rs = stmtFornecedor.executeQuery();
	        
	        if(rs.next()) {
            int fornecedorId = rs.getInt(1);
            */
            stmtProduto.setString(1, produto.getNomeProduto());
            stmtProduto.setString(2, produto.getMaterial());
            stmtProduto.setString(3, produto.getCategoria());
            stmtProduto.setString(4, produto.getVariacao());
            stmtProduto.setFloat(5, produto.getValor());
            stmtProduto.setInt(6, produto.getQuantEstoque());
            stmtProduto.setString(7, String.valueOf(produto.getTamanho()));
            
            stmtProduto.executeUpdate();
            return true;
	        /*} else {
	        	System.out.println("deu merda aqui");
	        	return false;	        			
	        }*/
        } catch (SQLException e) {
        	System.out.println("VAI TOMA NO CUUUUUUUUUUUUUUUUUUUUU");
            e.printStackTrace();
            return false;
        }
        
        
    }

}

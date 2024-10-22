package modelo.dao.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controle.entidade.conexao.ConexaoBD;
import modelo.entidade.produto.Produto;

public class ProdutoDAO {
	  public Produto[] consultar() {
		  
    	String sqlP = "select * from produto";
    	Produto[] produto = null;
    	
    	try (Connection conn = ConexaoBD.getConexaoMySQL();
    			PreparedStatement stmt = conn.prepareStatement(sqlP)) {               
                ResultSet rs = stmt.executeQuery(); 
               for (int i = 0;rs.next();i++) {		// enquando tiver produtos
                   produto[i].setIdProduto(rs.getInt("idProduto"));
                   produto[i].setNomeProduto(rs.getString("nome"));
                   produto[i].setMaterial(rs.getString("caterial"));
                   produto[i].setCategoria(rs.getString("categoria"));
                   produto[i].setValor(rs.getFloat("valor"));
                   produto[i].setQuantEstoque(rs.getInt("estoque"));
                   produto[i].setTamanho(rs.getString("tamanho"));
                   produto[i].setVariacao(rs.getString("variacao"));
                   System.out.println(produto[i].getNomeProduto());
               }
               return produto;
               
           } catch (SQLException e) {
               e.printStackTrace();
               return produto;
           }
    }
    
    
    public boolean cadastrarProduto(Produto produto) {
    	String sqlFornecedor = "SELECT nome FROM Fornecedor WHERE nome = ?";
        String sqlProduto= "INSERT INTO produto (nome, material, categoria, variacao, valor, estoque, tamanho) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexaoMySQL();
        		
             PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto);
        	 PreparedStatement stmtFornecedor = conn.prepareStatement(sqlFornecedor)){
            stmtProduto.setString(1, produto.getNomeProduto());
            stmtProduto.setString(2, produto.getMaterial());
            stmtProduto.setString(3, produto.getCategoria());
            stmtProduto.setString(4, produto.getVariacao());
            stmtProduto.setFloat(5, produto.getValor());
            stmtProduto.setInt(6, produto.getQuantEstoque());
            stmtProduto.setString(7, produto.getTamanho());
            
            stmtProduto.executeUpdate();
            return true;
            
        } catch (SQLException e) {
        	System.out.println("Erro ao adicionar ao banco de dados");
            e.printStackTrace();
            return false;
        }
        
        
    }
    public boolean deletarProduto(Produto produto) {
    	String sqlP = "DELETE FROM produto WHERE idProduto = ?";
    	
    	try(Connection conn = ConexaoBD.getConexaoMySQL();
    		PreparedStatement stmt = conn.prepareStatement(sqlP)){
    		stmt.setInt(1, produto.getIdProduto());
    		stmt.executeUpdate();
    		return true;
    		
    	}  catch (SQLException e) {
    		System.out.println(e);
    		return false;
    	}
    }
    public int Idshow() {
    	String sql = "SELECT COUNT(*) FROM produto";
    	
    	try(Connection conn = ConexaoBD.getConexaoMySQL();
    		PreparedStatement stmt = conn.prepareStatement(sql)){
    		ResultSet rs = stmt.executeQuery();
    		rs.next();
    		int id = rs.getInt(1)+1;;
    		return id;
    	} catch (SQLException e) {
    		System.out.println(e);
    		return 0;
    	}
    }

}

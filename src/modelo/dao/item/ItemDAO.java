package modelo.dao.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controle.entidade.conexao.ConexaoBD;
import modelo.entidade.item.Item;
import modelo.entidade.produto.Produto;

public class ItemDAO {

	public boolean cadastrarItem(Item item) {

		int produtoId = verificarProdutoExistente(item.getProduto().getNomeProduto());

		if (produtoId == -1) {
	        System.err.println("Produto não encontrado: " + item.getProduto().getNomeProduto());
	        return false;
	    }

		
		String sqlVenda = "INSERT INTO venda (cliente_id, data_venda, total) VALUES (NULL, NOW(), 0.00)";
		String sqlItem = "INSERT INTO venda_produto (venda_id, prod_id, quantidade, preco) VALUES(?, ?, ?, ?)";
		String sqlProduto = "SELECT valor FROM produto WHERE idProduto = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
			PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
			PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto)) {
			
			stmtVenda.executeUpdate();
		    ResultSet rsVenda = stmtVenda.getGeneratedKeys();
		    int vendaId = -1;
		    if (rsVenda.next()) {
		        vendaId = rsVenda.getInt(1); // Recupera o ID da venda inserida
		    }

		    // pega o preço atual do produto
		    stmtProduto.setInt(1, produtoId);
		    ResultSet rsProduto = stmtProduto.executeQuery();
		    double precoProduto = 0.0;
		    if (rsProduto.next()) {
		        precoProduto = rsProduto.getDouble("valor"); // Recupera o preço do produto
		    }

		    // inserindo o item na venda
		    if (vendaId != -1 && precoProduto > 0) {
		        stmtItem.setInt(1, vendaId);  
		        stmtItem.setInt(2, produtoId);
		        stmtItem.setInt(3, item.getQuantidade()); 
		        stmtItem.setDouble(4, precoProduto); 
		        stmtItem.executeUpdate();
		    }

		    return true;

		} catch (SQLException e) {
		    e.printStackTrace();
		    return false;
		}
	}

//    public Produto buscarProdutoPorNome(String nomeProduto) {
//        Produto produto = null;
//        String sqlProduto = "SELECT id, nome, valor, material, categoria, variação, estoque, tamanho   FROM produto WHERE nome = ?";
//
//        try (Connection conn = ConexaoBD.getConexaoMySQL();
//             PreparedStatement stmt = conn.prepareStatement(sqlProduto)) {
//
//            stmt.setString(1, nomeProduto);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                produto = new Produto();
//                produto.setIdProduto(rs.getInt("id"));
//                produto.setNomeProduto(rs.getString("nome"));
//                produto.setValor(rs.getFloat("p.valor"));
//                produto.setMaterial(rs.getString("p.material"));
//                produto.setCategoria(rs.getString("p.categoria"));
//                produto.setVariacao(rs.getString("p.variação"));
//                produto.setQuantEstoque(rs.getInt("p.estoque"));
//                produto.setTamanho(rs.getString("p.tamanho"));
//                
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return produto; // Retorna o Produto encontrado ou null se não encontrado
//    }

	private int verificarProdutoExistente(String nomeProduto) {
		String sqlProduto = "SELECT idProduto FROM produto WHERE nome = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmt = conn.prepareStatement(sqlProduto)) {

			stmt.setString(1, nomeProduto);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("idProduto");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public boolean excluirItem(int idItem) {
		String sql = "DELETE FROM item WHERE id = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idItem);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void excluirTodos() throws SQLException { 
		String sql = "DELETE FROM item"; 
		//			+ "WHERE item_id NOT IN (SELECT DISTINCT item_id FROM venda)"		
		
		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
		 stmt.executeUpdate(); 
		      }
		 }

	public List<Item> listarItens() {
		List<Item> itens = new ArrayList<>();
		String sql = "SELECT i.venda_produto_id AS item_id, " +
	             "i.quantidade, " +
	             "p.nome, " +
	             "p.valor, " +
	             "p.idProduto AS produto_id " +
	             "FROM venda_produto i " +
	             "JOIN produto p ON i.prod_id = p.idProduto";	
		
		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("produto_id"));
				produto.setNomeProduto(rs.getString("p.nome"));
				produto.setValor(rs.getFloat("p.valor"));

				Item item = new Item();
				item.setQuantidade(rs.getInt("i.quantidade"));
				item.setProduto(produto);

				itens.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itens;
	}

}
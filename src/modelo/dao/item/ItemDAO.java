package modelo.dao.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controle.entidade.conexao.ConexaoBD;
import controle.entidade.funcionariocontrole.FuncionarioControle;
import modelo.entidade.item.Item;
import modelo.entidade.produto.Produto;

public class ItemDAO {
	private Integer idVendaAtual = null;

	public boolean verificaVenda() {
		String sqlVerificaVenda = "SELECT venda_id FROM venda WHERE data_venda IS NULL LIMIT 1";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtSelect = conn.prepareStatement(sqlVerificaVenda)) {

			ResultSet rs = stmtSelect.executeQuery();
			if (rs.next()) {
				idVendaAtual = rs.getInt("venda_id");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getId() {
		String sql = "SELECT venda_produto_id FROM venda_produto ORDER BY venda_produto_id DESC LIMIT 1;";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1) ;
			} else {
				return 1;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao obter o ID: " + e.getMessage());
			return 0;
		}
	}

	public void realizaVenda(Item item, String cpf) {

		String sqlVenda = "INSERT INTO venda (cliente_id, funcionario_cpf, total) VALUES (NULL, ?, 0.00)";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {

			stmtVenda.setString(1, cpf);
			stmtVenda.executeUpdate();

			// Recuperar o ID da venda gerado
			ResultSet rsVenda = stmtVenda.getGeneratedKeys();
			int vendaId = -1;
			if (rsVenda.next()) {
				vendaId = rsVenda.getInt(1); // pega o id da venda criada
				idVendaAtual = vendaId; // seta como venda atual
			}

			// Verificar se a venda foi criada com sucesso
			if (vendaId == -1) {
				System.err.println("Erro ao criar a venda.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean cadastrarItem(Item item, String cpf) {

		int produtoId = verificarProdutoExistente(item.getProduto().getNomeProduto());

		if (produtoId == -1) {
			System.err.println("Produto não encontrado: " + item.getProduto().getNomeProduto());
			return false;
		}

		if (!verificaVenda()) {
			realizaVenda(item, cpf);
		}

		String sqlItem = "INSERT INTO venda_produto (venda_id, prod_id, quantidade, preco) VALUES(?, ?, ?, ?)";
		String sqlProduto = "SELECT valor FROM produto WHERE idProduto = ?";
		String sqlUpdate = "UPDATE venda SET total = (SELECT SUM(vp.quantidade * vp.preco) FROM venda_produto vp WHERE vp.venda_id = ?) WHERE venda_id = ?;";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
				PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
				PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto)) {

			// pega o preço atual do produto
			stmtProduto.setInt(1, produtoId);
			ResultSet rsProduto = stmtProduto.executeQuery();
			if (rsProduto.next()) {
				float precoProduto = rsProduto.getFloat("valor");

				// inserindo o item na venda
				stmtItem.setInt(1, idVendaAtual);
				stmtItem.setInt(2, produtoId);
				stmtItem.setInt(3, item.getQuantidade());
				stmtItem.setDouble(4, precoProduto);
				stmtItem.executeUpdate();

			} else {
				System.err.println("Produto com id " + produtoId + " não encontrado na tabela produto.");
				return false;
			}

			// atualizando a venda
			stmtUpdate.setInt(1, idVendaAtual);
			stmtUpdate.setInt(2, idVendaAtual);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int GetVendaId() {
		return idVendaAtual;
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

	public boolean excluirItem(int idProduto) {

		String sql = "DELETE FROM venda_produto WHERE venda_id = ? AND prod_id = ?";
		String update = "UPDATE venda SET total = (SELECT SUM(vp.quantidade * vp.preco) FROM venda_produto vp WHERE vp.venda_id = ? ) WHERE venda_id = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idVendaAtual);
			stmt.setInt(2, idProduto);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				try (PreparedStatement stmtAtualizarTotal = conn.prepareStatement(update)) {
					stmtAtualizarTotal.setInt(1, idVendaAtual);
					stmtAtualizarTotal.setInt(2, idVendaAtual);

					// Executando a atualização do total
					stmtAtualizarTotal.executeUpdate();
					return true;
				}

			} else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void excluirTodos() throws SQLException {

		String sql = "DELETE FROM venda_produto WHERE venda_id = ?"; // id da venda vai ter q ser a venda atual

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idVendaAtual);
			stmt.executeUpdate();
		}
	}

	public List<Item> listarItens() {
		List<Item> itens = new ArrayList<>();
		String sql = "SELECT i.venda_produto_id AS item_id, " + "i.quantidade, " + "p.nome, " + "p.valor, "
				+ "p.idProduto AS produto_id " + "FROM venda_produto i " + "JOIN produto p ON i.prod_id = p.idProduto";

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
				item.setId(rs.getInt("item_id"));

				itens.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itens;
	}

}
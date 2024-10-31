package modelo.dao.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controle.entidade.conexao.ConexaoBD;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.funcionario.Funcionario;
import modelo.entidade.produto.Produto;
import modelo.enumeracao.tamanho.Tamanho;

public class ProdutoDAO {
	public Produto[] consultar() {

		String sqlP = "select * from produto";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sqlP)) {
			ResultSet rs = stmt.executeQuery();

			Produto[] produto = null;
			try {
				for (int i = 0; rs.next(); i++) { // enquando tiver produtos
					// System.out.println(i+" : "+rs.getString("nome"));
					produto[i].setIdProduto(rs.getInt("idProduto"));
					produto[i].setNomeProduto(rs.getString("nome"));
					produto[i].setMaterial(rs.getString("material"));
					produto[i].setCategoria(rs.getString("categoria"));
					produto[i].setValor(rs.getFloat("valor"));
					produto[i].setQuantEstoque(rs.getInt("estoque"));
					produto[i].setTamanho(Tamanho.valueOf(rs.getString("tamanho").toUpperCase()));
					produto[i].setVariacao(rs.getString("variacao"));

				}
				System.out.println("aqui");
				return null;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean cadastrarProduto(Produto produto) {
		String sqlFornecedor = "SELECT nome FROM Fornecedor WHERE nome = ?";
		String sqlProduto = "INSERT INTO produto (nome, material, categoria, variacao, valor, estoque, tamanho) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConexaoBD.getConexaoMySQL();

				PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto);
				PreparedStatement stmtFornecedor = conn.prepareStatement(sqlFornecedor)) {
			stmtProduto.setString(1, produto.getNomeProduto());
			stmtProduto.setString(2, produto.getMaterial());
			stmtProduto.setString(3, produto.getCategoria());
			stmtProduto.setString(4, produto.getVariacao());
			stmtProduto.setFloat(5, produto.getValor());
			stmtProduto.setInt(6, produto.getQuantEstoque());
			stmtProduto.setString(7, produto.getTamanho().name());

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

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sqlP)) {
			stmt.setInt(1, produto.getIdProduto());
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public int Idshow() {
		String sql = "SELECT COUNT(*) FROM produto";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int id = rs.getInt(1) + 1;
			;
			return id;
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		}
	}
	public List<Produto> listarProdutos() {
		List<Produto> produtos = new ArrayList<>();
		String sqlSelect = "SELECT * FROM funcionario";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmt = conn.prepareStatement(sqlSelect);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Produto produto = new Produto();
				
				produto.setNomeProduto(rs.getString("nome"));
				produto.setMaterial(rs.getString("material"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setValor(Float.parseFloat(rs.getString("valor")));
				produto.setQuantEstoque(Integer.parseInt(rs.getString("estoque")));
				produto.setTamanho(Tamanho.valueOf(rs.getString("tamanho").toUpperCase()));
				produto.setVariacao(rs.getString("variacao"));
				
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}

}

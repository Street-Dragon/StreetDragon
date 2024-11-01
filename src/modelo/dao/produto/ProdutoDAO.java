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
			stmtProduto.setString(7, produto.getTamanho());

			stmtProduto.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Erro ao adicionar ao banco de dados");
			e.printStackTrace();
			return false;
		}

	}

	public boolean deletarProduto(int produto) {
		String sqlP = "DELETE FROM produto WHERE idProduto = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sqlP)) {
			stmt.setInt(1, produto);
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
		String sqlSelect = "SELECT * FROM produto";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmt = conn.prepareStatement(sqlSelect);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setNomeProduto(rs.getString("nome"));
				produto.setMaterial(rs.getString("material"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setValor(Float.parseFloat(rs.getString("valor")));
				produto.setQuantEstoque(rs.getInt("estoque"));
				produto.setTamanho(rs.getString("tamanho"));
				produto.setVariacao(rs.getString("variacao"));
				
				produtos.add(produto);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return produtos;
	}

	public boolean editarProduto(Integer id, Produto produto) {
		String sqlP = "UPDATE produto set nome = ?, material = ?, Categoria = ?, valor = ?, estoque = ?, tamanho = ?, variacao = ? where idProduto = ?;";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmtProduto = conn.prepareStatement(sqlP)) {
			stmtProduto.setString(1, produto.getNomeProduto());
			stmtProduto.setString(2, produto.getMaterial());
			stmtProduto.setString(3, produto.getCategoria());
			stmtProduto.setString(4, produto.getVariacao());
			stmtProduto.setFloat(5, produto.getValor());
			stmtProduto.setInt(6, produto.getQuantEstoque());
			stmtProduto.setString(7, produto.getTamanho());
			stmtProduto.setInt(8, id);
			stmtProduto.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		
	}
	public Produto getId(int id) {
	    String sqlSelect = "SELECT * FROM produto WHERE idProduto = ?";
	    try (Connection conn = ConexaoBD.getConexaoMySQL();
	         PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            Produto produto = new Produto();
	            produto.setIdProduto(rs.getInt("idProduto"));
	            produto.setNomeProduto(rs.getString("nome"));
	            produto.setMaterial(rs.getString("material"));
	            produto.setCategoria(rs.getString("categoria"));
	            produto.setVariacao(rs.getString("variacao"));
	            produto.setValor(rs.getFloat("valor"));
	            produto.setQuantEstoque(rs.getInt("estoque"));
	            produto.setTamanho(rs.getString("tamanho"));
	            return produto;
	        } else {
	            return null;
	        }
	    } catch (SQLException e) {
	        System.out.println(e);
	        return null;
	    }
	}
}

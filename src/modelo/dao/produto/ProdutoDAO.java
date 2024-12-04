package modelo.dao.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controle.entidade.conexao.ConexaoBD;
import controle.entidade.produto.ProdutoControle;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.fornecedor.Fornecedor;
import modelo.entidade.pessoa.funcionario.Funcionario;
import modelo.entidade.produto.Produto;
import modelo.enumeracao.tamanho.Tamanho;
import visao.TelaMensagens;

public class ProdutoDAO {

	public boolean cadastrarProduto(Produto produto) {
		String sqlFornecedor = "SELECT nome FROM Fornecedor WHERE nome = ?";
		String sqlProduto = "INSERT INTO produto (nome, material, categoria, variacao, valor, estoque, tamanho, idFornecedores) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

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
			stmtProduto.setInt(8, produto.getFornecedorid());
			System.out.println(produto.getFornecedorid());

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
		String sql = "select idProduto from produto Order by idProduto desc limit 1;";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1) + 1;
				return id;
			} else {
				return 1;
			}
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
				produto.setFornecedorid(rs.getInt("idFornecedores"));
				
				produtos.add(produto);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return produtos;
	}
	public List<Produto> pesquisa(String pesquisa, int num){
		List<Produto> produtos = new ArrayList<>();
		String sqlSelect = null;
		int tipo = 0;
		switch(num) {
		case 0:
			sqlSelect = "SELECT * FROM produto where nome = ?";
			tipo = 3;
			break;
		case 1:
			if (new ProdutoControle().verificarNum(pesquisa,1)) 
				sqlSelect = "SELECT * FROM produto where idProduto = ?";
			else
				sqlSelect = "SELECT * FROM produto";
			tipo = 1;
			break;
		case 2:
			if (new ProdutoControle().verificarNum(pesquisa,2)) 
				sqlSelect = "SELECT * FROM produto WHERE valor BETWEEN ? AND ?";	
			else
				sqlSelect = "SELECT * FROM produto";
			tipo = 2;
			break;
		case 3:
			sqlSelect = "SELECT * FROM produto where IdFornecedores = ?";
			tipo = 4;
			break;
		case 4:
			sqlSelect = "SELECT * FROM produto where material = ?";
			tipo = 3;
			break;
		case 5:
			sqlSelect = "SELECT * FROM produto where categoria = ?";
			tipo = 3;
			break;
		}
		try (Connection conn = ConexaoBD.getConexaoMySQL();				
				PreparedStatement stmt = conn.prepareStatement(sqlSelect)){
				switch(tipo) {
				case 1:
					if (new ProdutoControle().verificarNum(pesquisa,1)) 
						stmt.setInt(1, Integer.valueOf(pesquisa));		
					else
						new TelaMensagens("Valor preenchido incorretamente!",1);
					
					break;
				case 2:
					if (new ProdutoControle().verificarNum(pesquisa,2)) {
						float num1 = (float) (Float.valueOf(pesquisa)-0.1);
						float num2 = (float) (Float.valueOf(pesquisa)+0.1);
						stmt.setFloat(1, num1);
						stmt.setFloat(2, num2);
					}
					else 
						new TelaMensagens("Valor preenchido incorretamente!",1);
					break;
				case 3:
					stmt.setString(1, pesquisa);
					break;
				case 4:
					int F = FornecedorID(pesquisa);
					stmt.setInt(1, F);
					break;
				}
				ResultSet rs = stmt.executeQuery();

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
				produto.setFornecedorid(rs.getInt("idFornecedores"));
				
				produtos.add(produto);
			}
			return produtos;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return produtos;
	}

	public boolean editarProduto(Produto produto) {
		String sqlP = "UPDATE produto set nome = ?, material = ?, Categoria = ?, valor = ?, estoque = ?, tamanho = ?, variacao = ?, idFornecedores = ? where idProduto = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmtProduto = conn.prepareStatement(sqlP)) {
			stmtProduto.setString(1, produto.getNomeProduto());
			stmtProduto.setString(2, produto.getMaterial());
			stmtProduto.setString(3, produto.getCategoria());
			stmtProduto.setFloat(4, produto.getValor());
			stmtProduto.setInt(5, produto.getQuantEstoque());
			stmtProduto.setString(6, produto.getTamanho());
			stmtProduto.setString(7, produto.getVariacao());
			stmtProduto.setInt(8, produto.getFornecedorid());
			stmtProduto.setInt(9, produto.getIdProduto());
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
	            produto.setFornecedorid(rs.getInt("idFornecedores"));
	            return produto;
	        } else {
	            return null;
	        }
	    } catch (SQLException e) {
	        System.out.println(e);
	        return null;
	    }
	}
	
	// ----------------Funções para fazer a verifição do fornecedor------------------
	
	// ----------------Pega o nome do fornecedor com o id dele, isso é para mostrar o nome em vez do id pro usuario
	public String getIdF(int id) {
		
		 String sqlSelect = "select nome from fornecedor where idFornecedores = ?";
		    try (Connection conn = ConexaoBD.getConexaoMySQL();
		         PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
		        stmt.setInt(1, id);
		        ResultSet rs = stmt.executeQuery();
		        
		        if (rs.next()) {
		        	String nome = (rs.getString("nome"));
			        return nome;
	            } else
	            	return null;
		        
		    } catch (SQLException e) {
		        System.out.println(e);
		        return null;
		    }
	}
	
	// ----------------Verifica se um fornecedor com um certo nome existe
	public boolean ForncedorEx(String nome) {
	    String sqlSelect = "select Count(*) as total from fornecedor where nome = ?";
	    try (Connection conn = ConexaoBD.getConexaoMySQL();
	         PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
	    	
	        stmt.setString(1, nome);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int count = rs.getInt("total");
	                return count > 0; 
	            }
	        }
	    } catch (SQLException e) {
	    	 System.out.println("check4");
	        System.out.println(e);
	    }
	    return false;
	}
	
	// ----------------pega o id do fornecedor com o nome dele
	public int FornecedorID(String Nome) {
		String sqlSelect = "select idFornecedores from fornecedor where nome = ?";
	    try (Connection conn = ConexaoBD.getConexaoMySQL();
	         PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
	        stmt.setString(1, Nome);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	        	int id = (rs.getInt("idFornecedores"));
                return id;
            } else
            	return 0;
	    } catch (SQLException e) {
	        System.out.println(e);
	        return 0;
	    }
	}
//	--------------------------------------------------------------------------------------
	
}

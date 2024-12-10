package modelo.dao.promocao;

import modelo.entidade.promocao.Promocao;
import controle.entidade.conexao.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromocaoDAO {

    private Connection connection;

    public PromocaoDAO() {
        this.connection = ConexaoBD.getConexaoMySQL();
    }
    public Promocao getIdPromocao(int idPromocao) {
        Promocao promocao = null;
        String sql = "SELECT * FROM promocao WHERE idPromocao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPromocao);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                promocao = new Promocao();
                promocao.setIdPromocao(rs.getInt("idPromocao"));
                promocao.setNome(rs.getString("nome"));
                promocao.setDesconto(rs.getFloat("desconto"));
                promocao.setTermino(rs.getString("termino"));
                promocao.setInicio(rs.getString("inicio"));
                promocao.setCategoria(rs.getString("categoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar promoção por ID: " + e.getMessage());
        }

        return promocao;
    }

    public void cadastrarPromocao(Promocao promocao) {
        String sql = "INSERT INTO promocao (nome, desconto,termino, inicio, categoria) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, promocao.getNome());
            stmt.setFloat(2, promocao.getDesconto());
            stmt.setString(3, promocao.getTermino());
            stmt.setString(4, promocao.getInicio());
            stmt.setString(5, promocao.getCategoria());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idPromocao = generatedKeys.getInt(1);
                    promocao.setIdPromocao(idPromocao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  
            throw new RuntimeException("Erro ao cadastrar promoção: " + e.getMessage());
        }
    }

    public void editarPromocao(Promocao promocao) {
        String sql = "UPDATE promocao SET nome = ?, desconto = ?, termino = ?, inicio = ?, categoria = ? WHERE idPromocao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, promocao.getNome());
            stmt.setFloat(2, promocao.getDesconto());
            stmt.setString(3, promocao.getTermino());
            stmt.setString(4, promocao.getInicio());
            stmt.setInt(6, promocao.getIdPromocao());
            stmt.setString(5, promocao.getCategoria());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao editar promoção: " + e.getMessage());
        }
    }

 
    public void excluirPromocao(int idPromocao) {
        String sql = "DELETE FROM promocao WHERE idPromocao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPromocao);
            stmt.executeUpdate();
            System.out.println("eee");
        } catch (SQLException e) {
            e.printStackTrace();  
            throw new RuntimeException("Erro ao excluir promoção: " + e.getMessage());
        }
    }

    public List<Promocao> listarTodos() {
        List<Promocao> promocoes = new ArrayList<>();
        String sql = "SELECT * FROM promocao";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Promocao promocao = new Promocao();
                promocao.setIdPromocao(rs.getInt("idPromocao"));
                promocao.setNome(rs.getString("nome"));
                promocao.setDesconto(rs.getFloat("desconto"));
                promocao.setTermino(rs.getString("termino"));
                promocao.setInicio(rs.getString("inicio"));
                promocao.setCategoria(rs.getString("categoria"));

                promocoes.add(promocao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar promoções: " + e.getMessage());
        }

        return promocoes;
    }
}

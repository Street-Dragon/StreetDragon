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

    // Método para cadastrar uma promoção
    public void cadastrarPromocao(Promocao promocao) {
        String sql = "INSERT INTO promocao (nome, desconto, termino) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, promocao.getNome());
            stmt.setFloat(2, promocao.getDesconto());
            stmt.setString(3, promocao.getTermino());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idPromocao = generatedKeys.getInt(1);
                    promocao.setIdPromocao(idPromocao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Em caso de erro, printa a stack trace
            throw new RuntimeException("Erro ao cadastrar promoção: " + e.getMessage());
        }
    }

    // Método para editar uma promoção
    public void editarPromocao(Promocao promocao) {
        String sql = "UPDATE promocao SET nome = ?, desconto = ?, termino = ? WHERE idPromocao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, promocao.getNome());
            stmt.setFloat(2, promocao.getDesconto());
            stmt.setString(3, promocao.getTermino());
            stmt.setInt(4, promocao.getIdPromocao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Em caso de erro, printa a stack trace
            throw new RuntimeException("Erro ao editar promoção: " + e.getMessage());
        }
    }

    // Método para excluir uma promoção
    public void excluirPromocao(int idPromocao) {
        String sql = "DELETE FROM promocao WHERE idPromocao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPromocao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Em caso de erro, printa a stack trace
            throw new RuntimeException("Erro ao excluir promoção: " + e.getMessage());
        }
    }

    // Método para listar todas as promoções
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

                promocoes.add(promocao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar promoções: " + e.getMessage());
        }

        return promocoes;
    }
}

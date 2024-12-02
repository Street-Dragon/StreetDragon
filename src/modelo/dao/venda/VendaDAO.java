package modelo.dao.venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controle.entidade.conexao.ConexaoBD;
import modelo.entidade.item.Item;
import modelo.entidade.venda.Venda;

public class VendaDAO {
    private Connection connection;

    public VendaDAO() {
        this.connection = ConexaoBD.getConexaoMySQL();
    }

    public void salvarVenda(Venda venda) {
        String sql = "INSERT INTO venda (cliente_id, funcionario_cpf, total) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, venda.isCliente() ? venda.getFuncionario().getCpf() : null);
            stmt.setString(2, venda.getFuncionario().getCpf());
            stmt.setFloat(3, venda.getPrecoTotal());
            stmt.executeUpdate();

            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int vendaId = generatedKeys.getInt(1);

                
                for (Item item : venda.getItens()) {
                    salvarItemVenda(vendaId, item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void salvarItemVenda(int vendaId, Item item) {
        String sql = "INSERT INTO venda_produto (venda_id, prod_id, quantidade, preco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vendaId);
            stmt.setInt(2, item.getProduto().getIdProduto());
            stmt.setInt(3, item.getQuantidade());
            stmt.setFloat(4, item.getProduto().getValor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

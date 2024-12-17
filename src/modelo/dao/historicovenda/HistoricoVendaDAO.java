package modelo.dao.historicovenda;

import modelo.entidade.venda.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoVendaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    // Método para obter uma conexão com o banco de dados
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para buscar vendas por código
    public List<Venda> buscarPorCodigo(String codigo) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE codigoVenda = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("codigoVenda"));
                    venda.setPrecoTotal(rs.getFloat("precoTotal"));
                    venda.setDataVenda(rs.getDate("dataVenda"));
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    // Método para buscar vendas por nome do cliente
    public List<Venda> buscarPorNomeCliente(String nome) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE nomeCliente LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("codigoVenda"));
                    venda.setPrecoTotal(rs.getFloat("precoTotal"));
                    venda.setDataVenda(rs.getDate("dataVenda"));
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    // Método para buscar vendas por data
    public List<Venda> buscarPorData(String data) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE dataVenda = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("codigoVenda"));
                    venda.setPrecoTotal(rs.getFloat("precoTotal"));
                    venda.setDataVenda(rs.getDate("dataVenda"));
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    // Método para listar todas as vendas
    public List<Venda> listarTodasVendas() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setCodigoVenda(rs.getInt("codigoVenda"));
                venda.setPrecoTotal(rs.getFloat("precoTotal"));
                venda.setDataVenda(rs.getDate("dataVenda"));
                vendas.add(venda);
            }
        }
        return vendas;
    }
}

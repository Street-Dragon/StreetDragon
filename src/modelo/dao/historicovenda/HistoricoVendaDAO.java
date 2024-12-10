package modelo.dao.historicovenda;

import modelo.entidade.venda.Venda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controle.entidade.conexao.ConexaoBD;

public class HistoricoVendaDAO {


    // Método para buscar vendas por código
    public List<Venda> buscarPorCodigo(String codigo) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE codigoVenda = ?"; // SQL para buscar pela venda pelo código

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("codigoVenda"));
                    venda.setPrecoTotal(rs.getFloat("precoTotal"));
                    venda.setDataVenda(rs.getDate("dataVenda"));
                    // Adicione outros dados necessários como Funcionario, Itens etc.
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    // Método para buscar vendas por nome do cliente
    public List<Venda> buscarPorNomeCliente(String nomeCliente) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE nomeCliente LIKE ?"; // SQL para buscar pelo nome do cliente

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nomeCliente + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("codigoVenda"));
                    venda.setPrecoTotal(rs.getFloat("precoTotal"));
                    venda.setDataVenda(rs.getDate("dataVenda"));
                    // Adicione outros dados necessários como Funcionario, Itens etc.
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    // Método para buscar vendas por data
    public List<Venda> buscarPorData(String data) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE dataVenda = ?"; // SQL para buscar pela data

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("codigoVenda"));
                    venda.setPrecoTotal(rs.getFloat("precoTotal"));
                    venda.setDataVenda(rs.getDate("dataVenda"));
                    // Adicione outros dados necessários como Funcionario, Itens etc.
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    // Método para listar todas as vendas
    public List<Venda> listarTodasVendas() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas"; // SQL para listar todas as vendas

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setCodigoVenda(rs.getInt("codigoVenda"));
                venda.setPrecoTotal(rs.getFloat("precoTotal"));
                venda.setDataVenda(rs.getDate("dataVenda"));
                // Adicione outros dados necessários como Funcionario, Itens etc.
                vendas.add(venda);
            }
        }
        return vendas;
    }
}


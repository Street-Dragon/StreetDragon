package modelo.dao.historicovenda;

import modelo.entidade.venda.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controle.entidade.conexao.ConexaoBD;

public class HistoricoVendaDAO {

    
    public List<Venda> buscarPorCodigo(String codigo) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        System.out.println(vendas); 
        String sql = "SELECT * FROM venda WHERE venda_id = ?"; 

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("venda_id")); 
                    venda.setPrecoTotal(rs.getFloat("total")); 
                    venda.setDataVenda(rs.getDate("data_venda")); 
                    venda.setFuncionarioCpf(rs.getString("funcionario_cpf"));  
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }
    

    public List<Venda> buscarPorNomeCliente(String Cliente) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE cliente_id LIKE ?"; 

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + Cliente + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("venda_id"));
                    venda.setPrecoTotal(rs.getFloat("total")); 
                    venda.setDataVenda(rs.getDate("data_venda"));
                    venda.setFuncionarioCpf(rs.getString("funcionario_cpf")); 
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    // Buscar vendas por data
    public List<Venda> buscarPorData(String data) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE data_venda = ?"; 

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("venda_id")); 
                    venda.setPrecoTotal(rs.getFloat("total")); 
                    venda.setDataVenda(rs.getDate("data_venda"));
                    venda.setFuncionarioCpf(rs.getString("funcionario_cpf"));  // Mapeando o campo funcionario_cpf
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    public List<Venda> listarTodasVendas() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE data_venda IS NOT NULL"; 

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setCodigoVenda(rs.getInt("venda_id")); 
                venda.setPrecoTotal(rs.getFloat("total")); 
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setFuncionarioCpf(rs.getString("funcionario_cpf")); 
                vendas.add(venda);
            }
        }
        return vendas;
    }
}

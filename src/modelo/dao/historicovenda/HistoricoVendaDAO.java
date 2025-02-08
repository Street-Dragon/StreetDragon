package modelo.dao.historicovenda;

import modelo.entidade.venda.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import controle.entidade.conexao.ConexaoBD;

public class HistoricoVendaDAO {

    
	public List<Venda> buscarPorCodigo(String codigo) throws SQLException {
	    List<Venda> vendas = new ArrayList<>();
	    String sql = "SELECT * FROM venda WHERE venda_id = ?"; 

	    try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	    	stmt.setString(1, codigo);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Venda venda = new Venda();
	                venda.setCodigoVenda(rs.getInt("venda_id")); 
	                venda.setFuncionarioCpf(rs.getString("funcionario_cpf")); 
	                venda.setClienteCpf(rs.getString("cliente_id"));
	                venda.setPrecoTotal(rs.getFloat("total")); 
	                venda.setDataVenda(rs.getDate("data_venda"));
	                vendas.add(venda);
	            }
	        }
	    }
	    return vendas;
	}
    

    public List<Venda> buscarPorValorTotal(float valorTotal) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE total = ?"; 

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, valorTotal);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("venda_id")); 
                    venda.setFuncionarioCpf(rs.getString("funcionario_cpf")); 
                    venda.setClienteCpf(rs.getString("cliente_id"));
                    venda.setPrecoTotal(rs.getFloat("total")); 
                    venda.setDataVenda(rs.getDate("data_venda"));
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }
    
    public List<Venda> buscarPorFuncionarios(String funcionarioCpf) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE funcionario_cpf = ?"; 

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionarioCpf);  
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("venda_id")); 
                    venda.setFuncionarioCpf(rs.getString("funcionario_cpf")); 
                    venda.setClienteCpf(rs.getString("cliente_id"));
                    venda.setPrecoTotal(rs.getFloat("total")); 
                    venda.setDataVenda(rs.getDate("data_venda"));
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }
    
    public List<Venda> buscarPorClientes(String clienteCpf) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE cliente_id = ?"; 

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clienteCpf);  
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setCodigoVenda(rs.getInt("venda_id")); 
                    venda.setFuncionarioCpf(rs.getString("funcionario_cpf")); 
                    venda.setClienteCpf(rs.getString("cliente_id"));
                    venda.setPrecoTotal(rs.getFloat("total")); 
                    venda.setDataVenda(rs.getDate("data_venda"));
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    
    public List<Venda> listarTodasVendas() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE venda_id IS NOT NULL"; 

        try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setCodigoVenda(rs.getInt("venda_id")); 
                venda.setFuncionarioCpf(rs.getString("funcionario_cpf")); 
                venda.setClienteCpf(rs.getString("cliente_id"));
                venda.setPrecoTotal(rs.getFloat("total")); 
                venda.setDataVenda(rs.getDate("data_venda"));
                vendas.add(venda);
            }
        }
        return vendas;
    }


	
}

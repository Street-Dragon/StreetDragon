package modelo.dao.item;
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.SQLException;

import controle.entidade.conexao.ConexaoBD;
import modelo.entidade.item.Item;
public class ItemDAO {
	public void adicionarItem(Item item) { 
		String sql = "INSERT INTO itens (quantidade, idProduto) VALUES (?, ?)";
		
		try (Connection conn = ConexaoBD.getConexaoMySQL(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, item.getQuantidade());
			//pstmt.setInt(2, item.getProduto()); 
			pstmt.executeUpdate();
			} catch (SQLException e) 
				{ e.printStackTrace(); } } }
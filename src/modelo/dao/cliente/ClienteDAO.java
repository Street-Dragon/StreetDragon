package modelo.dao.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controle.entidade.conexao.ConexaoBD;
import modelo.dao.genericdao.GenericDAO;
import modelo.entidade.pessoa.cliente.Cliente;

public class ClienteDAO extends GenericDAO{
	
	public boolean cadastrarCliente(Cliente cliente) {
		String sqlContato = "INSERT INTO contato (email, telefone) VALUES (?, ?)";
		String sqlCliente = "INSERT INTO cliente (cpf,nome, contato_id) VALUES (?, ?, ?)";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtContato = conn.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente)) {

			stmtContato.setString(1, cliente.getContato().getEmail());
			stmtContato.setString(2, cliente.getContato().getTelefone());
			stmtContato.executeUpdate();

			ResultSet generatedKeys = stmtContato.getGeneratedKeys();
			if (generatedKeys.next()) {
				int contatoId = generatedKeys.getInt(1);
				stmtCliente.setString(1, cliente.getCpf());
				stmtCliente.setString(2, cliente.getNome());
				stmtCliente.setInt(3, contatoId);
				stmtCliente.executeUpdate();
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean editarCliente(Cliente cliente) {
		String sqlCliente = "UPDATE cliente SET nome = ?, senha = ? WHERE cpf = ?";
		String sqlContato = "UPDATE contato SET email = ?, telefone = ? WHERE id_contato = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement stmtContato = conn.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente)) {

			stmtCliente.setString(1, cliente.getNome());
			stmtCliente.setString(2, cliente.getCpf());
			stmtCliente.executeUpdate();
			int rowsAffectedCliente = stmtCliente.executeUpdate();

			stmtContato.setString(1, cliente.getContato().getEmail());
			stmtContato.setString(2, cliente.getContato().getTelefone());
			stmtContato.setInt(3, cliente.getContato().getId());
			stmtContato.executeUpdate();
			int rowsAffectedContato = stmtContato.executeUpdate();

			// Verifica se as atualizações foram bem-sucedidas
			return rowsAffectedCliente > 0 || rowsAffectedContato > 0;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}
	
	public boolean excluirCliente(String cpf) {
		String sql = "DELETE FROM cliente WHERE cpf = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, cpf);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	    public void saveCliente(Cliente cliente) throws SQLException {
	       
	        String sqlContato = "INSERT INTO contato (email, telefone) VALUES (?, ?)";
	        save(sqlContato, cliente.getContato().getEmail(), cliente.getContato().getTelefone());

	        String sqlGetContatoId = "SELECT LAST_INSERT_ID()";
	        ResultSet rs = find(sqlGetContatoId);
	        rs.next();
	        int contatoId = rs.getInt(1);

	        
	        String sqlCliente = "INSERT INTO cliente (nome, cpf, contato_id) VALUES (?, ?, ?)";
	        save(sqlCliente, cliente.getNome(), cliente.getCpf(), contatoId);
	    }
	
*/
	
//	    public Cliente findClienteById(int id) throws SQLException {
//	        String sql = "SELECT c.id, c.nome, c.cpf, ct.id as contato_id, ct.email, ct.telefone " +
//	                     "FROM cliente c " +
//	                     "JOIN contato ct ON c.contato_id = ct.id " +
//	                     "WHERE c.id = ?";
//	        ResultSet rs = find(sql, id);
//
//	        if (rs.next()) {
//	            Cliente cliente = new Cliente();
//	            cliente.setId(rs.getInt("id"));
//	            cliente.setNome(rs.getString("nome"));
//	            cliente.setCpf(rs.getString("cpf"));
//
//	            
//	            Contato contato = new Contato();
//	            contato.setId(rs.getInt("contato_id"));
//	            contato.setEmail(rs.getString("email"));
//	            contato.setTelefone(rs.getString("telefone"));
//
//	            cliente.setContato(contato);  
//	            return cliente;
//	        }
//	        return null;  
//	    }

	    
//	    public void updateCliente(Cliente cliente) throws SQLException {
//	        
//	        String sqlContato = "UPDATE contato SET email = ?, telefone = ? WHERE id = ?";
//	        update(sqlContato, cliente.getContato().getEmail(), cliente.getContato().getTelefone(), cliente.getContato().getId());
//
//	        
//	        String sqlCliente = "UPDATE cliente SET nome = ?, cpf = ? WHERE id = ?";
//	        update(sqlCliente, cliente.getNome(), cliente.getCpf(), cliente.getId());
//	    }

	    
//	    public void deleteCliente(int id) throws SQLException {
//	        // Primeiro, exclui o cliente
//	        String sqlCliente = "DELETE FROM cliente WHERE id = ?";
//	        delete(sqlCliente, id);
//
//	       
//	        String sqlContato = "DELETE FROM contato WHERE id = (SELECT contato_id FROM cliente WHERE id = ?)";
//	        delete(sqlContato, id);
//	    }

	    
//	    public ResultSet findAllClientes() throws SQLException {
//	        String sql = "SELECT c.id, c.nome, c.cpf, ct.id as contato_id, ct.email, ct.telefone " +
//	                     "FROM cliente c " +
//	                     "JOIN contato ct ON c.contato_id = ct.id";
//	        return find(sql);
//	    }
	}



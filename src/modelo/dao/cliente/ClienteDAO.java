package modelo.dao.cliente;

import java.sql.SQLException;

import modelo.dao.genericdao.GenericDAO;
import modelo.entidade.pessoa.cliente.Cliente;

public class ClienteDAO extends GenericDAO{
	
	
	  
	    public void saveCliente(Cliente cliente) throws SQLException {
	       
	        String sqlContato = "INSERT INTO contato (email, telefone) VALUES (?, ?)";
	        save(sqlContato, cliente.getContato().getEmail(), cliente.getContato().getTelefone());

	      
	        String sqlGetContatoId = "SELECT LAST_INSERT_ID()";
	        ResultSet rs = find(sqlGetContatoId);
	        rs.next();
	        int contatoId = rs.getInt(1);

	        
	        String sqlCliente = "INSERT INTO cliente (nome, cpf, contato_id) VALUES (?, ?, ?)";
	        save(sqlCliente, cliente.getNome(), cliente.getCpf(), contatoId);
	    }

	    
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



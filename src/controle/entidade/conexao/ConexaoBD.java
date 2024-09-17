package controle.entidade.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	public static Connection getConexaoMySQL() {

		// Atributo do tipo Connection
		Connection connection = null;

		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			System.out.println("O driver especificado não foi encontrado");

		}

		// Endereço do servidor do BD
		String serverName = "localhost";

		// Nome do seu banco de dados
		String mydatabase = "mydb";

		// String de Conexão.
		String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

		// Nome de um usuário de seu BD
		String username = "root";

		// A senha de acesso do usuário informado acima.
		String password = "aluno";

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Não foi possível conectar ao banco de dados");

		}

		return connection;
	}

}

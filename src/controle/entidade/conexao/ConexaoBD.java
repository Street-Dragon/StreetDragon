package controle.entidade.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {
    private static final String DB_URL = "jdbc:mysql://localhost/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aluno";

    public static Connection getConexaoMySQL() {
        Connection connection = null;
        String driverName = "com.mysql.cj.jdbc.Driver";
        
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(DB_URL + "streetdragon", USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("O driver especificado não foi encontrado");
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar ao banco de dados");
        }

        return connection;
    }

    public static void criarBancoDeDadosETabela() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Criar banco de dados
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS streetdragon");
            System.out.println("Banco de dados criado ou já existe!");

            // Usar o banco de dados
            stmt.executeUpdate("USE streetdragon");

            // Criar tabela
            String sql = "CREATE TABLE IF NOT EXISTS funcionarios (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "username VARCHAR(255) NOT NULL," +
                         "password VARCHAR(255) NOT NULL)";
                         
            stmt.executeUpdate(sql);
            System.out.println("Tabela criada ou já existe!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

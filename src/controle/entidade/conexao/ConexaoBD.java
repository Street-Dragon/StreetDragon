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
            stmt.executeUpdate("DROP DATABASE IF EXISTS streetdragon");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS streetdragon");
            System.out.println("Banco de dados criado ou já existe!");

            // Usar o banco de dados
            stmt.executeUpdate("USE streetdragon");
            
            stmt.executeUpdate("DROP TABLE IF EXISTS contato");
            stmt.executeUpdate("DROP TABLE IF EXISTS funcionarios");
            stmt.executeUpdate("DROP TABLE IF EXISTS clientes");
            stmt.executeUpdate("DROP TABLE IF EXISTS endereco");
            
            
            // Cria tabela contato 
            String sqlContato = "CREATE TABLE IF NOT EXISTS contato (" +
                                "id_contato INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                                "email VARCHAR(45) NOT NULL, " + 
                                "telefone VARCHAR(45) NOT NULL" +
                                ") ENGINE = InnoDB;";
            
            // Cria tabela funcionarios
            String sqlFuncionario = "CREATE TABLE IF NOT EXISTS funcionarios (" +
            	    "cpf INT NOT NULL, " +
            	    "senha VARCHAR(45) NOT NULL, " +
            	    "nome VARCHAR(45) NOT NULL, " +
            	    "contato_id INT NOT NULL, " + // 
            	    "PRIMARY KEY (cpf), " +
            	    "FOREIGN KEY (contato_id) REFERENCES contato(id_contato) " + // 
            	    "ON DELETE NO ACTION " +
            	    "ON UPDATE NO ACTION" +
            	    ") ENGINE = InnoDB;";
            
            String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
                    "cpf INT NOT NULL AUTO_INCREMENT, " +
                    "nome VARCHAR(45) NOT NULL, " +
                    "contato_id INT NOT NULL, " +
                    "PRIMARY KEY (cpf), " +
                    "INDEX fk_Clientes_Contato1_idx (contato_id ASC), " +
                    "CONSTRAINT fk_Clientes_Contato1 " +
                    "FOREIGN KEY (contato_id) " +
                    "REFERENCES contato(id_contato) " +
                    "ON DELETE NO ACTION " +
                    "ON UPDATE NO ACTION " +
                    ") ENGINE = InnoDB;";

            String sqlEndereco = "CREATE TABLE IF NOT EXISTS endereco (" +
                    "cep INT NOT NULL AUTO_INCREMENT, " +
                    "rua VARCHAR(45) NOT NULL, " +
                    "bairro VARCHAR(45) NOT NULL, " +
                    "complemento VARCHAR(45) NOT NULL, " +
                    ") ENGINE = InnoDB;";
            
            stmt.executeUpdate(sqlContato);
            stmt.executeUpdate(sqlEndereco);
            stmt.executeUpdate(sqlFuncionario);
            stmt.executeUpdate(sqlClientes);
            System.out.println("Tabela criada ou já existe!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

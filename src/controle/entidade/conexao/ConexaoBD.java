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
            stmt.executeUpdate("DROP TABLE IF EXISTS fornecedores");
            
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
            
            		//Cria tabela Fornecedores
            
            String sqlFornecedores = "CREATE TABLE IF NOT EXISTS fornecedores (" +
            		  "idFornecedores` INT NOT NULL AUTO_INCREMENT, " +
            		  "nome` VARCHAR(45) NOT NULL, " +
            		  "endereco_CEP INT NOT NULL, " +
            		  "produtos_idProdutos INT NOT NULL, " + //
            		  "PRIMARY KEY (idFornecedores), " + 
            		  "INDEX fk_Fornecedores_Endereço1_idx (Endereco_cep ASC), " +
            		  "INDEX fk_Fornecedores_Produtos1_idx (Produtos_idProdutos ASC), "  +
            		  "CONSTRAINT fk_Fornecedores_Endereço1 " + 
            		    "FOREIGN KEY (Endereco_cep) " +
            		    "REFERENCES Endereco (cep) " +
            		    "ON DELETE NO ACTION " +
            		    "ON UPDATE NO ACTION " +
            		  "CONSTRAINT fk_Fornecedores_Produtos1 " +
            		    "FOREIGN KEY (Produtos_idProdutos) " +
            		    "REFERENCES Produtos (idProdutos) " +
            		    "ON DELETE NO ACTION " +
            		    "ON UPDATE NO ACTION) " +
            		    ") ENGINE = InnoDB;";

	            
            stmt.executeUpdate(sqlContato);
            stmt.executeUpdate(sqlFuncionario);
            stmt.executeUpdate(sqlClientes);
            stmt.executeUpdate(sqlFornecedores);
            System.out.println("Tabela criada ou já existe!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

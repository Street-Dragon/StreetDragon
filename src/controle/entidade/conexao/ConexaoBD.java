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
			stmt.executeUpdate("DROP TABLE IF EXISTS vendas");
			stmt.executeUpdate("DROP TABLE IF EXISTS promocao");

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
			                        "contato_id INT NOT NULL, " +
			                        "PRIMARY KEY (cpf), " +
			                        "FOREIGN KEY (contato_id) REFERENCES contato(id_contato) " +
			                        "ON DELETE NO ACTION " +
			                        "ON UPDATE NO ACTION" +
			                        ") ENGINE = InnoDB;";

			// Cria tabela clientes
			String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
			                     "cpf INT NOT NULL, " + 
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

			// Cria tabela endereco
			String sqlEndereco = "CREATE TABLE IF NOT EXISTS endereco (" + 
			                     "cep INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + 
			                     "rua VARCHAR(45) NOT NULL, " +
			                     "bairro VARCHAR(45) NOT NULL, " +
			                     "complemento VARCHAR(45) NOT NULL " +
			                     ") ENGINE = InnoDB;";


			// Cria tabela vendas
			String sqlVendas = "CREATE TABLE IF NOT EXISTS vendas (" +
			                   "idVendas INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
			                   "Funcionarios_cpf INT NOT NULL, " +
			                   "Clientes_cpf INT NOT NULL, " +
			                   "INDEX fk_Vendas_Funcionarios1_idx (Funcionarios_cpf ASC), " +
			                   "INDEX fk_Vendas_Clientes1_idx (Clientes_cpf ASC), " +
			                   "CONSTRAINT fk_Vendas_Funcionarios1 " +
			                   "FOREIGN KEY (Funcionarios_cpf) " +
			                   "REFERENCES funcionarios (cpf) " +
			                   "ON DELETE NO ACTION " +
			                   "ON UPDATE NO ACTION, " +
			                   "CONSTRAINT fk_Vendas_Clientes1 " +
			                   "FOREIGN KEY (Clientes_cpf) " +
			                   "REFERENCES clientes (cpf) " + 
			                   "ON DELETE NO ACTION " +
			                   "ON UPDATE NO ACTION " +
			                   ") ENGINE = InnoDB;";
			
			// Cria tabela Promocoes
			String sqlPromocao = "CREATE TABLE IF NOT EXISTS promocao (" +
			                      "idPromocoes INT NOT NULL AUTO_INCREMENT, " +
			                      "desconto FLOAT NOT NULL, " +
			                      "Produtos_idProdutos INT NOT NULL, " +
			                      "PRIMARY KEY (idPromocoes), " +
			                      "INDEX fk_Promocoes_Produtos1_idx (Produtos_idProdutos ASC), " +
			                      "CONSTRAINT fk_Promocoes_Produtos1 " +
			                      "FOREIGN KEY (Produtos_idProdutos) " +
			                      "REFERENCES Produtos (idProdutos) " +
			                      "ON DELETE NO ACTION " +
			                      "ON UPDATE NO ACTION " +
			                      ") ENGINE = InnoDB;";
			
			String sqlPromocoesHasClientes = "CREATE TABLE IF NOT EXISTS Promocoes_has_Clientes (" +
						                    "Promocoes_idPromocoes INT NOT NULL, " +
						                    "Clientes_cpf INT NOT NULL, " +
						                    "idPromocoesCliente INT NOT NULL, " +
						                    "PRIMARY KEY (idPromocoesCliente), " +
						                    "INDEX fk_Promocoes_has_Clientes_Clientes1_idx (Clientes_cpf ASC), " +
						                    "INDEX fk_Promocoes_has_Clientes_Promocoes1_idx (Promocoes_idPromocoes ASC), "+
						                    "CONSTRAINT fk_Promocoes_has_Clientes_Promocoes1 " +
						                    "FOREIGN KEY (Promocoes_idPromocoes) " +
						                    "REFERENCES Promocoes (idPromocoes) " +
						                    "ON DELETE NO ACTION " +
						                    "ON UPDATE NO ACTION, " +
						                    "CONSTRAINT fk_Promocoes_has_Clientes_Clientes1 " +
						                    "FOREIGN KEY (Clientes_cpf) " +
						                    "REFERENCES Clientes (cpf) " + 
						                    "ON DELETE NO ACTION " +
						                    "ON UPDATE NO ACTION" +
						                    ") ENGINE = InnoDB;";

			stmt.executeUpdate(sqlContato);
			stmt.executeUpdate(sqlEndereco);
			stmt.executeUpdate(sqlFuncionario);
			stmt.executeUpdate(sqlClientes);
			stmt.executeUpdate(sqlVendas);
			stmt.executeUpdate(sqlPromocao);
			stmt.executeUpdate(sqlPromocoesHasClientes);
			System.out.println("Tabela criada ou já existe!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

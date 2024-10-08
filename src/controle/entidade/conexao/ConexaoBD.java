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

            stmt.executeUpdate("DROP DATABASE IF EXISTS streetdragon");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS streetdragon");
            System.out.println("Banco de dados criado ou já existe!");
            stmt.executeUpdate("USE streetdragon");

            // Cria tabela contato
            String sqlContato = "CREATE TABLE IF NOT EXISTS contato (" +
                                "id_contato INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                                "email VARCHAR(45) NOT NULL, " +
                                "telefone VARCHAR(45) NOT NULL" +
                                ") ENGINE = InnoDB;";

            // Cria tabela funcionario
            String sqlFuncionario = "CREATE TABLE IF NOT EXISTS funcionario (" +
                                    "cpf INT NOT NULL, " +
                                    "senha VARCHAR(45) NOT NULL, " +
                                    "nome VARCHAR(45) NOT NULL, " +
                                    "contato_id INT NOT NULL, " +
                                    "PRIMARY KEY (cpf), " +
                                    "FOREIGN KEY (contato_id) REFERENCES contato(id_contato) " +
                                    "ON DELETE NO ACTION " +
                                    "ON UPDATE NO ACTION" +
                                    ") ENGINE = InnoDB;";

            // Cria tabela cliente
            String sqlCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                                "cpf INT NOT NULL, " +
                                "nome VARCHAR(45) NOT NULL, " +
                                "contato_id INT NOT NULL, " +
                                "PRIMARY KEY (cpf), " +
                                "INDEX fk_Cliente_Contato1_idx (contato_id ASC), " +
                                "CONSTRAINT fk_Cliente_Contato1 " +
                                "FOREIGN KEY (contato_id) " +
                                "REFERENCES contato(id_contato) " +
                                "ON DELETE NO ACTION " +
                                "ON UPDATE NO ACTION " +
                                ") ENGINE = InnoDB;";
            
            // Cria tabela promocao
            String sqlPromocao = "CREATE TABLE IF NOT EXISTS promocao (" +
                                 "idPromocao INT NOT NULL, " +
                                 "desconto FLOAT NOT NULL, " +
                                 "PRIMARY KEY (idPromocao)" +
                                 ") ENGINE = InnoDB;";

            // Cria tabela endereco
            String sqlEndereco = "CREATE TABLE IF NOT EXISTS endereco (" +
                                 "cep INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                                 "rua VARCHAR(45) NOT NULL, " +
                                 "bairro VARCHAR(45) NOT NULL, " +
                                 "complemento VARCHAR(45) NOT NULL " +
                                 ") ENGINE = InnoDB;";

            // Cria tabela venda
            String sqlVenda = "CREATE TABLE IF NOT EXISTS venda (" +
                              "idVenda INT NOT NULL, " +
                              "funcionario_cpf INT NOT NULL, " +
                              "cliente_cpf INT NOT NULL, " +
                              "precoTotal FLOAT NOT NULL, " +
                              "item_idItem INT NOT NULL, " +
                              "PRIMARY KEY (idVenda, funcionario_cpf, cliente_cpf), " +
                              "INDEX fk_Venda_Funcionario1_idx (funcionario_cpf ASC), " +
                              "INDEX fk_Venda_Cliente1_idx (cliente_cpf ASC), " +
                              "INDEX fk_Venda_Item1_idx (item_idItem ASC), " +
                              "CONSTRAINT fk_Venda_Funcionario1 " +
                              "FOREIGN KEY (funcionario_cpf) " +
                              "REFERENCES funcionario (cpf) " +
                              "ON DELETE NO ACTION " +
                              "ON UPDATE NO ACTION, " +
                              "CONSTRAINT fk_Venda_Cliente1 " +
                              "FOREIGN KEY (cliente_cpf) " +
                              "REFERENCES cliente (cpf) " +
                              "ON DELETE NO ACTION " +
                              "ON UPDATE NO ACTION, " +
                              "CONSTRAINT fk_Venda_Item1 " +
                              "FOREIGN KEY (item_idItem) " +
                              "REFERENCES item (idItem) " +
                              "ON DELETE NO ACTION " +
                              "ON UPDATE NO ACTION" +
                              ") ENGINE = InnoDB;";


            // Cria tabela promocao_cliente
            String sqlPromocaoCliente = "CREATE TABLE IF NOT EXISTS promocao_cliente (" +
                                         "promocao_idPromocao INT NOT NULL, " +
                                         "cliente_cpf INT NOT NULL, " +
                                         "idPromocaoCliente INT NOT NULL, " +
                                         "PRIMARY KEY (idPromocaoCliente), " +
                                         "INDEX fk_Promocao_Cliente_Cliente1_idx (cliente_cpf ASC), " +
                                         "INDEX fk_Promocao_Cliente_Promocao1_idx (promocao_idPromocao ASC), " +
                                         "CONSTRAINT fk_Promocao_Cliente_Promocao1 " +
                                         "FOREIGN KEY (promocao_idPromocao) " +
                                         "REFERENCES promocao (idPromocao) " +
                                         "ON DELETE NO ACTION " +
                                         "ON UPDATE NO ACTION, " +
                                         "CONSTRAINT fk_Promocao_Cliente_Cliente1 " +
                                         "FOREIGN KEY (cliente_cpf) " +
                                         "REFERENCES cliente (cpf) " +
                                         "ON DELETE NO ACTION " +
                                         "ON UPDATE NO ACTION" +
                                         ") ENGINE = InnoDB;";

            // Cria tabela item
            String sqlItem = "CREATE TABLE IF NOT EXISTS item (" +
                             "idItem INT NOT NULL AUTO_INCREMENT, " +
                             "quantidade INT NOT NULL, " +
                             "produto_idProduto INT NOT NULL, " +
                             "promocao_idPromocao INT NULL, " +
                             "PRIMARY KEY (idItem), " +
                             "INDEX fk_Item_Produto1_idx (produto_idProduto ASC), " +
                             "INDEX fk_Item_Promocao1_idx (promocao_idPromocao ASC), " +
                             "CONSTRAINT fk_Item_Produto1 " +
                             "FOREIGN KEY (produto_idProduto) " +
                             "REFERENCES produto (idProduto) " +
                             "ON DELETE NO ACTION " +
                             "ON UPDATE NO ACTION, " +
                             "CONSTRAINT fk_Item_Promocao1 " +
                             "FOREIGN KEY (promocao_idPromocao) " +
                             "REFERENCES promocao (idPromocao) " +
                             "ON DELETE NO ACTION " +
                             "ON UPDATE NO ACTION" +
                             ") ENGINE = InnoDB;";

            // Cria tabela produto
            String sqlProduto = "CREATE TABLE IF NOT EXISTS produto (" +
                                "idProduto INT NOT NULL AUTO_INCREMENT, " +
                                "nome VARCHAR(45) NOT NULL, " +
                                "material VARCHAR(45) NOT NULL, " +
                                "categoria VARCHAR(45) NOT NULL, " +
                                "valor FLOAT NOT NULL, " +
                                "estoque INT NOT NULL, " +
                                "tamanho VARCHAR(45) NOT NULL, " +
                                "variacao VARCHAR(45) NULL, " +
                                "PRIMARY KEY (idProduto)" +
                                ") ENGINE = InnoDB;";


            stmt.executeUpdate(sqlContato);
            stmt.executeUpdate(sqlEndereco);
            stmt.executeUpdate(sqlFuncionario);
            stmt.executeUpdate(sqlCliente);
            stmt.executeUpdate(sqlPromocao);
            stmt.executeUpdate(sqlProduto);    
            stmt.executeUpdate(sqlItem);       
            stmt.executeUpdate(sqlVenda);
            stmt.executeUpdate(sqlPromocaoCliente);

            System.out.println("Tabelas criadas ou já existem!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

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
			System.out.println("Não foi possível conectar ao banco de dados: " + e.getMessage());
		}

		return connection;
	}

	public static void criarBancoDeDadosETabela() {
		try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				Statement stmt = conn.createStatement()) {

			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS streetdragon");
			System.out.println("Banco de dados criado ou já existe!");
			stmt.executeUpdate("USE streetdragon");

			String sqlContato = "CREATE TABLE IF NOT EXISTS contato ("
					+ "id_contato INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + "email VARCHAR(45) NOT NULL, "
					+ "telefone VARCHAR(15) NOT NULL" + ") ENGINE = InnoDB;";

			String sqlEndereco = "CREATE TABLE IF NOT EXISTS endereco (" + "cep VARCHAR(10) NOT NULL PRIMARY KEY, "
					+ "rua VARCHAR(45) NOT NULL, " + "bairro VARCHAR(45) NOT NULL, " + "complemento VARCHAR(45) NULL"
					+ ") ENGINE = InnoDB;";

			String sqlProduto = "CREATE TABLE IF NOT EXISTS produto ("
					+ "idProduto INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + "nome VARCHAR(45) NOT NULL, "
					+ "material VARCHAR(45) NOT NULL, " + "categoria VARCHAR(45) NOT NULL, " + "valor FLOAT NOT NULL, "
					+ "estoque INT NOT NULL, " + "tamanho VARCHAR(45) NOT NULL, " + "variacao VARCHAR(45) NULL"
					+ ") ENGINE = InnoDB;";

			String sqlPromocao = "CREATE TABLE IF NOT EXISTS promocao ("
					+ "idPromocao INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + "desconto FLOAT NOT NULL"
					+ ") ENGINE = InnoDB;";

			String sqlFuncionario = "CREATE TABLE IF NOT EXISTS funcionario ("
					+ "cpf VARCHAR(14) NOT NULL PRIMARY KEY, " + "senha VARCHAR(100) NOT NULL, "
					+ "nome VARCHAR(100) NOT NULL, " + "contato_id INT NOT NULL, "
					+ "adm BOOLEAN NOT NULL DEFAULT FALSE, "
					+ "FOREIGN KEY (contato_id) REFERENCES contato(id_contato) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION) ENGINE = InnoDB;";

			String sqlCliente = "CREATE TABLE IF NOT EXISTS cliente (" + "cpf VARCHAR(14) NOT NULL PRIMARY KEY, "
					+ "nome VARCHAR(100) NOT NULL, " + "contato_id INT NOT NULL, "
					+ "FOREIGN KEY (contato_id) REFERENCES contato(id_contato) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION) ENGINE = InnoDB;";

			String sqlItem = "CREATE TABLE IF NOT EXISTS item (" + "idItem INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
					+ "quantidade INT NOT NULL, " + "produto_idProduto INT NOT NULL, "
					+ "promocao_idPromocao INT NULL, "
					+ "FOREIGN KEY (produto_idProduto) REFERENCES produto(idProduto) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION, " + "FOREIGN KEY (promocao_idPromocao) REFERENCES promocao(idPromocao) "
					+ "ON DELETE NO ACTION " + "ON UPDATE NO ACTION) ENGINE = InnoDB;";

			String sqlVenda = "CREATE TABLE IF NOT EXISTS venda (" + "idVenda INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
					+ "funcionario_cpf VARCHAR(14) NOT NULL, " + "cliente_cpf VARCHAR(14) NOT NULL, "
					+ "precoTotal FLOAT NOT NULL, " + "item_idItem INT NOT NULL, "
					+ "FOREIGN KEY (funcionario_cpf) REFERENCES funcionario(cpf) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION, " + "FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf) "
					+ "ON DELETE NO ACTION " + "ON UPDATE NO ACTION, "
					+ "FOREIGN KEY (item_idItem) REFERENCES item(idItem) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION) ENGINE = InnoDB;";

			String sqlPromocaoCliente = "CREATE TABLE IF NOT EXISTS promocao_cliente ("
					+ "promocao_idPromocao INT NOT NULL, " + "cliente_cpf VARCHAR(14) NOT NULL, "
					+ "idPromocaoCliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
					+ "FOREIGN KEY (promocao_idPromocao) REFERENCES promocao(idPromocao) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION, " + "FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf) "
					+ "ON DELETE NO ACTION " + "ON UPDATE NO ACTION) ENGINE = InnoDB;";

			String sqlFornecedor = "CREATE TABLE IF NOT EXISTS fornecedor ("
					+ "idFornecedores INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + "nome VARCHAR(45) NOT NULL, "
					+ "endereco_CEP VARCHAR(10) NOT NULL, " + "produtos_idProdutos INT NOT NULL, "
					+ "FOREIGN KEY (endereco_CEP) REFERENCES endereco(cep) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION, " + "FOREIGN KEY (produtos_idProdutos) REFERENCES produto(idProduto) "
					+ "ON DELETE NO ACTION " + "ON UPDATE NO ACTION) ENGINE = InnoDB;";

			stmt.executeUpdate(sqlContato);
			stmt.executeUpdate(sqlEndereco);
			stmt.executeUpdate(sqlProduto);
			stmt.executeUpdate(sqlPromocao);
			stmt.executeUpdate(sqlFuncionario);
			stmt.executeUpdate(sqlCliente);
			stmt.executeUpdate(sqlItem);
			stmt.executeUpdate(sqlVenda);
			stmt.executeUpdate(sqlPromocaoCliente);
			stmt.executeUpdate(sqlFornecedor);

			String sqlContatoRoot = "INSERT INTO contato (email, telefone) VALUES ('mariana@aluno.ifsc', '48 9884651')";
			String sqlFuncionarioRoot = "INSERT INTO funcionario (cpf, senha, nome, contato_id) "
					+ "VALUES ('123', '321', 'Mari', " + 1 + ")";

			// root:
			stmt.executeUpdate(sqlContatoRoot);
			stmt.executeUpdate(sqlFuncionarioRoot);

			System.out.println("Tabelas criadas ou já existem!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
  
package controle.entidade.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

			String sqlFornecedor = "CREATE TABLE IF NOT EXISTS fornecedor ("
					+ "idFornecedores INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + "nome VARCHAR(45) NOT NULL, "
					+ "endereco_CEP VARCHAR(10) NOT NULL, " + "Cnpj VARCHAR(45) NOT NULL, " + "rua VARCHAR(45) NOT NULL"
					+ ") ENGINE = InnoDB;";

			String sqlProduto = "CREATE TABLE IF NOT EXISTS produto ("
					+ "idProduto INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + "nome VARCHAR(45) NOT NULL, "
					+ "material VARCHAR(45) NOT NULL, " + "categoria VARCHAR(45) NOT NULL, " + "valor FLOAT NOT NULL, "
					+ "estoque INT NOT NULL, " + "tamanho VARCHAR(45) NOT NULL, " + "variacao VARCHAR(45) NULL, "
					+ "idFornecedores INT NOT NULL, "
					+ "FOREIGN KEY (idFornecedores) REFERENCES fornecedor(idFornecedores) " + ") ENGINE = InnoDB;";

			String sqlPromocao = "CREATE TABLE IF NOT EXISTS promocao ("
                    + "idPromocao INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "desconto FLOAT NOT NULL, "
                    + "nome VARCHAR(45) NOT NULL, "
                    + "termino VARCHAR(45) NOT NULL, "   
                    + "inicio VARCHAR(45) NOT NULL"      
                    + ") ENGINE = InnoDB;";


			String sqlFuncionario = "CREATE TABLE IF NOT EXISTS funcionario ("
					+ "cpf VARCHAR(14) NOT NULL PRIMARY KEY, " + "senha VARCHAR(100) NOT NULL, "
					+ "nome VARCHAR(100) NOT NULL, " + "contato_id INT NOT NULL, "
					+ "adm BOOLEAN NOT NULL DEFAULT FALSE, "
					+ "FOREIGN KEY (contato_id) REFERENCES contato(id_contato) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION) ENGINE = InnoDB;";

			String sqlCliente = "CREATE TABLE IF NOT EXISTS cliente (" + "cpf VARCHAR(14) NOT NULL PRIMARY KEY, "
					+ "nome VARCHAR(100) NOT NULL, " + "contato_id INT NOT NULL, " + "numero_compras INT DEFAULT 0, "
					+ "FOREIGN KEY (contato_id) REFERENCES contato(id_contato) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION) ENGINE = InnoDB;";

			String sqlVenda = "CREATE TABLE IF NOT EXISTS venda (venda_id INT AUTO_INCREMENT PRIMARY KEY,"
					+ "cliente_id VARCHAR(14)," + "funcionario_cpf VARCHAR(14) NOT NULL,"
					+ "data_venda DATETIME DEFAULT CURRENT_TIMESTAMP, " + "total DECIMAL(10, 2) DEFAULT 0,"
					+ "FOREIGN KEY (funcionario_cpf) REFERENCES funcionario(cpf), "
					+ "FOREIGN KEY (cliente_id) REFERENCES cliente(cpf))" + "ENGINE = InnoDB;";
			
			String sqlVenda_Produto = "CREATE TABLE IF NOT EXISTS venda_produto (venda_produto_id INT AUTO_INCREMENT PRIMARY KEY,"
					+ "venda_id INT NOT NULL," + "prod_id INT NOT NULL,"
					+ "quantidade INT NOT NULL, " + "preco DECIMAL(10, 2) NOT NULL, "
					+"FOREIGN KEY (venda_id) REFERENCES venda(venda_id) ON DELETE CASCADE,"
					+"FOREIGN KEY (prod_id) REFERENCES produto(idProduto) ON DELETE CASCADE) "+ "ENGINE = InnoDB;";

			String sqlPromocaoCliente = "CREATE TABLE IF NOT EXISTS promocao_cliente ("
					+ "promocao_idPromocao INT NOT NULL, " + "cliente_cpf VARCHAR(14) NOT NULL, "
					+ "idPromocaoCliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
					+ "FOREIGN KEY (promocao_idPromocao) REFERENCES promocao(idPromocao) " + "ON DELETE NO ACTION "
					+ "ON UPDATE NO ACTION, " + "FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf) "
					+ "ON DELETE NO ACTION " + "ON UPDATE NO ACTION) ENGINE = InnoDB;";

			stmt.executeUpdate(sqlContato);
			stmt.executeUpdate(sqlEndereco);
			stmt.executeUpdate(sqlEndereco);
			stmt.executeUpdate(sqlFornecedor);
			stmt.executeUpdate(sqlProduto);
			stmt.executeUpdate(sqlPromocao);
			stmt.executeUpdate(sqlFuncionario);
			stmt.executeUpdate(sqlCliente);
			stmt.executeUpdate(sqlVenda);
			stmt.executeUpdate(sqlVenda_Produto);
			stmt.executeUpdate(sqlPromocaoCliente);

			String sqlVerificaFuncionario = "SELECT cpf FROM funcionario WHERE cpf = '123'";
			PreparedStatement psFuncionario = conn.prepareStatement(sqlVerificaFuncionario);
			ResultSet rsFuncionario = psFuncionario.executeQuery();

			if (!rsFuncionario.next()) {
				String sqlContatoRoot = "INSERT INTO contato (email, telefone) VALUES "
						+ "('mariana@aluno.ifsc', '48 9884651'), " + "('cadu@aluno.ifsc', '47912344321')";

				String sqlFuncionarioRoot = "INSERT INTO funcionario (cpf, senha, nome, contato_id, adm) " + "VALUES "
						+ "('123', '321', 'Mari', 1, 1), " // Mari (administrador)
						+ "('321', '123', 'Cadu', 2, 0)"; // Cadu (não administrador)

				String sqlCadastraEndereco = "INSERT INTO endereco (cep, rua, bairro, complemento)" + "VALUES"
						+ "('12345678', 'Rua das Flores', 'Centro', 'Apto 101'),"
						+ "('98765432', 'Avenida Paulista', 'Bela Vista', 'Andar 5')";

				String sqlCadastraFornecedores = "INSERT INTO fornecedor (nome, endereco_CEP, Cnpj, rua) " + "VALUES "
						+ "('Fornecedor A', '12345678', '12345678000190', 'Rua das flores'), "
						+ "('Fornecedor B', '98765432', '98765432000110', 'Avenida paulista')";

				String sqlCadastraProdutos = "INSERT INTO produto (nome, material, categoria, valor, estoque, tamanho, variacao, idFornecedores) "
						+ "VALUES " + "('JoJo', 'Algodão', 'Camiseta', 49.99, 100, 'M', 'Azul', 1), "
						+ "('Cinto punk', 'Couro', 'Acessórios', 30.90, 50, 'U', 'Spikes', 2)";

				stmt.executeUpdate(sqlContatoRoot);
				stmt.executeUpdate(sqlFuncionarioRoot);
				stmt.executeUpdate(sqlCadastraEndereco);
				stmt.executeUpdate(sqlCadastraFornecedores);
				stmt.executeUpdate(sqlCadastraProdutos);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

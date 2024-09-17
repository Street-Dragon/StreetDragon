package controle.entidade.conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Segundo {
	public static void main(String[] args) {

		Statement stmt1 = null;

		Connection conn = ConexaoBD.getConexaoMySQL();

		try {
			stmt1 = (Statement) conn.createStatement();
			ResultSet res1 = null;
			res1 = stmt1.executeQuery("Select * from clientes");

			System.out.println("CPF  NOME    E-MAIL");
			//Conta o numero de registros do ResultSet no BD
			int count = 0;
			while (res1.next()) {
				System.out.println(
						res1.getInt("cpf") + "   " + res1.getString("nome") + "    " + res1.getString("email"));
				count++;
			}
			System.out.println("Número de registros: " + count);
			

			res1.close();
			stmt1.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

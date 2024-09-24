package controle.entidade.funcionariocontrole;

import modelo.dao.funcionario.FuncionarioDAO;

public class FuncionarioControle {
	// Dentro do evento do botão de login
	String username = campoUsername.getText();
	String password = campoPassword.getText();

	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		if (funcionarioDAO.login(username, password)) {
		    // Login bem-sucedido
		    System.out.println("Login bem-sucedido!");
		} else {
		    // Falha no login
		    System.out.println("Login falhou! Verifique suas credenciais.");
		}

	}
}

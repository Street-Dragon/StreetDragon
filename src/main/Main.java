package main;

import controle.entidade.conexao.ConexaoBD;
import controle.entidade.funcionariocontrole.FuncionarioControle;
import controle.entidade.funcionariocontrole.TelaPrincipalControle;
import visao.TelaLogin;
import visao.TelaPrincipal;
import visao.TelaCadastroFuncionario;

public class Main {
	public static void main(String[] args) {
		// Cria o banco
		ConexaoBD.criarBancoDeDadosETabela();

		// Inicialização as telas
		TelaLogin telaLogin = new TelaLogin();
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario(telaPrincipal);

		// Instancia o controle
		FuncionarioControle funcionarioControle = new FuncionarioControle();
		// TelaPrincipalControle telaPrincipalControle = new TelaPrincipalControle();
		
		// Conecta as telas ao controle
		funcionarioControle.setTelaLogin(telaLogin);
		funcionarioControle.setCadastroFuncionario(telaCadastroFuncionario);
		funcionarioControle.setTelaPrincipal(telaPrincipal);
		// Exibe a tela de login
		telaLogin.setVisible(true);

        // Exibe a tela de cadastro
        // no primeiro momento, apenas exibe sem logica
        telaCadastroFuncionario.setVisible(true);
        //Atualizando a tabela logo no começo pq ss
        funcionarioControle.atualizarTabela();
    }
}

package main;

import controle.entidade.conexao.ConexaoBD;
import controle.entidade.funcionariocontrole.FuncionarioControle;
import visao.TelaLogin;
import visao.TelaCadastroFuncionario;


public class Main {
    public static void main(String[] args) {
    	// Criação do banco de dados e tabela
        ConexaoBD.criarBancoDeDadosETabela(); 

        // Inicialização das telas
        TelaLogin telaLogin = new TelaLogin(); 
        TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario();

        // Inicialização do controlador
        FuncionarioControle funcionarioControle = new FuncionarioControle();

        // Conectando as telas ao controlador
        funcionarioControle.setTelaLogin(telaLogin); 
        funcionarioControle.setCadastroFuncionario(telaCadastroFuncionario);

        // Exibindo a tela de login
        telaLogin.setVisible(true);

        // Exibindo a tela de cadastro de funcionários (opcional)
        // Se quiser que a tela de cadastro de funcionários abra em outro momento,
        // você pode chamar este método mais tarde, ou com base em uma ação.
        telaCadastroFuncionario.setVisible(true);
    }
}

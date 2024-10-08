package main;

import controle.entidade.conexao.ConexaoBD;
import controle.entidade.funcionariocontrole.FuncionarioControle;
import visao.TelaLogin;
import visao.TelaCadastroFuncionario;


public class Main {
    public static void main(String[] args) {
    	// Cria o banco
        ConexaoBD.criarBancoDeDadosETabela(); 

        // Inicialização as telas
        TelaLogin telaLogin = new TelaLogin(); 
        TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario();

        // Instancia o controle
        FuncionarioControle funcionarioControle = new FuncionarioControle();

        // Conecta as telas ao controle
        funcionarioControle.setTelaLogin(telaLogin); 
        funcionarioControle.setCadastroFuncionario(telaCadastroFuncionario);

        // Exibe a tela de login
        telaLogin.setVisible(true);

        // Exibe a tela de cadastro
        // no primeiro momento, apenas exibe sem logica
        telaCadastroFuncionario.setVisible(true);
    }
}

package main;

import controle.entidade.conexao.ConexaoBD;
import controle.entidade.funcionariocontrole.FuncionarioControle;
import controle.entidade.produto.ProdutoControle;
import visao.TelaLogin;
import visao.TelaPrincipal;
import visao.TelaProdutos;
import visao.TelaCadastroFuncionario;
import visao.TelaCadastroProdutos;

public class Main {
    public static void main(String[] args) {
        // Cria o banco
        ConexaoBD.criarBancoDeDadosETabela();

        // Inicialização das telas
        TelaLogin telaLogin = new TelaLogin();
        TelaPrincipal telaPrincipal = new TelaPrincipal();

        TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario(telaPrincipal);

        TelaProdutos telaProduto = new TelaProdutos(telaPrincipal);
        TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();
        ProdutoControle produtoControle = new ProdutoControle();
        produtoControle.setCadastroProdutobtn(telaProduto);
        produtoControle.setTelaCadastrarProduto(telaCadastroProdutos);
        produtoControle.setCadastroProduto(telaCadastroProdutos);
        produtoControle.setDeletarProduto(telaProduto);
        produtoControle.setTelaEditarProduto(telaProduto);
        produtoControle.fillPP(telaProduto);

        // Instancia o controle
        FuncionarioControle funcionarioControle = new FuncionarioControle();

        // Conecta as telas ao controle
        funcionarioControle.setTelaLogin(telaLogin);
        funcionarioControle.setTelaCadastroFuncionario(telaCadastroFuncionario);
        funcionarioControle.setTelaPrincipal(telaPrincipal);

        // Exibe a tela de login
        telaLogin.setVisible(true); // Exibe a tela de login

        // Exibe a tela de cadastro
        telaCadastroFuncionario.setVisible(true); // Exibe a tela de cadastro de funcionário
        // Atualizando a tabela logo no começo
        funcionarioControle.atualizarTabela();

        produtoControle.listarProdutosTable(); // Lista os produtos

    }
}

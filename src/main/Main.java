package main;

import controle.entidade.clientecontrole.ClienteControle;
import controle.entidade.conexao.ConexaoBD;
import controle.entidade.funcionariocontrole.FuncionarioControle;
import controle.entidade.produto.ProdutoControle;
import visao.TelaLogin;
import visao.TelaPrincipal;
import visao.TelaProdutos;
import visao.TelaCadastroFuncionario;
import visao.TelaCadastroProdutos;
import visao.TelaCliente;

public class Main {
    public static void main(String[] args) {
        // Cria o banco
        ConexaoBD.criarBancoDeDadosETabela();

        // Inicialização das telas
        TelaLogin telaLogin = new TelaLogin();
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        TelaCliente telaCliente = new TelaCliente(telaPrincipal);

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
        ClienteControle clienteControle = new ClienteControle();

        // Conecta as telas ao controle
        funcionarioControle.setTelaLogin(telaLogin);
        funcionarioControle.setTelaCadastroFuncionario(telaCadastroFuncionario);
        funcionarioControle.setTelaPrincipal(telaPrincipal);
        
        clienteControle.setTelaCadastroCliente(telaCliente);


        // Exibe a tela de login
        telaLogin.setVisible(true); // Exibe a tela de login

        // Exibe a tela de cadastro
        telaCadastroFuncionario.setVisible(true); // Exibe a tela de cadastro de funcionário
        // Atualizando a tabela logo no começo
        funcionarioControle.atualizarTabela();
        clienteControle.atualizarTabela();

        produtoControle.listarProdutosTable(); // Lista os produtos

    }
}

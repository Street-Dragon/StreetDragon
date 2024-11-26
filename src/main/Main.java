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

		// Inicialização as telas
		TelaLogin telaLogin = new TelaLogin();
		TelaPrincipal telaPrincipal = new TelaPrincipal();

		TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario(telaPrincipal);
		TelaProdutos telaProduto = new TelaProdutos(telaPrincipal);
		TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();

		// acho que não precisa instanciar o controle e era tudo mentira pessoal,
		// futuros instanciamentos devem ser na telaprincipal (visão)
		// se não for assim o código quebra.

		// Instancia o controle
		FuncionarioControle funcionarioControle = new FuncionarioControle();
		
		// Conecta as telas ao controle
		funcionarioControle.setTelaLogin(telaLogin);
		funcionarioControle.setTelaCadastroFuncionario(telaCadastroFuncionario);
		funcionarioControle.setTelaPrincipal(telaPrincipal);
		ProdutoControle produtoControle = new ProdutoControle();
		produtoControle.setTelaProdutos(telaProduto);
		produtoControle.setTelaCadastrarProduto(telaCadastroProdutos);

		// Exibe a tela de login
		telaLogin.setVisible(true);
			
        // Exibe a tela de cadastro
        telaCadastroFuncionario.setVisible(true); // Exibe a tela de cadastro de funcionário
        // Atualizando a tabela logo no começo
        funcionarioControle.atualizarTabela();
        		
        produtoControle.listarProdutosTable();
        
        
        clienteControle.atualizarTabela();
    }
}

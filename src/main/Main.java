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

		// Inicialização as telas
		TelaLogin telaLogin = new TelaLogin();
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		
		TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario(telaPrincipal);
		TelaProdutos telaProduto = new TelaProdutos(telaPrincipal);
		TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();
	
	
		
		// Instancia o controle
		FuncionarioControle funcionarioControle = new FuncionarioControle();
		
		ProdutoControle produtoControle = new ProdutoControle();
		
		// Conecta as telas ao controle
		funcionarioControle.setTelaLogin(telaLogin);
		funcionarioControle.setTelaCadastroFuncionario(telaCadastroFuncionario);
		funcionarioControle.setTelaPrincipal(telaPrincipal);
		
		produtoControle.setTelaProdutos(telaProduto);
		produtoControle.setTelaCadastrarProduto(telaCadastroProdutos);
		//produtoControle.setDeletarProduto(telaProduto);
		//produtoControle.setTelaEditarProduto(telaProduto);
		//produtoControle.fillPP(telaProduto);
		
		
		
		// Exibe a tela de login
		telaLogin.setVisible(true);
        
        
        //Atualizando a tabela logo no começo pq ss
        funcionarioControle.atualizarTabela();		
        //produtoControle.listarProdutosTable();
        
    }
}

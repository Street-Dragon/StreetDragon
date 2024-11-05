package main;

import controle.entidade.conexao.ConexaoBD;
import controle.entidade.funcionariocontrole.FuncionarioControle;
import controle.entidade.produto.ProdutoControle;
import controle.entidade.funcionariocontrole.TelaPrincipalControle;
import visao.TelaLogin;
import visao.TelaPrincipal;
import visao.TelaProdutos;
import visao.TelaCadastroFuncionario;
import visao.TelaCadastroProdutos;

public class Main {
	public static void main(String[] args) {
		// Cria o banco
		//ConexaoBD.criarBancoDeDadosETabela();

		// Inicialização as telas
		TelaLogin telaLogin = new TelaLogin();
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		
		TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario(telaPrincipal);
		
		TelaProdutos telaProduto = new TelaProdutos(telaPrincipal);
		TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();
		ProdutoControle produtoControle = new ProdutoControle();
		produtoControle.setCadastroProdutobtn(telaProduto);
		produtoControle.setTelaCadastrarProduto(telaCadastroProdutos);
		produtoControle.setCadastroProduto(telaCadastroProdutos);
		
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
        		
        produtoControle.listarProdutosTable();
        
        telaPrincipal.add(telaProduto); // or mainPanel.add(telaProdutos);
        telaPrincipal.revalidate();
        telaPrincipal.repaint();
        
    }
}

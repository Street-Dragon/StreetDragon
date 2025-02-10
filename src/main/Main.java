package main;

import controle.entidade.conexao.ConexaoBD;
import controle.entidade.funcionariocontrole.FuncionarioControle;
import controle.entidade.pagamentocontrole.TelaPagamentoControle;
import controle.entidade.produto.ProdutoControle;
import controle.entidade.promocao.PromocaoControle;
import visao.TelaLogin;
import visao.TelaPagamento;
import visao.TelaPrincipal;
import visao.TelaProdutos;
import visao.TelaPromocao;
import visao.TelaFuncionario;
import visao.TelaCadastroProdutos;
import visao.TelaCliente;

public class Main {
    public static void main(String[] args) {
        // Cria o banco
        ConexaoBD.criarBancoDeDadosETabela();

		// Inicialização as telas
		TelaLogin telaLogin = new TelaLogin();
		TelaPrincipal telaPrincipal = new TelaPrincipal();

		TelaFuncionario telaCadastroFuncionario = new TelaFuncionario(telaPrincipal);
		TelaProdutos telaProduto = new TelaProdutos(telaPrincipal);
		TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();
		TelaPromocao telaPromocao = new TelaPromocao(telaPrincipal);
		
		// talvz isso saia
		TelaPagamento telaPagamento = new TelaPagamento(telaPrincipal); // Se precisar do menu principal
		TelaPagamentoControle pagamentoControle = new TelaPagamentoControle(telaPagamento);
		
		//TelaHistoricoVenda telahistorico = new TelaHistoricoVenda(telaPrincipal);
		//HistoricoVendaControle historico = new HistoricoVendaControle(telahistorico);
		
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
		PromocaoControle promocaoControle = new PromocaoControle(telaPromocao);
		// Exibe a tela de login
		telaLogin.setVisible(true);
			
        // Exibe a tela de cadastro
        telaCadastroFuncionario.setVisible(true); // Exibe a tela de cadastro de funcionário
        // Atualizando a tabela logo no começo
        funcionarioControle.atualizarTabela();
        		
        produtoControle.atualizarTabela();
        
        
        promocaoControle.atualizarTabela();
        //clienteControle.atualizarTabela();
    }
}

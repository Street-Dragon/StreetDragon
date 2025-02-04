package controle.visao.principal;

import visao.TelaCadastroFuncionario;
import visao.TelaMensagens;
import visao.TelaPrincipal;
import visao.TelaVenda;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import controle.entidade.funcionariocontrole.FuncionarioControle;
import controle.entidade.pagamentocontrole.TelaPagamentoControle;
import modelo.dao.funcionario.FuncionarioDAO;

public class TelaPrincipalControle {
	private TelaPrincipal telaPrincipal;
	private JButton btnSelec;
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private FuncionarioControle funcionarioControle = new FuncionarioControle();
	private TelaVenda telaVenda;
	
	private TelaPagamentoControle tpc;

	public TelaPrincipalControle(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
		mudarCorBotao(telaPrincipal.getBtnVenda());

		// Configuração dos listeners dos botões
		this.telaPrincipal.getBtnVenda().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaVenda");
				mudarCorBotao(telaPrincipal.getBtnVenda());
				
			}
		});

		this.telaPrincipal.getBtnHistorico().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaHistoricoVenda");
				mudarCorBotao(telaPrincipal.getBtnHistorico());
			}
		});

		this.telaPrincipal.getBtnFornecedor().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaFornecedor");
				mudarCorBotao(telaPrincipal.getBtnFornecedor());
			}
		});

		this.telaPrincipal.getBtnProdutos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaProdutos");
				mudarCorBotao(telaPrincipal.getBtnProdutos());
			}
		});

		this.telaPrincipal.getBtnPromocoes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaPromocao");
				mudarCorBotao(telaPrincipal.getBtnPromocoes());
			}
		});

		this.telaPrincipal.getBtnClientes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaCliente");
				mudarCorBotao(telaPrincipal.getBtnClientes());
			}
		});

		this.telaPrincipal.getBtnFuncionarios().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaCadastroFuncionario");
				mudarCorBotao(telaPrincipal.getBtnFuncionarios());
			}
		});
	}

	public void setTelaVenda(TelaVenda telaVenda) {
	    this.telaVenda = telaVenda;

	    telaVenda.getBtnRealizarCompra().addActionListener(e -> {
	    	System.out.println("c");
	        // Validação do carrino vazio
	        DefaultTableModel model = (DefaultTableModel) telaVenda.getTable().getModel();
	        
	        if (model.getRowCount() == 0) {
	            new TelaMensagens("Adicione um item ao carrinho!", 3);
	            return;
	        }

	        telaPrincipal.getCardLayout().show(telaPrincipal.getMainPanel(), "TelaPagamento");
	    });
	}
	
	public void setTelaPagamentoControle(TelaPagamentoControle tpc) {
	this.tpc = tpc;
	}

	// método para trocar o jpanel atual
	private void trocarTela(String nomeTela) {
		CardLayout cardLayout = (CardLayout) telaPrincipal.getMainPanel().getLayout();
		cardLayout.show(telaPrincipal.getMainPanel(), nomeTela);
	}

	public void mudarCorBotao(JButton botao) {

		if (btnSelec != null) {
			btnSelec.setContentAreaFilled(false); // Deixa o fundo transparent
			btnSelec.setForeground(Color.BLACK);
		}
		btnSelec = botao;
		btnSelec.setContentAreaFilled(true);
		btnSelec.setBorderPainted(true);
		btnSelec.setBackground(new Color(255, 149, 149));
		btnSelec.setForeground(Color.WHITE);

	}

}
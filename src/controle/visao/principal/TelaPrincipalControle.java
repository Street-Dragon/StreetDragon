package controle.visao.principal;

import visao.TelaPrincipal;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TelaPrincipalControle {
	private TelaPrincipal telaPrincipal;
	private JButton btnSelec;

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
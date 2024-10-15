package visao;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controle.entidade.funcionariocontrole.TelaPrincipalControle;

import net.miginfocom.swing.MigLayout;
import utils.Utils;

import javax.swing.JLabel;

public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private JButton btnVenda;
	private JButton btnProdutos;
	private JButton btnHistorico;
	private JPanel panel;
	private Font hkGrotesk;
	private JLabel lblLogo;
	private JLabel lblFuncionario;
	private JButton btnDeslogar;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaPrincipal telaPrincipal = new TelaPrincipal();
				telaPrincipal.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaPrincipal() {
		hkGrotesk = Utils.loadCustomFont();
		setTitle("StreetDragon");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setBackground(new Color(246, 233, 233));

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Criando instâncias das telas
		TelaVenda telaVenda = new TelaVenda(this);
		TelaHistoricoVenda telaHistoricoVenda = new TelaHistoricoVenda(this);

		// Adiciona os painéis
		mainPanel.add(telaVenda, "TelaVenda");
		mainPanel.add(telaHistoricoVenda, "TelaHistoricoVenda");

		// Painel do menu lateral
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(246, 233, 233));
		menuPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));

		btnVenda = new JButton("Tela de venda");
		menuPanel.add(btnVenda, "cell 0 0,growx,aligny top");

		btnProdutos = new JButton("Produtos");
		menuPanel.add(btnProdutos, "cell 0 1,growx,aligny top");

		btnHistorico = new JButton("Histórico de vendas");
		menuPanel.add(btnHistorico, "cell 0 2,growx,aligny top");

		// Adicionando os painéis à janela principal
		getContentPane().add(menuPanel, BorderLayout.WEST);

		panel = new JPanel();
		panel.setBackground(new Color(246, 233, 233));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[grow][][][grow][grow][][][][][][][][grow]", "[grow]"));

		lblLogo = new JLabel("StreetDragon");
		panel.add(lblLogo, "cell 0 0,grow");

		lblFuncionario = new JLabel("Funcionário: Nenhum");
		lblFuncionario.setFont(hkGrotesk);
		panel.add(lblFuncionario, "cell 3 0");

		btnDeslogar = new JButton("Deslogar");
		btnDeslogar.setFont(hkGrotesk);
		panel.add(btnDeslogar, "cell 12 0,alignx right,growy");

		new TelaPrincipalControle(this);
	}

	// getters e setters

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JButton getBtnVenda() {
		return btnVenda;
	}

	public JButton getBtnProdutos() {
		return btnProdutos;
	}

	public JButton getBtnHistorico() {
		return btnHistorico;
	}

	public JButton getBtnDeslogar() {
		return btnDeslogar;
	}
	/*
	 * Método para trocar de tela que provavelmente deveria estar no controle public
	 * void trocarTela(String tela) { cardLayout.show(mainPanel, tela); }
	 */
}
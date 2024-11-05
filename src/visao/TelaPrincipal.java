package visao;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controle.entidade.funcionariocontrole.FuncionarioControle;
import controle.entidade.funcionariocontrole.TelaPrincipalControle;

import net.miginfocom.swing.MigLayout;
import utils.Utils;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private JButton btnVenda;
	private JButton btnProdutos;
	private JButton btnHistorico;
	private JPanel panel;
	private Font hkGrotesk;
	private JLabel lblFuncionario;
	private JButton btnDeslogar;
	private JButton btnFuncionarios;
	private JButton btnFornecedor;

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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setBackground(new Color(246, 233, 233));

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Criando instâncias das telas
		TelaVenda telaVenda = new TelaVenda(this);
		TelaHistoricoVenda telaHistoricoVenda = new TelaHistoricoVenda(this);
		TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario(this);
		TelaProdutos telaProdutos = new TelaProdutos(this);
		TelaFornecedor telaFornecedor = new TelaFornecedor(this);

		// Painel do menu lateral
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(246, 233, 233));
		menuPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow]"));

		btnVenda = new JButton("Venda");
		btnVenda.setBackground(new Color(250, 187, 187));
		menuPanel.add(btnVenda, "cell 0 0,grow");

		btnHistorico = new JButton("Histórico de vendas");
		menuPanel.add(btnHistorico, "cell 0 1,grow");
		
		btnFornecedor = new JButton("Fornecedor");
		menuPanel.add(btnFornecedor, "cell 0 4,grow");

		btnProdutos = new JButton("Produtos");
		menuPanel.add(btnProdutos, "cell 0 2,grow");

		// Adicionando os painéis à janela principal
		getContentPane().add(menuPanel, BorderLayout.WEST);

		btnFuncionarios = new JButton("Funcionários");
		menuPanel.add(btnFuncionarios, "cell 0 3,grow");

		panel = new JPanel();
		panel.setBackground(new Color(246, 233, 233));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow]"));

		lblFuncionario = new JLabel("Funcionário: Nenhum");
		lblFuncionario.setFont(hkGrotesk);
		panel.add(lblFuncionario, "cell 1 0,alignx left,growy");

		btnDeslogar = new JButton("Deslogar");
		btnDeslogar.setBackground(new Color(255, 149, 149));
		btnDeslogar.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
		btnDeslogar.setForeground(Color.WHITE);
		btnDeslogar.setIcon(Utils.carregarIcone("logout.png", 30, 30));

		panel.add(btnDeslogar, "cell 4 0,alignx right,growy");

		config(btnHistorico);
		config(btnProdutos);
		config(btnVenda);
		config(btnFuncionarios);
		config(btnFornecedor);

		

		new TelaPrincipalControle(this);

		FuncionarioControle funcionarioControle = new FuncionarioControle();
		funcionarioControle.setTelaPrincipal(this);
		funcionarioControle.setCadastroFuncionario(telaCadastroFuncionario);

		// Adiciona os painéis
		mainPanel.add(telaVenda, "TelaVenda");
		mainPanel.add(telaHistoricoVenda, "TelaHistoricoVenda");
		mainPanel.add(telaCadastroFuncionario, "TelaCadastroFuncionario");
		mainPanel.add(telaProdutos, "TelaProdutos");
		mainPanel.add(telaFornecedor, "TelaFornecedor");

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

	public JButton getBtnFuncionarios() {
		return btnFuncionarios;
	}
	
	public JButton getBtnFornecedor() {
		return btnFornecedor;
	}

	public JButton getBtnDeslogar() {
		return btnDeslogar;
	}

	public JLabel getLblFuncionario() {
		return lblFuncionario;
	}

	private static void config(JButton button) {
		button.setContentAreaFilled(false); // Deixa o fundo transparente
		button.setFocusPainted(false); // Remove o contorno ao focar
		button.setFont(new Font("Hanken Grotesk", Font.BOLD, 15));
	}
}

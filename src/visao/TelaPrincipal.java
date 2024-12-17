package visao;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controle.entidade.clientecontrole.ClienteControle;
import controle.entidade.funcionariocontrole.FuncionarioControle;
import controle.entidade.item.ItemController;
import controle.entidade.produto.ProdutoControle;
import controle.entidade.promocaocontrole.PromocaoControle;
//import controle.tela.historicovendacontrole.HistoricoVendaControle;
import controle.visao.principal.TelaPrincipalControle;
import net.miginfocom.swing.MigLayout;
import utils.Cores;
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
	private JLabel lblFuncionario;
	private JButton btnDeslogar;
	private JButton btnFuncionarios;
	private JButton btnFornecedor;
	private JButton btnClientes;
	private JButton btnPromocoes;
	private JPanel panel_logo;

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
		setBackground(Cores.COR_ROSA_CLARO);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Criando instâncias das telas
		TelaVenda telaVenda = new TelaVenda(this);
		TelaHistoricoVenda telaHistoricoVenda = new TelaHistoricoVenda(this);
		TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario(this);
		TelaProdutos telaProdutos = new TelaProdutos(this);
		TelaFornecedor telaFornecedor = new TelaFornecedor(this);
		TelaCliente telaCliente = new TelaCliente(this);
		TelaPromocao telaPromocao = new TelaPromocao(this);
        TelaPagamento telaPagamento = new TelaPagamento(this);

		// Painel do menu lateral
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Cores.COR_ROSA_CLARO);
		menuPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][grow][grow][grow]"));

		btnVenda = new JButton("Vendas");
		btnVenda.setBackground(new Color(253, 233, 235));
		btnVenda.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
		menuPanel.add(btnVenda, "cell 0 0,grow");

		btnHistorico = new JButton("Histórico de vendas");
		btnHistorico.setBackground(new Color(253, 233, 235));
		btnHistorico.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
		menuPanel.add(btnHistorico, "cell 0 1,grow");

		btnClientes = new JButton("Clientes");
		btnClientes.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
		btnClientes.setBackground(new Color(253, 233, 235));
		menuPanel.add(btnClientes, "cell 0 2,grow");

		btnPromocoes = new JButton("Promoções");
		btnPromocoes.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
		btnPromocoes.setBackground(new Color(253, 233, 235));
		menuPanel.add(btnPromocoes, "cell 0 5,grow");

		btnFornecedor = new JButton("Fornecedores");
		btnFornecedor.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
		btnFornecedor.setBackground(new Color(253, 233, 235));
		menuPanel.add(btnFornecedor, "cell 0 6,grow");

		btnProdutos = new JButton("Produtos");
		btnProdutos.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
		btnProdutos.setBackground(new Color(253, 233, 235));
		menuPanel.add(btnProdutos, "cell 0 3,grow");

		// Adicionando os painéis à janela principal
		getContentPane().add(menuPanel, BorderLayout.WEST);

		btnFuncionarios = new JButton("Funcionários");
		btnFuncionarios.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
		btnFuncionarios.setBackground(new Color(253, 233, 235));
		menuPanel.add(btnFuncionarios, "cell 0 4,grow");

		panel = new JPanel();
		panel.setBackground(Cores.COR_ROSA_CLARO);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow]"));

		lblFuncionario = new JLabel("Funcionário: Nenhum");
		lblFuncionario.setFont(new Font("Hanken Grotesk", Font.PLAIN, 20));
		panel.add(lblFuncionario, "cell 0 0 2 1,alignx left,growy");

		btnDeslogar = new JButton("Deslogar");
		btnDeslogar.setBackground(Cores.COR_ROSA_ESCURO);
		btnDeslogar.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
		btnDeslogar.setForeground(Color.WHITE);
		btnDeslogar.setIcon(Utils.carregarIcone("logout.png", 30, 30));

		panel.add(btnDeslogar, "cell 4 0,alignx right,growy");

		config(btnHistorico);
		config(btnProdutos);
		config(btnVenda);
		config(btnFuncionarios);
		config(btnFornecedor);
		config(btnPromocoes);
		config(btnClientes);

		panel_logo = new JPanel();
		menuPanel.add(panel_logo, "cell 0 7,alignx center,aligny center");
		panel_logo.setOpaque(false);

		// Verificação do caminho da imagem e redimensionamento
		java.net.URL imageURL = getClass().getResource("/resources/imagens/logo.png");
		if (imageURL == null) {
			System.out.println("Imagem não encontrada. Verifique o caminho");
		} else {
			ImageIcon imageIcon = new ImageIcon(imageURL);
			// Redimensionar a imagem
			Image img = imageIcon.getImage();
			Image resizedImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Ajuste o tamanho conforme
																					// necessário
			imageIcon = new ImageIcon(resizedImg);
			JLabel imageLabel = new JLabel(imageIcon);
			panel_logo.add(imageLabel);
		}

		//new TelaPrincipalControle(this);
		TelaPrincipalControle telaPrincipalControle = new TelaPrincipalControle(this);
		FuncionarioControle funcionarioControle = new FuncionarioControle();
		funcionarioControle.setTelaPrincipal(this);
		funcionarioControle.setTelaCadastroFuncionario(telaCadastroFuncionario);
		
		
		telaPrincipalControle.setTelaVenda(telaVenda);
		
		ClienteControle clienteControle = new ClienteControle();
		clienteControle.setTelaCadastroCliente(telaCliente);

		
		ProdutoControle produtoControle = new ProdutoControle();
		produtoControle.setTelaProdutos(telaProdutos);
		produtoControle.atualizarTabela();
		produtoControle.fillPP(telaProdutos);
		
		TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();
		produtoControle.setTelaCadastrarProduto(telaCadastroProdutos);
		
		
		ItemController itemControle = new ItemController();
		itemControle.setTelaVenda(telaVenda);
		
		PromocaoControle promocaoControle = new PromocaoControle(telaPromocao);
		
		//HistoricoVendaControle historicoControle = new HistoricoVendaControle(telaHistoricoVenda);
		
		// Adiciona os painéis
		mainPanel.add(telaVenda, "TelaVenda");
		mainPanel.add(telaHistoricoVenda, "TelaHistoricoVenda");
		mainPanel.add(telaCadastroFuncionario, "TelaCadastroFuncionario");
		mainPanel.add(telaProdutos, "TelaProdutos");
		mainPanel.add(telaFornecedor, "TelaFornecedor");	
		mainPanel.add(telaPromocao, "TelaPromocao");
		mainPanel.add(telaCliente, "TelaCliente");
		mainPanel.add(telaPagamento, "TelaPagamento");

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

	public JButton getBtnClientes() {
		return btnClientes;
	}

	public JButton getBtnPromocoes() {
		return btnPromocoes;
	}
	public CardLayout getCardLayout() {
        return cardLayout;
    }

	private static void config(JButton button) {
		button.setFont(new Font("Hanken Grotesk", Font.BOLD, 20));
	}
}

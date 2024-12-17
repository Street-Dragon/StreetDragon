package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import utils.Utils;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class TelaVenda extends JPanel { // mudado para jpanel ao invés de jframe

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtQuantidade;
	private JTextField txtValor;
	private Font hkGrotesk;
	private JButton btnRemoverProduto;
	private JButton btnAdicionarProduto;
	private JButton btnRealizarCompra;
	private JButton btnLimparCarrinho;

	private JLabel lblTotal;
	private static DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public TelaVenda(TelaPrincipal telaPrincipal) { // editado para poder ser chamada no menu
		setBackground(new Color(253, 233, 235));
		hkGrotesk = Utils.loadCustomFont();
		setLayout(new MigLayout("", "[75%][25%]", "[35%][65%]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[33%][33%][33%]", "[20%][20%][20%][20%][20%]"));

		JLabel lblNewLabel = new JLabel("Código:");
		panel.add(lblNewLabel, "cell 0 0,alignx left,growy");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));

		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtCodigo, "cell 1 0,growx");
		txtCodigo.setColumns(10);

		txtNome = new JTextField();
		panel.add(txtNome, "cell 1 1,growx");
		txtNome.setEditable(false);
		txtNome.setColumns(10);
		txtNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));

		JPanel panelImagem = new JPanel();
		panel.add(panelImagem, "cell 2 1 1 4,grow");
		
		java.net.URL imageURL = getClass().getResource("/resources/imagens/default.png");
		if (imageURL == null) {
			System.out.println("Imagem não encontrada. Verifique o caminho");
		} else {
			ImageIcon imageIcon = new ImageIcon(imageURL);
			JLabel imageLabel = new JLabel(imageIcon);
			panelImagem.add(imageLabel);
		}
		

		JLabel lblNewLabel_3 = new JLabel("Imagem:");
		panel.add(lblNewLabel_3, "cell 2 0,alignx center");
		lblNewLabel_3.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));

		JLabel lblNewLabel_5 = new JLabel("Nome: ");
		panel.add(lblNewLabel_5, "cell 0 1,alignx left,growy");
		lblNewLabel_5.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));

		JLabel lblNewLabel_1 = new JLabel("Quantidade:");
		panel.add(lblNewLabel_1, "cell 0 2,alignx left,growy");
		lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));

		txtQuantidade = new JTextField();
		panel.add(txtQuantidade, "cell 1 2,growx");
		txtQuantidade.setColumns(10);
		txtQuantidade.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));

		JLabel lblNewLabel_2 = new JLabel("Valor:");
		panel.add(lblNewLabel_2, "cell 0 3,alignx left,growy");
		lblNewLabel_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));

		txtValor = new JTextField();
		txtValor.setEditable(false);
		panel.add(txtValor, "cell 1 3,growx");
		txtValor.setColumns(10);
		txtValor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));

		btnRemoverProduto = new JButton("Remover produto");
		btnRemoverProduto.setForeground(new Color(255, 255, 255));
		btnRemoverProduto.setBackground(new Color(226, 61, 40));
		btnRemoverProduto.setFont(new Font("Hanken Grotesk", Font.BOLD, 25));
		panel.add(btnRemoverProduto, "cell 0 4,grow");

		btnAdicionarProduto = new JButton("Adicionar produto");
		btnAdicionarProduto.setForeground(new Color(255, 255, 255));
		btnAdicionarProduto.setBackground(new Color(114, 148, 235));
		btnAdicionarProduto.setFont(new Font("Hanken Grotesk", Font.BOLD, 25));
		panel.add(btnAdicionarProduto, "cell 1 4,grow");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		add(panel_1, "cell 1 0,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[60%][20%][20%]"));

		lblTotal = new JLabel("Total: 123 R$");
		lblTotal.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));

		panel_1.add(lblTotal, "cell 0 0,alignx center,aligny center");

		btnRealizarCompra = new JButton("Realizar Compra");
		btnRealizarCompra.setForeground(new Color(255, 255, 255));
		btnRealizarCompra.setBackground(new Color(114, 148, 235));
		panel_1.add(btnRealizarCompra, "cell 0 1,grow");

		btnLimparCarrinho = new JButton("Limpar Carrinho");
		btnLimparCarrinho.setForeground(new Color(255, 255, 255));
		btnLimparCarrinho.setBackground(new Color(226, 61, 40));
		panel_1.add(btnLimparCarrinho, "cell 0 2,grow");

		btnLimparCarrinho.setFont(new Font("Hanken Grotesk", Font.BOLD, 25));
		btnRealizarCompra.setFont(new Font("Hanken Grotesk", Font.BOLD, 25));

		btnRealizarCompra.setIcon(Utils.carregarIcone("carrinho.png", 30, 30));
		btnLimparCarrinho.setIcon(Utils.carregarIcone("apagador.png", 30, 30));
		btnAdicionarProduto.setIcon(Utils.carregarIcone("Add.png", 30, 30));
		btnRemoverProduto.setIcon(Utils.carregarIcone("lixo.png", 30, 30));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		add(panel_2, "cell 0 1 2 1,grow");

		// Definição das colunas da tabela
//		String[] colunas = { "Nome", "Código", "Quantidade", "Valor" };
//		table = new JTable(new Object[][] {}, colunas);
//		table.setFont(hkGrotesk);
		
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Código", "Quantidade", "Valor" });

		table = new JTable(tableModel) {
			// não deixa as células serem editadas
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		

		JScrollPane scrollPane = new JScrollPane(table);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		panel_2.add(scrollPane);
		
		Utils.configTabela(table, scrollPane);
		
	}

	public JTable getTable() {
		return table;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(JLabel lblTotal) {
		this.lblTotal = lblTotal;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnRemoverProduto() {
		return btnRemoverProduto;
	}

	public JButton getBtnAdicionarProduto() {
		return btnAdicionarProduto;
	}

	public JButton getBtnRealizarCompra() {
		return btnRealizarCompra;
	}

	public JButton getBtnLimparCarrinho() {
		return btnLimparCarrinho;
	}

	public String getTxtQuantidade() {
		
		return txtQuantidade.getText();
	}

	public String getTxtCodigo() {
		return txtCodigo.getText();
	}
	public JTextField setTxtNome() {
		return txtNome;
	}
	public JTextField setTxtValor() {
		return txtValor;
	}
	public JTextField setTxtCodigo() {
		return txtCodigo;
	}
	public JTextField setTxtQuantidade() {
		return txtQuantidade;
	}
}
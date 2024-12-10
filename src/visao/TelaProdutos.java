package visao;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.miginfocom.swing.MigLayout;
import utils.Cores;
import utils.Utils;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaProdutos extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font hkGrotesk;
	private JButton btnCadastrarProd;
	private JButton btnDeletarProd;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtFieldId;
	private JTextField txtFieldNome;
	private JTextField txtFieldValor;
	private JTextField txtFieldQntEstoque;
	private JButton btnEditarProd;
	private static DefaultTableModel tableModel;
	private JTextField txtFieldPesquisa;
	private JButton btnPesquisar;
	private JComboBox comboBox;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtFieldFornecedor;
	private JTextField txtFieldCategoria;

	/**
	 * Create the panel.
	 */
	public TelaProdutos(TelaPrincipal telaPrincipal) {
		setBackground(utils.Cores.COR_ROSA_CLARO);
		hkGrotesk = Utils.loadCustomFont();
		setLayout(new MigLayout("", "[75%][25%]", "[35%][65%]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[25%][25%][25%][25%]", "[25%][25%][25%][25%]"));

		lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel, "cell 0 0,alignx left,growy");

		txtFieldId = new JTextField();
		txtFieldId.setEditable(false);
		txtFieldId.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldId, "cell 1 0,growx");
		txtFieldId.setColumns(10);

		lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_2, "cell 2 0,alignx left,growy");

		txtFieldValor = new JTextField();
		txtFieldValor.setEditable(false);
		txtFieldValor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldValor, "cell 3 0,growx");
		txtFieldValor.setColumns(10);

		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 1 0,grow");
		panel_1.setBackground(new Color(255, 255, 255));

		// Botão Cadastrar
		btnCadastrarProd = new JButton("Cadastrar");
		btnCadastrarProd.setForeground(new Color(255, 255, 255));
		btnCadastrarProd.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		btnCadastrarProd.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrarProd, "cell 0 0,grow");

		btnEditarProd = new JButton("Editar");
		btnEditarProd.setForeground(new Color(255, 255, 255));
		btnEditarProd.setBackground(new Color(255, 175, 175));
		btnEditarProd.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_1.add(btnEditarProd, "cell 0 1,grow");

		// Botão Excluir
		btnDeletarProd = new JButton("Excluir");
		btnDeletarProd.setForeground(new Color(255, 255, 255));
		btnDeletarProd.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		btnDeletarProd.setBackground(new Color(255, 0, 0));
		panel_1.add(btnDeletarProd, "cell 0 2,grow");

		btnDeletarProd.setIcon(Utils.carregarIcone("lixo.png", 30, 30));
		btnCadastrarProd.setIcon(Utils.carregarIcone("Add.png", 30, 30));
		btnEditarProd.setIcon(Utils.carregarIcone("editar.png", 30, 30));
				
						lblNewLabel_1 = new JLabel("Nome");
						lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
						panel.add(lblNewLabel_1, "cell 0 1,alignx left,growy");
				
						txtFieldNome = new JTextField();
						txtFieldNome.setEditable(false);
						txtFieldNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
						panel.add(txtFieldNome, "cell 1 1,growx");
						txtFieldNome.setColumns(10);
				
						lblNewLabel_3 = new JLabel("Quant. Estoque");
						lblNewLabel_3.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
						panel.add(lblNewLabel_3, "cell 2 1,alignx left,growy");
		
				txtFieldQntEstoque = new JTextField();
				txtFieldQntEstoque.setEditable(false);
				txtFieldQntEstoque.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
				panel.add(txtFieldQntEstoque, "cell 3 1,growx");
				txtFieldQntEstoque.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Fornecedor");
		lblNewLabel_4.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_4, "cell 0 2,alignx left");
		
		txtFieldFornecedor = new JTextField();
		txtFieldFornecedor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtFieldFornecedor.setEditable(false);
		panel.add(txtFieldFornecedor, "cell 1 2,growx");
		txtFieldFornecedor.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Categoria");
		lblNewLabel_5.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_5, "cell 2 2,alignx left");
		
		txtFieldCategoria = new JTextField();
		txtFieldCategoria.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtFieldCategoria.setEditable(false);
		panel.add(txtFieldCategoria, "cell 3 2,growx");
		txtFieldCategoria.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nome", "Id", "Valor", "Fornecedor", "Material", "Categoria"}));
		comboBox.setSelectedIndex(0);
		comboBox.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel.add(comboBox, "cell 0 3,growx");
		
		txtFieldPesquisa = new JTextField();
		txtFieldPesquisa.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel.add(txtFieldPesquisa, "cell 1 3 2 1,growx");
		txtFieldPesquisa.setColumns(10);
		
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setForeground(new Color(255, 255, 255));
		btnPesquisar.setBackground(new Color(255, 175, 175));
		btnPesquisar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel.add(btnPesquisar, "cell 3 3,growx");
		btnPesquisar.setIcon(Utils.carregarIcone("lupa.png", 30, 30));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 2 1,grow");
		scrollPane.setViewportView(table);

		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Valor", "Qnt estoque" });

		table = new JTable(tableModel) {
			// não deixa as células serem editadas
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		Utils.configTabela(table, scrollPane);

	}

	public JTextField getTxtId() {
		return txtFieldId;
	}

	public JTextField getTxtNome() {
		return txtFieldNome;
	}

	public JTextField getTxtValor() {
		return txtFieldValor;
	}

	public JTextField getTxtQnt() {
		return txtFieldQntEstoque;
	}

	public JTable getTable() {
		return table;
	}
	
	public JTable setTable () {
		
		return table;
	}

	public JButton getBtnCadastrarProd() {
		return btnCadastrarProd;
	}

	public JButton getBtnDeletarProd() {
		return btnDeletarProd;
	}

	public JButton getBtnEditProd() {
		return btnEditarProd;
	}
	public JTextField getTxtPesquisa() {
		return txtFieldPesquisa;
	}
	public String setTxtPesquisa() {
		return txtFieldPesquisa.getText();
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public JButton getBtnPesquisar() {
		return btnPesquisar;
	}
	public JTextField getTxtFornecedor() {
		return txtFieldFornecedor;
	}
	public JTextField getTxtCategoria() {
		return txtFieldCategoria;
	}
}
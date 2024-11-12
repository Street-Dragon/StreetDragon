package visao;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.miginfocom.swing.MigLayout;
import utils.Utils;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtValor;
	private JTextField txtQuantEstoque;

	/**
	 * Create the panel.
	 */
	public TelaProdutos(TelaPrincipal telaPrincipal) {
		setBackground(new Color(255, 255, 255));
		hkGrotesk = Utils.loadCustomFont();
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow]"));

		lblNewLabel = new JLabel("  Id");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel, "cell 0 0,alignx left");

		txtId = new JTextField();
		txtId.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtId, "cell 1 0,growx");
		txtId.setColumns(10);

		lblNewLabel_2 = new JLabel("  Valor");
		lblNewLabel_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_2, "cell 3 0,alignx left,growy");

		txtValor = new JTextField();
		txtValor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtValor, "cell 4 0,growx");
		txtValor.setColumns(10);

		lblNewLabel_1 = new JLabel("  Nome");
		lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1, "cell 0 1,alignx left");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtNome, "cell 1 1,growx");
		txtNome.setColumns(10);

		lblNewLabel_3 = new JLabel("  Quant. Estoque");
		lblNewLabel_3.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_3, "cell 3 1,alignx left");

		txtQuantEstoque = new JTextField();
		txtQuantEstoque.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtQuantEstoque, "cell 4 1,growx");
		txtQuantEstoque.setColumns(10);

		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 3 0,grow");
		// panel_1.setBorder(new LineBorder(new Color(246, 233, 233), 5));
		panel_1.setBackground(new Color(255, 255, 255));

		// Botão Cadastrar
		btnCadastrarProd = new JButton("Cadastrar");
		btnCadastrarProd.setForeground(new Color(255, 255, 255));
		btnCadastrarProd.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		btnCadastrarProd.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrarProd, "cell 0 0,grow");

		// Botão Excluir
		btnDeletarProd = new JButton("Excluir");
		btnDeletarProd.setForeground(new Color(255, 255, 255));
		btnDeletarProd.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		btnDeletarProd.setBackground(new Color(255, 0, 0));
		panel_1.add(btnDeletarProd, "cell 0 2,grow");

		btnDeletarProd.setIcon(Utils.carregarIcone("lixo.png", 30, 30));
		btnCadastrarProd.setIcon(Utils.carregarIcone("editar.png", 30, 30));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 4 3,grow");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Valor", "Qnt estoque" }));
		scrollPane.setViewportView(table);

		Utils.configTabela(table, scrollPane);
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JButton getBtnCadastrarProd() {
		return btnCadastrarProd;
	}

	public JButton getBtnDeletarProd() {
		return btnDeletarProd;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}

	public void setTxtQuantEstoque(JTextField txtQuantEstoque) {
		this.txtQuantEstoque = txtQuantEstoque;
	}

	public JTable geTable() {
		return table;
	}
}
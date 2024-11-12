package visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.miginfocom.swing.MigLayout;
import utils.Utils;

public class TelaFornecedor extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font hkGrotesk;
	private JButton btnCadastrarfFor;
	private JButton btnDeletarFor;
	private JTable table;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtCnpj;
	private JTextField txtRua;
	private JTextField txtCep;
	private JLabel lblNewLabel;
	private JTextField textNome;
	private JButton btnEditarFor;

	/**
	 * Create the panel.
	 */
	public TelaFornecedor(TelaPrincipal telaPrincipal) {
		setBackground(new Color(255, 255, 255));
		hkGrotesk = Utils.loadCustomFont();
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow]"));
		
				lblNewLabel_2 = new JLabel("Nome");
				lblNewLabel_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
				panel.add(lblNewLabel_2, "cell 0 0,alignx left,growy");
		
		textNome = new JTextField();
		textNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		textNome.setColumns(10);
		panel.add(textNome, "cell 1 0,growx");
		
		lblNewLabel = new JLabel("Rua");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel, "cell 3 0,alignx left,growy");

		txtRua = new JTextField();
		txtRua.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtRua, "cell 4 0,growx");
		txtRua.setColumns(10);

		lblNewLabel_1 = new JLabel("CNPJ");
		lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1, "cell 0 1,alignx left,growy");

		txtCnpj = new JTextField();
		txtCnpj.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtCnpj, "cell 1 1,growx");
		txtCnpj.setColumns(10);

		lblNewLabel_3 = new JLabel("CEP");
		lblNewLabel_3.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_3, "cell 3 1,alignx left,growy");

		txtCep = new JTextField();
		txtCep.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtCep, "cell 4 1,growx");
		txtCep.setColumns(10);

		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 3 0,grow");
		// panel_1.setBorder(new LineBorder(new Color(246, 233, 233), 5));
		panel_1.setBackground(new Color(255, 255, 255));

		// Botão Cadastrar
		btnCadastrarfFor = new JButton("Cadastrar");
		btnCadastrarfFor.setForeground(new Color(255, 255, 255));
		btnCadastrarfFor.setFont(hkGrotesk);
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][][grow][grow][grow]"));
		btnCadastrarfFor.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrarfFor, "cell 0 1,grow");
		
		btnEditarFor = new JButton("Editar Fornecedor");
		btnEditarFor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 20));
		btnEditarFor.setForeground(new Color(255, 255, 255));
		btnEditarFor.setBackground(new Color(255, 149, 149));
		panel_1.add(btnEditarFor, "cell 0 5,grow");

		// Botão Excluir
		btnDeletarFor = new JButton("Excluir");
		btnDeletarFor.setForeground(new Color(255, 255, 255));
		btnDeletarFor.setFont(hkGrotesk);
		btnDeletarFor.setBackground(new Color(255, 0, 0));
		panel_1.add(btnDeletarFor, "cell 0 5,grow");

		btnDeletarFor.setIcon(Utils.carregarIcone("lixo.png", 30, 30));
		btnCadastrarfFor.setIcon(Utils.carregarIcone("editar.png", 30, 30));
		btnEditarFor.setIcon(Utils.carregarIcone("editar.png", 30 ,30));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 4 3,grow");

		table = new JTable();
		table.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "CNPJ", "Rua" }));
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(255, 233, 233));
		scrollPane.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		header.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
	}
	
	//bocetinha
	public JTextField getTxtNome() {
		return txtCnpj;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtCnpj = txtNome;
	}

	public JButton getBtnCadastrarProd() {
		return btnCadastrarfFor;
	}

	public JButton getBtnDeletarProd() {
		return btnDeletarFor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtRua = txtValor;
	}

	public void setTxtQuantEstoque(JTextField txtQuantEstoque) {
		this.txtCep = txtQuantEstoque;
	}

	public JTable geTable() {
		return table;
	}
}

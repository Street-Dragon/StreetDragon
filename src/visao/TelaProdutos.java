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
	private JTextField txtFieldId;
	private JTextField txtFieldNome;
	private JTextField txtFieldValor;
	private JTextField txtFieldQntEstoque;
	private static DefaultTableModel tableModel;
	private JButton btnEditarProd;

	
	public JButton getBtnCadastrarProd() {
		return btnCadastrarProd;
	}

	public JButton getBtnDeletarProd() {
		return btnDeletarProd;
	}
	
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

		lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel, "cell 0 0,alignx left");

		txtFieldId = new JTextField();
		txtFieldId.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldId, "cell 1 0,growx");
		txtFieldId.setColumns(10);

		lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_2, "cell 3 0,alignx left,growy");

		txtFieldValor = new JTextField();
		txtFieldValor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldValor, "cell 4 0,growx");
		txtFieldValor.setColumns(10);

		lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1, "cell 0 1,alignx left");

		txtFieldNome = new JTextField();
		txtFieldNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldNome, "cell 1 1,growx");
		txtFieldNome.setColumns(10);

		lblNewLabel_3 = new JLabel("Quant. Estoque");
		lblNewLabel_3.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_3, "cell 3 1,alignx left");

		txtFieldQntEstoque = new JTextField();
		txtFieldQntEstoque.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldQntEstoque, "cell 4 1,growx");
		txtFieldQntEstoque.setColumns(10);

		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 3 0,grow");
		// panel_1.setBorder(new LineBorder(new Color(246, 233, 233), 5));
		panel_1.setBackground(new Color(255, 255, 255));

		// Botão Cadastrar
		btnCadastrarProd = new JButton("Cadastrar");
		btnCadastrarProd.setForeground(new Color(255, 255, 255));
		btnCadastrarProd.setFont(hkGrotesk);
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		btnCadastrarProd.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrarProd, "cell 0 0,grow");
		
		btnEditarProd = new JButton("Editar");
		panel_1.add(btnEditarProd, "cell 0 1,grow");

		// Botão Excluir
		btnDeletarProd = new JButton("Excluir");
		btnDeletarProd.setForeground(new Color(255, 255, 255));
		btnDeletarProd.setFont(hkGrotesk);
		btnDeletarProd.setBackground(new Color(255, 0, 0));
		panel_1.add(btnDeletarProd, "cell 0 2,grow");

		btnDeletarProd.setIcon(Utils.carregarIcone("lixo.png", 30, 30));
		btnCadastrarProd.setIcon(Utils.carregarIcone("editar.png", 30, 30));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 4 3,grow");

		table = new JTable();
		table.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Valor", "Qnt estoque" }));
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(255, 233, 233));
		scrollPane.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		header.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
	}

	public JTextField getTxtId() {
		return txtFieldId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtFieldId = txtId;
	}

	public JTextField getTxtNome() {
		return txtFieldNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtFieldNome = txtNome;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtFieldValor = txtValor;
	}

	public void setTxtQuantEstoque(JTextField txtQuantEstoque) {
		this.txtFieldQntEstoque = txtQuantEstoque;
	}

	public JTable getTable() {
		return table;
	}
}
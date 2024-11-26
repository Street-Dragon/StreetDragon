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

	/**
	 * Create the panel.
	 */
	public TelaProdutos(TelaPrincipal telaPrincipal) {
		setBackground(utils.Cores.COR_ROSA_CLARO);
		hkGrotesk = Utils.loadCustomFont();
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel, "cell 0 0,alignx left");

		txtFieldId = new JTextField();
		txtFieldId.setEditable(false);
		txtFieldId.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldId, "cell 1 0,growx");
		txtFieldId.setColumns(10);

		lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_2, "cell 3 0,alignx left,growy");

		txtFieldValor = new JTextField();
		txtFieldValor.setEditable(false);
		txtFieldValor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldValor, "cell 4 0,growx");
		txtFieldValor.setColumns(10);

		lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1, "cell 0 1,alignx left");

		txtFieldNome = new JTextField();
		txtFieldNome.setEditable(false);
		txtFieldNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldNome, "cell 1 1,growx");
		txtFieldNome.setColumns(10);

		lblNewLabel_3 = new JLabel("Quant. Estoque");
		lblNewLabel_3.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_3, "cell 3 1,alignx left");

		txtFieldQntEstoque = new JTextField();
		txtFieldQntEstoque.setEditable(false);
		txtFieldQntEstoque.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtFieldQntEstoque, "cell 4 1,growx");
		txtFieldQntEstoque.setColumns(10);

		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 3 0,grow");
		panel_1.setBorder(new CompoundBorder(null, new LineBorder(Cores.COR_ROSA_CLARO, 1)));
		panel_1.setBackground(new Color(255, 255, 255));

		// Botão Cadastrar
		btnCadastrarProd = new JButton("Cadastrar");
		btnCadastrarProd.setForeground(new Color(255, 255, 255));
		btnCadastrarProd.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		btnCadastrarProd.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrarProd, "cell 0 0,grow");

		btnEditarProd = new JButton("Editar");
		panel_1.add(btnEditarProd, "cell 0 1,grow");

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

	public JButton getBtnCadastrarProd() {
		return btnCadastrarProd;
	}

	public JButton getBtnDeletarProd() {
		return btnDeletarProd;
	}

	public JButton getBtnEditProd() {
		return btnEditarProd;
	}
}
package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import utils.Cores;
import utils.Utils;

public class TelaCliente extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtCompras;
	private JTextField txtEmail;
	private JTextField txtCpf;
	private JButton btnCadastrar;
	private JTable table;
	private static DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public TelaCliente(TelaPrincipal telaPrincipal) {

	

		setBounds(100, 100, 926, 526);
		setBackground(Cores.COR_ROSA_CLARO);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		
		Border borda = new LineBorder(Cores.COR_ROSA_CLARO, 1);
		Utils.loadCustomFont();
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[::30%,grow][grow]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255)), null));
		add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[grow][grow]"));
		
				JLabel lblnome = new JLabel("Nome");
				lblnome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
				panel.add(lblnome, "cell 0 0,alignx center,aligny center");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtNome, "cell 1 0,growx,aligny center");
		txtNome.setColumns(10);

		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblemail, "cell 3 0,alignx left");

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtEmail, "cell 4 0,growx");
		txtEmail.setColumns(10);
		
				JLabel lblNCompras = new JLabel("N. Compras");
				lblNCompras.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
				panel.add(lblNCompras, "cell 0 1,alignx left");

		txtCompras = new JTextField();
		txtCompras.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtCompras.setColumns(10);
		panel.add(txtCompras, "cell 1 1,growx,aligny center");
		
				JLabel lblcpf = new JLabel("Cpf");
				lblcpf.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
				panel.add(lblcpf, "cell 3 1,alignx center,aligny center");

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtCpf, "cell 4 1,growx");
		txtCpf.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(null, borda));
		add(panel_1, "cell 3 0,grow");
		panel_1.setBackground(new Color(255, 255, 255));
		// Botão Cadastrar
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		btnCadastrar.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrar, "cell 0 0,grow");
		// Botão Ecluir
		JButton btnDeletar = new JButton("Excluir Funcionario");
		btnDeletar.setForeground(new Color(255, 255, 255));
		btnDeletar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		
		
		
		
		btnDeletar.setBackground(new Color(255, 0, 0));
		panel_1.add(btnDeletar, "cell 0 1,grow");

		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 4 1,grow");

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Nome");
		tableModel.addColumn("Email");
		tableModel.addColumn("Cpf");
		tableModel.addColumn("N. Compras");

		table = new JTable(tableModel) {
		    // não deixa as células serem editadas
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};
		
		Utils.configTabela(table, scrollPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						int id = (int) tableModel.getValueAt(selectedRow, 0);

					}
				}
			}
		});
	}
}

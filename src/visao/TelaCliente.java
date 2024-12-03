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
	private JTextField txtEmail;
	private JTextField txtCpf;
	private JTextField txtTelefone;
	private JButton btnCadastrar;
	private JButton btnDeletar;
	//private JButton btnEditar;
	private JTable table;
	private static DefaultTableModel tableModel;
	
	public JTextField getTextFieldNome() {
		return txtNome;
	}
	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}
	public JTextField gettxtTelefone() {
		return txtTelefone;
	}
	public void settxtTelefone(JTextField txtTelefone) {
		this.txtTelefone = txtTelefone;
	}
	public JTextField getTextFieldEmail() {
		return txtEmail;
	}
	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}
	public JTextField getTextFieldCpf() {
		return txtCpf;
	}
	public void setTxtCpf(JTextField txtCpf) {
		this.txtCpf = txtCpf;
	}
	
	public JTextField getTextFieldTelefone() {
		return txtTelefone;
	}
	public void setTxtTelefone(JTextField txtTelefone) {
		this.txtTelefone = txtTelefone;
	}
	
	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}
	public void setBtnCadastrar(JButton btnCadastrar) {
		this.btnCadastrar = btnCadastrar;
	}
	public JButton getBtnDeletar() {
		return btnDeletar;
	}
	public void setBtnDeletar(JButton btnDeletar) {
		this.btnDeletar = btnDeletar;
	}
	/*
	public JButton getBtnEditar() {
		return btnEditar;
	}
	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}*/
	
	public JTable getTable() {
		return table;
	}
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	public String getTxtNome() {
		return txtNome.getText();
	}
	public String getTxtCpf() {
		return txtCpf.getText();
	}
	public String getTxtEmail() {
		return txtEmail.getText();
	}
	public String getTxtTelefone() {
		return txtTelefone.getText();
	}

	public TelaCliente(TelaPrincipal telaPrincipal) {

		setBounds(100, 100, 926, 526);
		setBackground(Cores.COR_ROSA_CLARO);

		Border borda = new LineBorder(Cores.COR_ROSA_CLARO, 1);
		Utils.loadCustomFont();
		setLayout(new MigLayout("", "[75%][25%]", "[35%][65%]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[25%][25%][25%][25%]", "[50%][50%]"));
		
		JLabel lblnome = new JLabel("Nome");
		lblnome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblnome, "cell 0 0,alignx left,growy");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtNome, "cell 1 0,growx");
		txtNome.setColumns(10);

		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblemail, "cell 2 0,alignx left,growy");

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtEmail, "cell 3 0,growx");
		txtEmail.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblTelefone, "cell 0 1,alignx left,growy");

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtTelefone.setColumns(10);
		panel.add(txtTelefone, "cell 1 1,growx");
		
		JLabel lblcpf = new JLabel("Cpf");
		lblcpf.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblcpf, "cell 2 1,alignx left,growy");

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtCpf, "cell 3 1,growx");
		txtCpf.setColumns(10);

		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 1 0,grow");
		panel_1.setBackground(new Color(255, 255, 255));
		// Botão Cadastrar
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_1.setLayout(new MigLayout("", "[grow]", "[50%][50%]"));
		btnCadastrar.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrar, "cell 0 0,grow");
		// Botão Ecluir
		btnDeletar = new JButton("Excluir");
		btnDeletar.setForeground(new Color(255, 255, 255));
		btnDeletar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));	

		btnDeletar.setBackground(new Color(255, 0, 0));
		
		
		btnCadastrar.setIcon(Utils.carregarIcone("Add.png", 30, 30));
		btnDeletar.setIcon(Utils.carregarIcone("lixo.png", 30, 30));
		
		
		
		panel_1.add(btnDeletar, "cell 0 1,grow");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 2 1,grow");

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Nome");
		tableModel.addColumn("Email");
		tableModel.addColumn("Número");
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
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) { // Verifica se é um clique simples
		            int selectedRow = table.getSelectedRow(); // Obtém a linha selecionada
		            if (selectedRow != -1 && tableModel.getRowCount() > 0) {
		                String cpf = (String) tableModel.getValueAt(selectedRow, 3);
		                System.out.println("CPF selecionado: " + cpf);
		            }
		        }
		    }
		});
	}
	
	public void limparCampos() {
		txtNome.setText("");
		txtCpf.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
	}
	
}

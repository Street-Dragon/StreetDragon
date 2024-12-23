package visao;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import utils.Cores;
import utils.Utils;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import modelo.entidade.pessoa.funcionario.Funcionario;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;

public class TelaCadastroFuncionario extends JPanel { // jpanel ao invés de jframe

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Font hkGrotesk;
	private JTextField textId;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textEmail;
	private JTextField textTelefone;
	private JButton btnCadastrarFuncionario;
	private JButton btnDeletarFuncionario;
	private JButton btnEditarFuncionario;
	private JCheckBox chckbxVerSenha;
	private JCheckBox chckbxAdm;
	private JTable table;
	private static DefaultTableModel tableModel;
	private JPasswordField textSenha;

	public char[] getPasswordField() {
		return textSenha.getPassword();
	}

	public JButton getBtnCadastrarFuncionario() {
		return btnCadastrarFuncionario;
	}
	
	public JButton getBtnEditarFuncionario() {
		return btnEditarFuncionario;
	}

	public boolean getChckbxAdm() {
		return chckbxAdm.isSelected();
	}

	public boolean getChckbxSenha() {
		return chckbxVerSenha.isSelected();
	}

	// limpa campos
	public void setTextNome(String nome) {
		textNome.setText(nome);
	}

	public void setPasswordField(String senha) {
		textSenha.setText(senha);
	}

	public void setTextCpf(String cpf) {
		textCpf.setText(cpf);
	}

	public JButton getBtnDeletarFuncionario() {
		return btnDeletarFuncionario;
	}

	public void setChckbxAdm(boolean isAdm) {
		chckbxAdm.setSelected(isAdm);
	}

	public void setChckbxSenha(boolean verSenha) {
		chckbxVerSenha.setSelected(verSenha);

	}

	public void setTextEmail(String email) {
		textEmail.setText(email);
	}

	public void setTextTelefone(String telefone) {
		textTelefone.setText(telefone);
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroFuncionario(TelaPrincipal telaPrincipal) { // telaprincipal

		setBounds(100, 100, 926, 526);
		setBackground(Cores.COR_ROSA_CLARO);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		
		Border borda = new LineBorder(Cores.COR_ROSA_CLARO, 1);
		hkGrotesk = Utils.loadCustomFont();
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255)), null));
		add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblId, "cell 0 0,alignx center,growy");

		textId = new JTextField();
		textId.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(textId, "cell 1 0,growx,aligny center");
		textId.setEditable(false);
		textId.setColumns(10);

		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblemail, "cell 3 0,alignx left");

		textEmail = new JTextField();
		textEmail.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(textEmail, "cell 4 0,growx");
		textEmail.setColumns(10);

		JLabel lblnome = new JLabel("Nome");
		lblnome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblnome, "cell 0 1,alignx center,aligny center");

		textNome = new JTextField();
		textNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		textNome.setColumns(10);
		panel.add(textNome, "cell 1 1,growx,aligny center");

		JLabel lbltelefone = new JLabel("Telefone");
		lbltelefone.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lbltelefone, "cell 3 1,alignx left");

		textTelefone = new JTextField();
		textTelefone.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(textTelefone, "cell 4 1,growx");
		textTelefone.setColumns(10);

		JLabel lblcpf = new JLabel("Cpf");
		lblcpf.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblcpf, "cell 0 2,alignx center,aligny center");

		textCpf = new JTextField();
		textCpf.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		textCpf.setColumns(10);
		panel.add(textCpf, "cell 1 2,growx,aligny center");

		JLabel lblsenha = new JLabel("Senha");
		lblsenha.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblsenha, "cell 3 2,alignx left,aligny center");

		chckbxVerSenha = new JCheckBox("Ver senha");
		chckbxVerSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxVerSenha.isSelected()) {
					textSenha.setEchoChar((char) 0);
				} else {
					textSenha.setEchoChar('•');
				}

			}
		});

		textSenha = new JPasswordField();
		textSenha.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(textSenha, "cell 4 2,growx");
		chckbxVerSenha.setBackground(new Color(255, 255, 255));
		chckbxVerSenha.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(chckbxVerSenha, "cell 4 3 2 1");

		chckbxAdm = new JCheckBox("Adminstrador");
		chckbxAdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean adm = false;

				if (chckbxAdm.isSelected()) {
					// Usuário possui poderes
					adm = true;
					chckbxAdm.setBackground(Color.PINK);
				} else {
					// Usuário não possui poderes
					adm = false;
					chckbxAdm.setBackground(Color.WHITE);
				}

			}
		});
		chckbxAdm.setBackground(new Color(255, 255, 255));
		chckbxAdm.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(chckbxAdm, "cell 1 3 2 1,alignx left");

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(null, borda));
		add(panel_1, "cell 3 0,grow");
		panel_1.setBackground(new Color(255, 255, 255));
		// Botão Cadastrar
		btnCadastrarFuncionario = new JButton("Cadastrar");
		btnCadastrarFuncionario.setForeground(new Color(255, 255, 255));
		btnCadastrarFuncionario.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		btnCadastrarFuncionario.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrarFuncionario, "cell 0 0,grow");
		// Botão Ecluir
		btnDeletarFuncionario = new JButton("Excluir Funcionario");
		btnDeletarFuncionario.setForeground(new Color(255, 255, 255));
		btnDeletarFuncionario.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));	

		btnDeletarFuncionario.setBackground(new Color(255, 0, 0));
		panel_1.add(btnDeletarFuncionario, "cell 0 2,grow");
		
		
		// Botâo Editar
		btnEditarFuncionario = new JButton("Editar Funcionario");
		btnEditarFuncionario.setForeground(new Color(255, 255, 255));
		btnEditarFuncionario.setFont(hkGrotesk);
		btnEditarFuncionario.setBackground(Color.PINK);
		panel_1.add(btnEditarFuncionario, "cell 0 1,grow");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 4 3,grow");

		tableModel = new DefaultTableModel();
		tableModel.addColumn("CPF");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Senha");
		tableModel.addColumn("Email");
		tableModel.addColumn("Telefone");
		tableModel.addColumn("Administrador");

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
	
	
	public void limparCampos() {
		textId.setText("");
		textNome.setText("");
		textCpf.setText("");
		textEmail.setText("");
		textTelefone.setText("");
		textSenha.setText("");
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JTextField getTextFieldId() {
		return textId;
	}

	public JTextField getTextFieldNome() {
		return textNome;
	}

	public JTextField getTextFieldCpf() {
		return textCpf;
	}

	public JTextField getTextFieldEmail() {
		return textEmail;
	}

	public JTextField getTextFieldTelefone() {
		return textTelefone;
	}

	public JPasswordField getTextFieldSenha() {
		return textSenha;
	}

	public String getTextId() {
		return textId.getText();
	}

	public String getTextNome() {
		return textNome.getText();
	}

	public String getTextCpf() {
		return textCpf.getText();
	}

	public String getTextEmail() {
		return textEmail.getText();
	}

	public String getTextTelefone() {
		return textTelefone.getText();
	}

}
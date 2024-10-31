package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import utils.Utils;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.entidade.pessoa.funcionario.Funcionario;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;

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
	private JCheckBox chckbxVerSenha;
	private JCheckBox chckbxAdm;
	private JTable table;
	private static DefaultTableModel tableModel;
	private JPasswordField textSenha;

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

	public char[] getPasswordField() {
		return textSenha.getPassword();
	}

	public JButton getBtnCadastrarFuncionario() {
		return btnCadastrarFuncionario;
	}

	public boolean getChckbxAdm() {
		return chckbxAdm.isSelected();
	}

	// main apagado

	/**
	 * Create the frame.
	 */
	public TelaCadastroFuncionario(TelaPrincipal telaPrincipal) { // telaprincipal
		// setTitle("Cadastrar Funcionario");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// contentPane = new JPanel();
		// setContentPane(contentPane);

		// apagadas todas as linhas acima e os "contentPane.add" trocados para apenas
		// "add"

		setBounds(100, 100, 926, 526);
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		hkGrotesk = Utils.loadCustomFont();
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		JLabel lblId = new JLabel("Id");
		lblId.setFont(hkGrotesk);
		panel.add(lblId, "cell 0 0,alignx center,growy");
		lblId.setFont(hkGrotesk);

		textId = new JTextField();
		textId.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel.add(textId, "cell 1 0,growx,aligny center");
		textId.setEditable(false);
		textId.setColumns(10);

		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(hkGrotesk);
		panel.add(lblemail, "cell 3 0,alignx left");

		textEmail = new JTextField();
		textEmail.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel.add(textEmail, "cell 4 0,growx");
		textEmail.setColumns(10);

		JLabel lblnome = new JLabel("Nome");
		lblnome.setFont(hkGrotesk);
		panel.add(lblnome, "cell 0 1,alignx center,aligny center");

		textNome = new JTextField();
		textNome.setFont(new Font("Dialog", Font.PLAIN, 15));
		textNome.setColumns(10);
		panel.add(textNome, "cell 1 1,growx,aligny center");

		JLabel lbltelefone = new JLabel("Telefone");
		lbltelefone.setFont(hkGrotesk);
		panel.add(lbltelefone, "cell 3 1,alignx left");

		textTelefone = new JTextField();
		textTelefone.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel.add(textTelefone, "cell 4 1,growx");
		textTelefone.setColumns(10);

		JLabel lblcpf = new JLabel("Cpf");
		lblcpf.setFont(hkGrotesk);
		panel.add(lblcpf, "cell 0 2,alignx center,aligny center");

		textCpf = new JTextField();
		textCpf.setFont(new Font("Dialog", Font.PLAIN, 15));
		textCpf.setColumns(10);
		panel.add(textCpf, "cell 1 2,growx,aligny center");

		JLabel lblsenha = new JLabel("Senha");
		lblsenha.setFont(hkGrotesk);
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
		textSenha.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel.add(textSenha, "cell 4 2,growx");
		chckbxVerSenha.setBackground(new Color(255, 255, 255));
		chckbxVerSenha.setFont(new Font("Dialog", Font.PLAIN, 15));
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
		chckbxAdm.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel.add(chckbxAdm, "cell 1 3 2 1,alignx left");

		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 3 0,grow");
		panel_1.setBorder(new LineBorder(new Color(246, 233, 233), 5));
		panel_1.setBackground(new Color(255, 255, 255));
		// Botão Cadastrar
		btnCadastrarFuncionario = new JButton("Cadastrar");
		btnCadastrarFuncionario.setForeground(new Color(255, 255, 255));
		btnCadastrarFuncionario.setFont(hkGrotesk);
		/*
		 * btnCadastrarFuncionario.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) {
		 * 
		 * } });
		 */
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		btnCadastrarFuncionario.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrarFuncionario, "cell 0 0,grow");
		// Botão Ecluir
		JButton btnDeletarFuncionario = new JButton("Excluir Funcionario");
		btnDeletarFuncionario.setForeground(new Color(255, 255, 255));
		btnDeletarFuncionario.setFont(hkGrotesk);
		btnDeletarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeletarFuncionario.setBackground(new Color(255, 0, 0));
		panel_1.add(btnDeletarFuncionario, "cell 0 2,grow");
		// Botâo Editar
		JButton btnEditarFuncionario = new JButton("Editar Funcionario");
		btnEditarFuncionario.setForeground(new Color(255, 255, 255));
		btnEditarFuncionario.setFont(hkGrotesk);
		btnEditarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnEditarFuncionario.setBackground(Color.PINK);
		panel_1.add(btnEditarFuncionario, "cell 0 1,grow");

		JScrollPane scrollPane = new JScrollPane();

		add(scrollPane, "cell 0 1 4 3,grow");

		tableModel = new DefaultTableModel();
        tableModel.addColumn("CPF");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Senha");
        tableModel.addColumn("Administrador");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefone");
        
		table = new JTable(tableModel);

		scrollPane.setViewportView(table);


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

}
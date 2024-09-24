package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	 private Font hkGrotesk; 
	 private JTextField textId;
	 private JTextField textNome;
	 private JTextField textCpf;
	 private JTextField textEmail;
	 private JTextField textTelefone;
	 private JPasswordField passwordField;
	 private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroFuncionario frame = new TelaCadastroFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JTextField getTextId() {
		return textId;
	}

	public void setTextId(JTextField textId) {
		this.textId = textId;
	}

	public JTextField getTextNome() {
		return textNome;
	}

	public void setTextNome(JTextField textNome) {
		this.textNome = textNome;
	}

	public JTextField getTextCpf() {
		return textCpf;
	}

	public void setTextCpf(JTextField textCpf) {
		this.textCpf = textCpf;
	}

	public JTextField getTextEmail() {
		return textEmail;
	}

	public void setTextEmail(JTextField textEmail) {
		this.textEmail = textEmail;
	}

	public JTextField getTextTelefone() {
		return textTelefone;
	}

	public void setTextTelefone(JTextField textTelefone) {
		this.textTelefone = textTelefone;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246, 233, 233));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		loadCustomFont();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][]", "[grow 80][grow 20][grow 40][grow]"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(250, 187, 187)));
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, "cell 0 0 5 2,grow");
		panel.setLayout(new MigLayout("", "[grow 20][grow 20][grow 20][grow][grow]", "[grow][grow][grow][grow]"));
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblId, "cell 0 0,alignx center,growy");
		
		textId = new JTextField();
		panel.add(textId, "cell 1 0,alignx left,aligny center");
		textId.setColumns(10);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblemail, "cell 2 0,alignx left");
		
		textEmail = new JTextField();
		panel.add(textEmail, "cell 3 0,alignx left");
		textEmail.setColumns(10);
		
		JLabel lblnome = new JLabel("Nome");
		lblnome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblnome, "cell 0 1,alignx center,aligny center");
		
		textNome = new JTextField();
		textNome.setColumns(10);
		panel.add(textNome, "cell 1 1,alignx left,aligny center");
		
		JLabel lbltelefone = new JLabel("Telefone");
		lbltelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lbltelefone, "cell 2 1,alignx left");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(246, 233, 233), 5));
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, "cell 4 0 1 4,grow");
		panel_1.setLayout(null);
		//Botão Cadastrar
		JButton btnNewButton = new JButton("Cadasrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBackground(new Color(114, 148, 235));
		btnNewButton.setBounds(10, 11, 143, 28);
		panel_1.add(btnNewButton);
		//Botão Ecluir
		JButton btnEditarFuncionario = new JButton("Excluir Funcionario");
		btnEditarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarFuncionario.setBackground(new Color(250, 187, 187));
		btnEditarFuncionario.setBounds(10, 131, 143, 28);
		panel_1.add(btnEditarFuncionario);
		//Botâo Editar
		JButton btnEditarFuncionrio = new JButton("Editar Funcionrio");
		btnEditarFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarFuncionrio.setBackground(new Color(226, 61, 40));
		btnEditarFuncionrio.setBounds(10, 67, 143, 28);
		panel_1.add(btnEditarFuncionrio);
		
		textTelefone = new JTextField();
		panel.add(textTelefone, "cell 3 1,alignx left");
		textTelefone.setColumns(10);
		
		JLabel lblcpf = new JLabel("Cpf");
		lblcpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblcpf, "cell 0 2,alignx center,aligny center");
		
		textCpf = new JTextField();
		textCpf.setColumns(10);
		panel.add(textCpf, "cell 1 2,alignx left,aligny center");
		
		JLabel lblsenha = new JLabel("Senha");
		lblsenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblsenha, "cell 2 2,alignx left,aligny center");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel.add(panel_2, "cell 3 2,grow");
		panel_2.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(0, 23, 115, 20);
		panel_2.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Adminstrador");
		chckbxNewCheckBox.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(chckbxNewCheckBox, "cell 0 3,alignx center");
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Ver senha");
		chckbxNewCheckBox_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(chckbxNewCheckBox_1, "cell 2 3");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 2 4 2,grow");
		//tabela
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Cpf", "Administrador"
			}
		));
		scrollPane.setViewportView(table);
	}
	 private void loadCustomFont() {
	        try {
	    		Font hkGrotesk = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fontes/HankenGrotesk-VariableFont_wght.ttf")).deriveFont(20f);
	    		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            ge.registerFont(hkGrotesk);
	    	} catch (FontFormatException e) {
	            System.err.println("Formato de fonte inválido: " + e.getMessage());
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.err.println("Erro ao ler o arquivo da fonte: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
}
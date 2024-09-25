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
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;

public class TelaCadastroFuncionario extends JFrame {

	 private JPanel contentPane;
	 private Font hkGrotesk; 
	 private JTextField textId;
	 private JTextField textNome;
	 private JTextField textCpf;
	 private JTextField textEmail;
	 private JTextField textTelefone;
	 private JPasswordField passwordField;
	 private JButton btnCadastrarFuncionario;
	 private JCheckBox chckbxVerSenha;
	 private JCheckBox chckbxAdm;
	 private JTable table;
	
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
		return passwordField.getPassword();
	}
		
	public JButton getBtnCadastrarFuncionario() {
		return btnCadastrarFuncionario;
	}
	public boolean getChckbxAdm() {
		return chckbxAdm.isSelected();
	}
	
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

	/**
	 * Create the frame.
	 */
	public TelaCadastroFuncionario() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246, 233, 233));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//loadCustomFont();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][]", "[grow][grow][grow][grow]"));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));
		
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
		panel.add(panel_2, "cell 3 2");
		panel_2.setLayout(new MigLayout("", "[115px]", "[20px]"));
		
		passwordField = new JPasswordField();
		panel_2.add(passwordField, "cell 0 0,grow");
		
		chckbxVerSenha = new JCheckBox("Ver senha");
		chckbxVerSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbxVerSenha.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('•');
                }
				
			}
		});
		chckbxVerSenha.setBackground(new Color(255, 255, 255));
		chckbxVerSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(chckbxVerSenha, "cell 3 3");
		
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
		chckbxAdm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(chckbxAdm, "cell 1 3,alignx center");
				
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 3 0,grow");
		panel_1.setBorder(new LineBorder(new Color(246, 233, 233), 5));
		panel_1.setBackground(new Color(255, 255, 255));
		//Botão Cadastrar
		btnCadastrarFuncionario = new JButton("Cadastrar");
		btnCadastrarFuncionario.setForeground(new Color(255, 255, 255));
		btnCadastrarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		btnCadastrarFuncionario.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrarFuncionario, "cell 0 0,grow");
		//Botão Ecluir
		JButton btnDeletarFuncionario = new JButton("Excluir Funcionario");
		btnDeletarFuncionario.setForeground(new Color(255, 255, 255));
		btnDeletarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeletarFuncionario.setBackground(new Color(255, 0, 0));
		panel_1.add(btnDeletarFuncionario, "cell 0 2,grow");
		//Botâo Editar
		JButton btnEditarFuncionario = new JButton("Editar Funcionrio");
		btnEditarFuncionario.setForeground(new Color(255, 255, 255));
		btnEditarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnEditarFuncionario.setBackground(Color.PINK);
		panel_1.add(btnEditarFuncionario, "cell 0 1,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 4 3,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Cpf", "Administrador"
			}
		));
		scrollPane.setViewportView(table);
	}
	/*
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


*/


}
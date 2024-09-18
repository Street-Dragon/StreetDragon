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

public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	 private Font hkGrotesk; 
	 private JTextField textid;
	 private JTextField textnome;
	 private JTextField textcpf;
	

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
		setBounds(100, 100, 718, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		loadCustomFont();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow 60][grow][grow][grow][]", "[grow 80][grow 20][grow][grow]"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(250, 187, 187)));
		panel.setBackground(new Color(246, 233, 233));
		contentPane.add(panel, "cell 0 0 5 2,grow");
		panel.setLayout(new MigLayout("", "[grow 20][grow][grow][grow]", "[grow][grow][grow][grow]"));
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblId, "cell 0 0,alignx center,growy");
		
		textid = new JTextField();
		panel.add(textid, "cell 1 0,alignx center,aligny center");
		textid.setColumns(10);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblemail, "cell 2 0");
		
		JLabel lblnome = new JLabel("Nome");
		lblnome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblnome, "cell 0 1,alignx center,aligny center");
		
		textnome = new JTextField();
		textnome.setColumns(10);
		panel.add(textnome, "cell 1 1,alignx center,aligny center");
		
		JLabel lbltelefone = new JLabel("Telefone");
		lbltelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lbltelefone, "cell 2 1");
		
		JLabel lblcpf = new JLabel("Cpf");
		lblcpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblcpf, "cell 0 2,alignx center,aligny center");
		
		textcpf = new JTextField();
		textcpf.setColumns(10);
		panel.add(textcpf, "cell 1 2,alignx center,aligny center");
		
		JLabel lblsenha = new JLabel("Senha");
		lblsenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblsenha, "cell 2 2");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Adminstrador");
		chckbxNewCheckBox.setBackground(new Color(246, 233, 233));
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(chckbxNewCheckBox, "cell 0 3,alignx center");
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Ver senha");
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(chckbxNewCheckBox_1, "cell 2 3");
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

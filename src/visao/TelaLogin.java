package visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

import javax.swing.event.ChangeEvent;

import utils.Cores;
import utils.Utils;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCpfLogin;
	private Font hkGrotesk;
	private JPasswordField txtSenhaLogin;
	private JButton btnContinuar;
	private JCheckBox ckboxMotrarSenha;

	public String getCampoCpf() {
		return txtCpfLogin.getText();
	}

	public String getCampoSenha() {
		return new String(txtSenhaLogin.getPassword());
	}

	public JPasswordField getTxtSenha() {
		return txtSenhaLogin;
	}

	public JCheckBox getCkboxMotrarSenha() {
		return ckboxMotrarSenha;
	}

	public JTextField getTxtCpf() {
		return txtCpfLogin;
	}

	public JButton getBtnContinuar() {
		return btnContinuar;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaLogin frame = new TelaLogin();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setAlwaysOnTop(true);
		setTitle("Tela de Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 750);
		contentPane = new JPanel();
		contentPane.setBackground(Cores.COR_ROSA); // Fundo rosa
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow]"));
		setLocationRelativeTo(this);

		// Chama o método de utilidades para carregar a fonte
		hkGrotesk = Utils.loadCustomFont();

		JPanel panel = new JPanel();
		panel.setBackground(Cores.COR_ROSA_CLARO);
		contentPane.add(panel, "cell 1 0,grow");
		panel.setLayout(
				new MigLayout("", "[][grow][]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblLogin, "cell 1 0,alignx center,aligny center");

		// Painel da imagem:
		JPanel panelImage = new JPanel();
		panelImage.setOpaque(false);
		panel.add(panelImage, "cell 1 1,alignx center,aligny center");

		// Verificação do caminho da imagem e redimensionamento
		java.net.URL imageURL = getClass().getResource("/resources/imagens/logo.png");
		if (imageURL == null) {
			System.out.println("Imagem não encontrada. Verifique o caminho");
		} else {
			ImageIcon imageIcon = new ImageIcon(imageURL);
			// Redimensionar a imagem
			Image img = imageIcon.getImage();
			Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajuste o tamanho conforme
																					// necessário
			imageIcon = new ImageIcon(resizedImg);
			JLabel imageLabel = new JLabel(imageIcon);
			panelImage.add(imageLabel);
		}

		JLabel lblStreetdragon = new JLabel("StreetDragon");
		lblStreetdragon.setHorizontalAlignment(SwingConstants.CENTER);
		lblStreetdragon.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblStreetdragon, "cell 1 2,alignx center,aligny center");

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel.add(lblCpf, "cell 1 3,alignx left,aligny center");

		txtCpfLogin = new JTextField();
		txtCpfLogin.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtCpfLogin.setColumns(10);
		panel.add(txtCpfLogin, "cell 1 4,grow");

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel.add(lblSenha, "flowx,cell 1 5,alignx left,aligny center");

		txtSenhaLogin = new JPasswordField();
		txtSenhaLogin.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(txtSenhaLogin, "cell 1 6,grow");

		ckboxMotrarSenha = new JCheckBox("Motrar Senha");
		ckboxMotrarSenha.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		ckboxMotrarSenha.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (ckboxMotrarSenha.isSelected()) {
					txtSenhaLogin.setEchoChar((char) 0);
				} else {
					txtSenhaLogin.setEchoChar('•');
				}
			}
		});

		ckboxMotrarSenha.setBackground(Cores.COR_ROSA_CLARO);
		panel.add(ckboxMotrarSenha, "cell 1 7,alignx left");

		btnContinuar = new JButton("Continuar");
		btnContinuar.setForeground(new Color(255, 255, 255));
		btnContinuar.setBackground(new Color(226, 60, 41));
		btnContinuar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(btnContinuar, "cell 1 8,grow");
	}
}
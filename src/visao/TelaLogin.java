package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class TelaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCpfLogin;
    private JTextField txtSenhaLogin;
    private JButton btnContinuar;
    private Font hkGrotesk;

    public String getCampoUsername() {
        return txtCpfLogin.getText();
    }

    public String getCampoPassword() {
        return txtSenhaLogin.getText();
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
    public TelaLogin() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 525, 366);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(250, 188, 188)); // Fundo rosa
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[][grow 200][]", "[grow]"));
        //loadCustomFont();
        JPanel panel = new JPanel();
        panel.setBackground(new Color(246, 233, 233));
        contentPane.add(panel, "cell 1 0,alignx center,aligny center");
        panel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow]"));

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblLogin, "cell 0 0,alignx center,aligny center");
/*
        // Painel da imagem:
        JPanel panelImage = new JPanel();
        panelImage.setOpaque(false);
        panel.add(panelImage, "cell 0 1,alignx center,aligny center");

        // Verificação do caminho da imagem e redimensionamento
        java.net.URL imageURL = getClass().getResource("/resources/imagens/logo.png");
        if (imageURL == null) {
            System.out.println("Imagem não encontrada. Verifique o caminho");
        } else {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            // Redimensionar a imagem
            Image img = imageIcon.getImage(); 
            Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajuste o tamanho conforme necessário
            imageIcon = new ImageIcon(resizedImg);
            JLabel imageLabel = new JLabel(imageIcon);
            panelImage.add(imageLabel);
        }
*/
        JLabel lblStreetdragon = new JLabel("StreetDragon");
        lblStreetdragon.setHorizontalAlignment(SwingConstants.CENTER);
        lblStreetdragon.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblStreetdragon, "cell 0 2,alignx center,aligny center");

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setFont(hkGrotesk);
        panel.add(lblCpf, "cell 0 3,alignx left,aligny center");

        txtCpfLogin = new JTextField();
        txtCpfLogin.setColumns(10);
        panel.add(txtCpfLogin, "cell 0 4,grow");

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(hkGrotesk);
        panel.add(lblSenha, "cell 0 5,alignx left,aligny center");

        txtSenhaLogin = new JTextField();
        txtSenhaLogin.setColumns(10);
        panel.add(txtSenhaLogin, "cell 0 6,grow");

        btnContinuar = new JButton("Continuar");
        btnContinuar.setBackground(new Color(250, 187, 187));
        btnContinuar.setFont(hkGrotesk);
        panel.add(btnContinuar, "cell 0 7,grow");
    }
/*
    // Carregar a fonte personalizada
    private void loadCustomFont() {
        try {
    		hkGrotesk = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fontes/HankenGrotesk-VariableFont_wght.ttf")).deriveFont(20f);
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
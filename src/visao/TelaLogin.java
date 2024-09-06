package visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;

public class TelaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCpfLogin;
    private JTextField txtSenhaLogin;

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
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(250, 188, 188)); // Fundo rosa
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow]"));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(246, 233, 233));
        contentPane.add(panel, "cell 1 0 3 2,grow");
        panel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow][grow][][grow][][grow][grow]"));

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblLogin, "cell 0 0 3 1,grow");


     // Painel da imagem:
        JPanel panelImage = new JPanel();
        panelImage.setBackground(new Color(246, 233, 233));
        panel.add(panelImage, "cell 1 1,grow");

        // Verificação do caminho da imagem
        java.net.URL imageURL = getClass().getResource("src/resources/images/logo.jpg");
        if (imageURL == null) {
            System.out.println("Imagem não encontrada. Verifique o caminho");
        } else {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            JLabel imageLabel = new JLabel(imageIcon);
            panelImage.add(imageLabel);
        }
        
        
        JLabel lblStreetdragon = new JLabel("StreetDragon");
        lblStreetdragon.setHorizontalAlignment(SwingConstants.CENTER);
        lblStreetdragon.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblStreetdragon, "cell 1 2,grow");
        
        JLabel lblNewLabel_1 = new JLabel("CPF");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblNewLabel_1, "cell 1 4,grow");
        
        txtCpfLogin = new JTextField();
        panel.add(txtCpfLogin, "cell 1 5,grow");
        txtCpfLogin.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Senha");
        panel.add(lblNewLabel_2, "cell 1 6,grow");
        
        lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 15));
        
        
        
        
        
        
        txtSenhaLogin = new JTextField();
        panel.add(txtSenhaLogin, "cell 1 7,grow");
        txtSenhaLogin.setColumns(10);
        
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel.add(btnContinuar, "cell 1 8,alignx center,aligny center");
		
		
		
		
		
		
	}

}

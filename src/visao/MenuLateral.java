package visao;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuLateral extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuLateral frame = new MenuLateral();
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
    public MenuLateral() {
        setTitle("Aplicação com CardLayout");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Adiciona os painéis
        mainPanel.add(new TelaPrincipal(this), "TelaPrincipal");
        // Adicione outros painéis aqui, por exemplo:
        // mainPanel.add(new TelaCadastro(this), "TelaCadastro");
        // mainPanel.add(new TelaPrincipal(this), "TelaPrincipal");

        // Painel do menu lateral
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(250, 188, 188));
        //menuPanel.setPreferredSize(new Dimension(150, 0));

        JButton buttonLogin = new JButton("Tela Principal");
        //new JButton("Tela de Cadastro");
        //new JButton("Tela Principal");

        
        menuPanel.add(buttonLogin);
        // menuPanel.add(buttonCadastro);
        // menuPanel.add(buttonPrincipal);

        // Adicionando os painéis à janela principal
        add(menuPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }
}
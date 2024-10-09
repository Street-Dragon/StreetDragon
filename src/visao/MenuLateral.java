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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        setTitle("StreetDragon");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Adiciona os painéis
        mainPanel.add(new TelaPrincipal(this), "TelaPrincipal");
        mainPanel.add(new TelaCadastroProdutos(this), "TelaCadastroProdutos");
        mainPanel.add(new TelaHistoricoVenda(this), "TelaHistoricoVenda");

        // Painel do menu lateral
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(250, 188, 188));
        //menuPanel.setPreferredSize(new Dimension(150, 0));

        JButton btnPrincipal = new JButton("Tela Principal");
        menuPanel.add(btnPrincipal);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        JButton btnProdutos = new JButton("Produtos");
        btnProdutos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        menuPanel.add(btnProdutos);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        JButton btnHistorico = new JButton("Histórico de vendas");
        menuPanel.add(btnHistorico);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        

        // Adicionando os painéis à janela principal
        getContentPane().add(menuPanel, BorderLayout.WEST);
        
        
    }
}
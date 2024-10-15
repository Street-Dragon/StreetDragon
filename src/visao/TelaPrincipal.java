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

import controle.entidade.funcionariocontrole.TelaPrincipalControle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel mainPanel;
    private TelaPrincipalControle controle;
    private JButton btnPrincipal;
    private JButton btnProdutos;
    private JButton btnHistorico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setTitle("StreetDragon");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setBackground(new Color(246, 233, 233));
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		getContentPane().add(mainPanel, BorderLayout.CENTER);


		// Adiciona os painéis
		mainPanel.add(new TelaVenda(this), "TelaVenda");
		//mainPanel.add(new TelaCadastroProdutos(this), "TelaCadastroProdutos");
		mainPanel.add(new TelaHistoricoVenda(this), "TelaHistoricoVenda");

		// Painel do menu lateral
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(246, 233, 233));
		setBackground(new Color(246, 233, 233));
		menuPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		// menuPanel.setPreferredSize(new Dimension(150, 0));

		btnPrincipal = new JButton("Tela Principal");
		menuPanel.add(btnPrincipal, "cell 0 0,growx,aligny top");

		btnProdutos = new JButton("Produtos");
		menuPanel.add(btnProdutos, "cell 0 1,growx,aligny top");

		btnHistorico = new JButton("Histórico de vendas");
		menuPanel.add(btnHistorico, "cell 0 2,growx,aligny top");

		// Adicionando os painéis à janela principal
		getContentPane().add(menuPanel, BorderLayout.WEST);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(246, 233, 233));
		getContentPane().add(panel, BorderLayout.NORTH);

		controle = new TelaPrincipalControle(this); // Inicializando o controlador da tela
    }

    // Métodos para acessar os botões e o painel principal
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getBtnPrincipal() {
        return btnPrincipal;
    }

    public JButton getBtnProdutos() {
        return btnProdutos;
    }

    public JButton getBtnHistorico() {
        return btnHistorico;
    }
}
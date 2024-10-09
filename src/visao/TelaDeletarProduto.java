package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class TelaDeletarProduto extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeletarProduto frame = new TelaDeletarProduto();
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
	public TelaDeletarProduto() {
		setTitle("Deletar Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 405);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(246, 233, 233), 10));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Deletar Produto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		contentPane.add(lblNewLabel, "cell 1 0 3 1,alignx center,aligny center");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(246, 233, 233));
		contentPane.add(panel_1, "cell 0 1 2 1,growx");
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JLabel lblCategoria = new JLabel("\"Categoria\"");
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblCategoria, "cell 0 0");
		
		JLabel lblNome = new JLabel("\"Nome\"");
		panel_1.add(lblNome, "cell 0 1");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 3 1 2 4,grow");
		
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
            Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajuste o tamanho conforme necessário
            imageIcon = new ImageIcon(resizedImg);
            JLabel imageLabel = new JLabel(imageIcon);
            panelImage.add(imageLabel);
        }
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(246, 233, 233));
		contentPane.add(panel_2, "cell 0 2 2 1,growx");
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JLabel lblCor = new JLabel("Cor: \"Cor\"");
		panel_2.add(lblCor, "cell 0 0");
		
		JLabel lblTamanho = new JLabel("Tamanho: \"Tamanho\"");
		panel_2.add(lblTamanho, "cell 0 1");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(246, 233, 233));
		contentPane.add(panel_3, "cell 0 3 2 1,growx");
		panel_3.setLayout(new MigLayout("", "[grow][]", "[grow][][grow]"));
		
		JLabel lblCodigo = new JLabel("Codigo: \"Codigo\"");
		panel_3.add(lblCodigo, "cell 0 1");
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(new Color(255, 255, 255));
		btnConfirmar.setBackground(new Color(114, 148, 235));
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnConfirmar, "cell 0 6 2 1,growx");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(226, 61, 40));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnCancelar, "cell 3 6 2 1,growx");
	}
}

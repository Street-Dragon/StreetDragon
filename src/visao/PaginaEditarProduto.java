package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.Font;

public class PaginaEditarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textNomeProduto;
	private JTextField textPrecoProduto;
	private JTextField textCategoriaProduto;
	private JTextField textField_3;
	private JButton btnBotaoVoltar;
	private JLabel lblNewLabel;
	private JButton btnBotaoConfirmar;
	private JButton btnDeletar;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaEditarProduto frame = new PaginaEditarProduto();
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
	public PaginaEditarProduto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 405);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 187, 187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow]"));
		
		btnBotaoVoltar = new JButton("Voltar");
		btnBotaoVoltar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnBotaoVoltar.setBackground(new Color(246, 233, 233));
		btnBotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnBotaoVoltar, "cell 0 0,alignx left,aligny top");
		
		lblNewLabel = new JLabel("Editar Produto");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBackground(new Color(255, 0, 0));
		contentPane.add(lblNewLabel, "cell 1 0,alignx center,growy");
		
		lblNewLabel_1 = new JLabel("Inserir Imagem");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1, "cell 2 1,alignx center");
		
		textNomeProduto = new JTextField();
		textNomeProduto.setBackground(new Color(246, 233, 233));
		contentPane.add(textNomeProduto, "cell 0 2,grow");
		textNomeProduto.setColumns(10);
		
		textPrecoProduto = new JTextField();
		textPrecoProduto.setBackground(new Color(246, 233, 233));
		contentPane.add(textPrecoProduto, "cell 0 3,grow");
		textPrecoProduto.setColumns(10);
		
		textCategoriaProduto = new JTextField();
		textCategoriaProduto.setBackground(new Color(246, 233, 233));
		contentPane.add(textCategoriaProduto, "cell 0 4,grow");
		textCategoriaProduto.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBackground(new Color(246, 233, 233));
		contentPane.add(textField_3, "cell 2 2 1 3,grow");
		textField_3.setColumns(10);
		
		btnBotaoConfirmar = new JButton("Confirmar");
		btnBotaoConfirmar.setForeground(Color.WHITE);
		btnBotaoConfirmar.setBackground(new Color(114, 148, 235));
		btnBotaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnBotaoConfirmar, "cell 0 6,grow");
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setBackground(Color.RED);
		contentPane.add(btnDeletar, "cell 2 6,grow");
	}
}

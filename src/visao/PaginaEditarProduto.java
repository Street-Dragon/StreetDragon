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

public class PaginaEditarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnBotaoVoltar;
	private JLabel lblNewLabel;
	private JButton btnBotaoConfirmar;
	private JButton btnDeletar;
	private JPanel panel;
	private JTextField textField_4;

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
		setBounds(100, 100, 573, 353);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 187, 187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow]"));
		
		btnBotaoVoltar = new JButton("Voltar");
		btnBotaoVoltar.setBackground(new Color(246, 233, 233));
		btnBotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnBotaoVoltar, "cell 0 0,growy");
		
		lblNewLabel = new JLabel("Editar Produto");
		lblNewLabel.setBackground(new Color(255, 0, 0));
		contentPane.add(lblNewLabel, "cell 1 0,alignx center,growy");
		
		panel = new JPanel();
		panel.setBackground(new Color(246, 233, 233));
		panel.setForeground(new Color(246, 233, 233));
		contentPane.add(panel, "cell 2 1,grow");
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		textField_4 = new JTextField();
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField = new JTextField();
		textField.setBackground(new Color(246, 233, 233));
		contentPane.add(textField, "cell 0 2,grow");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(246, 233, 233));
		contentPane.add(textField_1, "cell 0 3,grow");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBackground(new Color(246, 233, 233));
		contentPane.add(textField_2, "cell 0 4,grow");
		textField_2.setColumns(10);
		
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

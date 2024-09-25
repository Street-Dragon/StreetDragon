package visao;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import utils.Utils;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtQuantidade;
	private JTextField txtValor;
	private Font hkGrotesk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246, 233, 233));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][grow][grow]"));
		
		JButton btnDeslogar = new JButton("Deslogar");
		btnDeslogar.setFont(hkGrotesk);
		btnDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnDeslogar, "cell 3 0,alignx right");
		hkGrotesk = Utils.loadCustomFont();
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, "cell 0 1 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Código:");
		panel.add(lblNewLabel, "cell 0 0,alignx center,growy");
		lblNewLabel.setFont(hkGrotesk);
		
		txtCodigo = new JTextField();
		panel.add(txtCodigo, "cell 1 0,grow");
		txtCodigo.setColumns(10);
		txtNome = new JTextField();
		panel.add(txtNome, "cell 1 1,grow");
		txtNome.setEditable(false);
		txtNome.setColumns(10);
		
		JPanel panelImagem = new JPanel();
		panel.add(panelImagem, "cell 2 1 1 4,growx,aligny top");
		
		JLabel lblNewLabel_3 = new JLabel("Imagem:");
		panel.add(lblNewLabel_3, "cell 2 0,alignx center");
		lblNewLabel_3.setFont(hkGrotesk);
		
		JLabel lblNewLabel_5 = new JLabel("Nome: ");
		panel.add(lblNewLabel_5, "cell 0 1,alignx center");
		lblNewLabel_5.setFont(hkGrotesk);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade:");
		panel.add(lblNewLabel_1, "cell 0 2,alignx center");
		lblNewLabel_1.setFont(hkGrotesk);
		
		txtQuantidade = new JTextField();
		panel.add(txtQuantidade, "cell 1 2,grow");
		txtQuantidade.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("Valor:");
		panel.add(lblNewLabel_2, "cell 0 3,alignx center");
		lblNewLabel_2.setFont(hkGrotesk);
		
		txtValor = new JTextField();
		panel.add(txtValor, "cell 1 3,grow");
		txtValor.setEditable(false);
		txtValor.setColumns(10);
		
		JButton btnRemoverProduto = new JButton("Remover produto");
		btnRemoverProduto.setForeground(new Color(255, 255, 255));
		btnRemoverProduto.setBackground(new Color(226, 61, 40));
		btnRemoverProduto.setFont(hkGrotesk);
		btnRemoverProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnRemoverProduto, "cell 0 4,grow");
		
		JButton btnAdicionarProduto = new JButton("Adicionar produto");
		btnAdicionarProduto.setForeground(new Color(255, 255, 255));
		btnAdicionarProduto.setBackground(new Color(114, 148, 235));
		btnAdicionarProduto.setFont(new Font("Hanken Grotesk", Font.PLAIN, 20));
		panel.add(btnAdicionarProduto, "cell 1 4,grow");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, "cell 3 1,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][::50px,grow 50][::50px,grow 50]"));
		
		JLabel lblTotal = new JLabel("Total: 123 R$");
		lblTotal.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_1.add(lblTotal, "cell 0 0,alignx center,aligny center");
		
		JButton btnRealizarCompra = new JButton("Realizar Compra");
		btnRealizarCompra.setForeground(new Color(255, 255, 255));
		btnRealizarCompra.setBackground(new Color(114, 148, 235));
		panel_1.add(btnRealizarCompra, "cell 0 1,grow");
		
		JButton btnLimparCarrinho = new JButton("Limpar Carrinho");
		btnLimparCarrinho.setForeground(new Color(255, 255, 255));
		btnLimparCarrinho.setBackground(new Color(226, 61, 40));
		panel_1.add(btnLimparCarrinho, "cell 0 2,grow");
		
		btnLimparCarrinho.setFont(new Font("Hanken Grotesk", Font.PLAIN, 24));
		btnRealizarCompra.setFont(new Font("Hanken Grotesk", Font.PLAIN, 24));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_2, "cell 0 2 4 1,grow");

		// Definição das colunas da tabela
		String[] colunas = {"Nome", "Código", "Quantidade", "Valor"};
		JTable table = new JTable(new Object[][] {}, colunas);
		table.setFont(hkGrotesk);

		JScrollPane scrollPane = new JScrollPane(table);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		panel_2.add(scrollPane);
	}

}

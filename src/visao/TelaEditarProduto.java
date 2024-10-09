package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import utils.Utils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;


public class TelaEditarProduto extends JFrame {

	private JPanel contentPane;

	private JTextField textNomeProduto;
	private JTextField textPrecoProduto;
	private JTextField textCategoriaProduto;
	private JTextField textField_3;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JPanel panelimage;
	private JLabel txtId;
	private JLabel txtNome;
	private JLabel txtMaterial;
	private JLabel txtCategoria;
	private JLabel txtvariacao;
	private JLabel txtValor;
	private JLabel txtTamanho;
	private JTextField textFieldId;
	private JTextField textFieldNome;
	private JTextField textFieldVariacao;
	private JTextField textFieldValor;
	private JLabel txtFonecedor;
	private JLabel txtQuantidade;
	private JTextField textFieldQuantidade;
	private JComboBox comboBoxTamanho;
	private JComboBox comboBoxCategoria;
	private JTextField textFiedFornecedor;
	private JComboBox comboBoxMaterial;
	private Font hkGrotesk;
	private JLabel lblEditarProduto;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditarProduto frame = new TelaEditarProduto();
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
	
	public TelaEditarProduto() {
		setTitle("Editar Produto");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(246, 233, 233), 10));
		
		hkGrotesk = Utils.loadCustomFont();

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][][grow]"));
		
		lblEditarProduto = new JLabel("Editar Produto");
		lblEditarProduto.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEditarProduto, "cell 0 0 5 1,grow");
		lblEditarProduto.setFont(hkGrotesk);
		
		txtId = new JLabel("Id");
		contentPane.add(txtId, "cell 0 1,alignx center");
		txtId.setFont(hkGrotesk);
		
		textFieldId = new JTextField();
		textFieldId.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldId.setEditable(false);
		textFieldId.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldId, "cell 1 1,growx");
		textFieldId.setColumns(10);
		
		panelimage = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelimage.getLayout();
		contentPane.add(panelimage, "cell 3 1 2 5,grow");
		
		java.net.URL imageURL = getClass().getResource("/resources/images/default.png");
        if (imageURL == null) {
            System.out.println("Imagem não encontrada. Verifique o caminho");
        } else {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            JLabel imageLabel = new JLabel(imageIcon);
            panelimage.add(imageLabel);
        }
		
				
				btnConfirmar = new JButton("Confirmar");
				btnConfirmar.setForeground(Color.WHITE);
				btnConfirmar.setBackground(new Color(114, 148, 235));
				btnConfirmar.setFont(hkGrotesk);
				btnConfirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				
				txtNome = new JLabel("Nome");
				txtNome.setHorizontalAlignment(SwingConstants.CENTER);
				contentPane.add(txtNome, "cell 0 2,grow");
				txtNome.setFont(hkGrotesk);
				
				textFieldNome = new JTextField();
				textFieldNome.setFont(new Font("Dialog", Font.PLAIN, 15));
				textFieldNome.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldNome, "cell 1 2,growx");
				textFieldNome.setColumns(10);
				
				txtMaterial = new JLabel("Material");
				contentPane.add(txtMaterial, "cell 0 3,alignx center");
				txtMaterial.setFont(hkGrotesk);
				
				comboBoxMaterial = new JComboBox();
				comboBoxMaterial.setFont(new Font("Dialog", Font.PLAIN, 15));
				comboBoxMaterial.setBackground(new Color(246, 233, 233));
				contentPane.add(comboBoxMaterial, "cell 1 3,growx");
				comboBoxMaterial.setModel(new DefaultComboBoxModel(new String[] {"", "Algodão", "Lã", "Seda", "Viscose", "Tencel", "Linho", "Poliéster", "Elastano"}));
				
				txtCategoria = new JLabel("Categoria");
				contentPane.add(txtCategoria, "cell 0 4,alignx center");
				txtCategoria.setFont(hkGrotesk);
				
				comboBoxCategoria = new JComboBox();
				comboBoxCategoria.setFont(new Font("Dialog", Font.PLAIN, 15));
				comboBoxCategoria.setBackground(new Color(246, 233, 233));
				contentPane.add(comboBoxCategoria, "cell 1 4,growx");
				comboBoxCategoria.setModel(new DefaultComboBoxModel(new String[] {"", "Calsa", "Camisa", "Camiseta", "Moleton", "Boné", "Toca", "Tênis", "Acessórios"}));
				
				txtvariacao = new JLabel("Variação");
				contentPane.add(txtvariacao, "cell 0 5,alignx center");
				txtvariacao.setFont(hkGrotesk);
				
				textFieldVariacao = new JTextField();
				textFieldVariacao.setFont(new Font("Dialog", Font.PLAIN, 15));
				textFieldVariacao.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldVariacao, "cell 1 5,growx");
				textFieldVariacao.setColumns(10);
				
				txtValor = new JLabel("Valor");
				contentPane.add(txtValor, "cell 0 6,alignx center");
				txtValor.setFont(hkGrotesk);
				
				textFieldValor = new JTextField();
				textFieldValor.setFont(new Font("Dialog", Font.PLAIN, 15));
				textFieldValor.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldValor, "cell 1 6,growx");
				textFieldValor.setColumns(10);
				
				txtFonecedor = new JLabel("Fornecedor");
				contentPane.add(txtFonecedor, "cell 3 6,alignx center");
				txtFonecedor.setFont(hkGrotesk);
				
				textFiedFornecedor = new JTextField();
				textFiedFornecedor.setFont(new Font("Dialog", Font.PLAIN, 15));
				textFiedFornecedor.setBackground(new Color(246, 233, 233));
				contentPane.add(textFiedFornecedor, "cell 4 6,growx");
				textFiedFornecedor.setColumns(10);
				
				txtTamanho = new JLabel("Tamanho");
				contentPane.add(txtTamanho, "cell 0 7,alignx center");
				txtTamanho.setFont(hkGrotesk);
				
				comboBoxTamanho = new JComboBox();
				comboBoxTamanho.setFont(new Font("Dialog", Font.PLAIN, 15));
				comboBoxTamanho.setBackground(new Color(246, 233, 233));
				contentPane.add(comboBoxTamanho, "cell 1 7,growx");
				comboBoxTamanho.setModel(new DefaultComboBoxModel(new String[] {"", "PP", "P", "M", "G", "GG", "XG", "XGG", "EG", "EGG"}));
				
				txtQuantidade = new JLabel("Quantidade");
				contentPane.add(txtQuantidade, "cell 3 7,alignx center");
				txtQuantidade.setFont(hkGrotesk);
				
				textFieldQuantidade = new JTextField();
				textFieldQuantidade.setFont(new Font("Dialog", Font.PLAIN, 15));
				textFieldQuantidade.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldQuantidade, "cell 4 7,growx");
				textFieldQuantidade.setColumns(10);
				
				contentPane.add(btnConfirmar, "cell 0 9 2 1,growx");
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(hkGrotesk);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText(null);
				textFieldVariacao.setText(null);
				textFieldValor.setText(null);
				textFiedFornecedor.setText(null);
				textFieldQuantidade.setText(null);
				comboBoxCategoria.setSelectedIndex(0);
				comboBoxTamanho.setSelectedIndex(0);
				comboBoxMaterial.setSelectedIndex(0);
				
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.RED);
		contentPane.add(btnCancelar, "cell 3 9 2 1,growx");
	}
	
}

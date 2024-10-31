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

import controle.entidade.produto.ProdutoControle;


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
	private JTextField textFieldQntEstoque;
	private JComboBox cbTamanho;
	private JComboBox cbCategoria;
	private JTextField textFieldFornecedor;
	private JComboBox cbMaterial;
	private Font hkGrotesk;
	private JLabel lblEditarProduto;
	private ProdutoControle Pcontrole = new ProdutoControle();

	public JButton getbtnConfirmar() {
		return btnConfirmar;
	}

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
	
	public TelaEditarProduto(Produto produto) {
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
		
		java.net.URL imageURL = getClass().getResource("/resources/imagens/default.png");
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
						Pcontrole.EditProduto();
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
				
				cbMaterial = new JComboBox();
				cbMaterial.setFont(new Font("Dialog", Font.PLAIN, 15));
				cbMaterial.setBackground(new Color(246, 233, 233));
				contentPane.add(cbMaterial, "cell 1 3,growx");
				cbMaterial.setModel(new DefaultComboBoxModel(new String[] {"", "Algodão", "Lã", "Seda", "Viscose", "Tencel", "Linho", "Poliéster", "Elastano"}));
				
				txtCategoria = new JLabel("Categoria");
				contentPane.add(txtCategoria, "cell 0 4,alignx center");
				txtCategoria.setFont(hkGrotesk);
				
				cbCategoria = new JComboBox();
				cbCategoria.setFont(new Font("Dialog", Font.PLAIN, 15));
				cbCategoria.setBackground(new Color(246, 233, 233));
				contentPane.add(cbCategoria, "cell 1 4,growx");
				cbCategoria.setModel(new DefaultComboBoxModel(new String[] {"", "Calsa", "Camisa", "Camiseta", "Moleton", "Boné", "Toca", "Tênis", "Acessórios"}));
				
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
				
				textFieldFornecedor = new JTextField();
				textFieldFornecedor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
			
					}
				});
				textFieldFornecedor.setFont(new Font("Dialog", Font.PLAIN, 15));
				textFieldFornecedor.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldFornecedor, "cell 4 6,growx");
				textFieldFornecedor.setColumns(10);
				
				txtTamanho = new JLabel("Tamanho");
				contentPane.add(txtTamanho, "cell 0 7,alignx center");
				txtTamanho.setFont(hkGrotesk);
				
				cbTamanho = new JComboBox();
				cbTamanho.setFont(new Font("Dialog", Font.PLAIN, 15));
				cbTamanho.setBackground(new Color(246, 233, 233));
				contentPane.add(cbTamanho, "cell 1 7,growx");
				cbTamanho.setModel(new DefaultComboBoxModel(new String[] {"", "PP", "P", "M", "G", "GG", "XG", "XGG", "EG", "EGG"}));
				
				txtQuantidade = new JLabel("Quantidade");
				contentPane.add(txtQuantidade, "cell 3 7,alignx center");
				txtQuantidade.setFont(hkGrotesk);
				
				textFieldQntEstoque = new JTextField();
				textFieldQntEstoque.setFont(new Font("Dialog", Font.PLAIN, 15));
				textFieldQntEstoque.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldQntEstoque, "cell 4 7,growx");
				textFieldQntEstoque.setColumns(10);
				
				contentPane.add(btnConfirmar, "cell 0 9 2 1,growx");
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(hkGrotesk);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText(null);
				textFieldVariacao.setText(null);
				textFieldValor.setText(null);
				textFieldFornecedor.setText(null);
				textFieldQntEstoque.setText(null);
				cbCategoria.setSelectedIndex(0);
				cbTamanho.setSelectedIndex(0);
				cbMaterial.setSelectedIndex(0);
				
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.RED);
		contentPane.add(btnCancelar, "cell 3 9 2 1,growx");
		
		Pcontrole.listarProdutosTable();
		
	}
	public int getTextFieldId() {
		return Integer.valueOf(textFieldId.getText());
	}

	public String getTextFieldNome() {
		return textFieldNome.getText();
	}

	public float getTextFieldValor() {
		return Float.valueOf(textFieldValor.getText());
	}

	public int getTextFieldQntEstoque() {
		return Integer.valueOf(textFieldQntEstoque.getText());
	}

	public String getTextFieldVariacao() {
		return textFieldVariacao.getText();
	}

	public String getTextFieldFornecedor() {
		return textFieldFornecedor.getText();
	}

	public String getCbMaterial() {
		return String.valueOf(cbMaterial.getSelectedObjects());
	}

	public String getCbCategoria() {
		return String.valueOf(cbCategoria.getSelectedObjects());
	}
	public String getCbTamnho() {
		return String.valueOf(cbTamanho.getSelectedObjects());
	}
	
}

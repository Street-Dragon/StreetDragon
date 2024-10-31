package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.entidade.produto.ProdutoControle;
import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.fornecedor.Fornecedor;
import modelo.entidade.produto.Produto;
import modelo.enumeracao.tamanho.Tamanho;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroProdutos extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldNome;
	private JTextField textFieldValor;
	private JTextField textFieldQntEstoque;
	private JTextField textFieldVariacao;
	private JTextField textFieldFornecedor;
	private JComboBox cbMaterial;
	private JComboBox cbCategoria;
	private JComboBox cBTamanho;
	private Font hkGrotesk;
	private JButton btnCancelar; 
	private JButton btnCadastrarProduto;
	private ProdutoDAO pDAO = new ProdutoDAO();
	ProdutoControle produtoC = new ProdutoControle();
	
	public JButton getbtnCancelar() {
		return btnCancelar;
	}
	public JButton getbtnCadastrarProduto() {
		return btnCadastrarProduto;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroProdutos frame = new TelaCadastroProdutos();
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
	public TelaCadastroProdutos() {	
		setTitle("Cadastrar Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		loadCustomFont();

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][][grow]"));
		
		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblId, "cell 0 0,growx");
		lblId.setFont(hkGrotesk);
		
		textFieldId = new JTextField(String.valueOf(pDAO.Idshow()));
		textFieldId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldId.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldId.setEditable(false);
		textFieldId.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldId, "cell 1 0,growx");
		textFieldId.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNome, "cell 0 1,growx");
		lblNome.setFont(hkGrotesk);
		
		textFieldNome = new JTextField();
		textFieldNome.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldNome, "cell 1 1,growx");
		textFieldNome.setColumns(10);
		textFieldNome.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMaterial, "cell 0 2,growx");
		lblMaterial.setFont(hkGrotesk);
		
		cbMaterial = new JComboBox();
		cbMaterial.setBackground(new Color(246, 233, 233));
		cbMaterial.setModel(new DefaultComboBoxModel(new String[] {"", "Algodão", "Lã", "Seda", "Viscose", "Tencel", "Linho", "Poliéster", "Elastano"}));
		cbMaterial.setSelectedIndex(0);
		contentPane.add(cbMaterial, "cell 1 2,growx");
		cbMaterial.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JPanel panelimg = new JPanel();
		contentPane.add(panelimg, "cell 3 0 2 5,grow");
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCategoria, "cell 0 3,growx");
		lblCategoria.setFont(hkGrotesk);
		
		cbCategoria = new JComboBox();
		cbCategoria.setBackground(new Color(246, 233, 233));
		cbCategoria.setModel(new DefaultComboBoxModel(new String[] {"", "Calsa", "Camisa", "Camiseta", "Moleton", "Boné", "Toca", "Tênis", "Acessórios"}));
		cbCategoria.setSelectedIndex(0);
		contentPane.add(cbCategoria, "cell 1 3,growx");
		cbCategoria.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblVariação = new JLabel("Variação");
		lblVariação.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblVariação, "cell 0 4,growx");
		lblVariação.setFont(hkGrotesk);
		
		textFieldVariacao = new JTextField();
		textFieldVariacao.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldVariacao, "cell 1 4,growx");
		textFieldVariacao.setColumns(10);
		textFieldVariacao.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblValor, "cell 0 5,growx");
		lblValor.setFont(hkGrotesk);
		
		textFieldValor = new JTextField();
		textFieldValor.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldValor, "cell 1 5,growx");
		textFieldValor.setColumns(10);
		textFieldValor.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblFornecedor, "cell 3 5,growx");
		lblFornecedor.setFont(hkGrotesk);
		
		textFieldFornecedor = new JTextField();
		textFieldFornecedor.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldFornecedor, "cell 4 5,growx");
		textFieldFornecedor.setColumns(10);
		textFieldFornecedor.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTamanho, "cell 0 6,growx");
		lblTamanho.setFont(hkGrotesk);
		
		cBTamanho = new JComboBox();
		cBTamanho.setBackground(new Color(246, 233, 233));
		cBTamanho.setModel(new DefaultComboBoxModel(new String[] {"", "PP", "P", "M", "G", "GG", "XG", "XGG", "EG", "EGG"}));
		cBTamanho.setSelectedIndex(0);
		contentPane.add(cBTamanho, "cell 1 6,growx");
		cBTamanho.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblQntEstoque = new JLabel("Qnt estoque");
		lblQntEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblQntEstoque, "cell 3 6,growx");
		lblQntEstoque.setFont(hkGrotesk);
		
		textFieldQntEstoque = new JTextField();
		textFieldQntEstoque.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldQntEstoque, "cell 4 6,growx");
		textFieldQntEstoque.setColumns(10);
		textFieldQntEstoque.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            ProdutoControle Pcontrole = new ProdutoControle();
	            produtoC.cadastrarProduto(TelaCadastroProdutos.this);
	        }
	    });
		btnCadastrarProduto.setForeground(new Color(255, 255, 255));
		btnCadastrarProduto.setBackground(new Color(100, 149, 255));
		contentPane.add(btnCadastrarProduto, "cell 0 8 2 1,growx");
		btnCadastrarProduto.setFont(hkGrotesk);
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearText();
			}
		});
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(226, 61, 40));
		contentPane.add(btnCancelar, "cell 3 8 2 1,growx");
		btnCancelar.setFont(hkGrotesk);
		
		java.net.URL imageURL = getClass().getResource("/resources/imagens/default.png");
        if (imageURL == null) {
            System.out.println("Imagem não encontrada. Verifique o caminho");
        } else {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            JLabel imageLabel = new JLabel(imageIcon);
            panelimg.add(imageLabel);
        }
		
	}
	
	public void ClearText() {
		// TODO Auto-generated method stub
		textFieldId.setText(null);
		textFieldNome.setText(null);
		textFieldValor.setText(null);
		textFieldVariacao.setText(null);
		textFieldQntEstoque.setText(null);
		textFieldFornecedor.setText(null);
		cBTamanho.setSelectedItem(null);
		cbMaterial.setSelectedItem(null);
		cbCategoria.setSelectedItem(null);
	}

	private void loadCustomFont() {
        try {
    		hkGrotesk = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fontes/HankenGrotesk-VariableFont_wght.ttf")).deriveFont(20f);
    		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(hkGrotesk);
    	} catch (FontFormatException e) {
            System.err.println("Formato de fonte inválido: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo da fonte: " + e.getMessage());
            e.printStackTrace();
        }
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
		return String.valueOf(cBTamanho.getSelectedObjects());
	}
}

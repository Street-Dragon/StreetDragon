package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class TelaCadastroProdutos extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldNome;
	private JTextField textFeldValor;
	private JTextField textFieldQntEstoque;
	private JTextField textFieldVariacao;
	private JTextField textFieldFornecedor;

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

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][][grow]"));
		
		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblId, "cell 0 0,growx");
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldId, "cell 1 0,growx");
		textFieldId.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNome, "cell 0 1,growx");
		
		textFieldNome = new JTextField();
		textFieldNome.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldNome, "cell 1 1,growx");
		textFieldNome.setColumns(10);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMaterial, "cell 0 2,growx");
		
		JComboBox cbMaterial = new JComboBox();
		cbMaterial.setBackground(new Color(246, 233, 233));
		cbMaterial.setModel(new DefaultComboBoxModel(new String[] {"", "Algodão", "Lã", "Seda", "Viscose", "Tencel", "Linho", "Poliéster", "Elastano"}));
		contentPane.add(cbMaterial, "cell 1 2,growx");
		
		JPanel panelimg = new JPanel();
		contentPane.add(panelimg, "cell 3 0 2 5,grow");
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCategoria, "cell 0 3,growx");
		
		JComboBox cBCategoria = new JComboBox();
		cBCategoria.setBackground(new Color(246, 233, 233));
		cBCategoria.setModel(new DefaultComboBoxModel(new String[] {"", "Calsa", "Camisa", "Camiseta", "Moleton", "Boné", "Toca", "Tênis", "Acessórios"}));
		contentPane.add(cBCategoria, "cell 1 3,growx");
		
		JLabel lblVariação = new JLabel("Variação");
		lblVariação.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblVariação, "cell 0 4,growx");
		
		textFieldVariacao = new JTextField();
		textFieldVariacao.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldVariacao, "cell 1 4,growx");
		textFieldVariacao.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblValor, "cell 0 5,growx");
		
		textFeldValor = new JTextField();
		textFeldValor.setBackground(new Color(246, 233, 233));
		contentPane.add(textFeldValor, "cell 1 5,growx");
		textFeldValor.setColumns(10);
		
		JLabel lblMarca = new JLabel("Fornecedor");
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMarca, "cell 3 5,growx");
		
		textFieldFornecedor = new JTextField();
		textFieldFornecedor.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldFornecedor, "cell 4 5,growx");
		textFieldFornecedor.setColumns(10);
		
		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTamanho, "cell 0 6,growx");
		
		JComboBox cBTamanho = new JComboBox();
		cBTamanho.setBackground(new Color(246, 233, 233));
		cBTamanho.setModel(new DefaultComboBoxModel(new String[] {"", "PP", "P", "M", "G", "GG", "XG", "XGG", "EG", "EGG"}));
		contentPane.add(cBTamanho, "cell 1 6,growx");
		
		JLabel lblQntEstoque = new JLabel("Qnt estoque");
		lblQntEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblQntEstoque, "cell 3 6,growx");
		
		textFieldQntEstoque = new JTextField();
		textFieldQntEstoque.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldQntEstoque, "cell 4 6,growx");
		textFieldQntEstoque.setColumns(10);
		
		JButton btnCadastrarProuto = new JButton("Cadastrar Produto");
		btnCadastrarProuto.setBackground(new Color(100, 149, 255));
		contentPane.add(btnCadastrarProuto, "cell 0 8 2 1,growx");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(226, 61, 40));
		contentPane.add(btnCancelar, "cell 3 8 2 1,growx");
		
		java.net.URL imageURL = getClass().getResource("/resources/imagens/default.png");
        if (imageURL == null) {
            System.out.println("Imagem não encontrada. Verifique o caminho");
        } else {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            JLabel imageLabel = new JLabel(imageIcon);
            panelimg.add(imageLabel);
        }
		
	}

}

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
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import java.awt.Window.Type;
import java.awt.FlowLayout;


public class PaginaEditarProduto extends JFrame {

	private JPanel contentPane;

	private JTextField textNomeProduto;
	private JTextField textPrecoProduto;
	private JTextField textCategoriaProduto;
	private JTextField textField_3;
	private JButton btnVoltar;
	private JButton btnConfirmar;
	private JButton btnDeletar;
	private JLabel txtLabel;
	private JLabel txtLabeleditarimagen;
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
		setTitle("EdItar Produto");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 431);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 187, 187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][][grow]"));
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVoltar.setForeground(new Color(0, 0, 0));
		btnVoltar.setBackground(new Color(246, 233, 233));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mudar depois?
				dispose();
			}
		});
		contentPane.add(btnVoltar, "cell 0 0,growy");
		
		txtLabel = new JLabel("Editar Produto");
		txtLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtLabel, "cell 1 0 3 1,growx");
		
		txtLabeleditarimagen = new JLabel("Editar Imagen");
		txtLabeleditarimagen.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLabeleditarimagen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtLabeleditarimagen, "cell 2 1 3 1,growx");
		
		txtId = new JLabel("Id");
		contentPane.add(txtId, "cell 0 2,alignx center");
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setBackground(new Color(246, 233, 233));
		contentPane.add(textFieldId, "cell 1 2,growx");
		textFieldId.setColumns(10);
		
		panelimage = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelimage.getLayout();
		contentPane.add(panelimage, "cell 3 2 2 5,grow");
		
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
				btnConfirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				
				txtNome = new JLabel("Nome");
				contentPane.add(txtNome, "cell 0 3,alignx center");
				
				textFieldNome = new JTextField();
				textFieldNome.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldNome, "cell 1 3,growx");
				textFieldNome.setColumns(10);
				
				txtMaterial = new JLabel("Material");
				contentPane.add(txtMaterial, "cell 0 4,alignx trailing");
				
				comboBoxMaterial = new JComboBox();
				comboBoxMaterial.setBackground(new Color(246, 233, 233));
				contentPane.add(comboBoxMaterial, "cell 1 4,growx");
				comboBoxMaterial.setModel(new DefaultComboBoxModel(new String[] {"", "Algodão", "Lã", "Seda", "Viscose", "Tencel", "Linho", "Poliéster", "Elastano"}));
				
				txtCategoria = new JLabel("Categoria");
				contentPane.add(txtCategoria, "cell 0 5,alignx center");
				
				comboBoxCategoria = new JComboBox();
				comboBoxCategoria.setBackground(new Color(246, 233, 233));
				contentPane.add(comboBoxCategoria, "cell 1 5,growx");
				comboBoxCategoria.setModel(new DefaultComboBoxModel(new String[] {"", "Calsa", "Camisa", "Camiseta", "Moleton", "Boné", "Toca", "Tênis", "Acessórios"}));
				
				txtvariacao = new JLabel("Variação");
				contentPane.add(txtvariacao, "cell 0 6,alignx center");
				
				textFieldVariacao = new JTextField();
				textFieldVariacao.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldVariacao, "cell 1 6,growx");
				textFieldVariacao.setColumns(10);
				
				txtValor = new JLabel("Valor");
				contentPane.add(txtValor, "cell 0 7,alignx center");
				
				textFieldValor = new JTextField();
				textFieldValor.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldValor, "cell 1 7,growx");
				textFieldValor.setColumns(10);
				
				txtFonecedor = new JLabel("Fornecedor");
				contentPane.add(txtFonecedor, "cell 3 7,alignx center");
				
				textFiedFornecedor = new JTextField();
				textFiedFornecedor.setBackground(new Color(246, 233, 233));
				contentPane.add(textFiedFornecedor, "cell 4 7,growx");
				textFiedFornecedor.setColumns(10);
				
				txtTamanho = new JLabel("Tamanho");
				contentPane.add(txtTamanho, "cell 0 8,alignx center");
				
				comboBoxTamanho = new JComboBox();
				comboBoxTamanho.setBackground(new Color(246, 233, 233));
				contentPane.add(comboBoxTamanho, "cell 1 8,growx");
				comboBoxTamanho.setModel(new DefaultComboBoxModel(new String[] {"", "PP", "P", "M", "G", "GG", "XG", "XGG", "EG", "EGG"}));
				
				txtQuantidade = new JLabel("Quantidade");
				contentPane.add(txtQuantidade, "cell 3 8,alignx center");
				
				textFieldQuantidade = new JTextField();
				textFieldQuantidade.setBackground(new Color(246, 233, 233));
				contentPane.add(textFieldQuantidade, "cell 4 8,growx");
				textFieldQuantidade.setColumns(10);
				
				contentPane.add(btnConfirmar, "cell 0 10 2 1,grow");
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
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
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setBackground(Color.RED);
		contentPane.add(btnDeletar, "cell 3 10 2 1,grow");
	}
}
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

	private JTextField txtFieldNome;
	private JTextField txtFieldPreco;
	private JButton btnVoltar;
	private JButton btnConfirmar;
	private JButton btnDeletar;
	private JLabel txtLabel;
	private JLabel txtLabeleditarimagen;
	private JTextField txtFieldCategoria;
	private JPanel panelimage;


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
		setBounds(100, 100, 587, 405);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 187, 187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][::200px,grow]", "[grow][grow][grow][grow][grow][grow][grow]"));
		
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
		contentPane.add(txtLabel, "cell 1 0,growx");
		
		txtLabeleditarimagen = new JLabel("Editar Imagen");
		txtLabeleditarimagen.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLabeleditarimagen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtLabeleditarimagen, "cell 2 1,growx");
		
		txtFieldNome = new JTextField();
		txtFieldNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtFieldNome.getText().equals("Nome")) {
					txtFieldNome.setText("");
				}
			}
		});
		txtFieldNome.setText("Nome");
		txtFieldNome.setToolTipText("Nome");
		txtFieldNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldNome.setBackground(new Color(246, 233, 233));
		contentPane.add(txtFieldNome, "cell 0 2,grow");
		txtFieldNome.setColumns(10);
		
		txtFieldPreco = new JTextField();
		txtFieldPreco.setToolTipText("Preco");
		txtFieldPreco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtFieldPreco.getText().equals("Preco")) {
					txtFieldPreco.setText("");
				}
			}
		});
		txtFieldPreco.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldPreco.setText("Preco");
		txtFieldPreco.setBackground(new Color(246, 233, 233));
		contentPane.add(txtFieldPreco, "cell 0 3,grow");
		txtFieldPreco.setColumns(10);

		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBackground(new Color(114, 148, 235));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		txtFieldCategoria = new JTextField();
		txtFieldCategoria.setToolTipText("Categoria");
		txtFieldCategoria.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtFieldCategoria.getText().equals("Categoria")) {
					txtFieldCategoria.setText("");
				}
			}
		});
		
		panelimage = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelimage.getLayout();
		contentPane.add(panelimage, "cell 2 2 1 3,grow");
		txtFieldCategoria.setText("Categoria");
		txtFieldCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldCategoria.setBackground(new Color(246, 233, 233));
		contentPane.add(txtFieldCategoria, "cell 0 4,grow");
		txtFieldCategoria.setColumns(10);
		
		java.net.URL imageURL = getClass().getResource("/resources/images/default.png");
        if (imageURL == null) {
            System.out.println("Imagem não encontrada. Verifique o caminho");
        } else {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            JLabel imageLabel = new JLabel(imageIcon);
            panelimage.add(imageLabel);
        }
		
		contentPane.add(btnConfirmar, "cell 0 6,grow");
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldNome.setText("Nome");
				txtFieldCategoria.setText("Categoria");
				txtFieldPreco.setText("Preco");
			}
		});
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setBackground(Color.RED);
		contentPane.add(btnDeletar, "cell 2 6,grow");
	}
}

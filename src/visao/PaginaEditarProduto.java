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
	private JTextField txtFieldNome;
	private JTextField txtFieldPreco;
	private JButton btnBotaoVoltar;
	private JButton btnBotaoConfirmar;
	private JButton btnDeletar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
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
		setBounds(100, 100, 573, 353);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 187, 187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow]"));
		
		btnBotaoVoltar = new JButton("Voltar");
		btnBotaoVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBotaoVoltar.setForeground(new Color(0, 0, 0));
		btnBotaoVoltar.setBackground(new Color(246, 233, 233));
		btnBotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mudar depois?
				dispose();
			}
		});
		contentPane.add(btnBotaoVoltar, "cell 0 0,growy");
		
		lblNewLabel_1 = new JLabel("Editar Produto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1, "cell 1 0,growx");
		
		lblNewLabel_2 = new JLabel("Editar Imagen");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_2, "cell 2 1,growx");
		
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
		
		btnBotaoConfirmar = new JButton("Confirmar");
		btnBotaoConfirmar.setForeground(Color.WHITE);
		btnBotaoConfirmar.setBackground(new Color(114, 148, 235));
		btnBotaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		java.net.URL imageURL = getClass().getResource("/resources/images/default.png");
        if (imageURL == null) {
            System.out.println("Imagem não encontrada. Verifique o caminho");
        } else {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            JLabel imageLabel = new JLabel(imageIcon);
            panelimage.add(imageLabel);
        }
		
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
		
		contentPane.add(btnBotaoConfirmar, "cell 0 6,grow");
		
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

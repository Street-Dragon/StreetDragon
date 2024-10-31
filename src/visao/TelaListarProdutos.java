package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utils.Utils;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.produto.Produto;

public class TelaListarProdutos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarProdutos frame = new TelaListarProdutos();
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
	public TelaListarProdutos() {		
		
		setTitle("Lista de Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Utils.loadCustomFont();
		
		
		

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][grow]", "[grow]"));
		
		JLabel lblNewLabel = new JLabel("Consultar Nome");
		panel.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textFieldNome = new JTextField();
		panel.add(textFieldNome, "cell 1 0,growx");
		textFieldNome.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Valor", "Qnt estoque"
			}
		));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		//LoadTable();
	}
/*
	private void LoadTable() {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ProdutoDAO dao = new ProdutoDAO();
		Produto[] produto = dao.consultar();
		for(int i = 0; i>produto.length ; i++) {
		    Vector row = new Vector();
		    row.add(produto[i].getIdProduto());
		    row.add(produto[i].getNomeProduto());
		    row.add(produto[i].getValor());
		    row.add(produto[i].getQuantEstoque());
		    model.addRow(row);
		}
	}
	*/

}
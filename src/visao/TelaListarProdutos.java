package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.Panel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class TelaListarProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JRadioButton rdbtnFornecedor;
	private JRadioButton rdbtnCategoria;
	private JRadioButton rdbtnQuantidade;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		setBackground(new Color(246, 233, 233));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow 20][grow]", "[grow 10][grow][]"));
		
		lblNewLabel = new JLabel("Lista de produtos ");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		rdbtnFornecedor = new JRadioButton("Fornecedor");
		buttonGroup.add(rdbtnFornecedor);
		rdbtnFornecedor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 20));
		contentPane.add(rdbtnFornecedor, "flowx,cell 2 0");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 3 1,grow");
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(246, 233, 233)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{"cu", null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setBackground(new Color(246, 233, 233));
		scrollPane.setViewportView(table);
		
		rdbtnCategoria = new JRadioButton("Categoria");
		buttonGroup.add(rdbtnCategoria);
		rdbtnCategoria.setFont(new Font("Hanken Grotesk", Font.PLAIN, 20));
		contentPane.add(rdbtnCategoria, "cell 2 0");
		
		rdbtnQuantidade = new JRadioButton("Quantidade");
		buttonGroup.add(rdbtnQuantidade);
		rdbtnQuantidade.setFont(new Font("Hanken Grotesk", Font.PLAIN, 20));
		contentPane.add(rdbtnQuantidade, "cell 2 0");
	}

}

package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.awt.Panel;
import java.awt.TextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class TelaHistoricoVenda extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private Panel panel;
	private JLabel lblNewLabel;
	private TextField textFieldConsulta;
	private JRadioButton rdbtnCodigo;
	private JRadioButton rdbtnNome;
	private JRadioButton rdbtnnData;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaHistoricoVenda frame = new TelaHistoricoVenda();
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
	public TelaHistoricoVenda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow 5][grow][grow 5]", "[grow 5][][grow][grow 5]"));
		
		panel = new Panel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, "cell 1 1,grow");
		panel.setLayout(new MigLayout("", "[grow 10][grow 40][grow 10]", "[][]"));
		
		lblNewLabel = new JLabel("Consultar");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel, "cell 0 0,alignx left,aligny center");
		
		textFieldConsulta = new TextField();
		textFieldConsulta.setBackground(new Color(255, 255, 255));
		panel.add(textFieldConsulta, "cell 1 0,grow");
		
		rdbtnCodigo = new JRadioButton("Código");
		rdbtnCodigo.setBackground(new Color(255, 255, 255));
		rdbtnCodigo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonGroup.add(rdbtnCodigo);
		panel.add(rdbtnCodigo, "flowx,cell 2 0");
		
		rdbtnNome = new JRadioButton("Nome");
		rdbtnNome.setBackground(new Color(255, 255, 255));
		rdbtnNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonGroup.add(rdbtnNome);
		panel.add(rdbtnNome, "cell 2 0");
		
		rdbtnnData = new JRadioButton("Data");
		rdbtnnData.setBackground(new Color(255, 255, 255));
		rdbtnnData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonGroup.add(rdbtnnData);
		panel.add(rdbtnnData, "cell 2 0,alignx right");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 2,grow");
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(250, 187, 187), 2));
		table.setBackground(new Color(255, 233, 233));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
	}

}

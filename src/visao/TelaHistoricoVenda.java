package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import utils.Utils;

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
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class TelaHistoricoVenda extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private Panel panel;
	private JLabel lblNewLabel;
	private TextField textFieldConsulta;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboBox;
	private JButton btnConsultar;

	/**
	 * Create the frame.
	 */
	public TelaHistoricoVenda(TelaPrincipal telaPrincipal) {
		setBounds(100, 100, 682, 482);
		// contentPane = new JPanel();
		setBackground(new Color(253, 233, 235));

		setLayout(new MigLayout("", "[grow]", "[35%][65%]"));

		panel = new Panel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[20%][60%][20%]", "[grow][grow]"));
						
								lblNewLabel = new JLabel("Consulta");
								lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
								panel.add(lblNewLabel, "cell 1 0,alignx center,aligny bottom");
						
						comboBox = new JComboBox();
						comboBox.setBackground(new Color(255, 255, 255));
						comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Nome", "Categoria", "Preco"}));
						comboBox.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
						panel.add(comboBox, "cell 0 1,growx");
						
								textFieldConsulta = new TextField();
								textFieldConsulta.setBackground(new Color(255, 255, 255));
								textFieldConsulta.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
								panel.add(textFieldConsulta, "cell 1 1,growx,aligny center");
								
								btnConsultar = new JButton("Consultar");
								btnConsultar.setForeground(new Color(255, 255, 255));
								btnConsultar.setBackground(new Color(255, 175, 175));
								btnConsultar.setIcon(Utils.carregarIcone("lupa.png", 30, 30));
								btnConsultar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
								panel.add(btnConsultar, "cell 2 1,growx,aligny center");

		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");

		table = new JTable();
		table.setBorder(new LineBorder(new Color(250, 187, 187), 2));
		table.setBackground(new Color(255, 233, 233));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] { "New column", "New column", "New column" }));
		scrollPane.setViewportView(table);
		Utils.configTabela(table, scrollPane);
	}
}

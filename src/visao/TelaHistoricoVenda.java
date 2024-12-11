package visao;

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

public class TelaHistoricoVenda extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private Panel panel;
	private JLabel lblNewLabel;
	private TextField textFieldConsulta;
	private JRadioButton rdbtnCodigo;
	private JRadioButton rdbtnNome;
	private JRadioButton rdbtnnData;
	private static DefaultTableModel tableModel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public TextField getTextFieldConsulta() {
		return textFieldConsulta;
	}

	public void setTextFieldConsulta(TextField textFieldConsulta) {
		this.textFieldConsulta = textFieldConsulta;
	}

	public JRadioButton getRdbtnCodigo() {
		return rdbtnCodigo;
	}

	public void setRdbtnCodigo(JRadioButton rdbtnCodigo) {
		this.rdbtnCodigo = rdbtnCodigo;
	}

	public JRadioButton getRdbtnNome() {
		return rdbtnNome;
	}

	public void setRdbtnNome(JRadioButton rdbtnNome) {
		this.rdbtnNome = rdbtnNome;
	}

	public JRadioButton getRdbtnnData() {
		return rdbtnnData;
	}

	public void setRdbtnnData(JRadioButton rdbtnnData) {
		this.rdbtnnData = rdbtnnData;
	}
	
	public JTable getTable() {
		return table;
	}

	public TelaHistoricoVenda(TelaPrincipal telaPrincipal) {
		setBounds(100, 100, 568, 398);
		// contentPane = new JPanel();
		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(new MigLayout("", "[grow 5][grow][grow 5]", "[grow 5][][grow][grow 5]"));

		panel = new Panel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 1 1,grow");
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
		add(scrollPane, "cell 1 2,grow");

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Codigo");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Data");

		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		Utils.configTabela(table, scrollPane);
		scrollPane.setViewportView(table);
	}

}

package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import utils.Cores;
import utils.Utils;
import javax.swing.JComboBox;


public class TelaPromocao extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtDesconto;
	private JTextField txtNome;
	private JTextField txtTermino;
	private JButton btnCadastrar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private static DefaultTableModel tableModel;
	private JTable table;
	private JLabel lblInicio;
	private JTextField txtInicio;
	private JComboBox comboBoxCategoria;
	


	/**
	 * Create the panel.
	 */
	public TelaPromocao(TelaPrincipal telaPrincipal) {
		setBounds(100, 100, 926, 526);
		setBackground(Cores.COR_ROSA_CLARO);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		
		Border borda = new LineBorder(Cores.COR_ROSA_CLARO, 1);
		Utils.loadCustomFont();
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[::30%,grow][grow]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255)), null));
		add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[grow][grow][]"));
				
						JLabel lblNomePromo = new JLabel("Nome da Promoção");
						lblNomePromo.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
						panel.add(lblNomePromo, "cell 0 0,alignx left");
		
				txtNome = new JTextField();
				txtNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
				panel.add(txtNome, "cell 1 0,growx");
				txtNome.setColumns(10);
						
								JLabel lblTermino = new JLabel("Término");
								lblTermino.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
								panel.add(lblTermino, "cell 3 0,alignx center,aligny center");
				
						txtTermino = new JTextField();
						txtTermino.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
						panel.add(txtTermino, "cell 4 0,growx");
						txtTermino.setColumns(10);
		
				JLabel lblDesconto = new JLabel("% do Desconto");
				lblDesconto.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
				panel.add(lblDesconto, "cell 0 1,alignx left");

		txtDesconto = new JTextField();
		txtDesconto.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtDesconto.setColumns(10);
		panel.add(txtDesconto, "cell 1 1,growx,aligny center");
		
		lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Dialog", Font.PLAIN, 30));
		panel.add(lblInicio, "cell 3 1,alignx center,aligny center");
		
		txtInicio = new JTextField();
		txtInicio.setFont(new Font("Dialog", Font.PLAIN, 30));
		txtInicio.setColumns(10);
		panel.add(txtInicio, "cell 4 1,growx,aligny center");
		
		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBackground(new Color(255, 255, 255));
		comboBoxCategoria.setModel(new DefaultComboBoxModel(new String[] {"", "Calça", "Camisa", "Camiseta", "Moleton", "Boné", "Toca", "Tênis", "Acessórios", "Outro"}));
		comboBoxCategoria.setToolTipText("\r\n");
		panel.add(comboBoxCategoria, "cell 0 2,grow");

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(null, borda));
		add(panel_1, "cell 3 0,grow");
		panel_1.setBackground(new Color(255, 255, 255));
		// Botão Cadastrar
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][][grow][][grow][grow]"));
		btnCadastrar.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrar, "cell 0 0 1 2,grow");
		// Botão Ecluir
		btnExcluir = new JButton("Excluir ");
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		btnExcluir.setBackground(new Color(255, 0, 0));
		panel_1.add(btnExcluir, "cell 0 2 1 2,grow");
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		btnEditar.setBackground(Color.PINK);
		btnEditar.setForeground(Color.WHITE);
		panel_1.add(btnEditar, "cell 0 4 1 3,grow");

		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 4 1,grow");

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Id");
		tableModel.addColumn("Nome");
		tableModel.addColumn("%");
		tableModel.addColumn("Término");
		tableModel.addColumn("Inicio");
		tableModel.addColumn("Categoria");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "%","Término","Inicio","Categoria"
			}
			
			
		));
		
		table = new JTable(tableModel) {
		    // não deixa as células serem editadas
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};
		
		Utils.configTabela(table, scrollPane);
		
		scrollPane.setViewportView(table);
		
		
		

	}
	public JTextField getTxtInicio() {
		return txtInicio;
	}
	public void setTxtInicio(JTextField txtInicio) {
		this.txtInicio = txtInicio;
	}
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public static DefaultTableModel getTableModel() {
		return tableModel;
	}

	public static void setTableModel(DefaultTableModel tableModel) {
		TelaPromocao.tableModel = tableModel;
	}


	public void setTxtDesconto(JTextField txtDesconto) {
		this.txtDesconto = txtDesconto;
	}
	
	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public void setTxtTermino(JTextField txtTermino) {
		this.txtTermino = txtTermino;
	}

	public JTextField getTxtDesconto() {
		return txtDesconto;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextField getTxtTermino() {
		return txtTermino;
	}

	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}

	public void setBtnCadastrar(JButton btnCadastrar) {
		this.btnCadastrar = btnCadastrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnExcluir() {
		return btnExcluir;
	}

	public void setBtnExcluir(JButton btnExcluir) {
		this.btnExcluir = btnExcluir;
	}
	
	public String getcomboBoxCategoria () {
		return String.valueOf(comboBoxCategoria.getSelectedItem());
	}
	
	public JComboBox setComboBoxCategoria() {
		return this.comboBoxCategoria;
	}
	
	
}

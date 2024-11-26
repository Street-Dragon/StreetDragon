package visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import utils.Cores;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TelaPagamento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTotal;
	private JTextField txtDesconto;
	private JTextField txtDinheiro;
	private JTextField txtCartao;
	private JTextField txtOutros;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public TelaPagamento(TelaPrincipal telaPrincipal) {
		setBackground(Cores.COR_ROSA_CLARO);
		setLayout(new MigLayout("", "[::60%,grow][::40%,grow]", "[grow][60%:n,grow]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow][grow]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("Cliente:");
		panel.add(lblNewLabel, "cell 0 0,alignx left,growy");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(comboBox, "cell 1 0,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Preço Total:");
		lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1, "cell 0 1,alignx left");
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtTotal.setColumns(10);
		panel.add(txtTotal, "cell 1 1,growx");
		
		JLabel lblNewLabel_1_1 = new JLabel("Descontos:");
		lblNewLabel_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1_1, "cell 0 2,alignx left");
		
		txtDesconto = new JTextField();
		txtDesconto.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtDesconto.setColumns(10);
		panel.add(txtDesconto, "cell 1 2,growx");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1, "cell 1 0,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JLabel lblNewLabel_1_2 = new JLabel("Total a pagar:");
		lblNewLabel_1_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_1.add(lblNewLabel_1_2, "flowy,cell 0 0,alignx center,growy");
		
		JLabel lblNewLabel_1_2_1 = new JLabel("R$: 123.12");
		lblNewLabel_1_2_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_1.add(lblNewLabel_1_2_1, "cell 0 1,alignx center,growy");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, "cell 0 1 2 1,grow");
		panel_2.setLayout(new MigLayout("", "[20%,grow][30%,grow][20%,grow][30%,grow]", "[grow][grow][grow][grow]"));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Dinheiro:");
		lblNewLabel_1_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_1_1_1, "cell 0 0,alignx left");
		
		txtDinheiro = new JTextField();
		txtDinheiro.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtDinheiro.setColumns(10);
		panel_2.add(txtDinheiro, "cell 1 0,growx");
		
		JLabel lbltroco = new JLabel("Troco:");
		lbltroco.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lbltroco, "cell 2 0,alignx trailing");
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		textField.setColumns(10);
		panel_2.add(textField, "cell 3 0,growx");
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cartão:");
		lblNewLabel_1_1_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_1_1_1_1, "cell 0 1,alignx left");
		
		txtCartao = new JTextField();
		txtCartao.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtCartao.setColumns(10);
		panel_2.add(txtCartao, "cell 1 1,growx");
		
		JLabel lblFuncionrio = new JLabel("Vendedor:");
		lblFuncionrio.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lblFuncionrio, "cell 2 1,alignx trailing");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_2.add(comboBox_1, "cell 3 1,growx");
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Outros:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_1_1_1_1_1, "cell 0 2,alignx left");
		
		txtOutros = new JTextField();
		txtOutros.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtOutros.setColumns(10);
		panel_2.add(txtOutros, "cell 1 2,growx");
		
		JButton btnCancelar = new JButton("Cancelar");
		panel_2.add(btnCancelar, "cell 0 3,grow");
		
		JButton btnConfirmar = new JButton("Confirmar");
		panel_2.add(btnConfirmar, "cell 3 3,grow");

	}

}

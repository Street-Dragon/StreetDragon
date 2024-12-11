package visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import utils.Cores;
import utils.Utils;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JButton btnConfirmar;
	private TelaVenda telaVenda;
	private JButton btnCancelar;
	
	
	/**
	 * Create the panel.
	 */
	public TelaPagamento(TelaPrincipal telaPrincipal, TelaVenda telaVenda) {
		this.telaVenda = telaVenda;
		
		setBackground(Cores.COR_ROSA_CLARO);
		setLayout(new MigLayout("", "[::60%,grow][::40%,grow]", "[20%,grow][60%:n,grow]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[:35%:40%,grow][grow]", "[grow][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Cliente:");
		panel.add(lblNewLabel, "cell 0 0,alignx left,growy");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(comboBox, "cell 1 0,growx,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Preço Total:");
		lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1, "cell 0 1,alignx left");
		
		txtTotal = new JTextField();
		txtTotal.setText(telaVenda.getTxtTotal());
		txtTotal.setEnabled(false);
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtTotal.setColumns(10);
		panel.add(txtTotal, "cell 1 1,growx,aligny center");
		
		JLabel lblNewLabel_1_1 = new JLabel("Descontos:");
		lblNewLabel_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1_1, "cell 0 2,alignx left");
		
		txtDesconto = new JTextField();
		txtDesconto.setEnabled(false);
		txtDesconto.setEditable(false);
		txtDesconto.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtDesconto.setColumns(10);
		panel.add(txtDesconto, "cell 1 2,growx,aligny center");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1, "cell 1 0,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JLabel lblNewLabel_1_2 = new JLabel("Total a pagar:");
		lblNewLabel_1_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 35));
		panel_1.add(lblNewLabel_1_2, "flowy,cell 0 0,alignx center,growy");
		
		JLabel lblTotalDinheiro = new JLabel();
		lblTotalDinheiro.setText(telaVenda.getTxtTotal());
		lblTotalDinheiro.setFont(new Font("Hanken Grotesk", Font.PLAIN, 35));
		panel_1.add(lblTotalDinheiro, "cell 0 1,alignx center,growy");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, "cell 0 1 2 1,grow");
		panel_2.setLayout(new MigLayout("", "[:15%:20%,grow][30%,grow][5%:n,grow][:15%:20%,grow][30%,grow]", "[grow][grow][grow][::15%,grow]"));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Dinheiro:");
		lblNewLabel_1_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_1_1_1, "cell 0 0,alignx left");
		
		//aqui 1
		txtDinheiro = new JTextField();
		txtDinheiro.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtDinheiro.setColumns(10);
		panel_2.add(txtDinheiro, "cell 1 0,growx,aligny center");
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cartão:");
		lblNewLabel_1_1_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_1_1_1_1, "cell 3 0,alignx left");
		
		//aqui 3
		txtCartao = new JTextField();
		txtCartao.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtCartao.setColumns(10);
		panel_2.add(txtCartao, "cell 4 0,growx,aligny center");
		
		
		JLabel lbltroco = new JLabel("Troco:");
		lbltroco.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lbltroco, "cell 0 1,alignx left");
		
		//aqui troco
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		textField.setColumns(10);
		panel_2.add(textField, "cell 1 1,growx,aligny center");
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Outros:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_1_1_1_1_1, "cell 3 1,alignx left");
		
		// aqui 5
		txtOutros = new JTextField();
		txtOutros.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtOutros.setColumns(10);
		panel_2.add(txtOutros, "cell 4 1,growx,aligny center");
		
		//aqui
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(Cores.COR_VERMELHO);
		btnCancelar.setFont(new Font("Hanken Grotesk", Font.BOLD, 25));
		panel_2.add(btnCancelar, "cell 0 3,grow");
		
		JLabel lblFuncionrio = new JLabel("Vendedor:");
		lblFuncionrio.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		panel_2.add(lblFuncionrio, "cell 1 3,alignx right,aligny center");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_2.add(comboBox_1, "cell 2 3 2 1,growx,aligny center");
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(new Color(255, 255, 255));
		btnConfirmar.setBackground(Cores.COR_AZUL);
		btnConfirmar.setFont(new Font("Hanken Grotesk", Font.BOLD, 25));
		panel_2.add(btnConfirmar, "cell 4 3,grow");
		
		btnCancelar.setIcon(Utils.carregarIcone("lixo.png", 30, 30));
		btnConfirmar.setIcon(Utils.carregarIcone("carrinho.png", 30, 30));
		
		textField.addKeyListener(new KeyAdapter() {
	            public void keyReleased(KeyEvent e) {
	                calcularTroco();
	            }
	        });
		
		

	}
	
	private void setText(String txtTotal2) {
		// TODO Auto-generated method stub
		
	}

	public JButton getBtnConfirmar() {
		return btnConfirmar;
	}
	
	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	  private void calcularTroco() {
	        try {
	            
	            float total = Float.parseFloat(telaVenda.getTxtTotal());
	            float dinheiro = Float.parseFloat(txtDinheiro.getText());

	            
	            float troco = dinheiro - total;
	            
	            textField.setEnabled(true);
	    		textField.setEditable(true);

	            
	            if (troco >= 0) {
	            	textField.setText(String.format("R$ %.2f", troco));
	            } else {
	            	textField.setText("Valor insuficiente");
	            }
	        } catch (NumberFormatException e) {
	            
	        	textField.setText("");
	        }
	    }

}

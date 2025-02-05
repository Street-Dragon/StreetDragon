package visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import utils.Cores;
import utils.Utils;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import modelo.entidade.pessoa.cliente.Cliente;
import modelo.entidade.pessoa.funcionario.Funcionario;
import java.awt.event.KeyAdapter;

public class TelaPagamento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTotal;
	private JTextField txtDesconto;
	private JTextField txtDinheiro;
	private JTextField txtCartao;
	private JTextField txtOutros;
	private JTextField textTroco;
	private JComboBox<Cliente> comboBox;
	private JComboBox<Funcionario> comboBox_1;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	public JLabel lblTotalPagar;

	/**
	 * Create the panel.
	 */
	public TelaPagamento(TelaPrincipal telaPrincipal) {
		setBackground(Cores.COR_ROSA_CLARO);
		setLayout(new MigLayout("", "[75%][25%]", "[35%][65%]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[35%][65%]", "[grow][grow][grow]"));
		
		
			
	   

		panel.setLayout(new MigLayout("", "[35%][65%]", "[grow][grow][grow]"));

		
		JLabel lblNewLabel = new JLabel("Cliente:");
		panel.add(lblNewLabel, "cell 0 0,alignx left,growy");
		lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		
		comboBox = new JComboBox<Cliente>();
		comboBox.setEditable(true);
		comboBox.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(comboBox, "cell 1 0,growx,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Preço Total:");
		lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1, "cell 0 1,alignx left,growy");
		
		txtTotal = new JTextField();
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setEnabled(false);
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtTotal.setColumns(10);
		panel.add(txtTotal, "cell 1 1,growx,aligny center");
		
		JLabel lblNewLabel_1_1 = new JLabel("Descontos:");
		lblNewLabel_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel.add(lblNewLabel_1_1, "cell 0 2,alignx left,growy");
		
		txtDesconto = new JTextField();
		txtDesconto.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblNewLabel_1_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_1.add(lblNewLabel_1_2, "flowy,cell 0 0,alignx center,growy");
		
		 lblTotalPagar = new JLabel("R$: 123.12");
		lblTotalPagar.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_1.add(lblTotalPagar, "cell 0 1,alignx center,growy");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		
			
		add(panel_2, "cell 0 1 2 1,grow");
		panel_2.setLayout(new MigLayout("", "[20%][30%][20%][30%]", "[grow][grow][grow][::15%,grow]"));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Dinheiro:");
		lblNewLabel_1_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_2.add(lblNewLabel_1_1_1, "cell 0 0,alignx left,growy");
		
		txtDinheiro = new JTextField();
		txtDinheiro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calculartroco();
			}

		});
		txtDinheiro.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtDinheiro.setColumns(10);
		panel_2.add(txtDinheiro, "cell 1 0,growx,aligny center");
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cartão:");
		lblNewLabel_1_1_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_2.add(lblNewLabel_1_1_1_1, "cell 2 0,alignx left,growy");
		
		txtCartao = new JTextField();
		txtCartao.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtCartao.setColumns(10);
		panel_2.add(txtCartao, "cell 3 0,growx,aligny center");
		
		JLabel lbltroco = new JLabel("Troco:");
		lbltroco.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_2.add(lbltroco, "cell 0 1,alignx left,growy");
		
		textTroco = new JTextField();
		textTroco.setEnabled(false);
		textTroco.setEditable(false);
		textTroco.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		textTroco.setColumns(10);
		panel_2.add(textTroco, "cell 1 1,growx,aligny center");
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Outros:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_2.add(lblNewLabel_1_1_1_1_1, "cell 2 1,alignx left,growy");
		
		txtOutros = new JTextField();
		txtOutros.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		txtOutros.setColumns(10);
		panel_2.add(txtOutros, "cell 3 1,growx,aligny center");
		
		JLabel lblFuncionrio = new JLabel("Vendedor:");
		lblFuncionrio.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_2.add(lblFuncionrio, "cell 0 2,alignx left,growy");
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
		panel_2.add(comboBox_1, "cell 1 2,growx,aligny center");
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(new Color(255, 255, 255));
		btnConfirmar.setBackground(Cores.COR_AZUL);
		btnConfirmar.setFont(new Font("Hanken Grotesk", Font.BOLD, 30));
		panel_2.add(btnConfirmar, "cell 0 3 2 1,grow");
		btnConfirmar.setIcon(Utils.carregarIcone("Add.png", 30, 30));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(Cores.COR_VERMELHO);
		btnCancelar.setFont(new Font("Hanken Grotesk", Font.BOLD, 30));
		panel_2.add(btnCancelar, "cell 2 3 2 1,grow");
		
		btnCancelar.setIcon(Utils.carregarIcone("X.png", 30, 30));

	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JTextField txtTotal) {
		this.txtTotal = txtTotal;
	}

	public JTextField getTxtDesconto() {
		return txtDesconto;
	}

	public void setTxtDesconto(JTextField txtDesconto) {
		this.txtDesconto = txtDesconto;
	}

	public JTextField getTxtDinheiro() {
		return txtDinheiro;
	}

	public void setTxtDinheiro(JTextField txtDinheiro) {
		this.txtDinheiro = txtDinheiro;
	}

	public JTextField getTxtCartao() {
		return txtCartao;
	}

	public void setTxtCartao(JTextField txtCartao) {
		this.txtCartao = txtCartao;
	}

	public JTextField getTxtOutros() {
		return txtOutros;
	}

	public void setTxtOutros(JTextField txtOutros) {
		this.txtOutros = txtOutros;
	}

	public JTextField getTextField() {
		return textTroco;
	}

	public void setTextField(JTextField textField) {
		this.textTroco = textField;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getBtnConfirmar() {
		return btnConfirmar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	private void calculartroco() {
		// TODO Auto-generated method stub
		
	}

	// cpa q o problema ta aq

	public void setClientes(List<Cliente> clientes) {
	    comboBox.removeAllItems();
	    if (clientes == null || clientes.isEmpty()) {
	        System.out.println("NADA");
	    } else {
	        for (Cliente cliente : clientes) {
	            System.out.println("foi: " + cliente.getNome());
	            System.out.println(cliente);
	            comboBox.addItem(cliente);
	        }
	    }
	}
	
	public void setFuncionarios(List<Funcionario> funcionarios) {
		comboBox_1.removeAllItems();
	    if (funcionarios == null || funcionarios.isEmpty()) {
	        System.out.println("NADA");
	    } else {
	        for (Funcionario funcionario : funcionarios) {
	            System.out.println("foi: " + funcionario.getNome());
	            System.out.println(funcionario);
	            comboBox_1.addItem(funcionario);
	        }
	    }
	}
	
}


package controle.entidade.pagamentocontrole;

import visao.TelaMensagens;
import visao.TelaPagamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controle.entidade.item.ItemController;
import controle.entidade.promocao.PromocaoControle;
import modelo.dao.cliente.ClienteDAO;
import modelo.dao.funcionario.FuncionarioDAO;
import modelo.dao.item.ItemDAO;
import modelo.entidade.pessoa.cliente.Cliente;
import modelo.entidade.pessoa.funcionario.Funcionario;

public class TelaPagamentoControle {

	private TelaPagamento telaPagamento;
	private ClienteDAO clienteDAO;
	private ItemDAO itemDAO;
	private FuncionarioDAO funcionarioDAO;
	private ItemController itemControle;
	private PromocaoControle promocaoControle;

	public TelaPagamentoControle(TelaPagamento telaPagamento) {
		this.telaPagamento = telaPagamento;
		this.clienteDAO = new ClienteDAO();
		this.itemDAO = new ItemDAO();
		this.funcionarioDAO = new FuncionarioDAO();
		buscarClientes();
		buscarFuncionarios();
		adicionarListeners();
	}

	private void adicionarListeners() {

		// Listener para o botão Confirmar
		telaPagamento.getBtnConfirmar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				efetuarVenda();
			}
		});

		telaPagamento.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Botao de cancelar clicado");
			}
		});

		telaPagamento.getCboxCliente().addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// O combo box está prestes a ser expandido
				buscarClientes();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub

			}
		});

		telaPagamento.getCboxFuncionario().addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// O combo box está prestes a ser expandido
				buscarFuncionarios();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	// FUNÇÃO PRA TERMINAR A VENDA
	private void efetuarVenda() {

		String cpfFuncionario = getCpfFuncionario();
		String idCliente = getCpfCliente();
		
		String precoTotalString = telaPagamento.getLblTotalPagar().getText();
		float precoTotal = Float.parseFloat(precoTotalString);

		try {
			float dinheiro = telaPagamento.getTxtDinheiro().getText().isEmpty() ? 0 : Float.parseFloat(telaPagamento.getTxtDinheiro().getText());
	        float cartao = telaPagamento.getTxtCartao().getText().isEmpty() ? 0 : Float.parseFloat(telaPagamento.getTxtCartao().getText());
	        float outros = telaPagamento.getTxtOutros().getText().isEmpty() ? 0 : Float.parseFloat(telaPagamento.getTxtOutros().getText());
	        float totalPagar = Float.parseFloat(telaPagamento.lblTotalPagar.getText());
			
			   if (telaPagamento.comboBox.getSelectedItem() == null) {
	               new TelaMensagens("O campo 'Cliente' é obrigatório.", 3);
	               return;
	           }

	           if (telaPagamento.comboBox_1.getSelectedItem() == null) {
	               new TelaMensagens("O campo 'Vendedor' é obrigatório.", 3);
	               return;
	           }

	           // Verificar o valor total recebido
	           float totalRecebido = dinheiro + cartao + outros;

	           // Tolerância para erros de arredondamento (0.01)
	           final float TOLERANCIA = 0.01f;

	           // Caso o valor total recebido seja inferior ao total a pagar, considerando a tolerância
	           if (totalRecebido < totalPagar - TOLERANCIA) {
	               new TelaMensagens("Valor insuficiente para realizar a compra!", 3);
	               telaPagamento.getTextField().setText("Valor insuficiente");
	               return;
	           }

	           // Verificar se o valor do cartão é negativo
	           if (cartao < 0) {
	               new TelaMensagens("O valor do cartão não pode ser negativo.", 3);
	               return;
	           }

	           // Verificar se o valor do cartão é maior do que o total a pagar
	           if (cartao > totalPagar) {
	               new TelaMensagens("O valor do cartão é superior ao total a pagar. Ajuste o valor.", 3);
	               return;
	           }

	           // Calcular o troco
	           float troco = totalRecebido - totalPagar;


	           telaPagamento.getTextField().setText(String.format("%.2f", troco));


	           new TelaMensagens("Pagamento confirmado com sucesso!", 0);
	       } catch (NumberFormatException e) {
	           new TelaMensagens("Por favor, insira valores válidos.", 3);
	       }
		
		boolean confirma = itemDAO.efetuaVenda(idCliente, precoTotal, cpfFuncionario);

		if (confirma)
			JOptionPane.showMessageDialog(telaPagamento, "Venda Efetuada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(telaPagamento, "Houve algum problema :(", "Erro",
					JOptionPane.INFORMATION_MESSAGE);

	}

	private void buscarClientes() {

		System.out.println("Método buscarClientes() foi chamado!");
		List<Cliente> clientes = clienteDAO.listarClientes();
		telaPagamento.setClientes(clientes);

	}

	private void buscarFuncionarios() {
		List<Funcionario> funcionarios = funcionarioDAO.listarFuncionarios();
		telaPagamento.setFuncionarios(funcionarios);
	}


	private void cancelarPagamento() {
		// Confirmação de cancelamento
		int confirmacao = JOptionPane.showConfirmDialog(telaPagamento,
				"Tem certeza de que deseja cancelar o pagamento?", "Confirmar", JOptionPane.YES_NO_OPTION);
		if (confirmacao == JOptionPane.YES_OPTION) {
			// Limpar os campos de entrada
			telaPagamento.getTxtDinheiro().setText("");
			telaPagamento.getTxtCartao().setText("");
			telaPagamento.getTxtOutros().setText("");

			JOptionPane.showMessageDialog(telaPagamento, "Pagamento cancelado.", "Cancelado",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void setItemDAO(ItemController itemControle) {
		this.itemControle = itemControle;
		float total = this.itemControle.itemDAO.getTotal();
		telaPagamento.lblTotalPagar.setText(String.valueOf(total));
	}

	private String getCpfCliente() {
		Cliente clienteSelecionado = (Cliente) telaPagamento.getCboxCliente().getSelectedItem();
		if (clienteSelecionado != null) {
			String id = clienteSelecionado.getCpf();
			return id;
		}
		else
		return null;

	}
	
	private String getCpfFuncionario() {
		Funcionario funcionarioSelecionado = (Funcionario) telaPagamento.getCboxFuncionario().getSelectedItem();
		if (funcionarioSelecionado != null) {
			String cpf = funcionarioSelecionado.getCpf();
			return cpf;
		}
		else
		return null;
	}
}

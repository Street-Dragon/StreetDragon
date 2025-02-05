package controle.entidade.pagamentocontrole;

import visao.TelaPagamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controle.entidade.item.ItemController;
import controle.entidade.promocaocontrole.PromocaoControle;
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
    
    protected void efetuarVenda() {
		// FUNÇÃO PRA TERMINAR A VENDA
    	
    	int idCliente = 1;
    	float precoTotal = 1;
    	String cpfFuncionario = "123";
    	
    	boolean confirma = itemDAO.efetuaVenda(idCliente, precoTotal, cpfFuncionario);
    	
    	
    	if(confirma)
    		JOptionPane.showMessageDialog(telaPagamento, "Venda Efetuada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    	else
    		JOptionPane.showMessageDialog(telaPagamento, "Houve algum problema :(", "Erro", JOptionPane.INFORMATION_MESSAGE);
		
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
    
    private void confirmarPagamento() {
        // Aqui você pode implementar a lógica para confirmar o pagamento
        String dinheiro = telaPagamento.getTxtDinheiro().getText();
        String cartao = telaPagamento.getTxtCartao().getText();
        String outros = telaPagamento.getTxtOutros().getText();

        // Exemplo de validação básica
        if (dinheiro.isEmpty() && cartao.isEmpty() && outros.isEmpty()) {
            JOptionPane.showMessageDialog(telaPagamento, "Por favor, insira pelo menos uma forma de pagamento.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(telaPagamento, "Pagamento confirmado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void cancelarPagamento() {
        // Confirmação de cancelamento
        int confirmacao = JOptionPane.showConfirmDialog(telaPagamento, "Tem certeza de que deseja cancelar o pagamento?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            // Limpar os campos de entrada
            telaPagamento.getTxtDinheiro().setText("");
            telaPagamento.getTxtCartao().setText("");
            telaPagamento.getTxtOutros().setText("");

            JOptionPane.showMessageDialog(telaPagamento, "Pagamento cancelado.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }



	public void setItemDAO(ItemController itemControle) {
		this.itemControle = itemControle;		
		float total = this.itemControle.itemDAO.getTotal();
		telaPagamento.lblTotalPagar.setText(String.valueOf(total));
	}
}

	
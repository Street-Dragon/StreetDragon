package controle.entidade.pagamentocontrole;

import visao.TelaPagamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import controle.entidade.item.ItemController;
import controle.entidade.promocaocontrole.PromocaoControle;
import modelo.dao.cliente.ClienteDAO;
import modelo.entidade.pessoa.cliente.Cliente;


public class TelaPagamentoControle {

    private TelaPagamento telaPagamento;
    private ClienteDAO clienteDAO;
    private ItemController itemControle;
    private PromocaoControle promocaoControle;

    public TelaPagamentoControle(TelaPagamento telaPagamento) {
        this.telaPagamento = telaPagamento;
        adicionarListeners();
    }

    private void adicionarListeners() {
    	
    	// listener para pegar os clientes cadastrados
        telaPagamento.getBtnConfirmar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("aaaa");
            }
        });
    	
    	
        // Listener para o botão Confirmar
        telaPagamento.getBtnConfirmar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("aaaa");
            }
        });

        telaPagamento.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("bbbb");
            }
        });

    }
    
    private void buscandoClientes() {
        List<Cliente> clientes = clienteDAO.listarClientes();
        for (Cliente cliente : clientes) {
        	telaPagamento.setComboBox(cliente);
            //comboBoxClientes.addItem(cliente);
        }
   
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
}

	
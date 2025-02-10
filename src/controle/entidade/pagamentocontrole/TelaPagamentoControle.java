package controle.entidade.pagamentocontrole;

import visao.TelaMensagens;
import visao.TelaPagamento;

import java.util.List;

import controle.entidade.item.ItemController;
import modelo.dao.cliente.ClienteDAO;
import modelo.dao.funcionario.FuncionarioDAO;
import modelo.entidade.pessoa.cliente.Cliente;
import modelo.entidade.pessoa.funcionario.Funcionario;
public class TelaPagamentoControle {

    private TelaPagamento telaPagamento;
    private ClienteDAO clienteDAO;
    private FuncionarioDAO funcionarioDAO;
    private ItemController itemControle;

    public TelaPagamentoControle(TelaPagamento telaPagamento) {
        this.telaPagamento = telaPagamento;
        this.clienteDAO = new ClienteDAO();
        this.funcionarioDAO = new FuncionarioDAO();
        buscarClientes();
        buscarFuncionarios();
        adicionarListeners();
    }

    private void adicionarListeners() {
        telaPagamento.getBtnConfirmar().addActionListener(e -> confirmarPagamento());
        telaPagamento.getBtnCancelar().addActionListener(e -> cancelarPagamento());
    }
    
    private void buscarClientes() {
        List<Cliente> clientes = clienteDAO.listarClientes();
        telaPagamento.setClientes(clientes);
    }
    

    private void buscarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioDAO.listarFuncionarios();
        telaPagamento.setFuncionarios(funcionarios);
    }
    
    private void confirmarPagamento() {
        try {
            float dinheiro = telaPagamento.getTxtDinheiro().getText().isEmpty() ? 0 : Float.parseFloat(telaPagamento.getTxtDinheiro().getText());
            float cartao = telaPagamento.getTxtCartao().getText().isEmpty() ? 0 : Float.parseFloat(telaPagamento.getTxtCartao().getText());
            float outros = telaPagamento.getTxtOutros().getText().isEmpty() ? 0 : Float.parseFloat(telaPagamento.getTxtOutros().getText());
            float totalPagar = Float.parseFloat(telaPagamento.lblTotalPagar.getText());
            
            //Tome verificação
            
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
    }



    private void cancelarPagamento() {
        TelaMensagens Tm = new TelaMensagens("Tem certeza de que deseja cancelar o pagamento?");
        if (Tm.getResposta()) {
            telaPagamento.getTxtDinheiro().setText("");
            telaPagamento.getTxtCartao().setText("");
            telaPagamento.getTxtOutros().setText("");
        }
    }

    public void atualizarClientes() {
        buscarClientes();
    }

    public void setItemDAO(ItemController itemControle) {
        this.itemControle = itemControle;      
        float total = this.itemControle.itemDAO.getTotal();
        telaPagamento.lblTotalPagar.setText(String.valueOf(total));
    }
}

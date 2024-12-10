package controle.entidade.vendacontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.dao.venda.VendaDAO;
import modelo.entidade.venda.Venda;
import visao.TelaPagamento;
import visao.TelaVenda;

public class VendaController {
	
	private TelaPagamento telaPagamento;
	private VendaDAO vendaDAO = new VendaDAO();

	public void setTelaPagamento(TelaPagamento telaPagamento) {
	    this.telaPagamento = telaPagamento;

	    telaPagamento.getBtnConfirmar().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // realizarVenda();
	        	// tem que apagar a vendas pedentes tbm;
	        }
	    });

	    telaPagamento.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPagamento.setVisible(false);
            }
        });
    }
	
    public void realizarVenda(Venda venda) {
        vendaDAO.salvarVenda(venda);
        // Lógica adicional para processar o pagamento
    }

}

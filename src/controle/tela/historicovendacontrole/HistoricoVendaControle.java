package controle.tela.historicovendacontrole;

import modelo.dao.historicovenda.HistoricoVendaDAO;
import modelo.entidade.pessoa.fornecedor.Fornecedor;
import modelo.entidade.promocao.Promocao;
import modelo.entidade.venda.Venda;
import visao.TelaHistoricoVenda;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class HistoricoVendaControle {

    private HistoricoVendaDAO historicoVendaDAO;
    private TelaHistoricoVenda tela;

    
    public HistoricoVendaControle(TelaHistoricoVenda tela) {
        this.historicoVendaDAO = new HistoricoVendaDAO();
        this.tela = tela;
        listarTodasVendas();
    }

    public List<Venda> buscarVendasPorCodigo(String codigo) {
        try {
            List<Venda> vendas = historicoVendaDAO.buscarPorCodigo(codigo);
            System.out.println("Vendas encontradas no dao: " + vendas.size());
            for (Venda venda : vendas) {
            }

            atualizarTabela();

            return vendas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Venda> buscarVendasPorData(String data) {
        try {
            List<Venda> vendas = historicoVendaDAO.buscarPorData(data);

            for (Venda venda : vendas) {
            }

            atualizarTabela();

            return vendas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Venda> listarTodasVendas() {
        try {
            List<Venda> vendas = historicoVendaDAO.listarTodasVendas();

            for (Venda venda : vendas) {
            	System.out.println(venda.getCodigoVenda());
            }

            atualizarTabela();

            return vendas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizarTabela() {
    	System.out.println("cu");
        try {
            List<Venda> vendas = historicoVendaDAO.listarTodasVendas(); 
            
            DefaultTableModel  tabela = new DefaultTableModel();
            tabela.addColumn("Código");
            tabela.addColumn("Funcionario");
            tabela.addColumn("Valor");
            tabela.addColumn("Data da venda");
              
            
            for (Venda venda : vendas) {
            	System.out.println(venda.getCodigoVenda());
            	tabela.addRow(new Object[] {
                		venda.getCodigoVenda(), 
                		venda.getDataVenda(), 
                		venda.getFuncionarioCpf(), 
                		venda.getPrecoTotal(),
                		});
            }
           tela.getTable().setModel(tabela);
        } catch (SQLException e) {
            e.printStackTrace();  
        }
    }

}

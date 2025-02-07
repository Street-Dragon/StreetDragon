package controle.tela.historicovendacontrole;

import modelo.dao.historicovenda.HistoricoVendaDAO;
import modelo.entidade.pessoa.fornecedor.Fornecedor;
import modelo.entidade.promocao.Promocao;
import modelo.entidade.venda.Venda;
import visao.TelaHistoricoVenda;
import visao.TelaMensagens;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class HistoricoVendaControle {

    private HistoricoVendaDAO historicoVendaDAO;
    private TelaHistoricoVenda tela;

    
    public HistoricoVendaControle(TelaHistoricoVenda tela) {
        this.historicoVendaDAO = new HistoricoVendaDAO();
        this.tela = tela;
        listarTodasVendas();
        
        tela.getBtnConsultar().addActionListener(e -> {
            String filtroSelecionado = tela.getComboBox().getSelectedItem().toString();
            String valorPesquisa = tela.getTextFieldConsulta().getText().trim();

            if (!valorPesquisa.isEmpty()) {
                aplicarFiltro(filtroSelecionado, valorPesquisa);
            } else {
            	TelaMensagens Tm = new TelaMensagens("Você precisa inserir dados para prosseguir", 3);
            }
        });
    }
    
    private void aplicarFiltro(String filtro, String valor) {
        List<Venda> vendasFiltradas = null;
       
        try {
            switch (filtro) {
                case "Codigo":
                    vendasFiltradas = historicoVendaDAO.buscarPorCodigo(valor);
                    break;
              
                case "Valor Total":
                    vendasFiltradas = historicoVendaDAO.buscarPorValorTotal(Float.parseFloat(valor));
                    
                    break;
                case "Funcionário":
                    // Aqui, passamos o CPF como uma String
                	System.out.println("Buscando por CPF: " + valor);
                    vendasFiltradas = historicoVendaDAO.buscarPorFuncionario(valor);
                    break;
            }

            if (vendasFiltradas != null) {
                atualizarTabelaComVendas(vendasFiltradas);
            } else {
                System.out.println("Союз Нерушимых Свободных Республик");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
    	System.out.println("u");
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
                		venda.getFuncionarioCpf(),
                		venda.getPrecoTotal(),
                		venda.getDataVenda(), 
                		});
            }
           tela.getTable().setModel(tabela);
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        
    }
   
    private void atualizarTabelaComVendas(List<Venda> vendas) {
        DefaultTableModel tabela = new DefaultTableModel();
        tabela.addColumn("Código");
        tabela.addColumn("Funcionário");
        tabela.addColumn("Valor");
        tabela.addColumn("Data da Venda");

        for (Venda venda : vendas) {
            tabela.addRow(new Object[]{
                venda.getCodigoVenda(),
                venda.getFuncionarioCpf(),
                venda.getPrecoTotal(),
                venda.getDataVenda()
            });
        }
        tela.getTable().setModel(tabela);
    }


}

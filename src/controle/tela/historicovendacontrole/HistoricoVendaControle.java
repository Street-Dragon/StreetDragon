package controle.tela.historicovendacontrole;

import modelo.dao.historicovenda.HistoricoVendaDAO;
import modelo.entidade.venda.Venda;
import visao.TelaHistoricoVenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class HistoricoVendaControle {

    private TelaHistoricoVenda telaHistoricoVenda;
    private HistoricoVendaDAO historicoVendaDAO;

    public HistoricoVendaControle(TelaHistoricoVenda telaHistoricoVenda) {
        this.telaHistoricoVenda = telaHistoricoVenda;
        this.historicoVendaDAO = new HistoricoVendaDAO();

        // Carregar todas as vendas ao iniciar
        carregarVendas();

        // Configurar evento do botão "Consultar"
        telaHistoricoVenda.getBtnConsultar().addActionListener(e -> consultarVendas());
    }

    // Método para carregar todas as vendas
    public void carregarVendas() {
        try {
            List<Venda> vendas = historicoVendaDAO.listarTodasVendas();
            atualizarTabela(vendas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar vendas com base no filtro
    public void consultarVendas() {
        String consulta = telaHistoricoVenda.getTextFieldConsulta().getText().trim();
        String filtro = (String) telaHistoricoVenda.getComboBox().getSelectedItem();

        List<Venda> vendas = null;

        try {
            switch (filtro) {
                case "Codigo":
                    vendas = historicoVendaDAO.buscarPorCodigo(consulta);
                    break;
                case "Nome":
                    vendas = historicoVendaDAO.buscarPorNomeCliente(consulta);
                    break;
                case "Data":
                    vendas = historicoVendaDAO.buscarPorData(consulta);
                    break;
                default:
                    vendas = historicoVendaDAO.listarTodasVendas();
                    break;
            }
            atualizarTabela(vendas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atualiza a tabela com a lista de vendas
    private void atualizarTabela(List<Venda> vendas) {
        DefaultTableModel model = (DefaultTableModel) telaHistoricoVenda.getTable().getModel();
        model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados

        for (Venda venda : vendas) {
            model.addRow(new Object[]{
                venda.getCodigoVenda(),
                venda.getPrecoTotal(),
                venda.getDataVenda()
            });
        }
    }
}

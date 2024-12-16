package controle.tela.historicovendacontrole;

import modelo.dao.historicovenda.HistoricoVendaDAO;
import modelo.entidade.venda.Venda;
import visao.TelaHistoricoVenda;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class HistoricoVendaControle {

    private TelaHistoricoVenda telaHistoricoVenda;
    private HistoricoVendaDAO historicoVendaDAO;

    public HistoricoVendaControle(TelaHistoricoVenda telaHistoricoVenda) {
        this.telaHistoricoVenda = telaHistoricoVenda;
        this.historicoVendaDAO = new HistoricoVendaDAO();
    }

    // Atualiza a tabela com as vendas
    public void atualizarTabela(List<Venda> vendas) {
        DefaultTableModel model = (DefaultTableModel) telaHistoricoVenda.getTable().getModel();
        model.setRowCount(0);
        for (Venda venda : vendas) {
            model.addRow(new Object[]{venda.getCodigoVenda(), venda.getPrecoTotal(), venda.getDataVenda()});
        }
    }

    // Método para buscar vendas com base no filtro
    public void pesquisarVendas() {
        String textoConsulta = telaHistoricoVenda.getTextFieldConsulta().getText().trim();
        String filtroSelecionado = "";

        if (telaHistoricoVenda.getRdbtnCodigo().isSelected()) {
            filtroSelecionado = "codigo";
        } else if (telaHistoricoVenda.getRdbtnNome().isSelected()) {
            filtroSelecionado = "nome";
        } else if (telaHistoricoVenda.getRdbtnnData().isSelected()) {
            filtroSelecionado = "data";
        }

        List<Venda> vendas = null;
        try {
            switch (filtroSelecionado) {
                case "codigo":
                    vendas = historicoVendaDAO.buscarPorCodigo(textoConsulta);
                    break;
                case "nome":
                    vendas = historicoVendaDAO.buscarPorNomeCliente(textoConsulta);
                    break;
                case "data":
                    vendas = historicoVendaDAO.buscarPorData(textoConsulta);
                    break;
                default:
                    vendas = historicoVendaDAO.listarTodasVendas();
            }
            atualizarTabela(vendas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

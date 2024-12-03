package controle.tela.historicovendacontrole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.DocumentEvent;
import java.awt.event.DocumentListener;
import visao.TelaHistoricoVenda;
import modelo.VendaModel;

public class HistoricoVendaControle {
    private TelaHistoricoVenda historicoVenda;
    private VendaModel vendaModel;

    // Construtor
    public HistoricoVendaControle(TelaHistoricoVenda historicoVenda, VendaModel vendaModel) {
        this.historicoVenda = historicoVenda;
        this.vendaModel = vendaModel;

        // Configurar o DocumentListener para a pesquisa dinâmica
        configurarCampoPesquisa();
    }

    // Método para configurar o DocumentListener no campo de pesquisa
    private void configurarCampoPesquisa() {
        historicoVenda.getTfPesquisa().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                pesquisar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                pesquisar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                pesquisar();
            }
        });
    }

    // Método para executar a pesquisa conforme o filtro e o texto
    private void pesquisar() {
        String textoPesquisa = historicoVenda.getTfPesquisa().getText().trim();  // Remove espaços extras
        String filtroSelecionado = (String) historicoVenda.getCbFiltro().getSelectedItem();
        
        // Se o campo de pesquisa estiver vazio, exibe todas as vendas
        if (textoPesquisa.isEmpty()) {
            // Exibe todas as vendas sem filtro de pesquisa
            historicoVenda.exibirVendas(vendaModel.buscarTodasVendas());
        } else {
            // Obter as vendas filtradas com base no filtro selecionado e no texto de pesquisa
            switch (filtroSelecionado) {
                case "Código":
                    historicoVenda.exibirVendas(vendaModel.buscarPorCodigo(textoPesquisa));
                    break;
                case "Nome":
                    historicoVenda.exibirVendas(vendaModel.buscarPorNome(textoPesquisa));
                    break;
                case "Data":
                    historicoVenda.exibirVendas(vendaModel.buscarPorData(textoPesquisa));
                    break;
                default:
                    historicoVenda.exibirVendas(vendaModel.buscarTodasVendas());
                    break;
            }
        }
    }

}

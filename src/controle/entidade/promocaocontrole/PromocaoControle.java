package controle.entidade.promocaocontrole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.dao.promocao.PromocaoDAO;
import modelo.entidade.promocao.Promocao;
import visao.TelaMensagens;
import visao.TelaPromocao;

public class PromocaoControle {

    private TelaPromocao telaPromocao;
    private PromocaoDAO promocaoDAO = new PromocaoDAO();

    public PromocaoControle(TelaPromocao telaPromocao) {
        this.telaPromocao = telaPromocao;
        configurarBotoes();
        atualizarTabela(); // Garante que a tabela seja atualizada ao iniciar a tela
    }

    private void configurarBotoes() {
        telaPromocao.getBtnCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPromocao();
            }
        });

        telaPromocao.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPromocao();
            }
        });

        telaPromocao.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("e");
                excluirPromocao();
            }
        });
    }

    private void cadastrarPromocao() {
        String nome = telaPromocao.getTxtNome().getText();
        String termino = telaPromocao.getTxtTermino().getText();
        String descontoText = telaPromocao.getTxtDesconto().getText();

        if (nome.isEmpty() || termino.isEmpty() || descontoText.isEmpty()) {
            new TelaMensagens("Todos os campos devem ser preenchidos!", 1);
            return;
        }

        float desconto;
        try {
            desconto = Float.parseFloat(descontoText);
        } catch (NumberFormatException ex) {
            new TelaMensagens("O desconto deve ser um número válido!", 1);
            return;
        }

        Promocao promocao = new Promocao();
        promocao.setNome(nome);
        promocao.setDesconto(desconto);
        promocao.setTermino(termino);

        try {
            promocaoDAO.cadastrarPromocao(promocao);
            new TelaMensagens("Promoção cadastrada com sucesso!", 0);
            
            telaPromocao.getTxtNome().setText("");
            telaPromocao.getTxtTermino().setText("");
            telaPromocao.getTxtDesconto().setText("");
            
            
            atualizarTabela();
        } catch (RuntimeException e) {
            new TelaMensagens("Erro ao cadastrar promoção: " + e.getMessage(), 1);
        }
    }

    private void editarPromocao() {
        int selectedRow = telaPromocao.getTable().getSelectedRow();

        if (selectedRow == -1) {
            new TelaMensagens("Selecione uma promoção para editar!", 3);
            return;
        }

        int id = (int) telaPromocao.getTable().getValueAt(selectedRow, 0);
        String nome = telaPromocao.getTxtNome().getText();
        String termino = telaPromocao.getTxtTermino().getText();
        String descontoText = telaPromocao.getTxtDesconto().getText();

        if (nome.isEmpty() || termino.isEmpty() || descontoText.isEmpty()) {
            new TelaMensagens("Todos os campos devem ser preenchidos!", 1);
            return;
        }

        float desconto;
        try {
            desconto = Float.parseFloat(descontoText);
        } catch (NumberFormatException ex) {
            new TelaMensagens("O desconto deve ser um número válido!", 1);
            return;
        }

        Promocao promocao = new Promocao();
        promocao.setIdPromocao(id);
        promocao.setNome(nome);
        promocao.setDesconto(desconto);
        promocao.setTermino(termino);

        try {
            promocaoDAO.editarPromocao(promocao);
            new TelaMensagens("Promoção editada com sucesso!", 0);
            
            telaPromocao.getTxtNome().setText("");
            telaPromocao.getTxtTermino().setText("");
            telaPromocao.getTxtDesconto().setText("");
            
            
            atualizarTabela();
        } catch (RuntimeException e) {
            new TelaMensagens("Erro ao editar promoção: " + e.getMessage(), 1);
        }
    }

    public void excluirPromocao() {
        int selectedRow = telaPromocao.getTable().getSelectedRow();

        if (selectedRow == -1) {
            new TelaMensagens("Selecione uma promoção para excluir", 3);
            return;
        }

        int id = (int) telaPromocao.getTable().getValueAt(selectedRow, 0);
        TelaMensagens Tm = new TelaMensagens("Tem certeza que deseja excluir esta promoção?");
        if (Tm.getResposta()) {
            try {
            	telaPromocao.getTxtNome().setText("");
                telaPromocao.getTxtTermino().setText("");
                telaPromocao.getTxtDesconto().setText("");
                
                promocaoDAO.excluirPromocao(id);
                new TelaMensagens("Promoção excluída com sucesso!", 1);
                atualizarTabela();
            } catch (Exception e) {
                new TelaMensagens("Erro ao excluir promoção: " + e.getMessage(), 3);
            }
        }
    }

    public void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) telaPromocao.getTable().getModel();
        model.setRowCount(0); // Limpa a tabela

        for (Promocao promocao : promocaoDAO.listarTodos()) {
            model.addRow(new Object[]{
                promocao.getIdPromocao(), 
                promocao.getNome(), 
                promocao.getDesconto(), 
                promocao.getTermino()
            });
        }
    }
    
    public void fillPP(TelaPromocao telaPromocao) {
        telaPromocao.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable table = telaPromocao.getTable();
                int selectedRowIndex = table.getSelectedRow();
                if (selectedRowIndex == -1) {
                } else {
                    Promocao promocao = new Promocao();
                    String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
                    promocao = promocaoDAO.getIdPromocao(Integer.valueOf(firstColumnValue)); // Aqui você busca a promoção pelo ID
                    telaPromocao.getTxtNome().setText(promocao.getNome()); // Preenche o campo Nome
                    telaPromocao.getTxtDesconto().setText(String.valueOf(promocao.getDesconto())); // Preenche o campo Desconto
                    telaPromocao.getTxtTermino().setText(promocao.getTermino()); // Preenche o campo Término
                }
            }
        });
    }

    
    
}

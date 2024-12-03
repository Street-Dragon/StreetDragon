package controle.entidade.promocaocontrole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.dao.promocao.PromocaoDAO;
import modelo.entidade.promocao.Promocao;
import visao.TelaPromocao;
import javax.swing.table.DefaultTableModel;

public class PromocaoControle {
    
    private TelaPromocao telaPromocao;
    private PromocaoDAO promocaoDAO = new PromocaoDAO();
    
    
    public PromocaoControle(TelaPromocao telaPromocao) {
        this.telaPromocao = telaPromocao;
        configurarBotoes();

    }

  
    private void configurarBotoes() {
    
        telaPromocao.getBtnCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("a");
                cadastrarPromocao();
                
            }
        });

        telaPromocao.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 System.out.println("a");
                editarPromocao();
            }
        });

      
        telaPromocao.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("a");
                excluirPromocao();
            }
        });
    }

   
    private void cadastrarPromocao() {
        String nome = telaPromocao.getTxtNome().getText();
        String termino = telaPromocao.getTxtTermino().getText();
        String descontoText = telaPromocao.getTxtDesconto().getText();
        
        if (nome.isEmpty() || termino.isEmpty() || descontoText.isEmpty()) {
            JOptionPane.showMessageDialog(telaPromocao, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        float desconto;
        try {
            desconto = Float.parseFloat(descontoText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(telaPromocao, "O desconto deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Promocao promocao = new Promocao();
        promocao.setNome(nome);
        promocao.setDesconto(desconto);
        promocao.setTermino(termino);

        try {
            promocaoDAO.cadastrarPromocao(promocao);  // O método já foi corrigido no DAO
            JOptionPane.showMessageDialog(telaPromocao, "Promoção cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarTabela();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(telaPromocao, "Erro ao cadastrar promoção: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    private void editarPromocao() {
        int selectedRow = telaPromocao.getTable().getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(telaPromocao, "Selecione uma promoção para editar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) telaPromocao.getTable().getValueAt(selectedRow, 0);
        String nome = telaPromocao.getTxtNome().getText();
        String termino = telaPromocao.getTxtTermino().getText();
        String descontoText = telaPromocao.getTxtDesconto().getText();
        
        if (nome.isEmpty() || termino.isEmpty() || descontoText.isEmpty()) {
            JOptionPane.showMessageDialog(telaPromocao, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        float desconto;
        try {
            desconto = Float.parseFloat(descontoText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(telaPromocao, "O desconto deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Promocao promocao = new Promocao();
        promocao.setIdPromocao(id);
        promocao.setNome(nome);
        promocao.setDesconto(desconto);
        promocao.setTermino(termino);

        try {
            promocaoDAO.editarPromocao(promocao);  
            JOptionPane.showMessageDialog(telaPromocao, "Promoção editada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarTabela();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(telaPromocao, "Erro ao editar promoção: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void excluirPromocao() {
        int selectedRow = telaPromocao.getTable().getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(telaPromocao, "Selecione uma promoção para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) telaPromocao.getTable().getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(telaPromocao, "Tem certeza que deseja excluir esta promoção?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                promocaoDAO.excluirPromocao(id);
                JOptionPane.showMessageDialog(telaPromocao, "Promoção excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarTabela();
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(telaPromocao, "Erro ao excluir promoção: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

       private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) telaPromocao.getTable().getModel();
        model.setRowCount(0); 
        
     
        for (Promocao promocao : promocaoDAO.listarTodos()) {
            model.addRow(new Object[]{promocao.getIdPromocao(), promocao.getNome(), promocao.getDesconto(), promocao.getTermino()});
        }
    }
}

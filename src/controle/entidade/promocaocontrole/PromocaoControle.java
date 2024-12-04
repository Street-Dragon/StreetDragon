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
        atualizarTabela(); 
        fillPP(telaPromocao);
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
        String inicio = telaPromocao.getTxtInicio().getText();
        String descontoText = telaPromocao.getTxtDesconto().getText();
        // Captura a categoria selecionada no ComboBox
        String categoria = (String) telaPromocao.getComboBoxCategoria().getSelectedItem(); 

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
        promocao.setInicio(inicio);
        // Aqui você pode decidir se vai ou não associar a categoria. Se não for associada, basta exibir na UI.

        try {
            promocaoDAO.cadastrarPromocao(promocao); // Apenas cadastra os dados sem categoria
            new TelaMensagens("Promoção cadastrada com sucesso!", 0);

            // Limpa os campos após cadastro
            telaPromocao.getTxtNome().setText("");
            telaPromocao.getTxtTermino().setText("");
            telaPromocao.getTxtDesconto().setText("");
            telaPromocao.getTxtInicio().setText("");
            telaPromocao.getComboBoxCategoria().setSelectedIndex(0);

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
        String inicio = telaPromocao.getTxtInicio().getText();
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
        promocao.setInicio(inicio);

        try {
            promocaoDAO.editarPromocao(promocao);
            new TelaMensagens("Promoção editada com sucesso!", 0);
            
            telaPromocao.getTxtNome().setText("");
            telaPromocao.getTxtTermino().setText("");
            telaPromocao.getTxtDesconto().setText("");
            telaPromocao.getTxtInicio().setText("");      
            
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
                telaPromocao.getTxtInicio().setText("");
                
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

        // Agora, ao invés de pegar a categoria do ComboBox, pegue a categoria de cada promoção (se houver).
        for (Promocao promocao : promocaoDAO.listarTodos()) {
            String categoria = "Categoria não definida"; // Categoria padrão se não estiver associada

            // Aqui você pode tratar a categoria da forma que for necessária. Caso ela não seja associada,
            // a categoria será exibida como "Categoria não definida".
            
            model.addRow(new Object[]{
                promocao.getIdPromocao(), 
                promocao.getNome(), 
                promocao.getDesconto(), 
                promocao.getTermino(),
                promocao.getInicio(),
                categoria // Categoria associada a cada promoção, se for necessário
            });
        }
    }

    
    public void fillPP(TelaPromocao telaPromocao) {
        telaPromocao.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable table = telaPromocao.getTable();
                int selectedRowIndex = table.getSelectedRow(); // Pega o índice da linha selecionada
                
                // Verifica se o clique foi em uma linha válida (não cabeçalho)
                if (selectedRowIndex != -1) {
                    // Obtém o ID da promoção na primeira coluna da linha selecionada
                    String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
                    
                    // Busca a promoção no banco de dados ou repositório usando o ID
                    Promocao promocao = promocaoDAO.getIdPromocao(Integer.valueOf(firstColumnValue)); 
                    
                    // Preenche os campos com os dados da promoção seleciona
                    telaPromocao.getTxtNome().setText(promocao.getNome()); 
                    telaPromocao.getTxtDesconto().setText(String.valueOf(promocao.getDesconto())); 
                    telaPromocao.getTxtTermino().setText(promocao.getTermino()); 
                    telaPromocao.getTxtInicio().setText(promocao.getInicio());
                }
            }
        });
    }


    
    
}

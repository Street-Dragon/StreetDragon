package controle.entidade.promocaocontrole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.dao.promocao.PromocaoDAO;
import modelo.entidade.produto.Produto;
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
        String categoria = telaPromocao.getcomboBoxCategoria();

        if (nome.isEmpty() || termino.isEmpty() || descontoText.isEmpty() || inicio.isEmpty()) {
            new TelaMensagens("Todos os campos devem ser preenchidos!", 1);
            return;
        }

        if (!ValidarData(inicio)) {
            new TelaMensagens("Data de início inválida! O formato correto é dd/MM/yyyy.", 1);
            return;
        }

        if (!ValidarData(termino)) {
            new TelaMensagens("Data de término inválida! O formato correto é dd/MM/yyyy.", 1);
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
        promocao.setCategoria(categoria);

        try {
            promocaoDAO.cadastrarPromocao(promocao); 
            new TelaMensagens("Promoção cadastrada com sucesso!", 0);

            // Limpa os campos após o cadastro
            telaPromocao.getTxtNome().setText("");
            telaPromocao.getTxtTermino().setText("");
            telaPromocao.getTxtDesconto().setText("");
            telaPromocao.getTxtInicio().setText("");

            // Atualiza a tabela
            atualizarTabela();
        } catch (RuntimeException e) {
            new TelaMensagens("Erro ao cadastrar promoção: " + e.getMessage(), 1);
        }
    }

  
    private boolean ValidarData(String data) {
        if (data == null || data.length() != 10) {
            return false;
        }

        if (data.charAt(2) != '/' || data.charAt(5) != '/') {
            return false;
        }

        String day = data.substring(0, 2);
        String month = data.substring(3, 5);
        String year = data.substring(6, 10);

        if (!day.matches("\\d{2}") || !month.matches("\\d{2}") || !year.matches("\\d{4}")) {
            return false;
        }

        int diaInt = Integer.parseInt(day);
        int mesInt = Integer.parseInt(month);
        int anoInt = Integer.parseInt(year);

        if (mesInt < 1 || mesInt > 12) {
            return false;
        }

        if (diaInt < 1 || diaInt > 31) {
            return false;
        }

        if ((mesInt == 4 || mesInt == 6 || mesInt == 9 || mesInt == 11) && mesInt > 30) {
            return false;
        }

        if (mesInt == 2) {
            if (anoBisexto(anoInt)) {
                if (diaInt > 29) {
                    return false;
                }
            } else {
                if (diaInt > 28) {
                    return false;
                }
            }
        }

        return true;
    }

  
    private boolean anoBisexto(int ano) {
        return (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0));
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
        
        
        String categoria = telaPromocao.getcomboBoxCategoria();

        if (nome.isEmpty() || termino.isEmpty() || descontoText.isEmpty()) {
            new TelaMensagens("Todos os campos devem ser preenchidos!", 1);
            return;
        } if (!ValidarData(inicio)) {
            new TelaMensagens("Data de início inválida! O formato correto é dd/MM/yyyy.", 1);
            return;
        }

        if (!ValidarData(termino)) {
            new TelaMensagens("Data de término inválida! O formato correto é dd/MM/yyyy.", 1);
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
        promocao.setCategoria(categoria);  

        try {
            promocaoDAO.editarPromocao(promocao);
            new TelaMensagens("Promoção editada com sucesso!", 0);
            
            // Limpa os campos após edição
            telaPromocao.getTxtNome().setText("");
            telaPromocao.getTxtTermino().setText("");
            telaPromocao.getTxtDesconto().setText("");
            telaPromocao.getTxtInicio().setText("");
           
            
            // Atualiza a tabela após edição
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
                new TelaMensagens("Promoção excluída com sucesso!", 0);
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
                promocao.getTermino(),
                promocao.getInicio(),
                promocao.getCategoria(),
                
            });
        }
    }
    public void CheckBoxF(Promocao promocao) {
    	String Categoria = promocao.getCategoria();
    	int index2 = 0;
    	
    	
    	switch(Categoria) {
		case"Calça":
			index2 = 1;
			break;
		case"Camisa":
			index2 = 2;
		break;
		case"Camiseta":
			index2 = 3;
		break;
		case"Moleton":
			index2 = 4;
		break;
		case"Boné":
			index2 = 5;
		break;
		case"Toca":
			index2 = 6;
		break;
		case"Tênis":
			index2 = 7;
		break;
		case"Acessórios":
			index2 = 8;
		break;
		case"Outro":
			index2 = 9;
		break;
		}
    	telaPromocao.setComboBoxCategoria().setSelectedIndex(index2);
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
                    CheckBoxF(promocao);
                }
            }
        });
    }


}

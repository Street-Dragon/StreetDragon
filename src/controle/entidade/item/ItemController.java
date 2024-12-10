package controle.entidade.item;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import modelo.dao.item.ItemDAO;
import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.item.Item;
import modelo.entidade.produto.Produto;
import visao.TelaMensagens;
import visao.TelaVenda; 


public class ItemController { 
	private TelaVenda telaVenda; 
	private ItemDAO itemDAO = new ItemDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public void setTelaVenda(TelaVenda telaVenda) {
		this.telaVenda = telaVenda;
		atualizarTabela();
		
		telaVenda.getBtnAdicionarProduto().addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				
				adicionar();
				atualizarTabela();
			}
		});
		
		telaVenda.getBtnLimparCarrinho().addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				
				excluirTudo();
				atualizarTabela();
			}
		});
		
		telaVenda.getBtnRemoverProduto().addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				
				excluir();
				atualizarTabela();
			}
		});
		
		
	}

	protected void adicionar() {
		
		int quantidade = Integer.parseInt(telaVenda.getTxtQuantidade());
        int idProduto = Integer.parseInt(telaVenda.getTxtCodigo());

        
        Produto produto = produtoDAO.getId(idProduto);

        if (produto == null) {
            new TelaMensagens("Produto não encontrado no sistema.",1);
            return;
        }

        if (quantidade <= 0) {
            new TelaMensagens("Quantidade deve ser maior que zero.",1);
            return;
        }

        Item item = new Item();
        item.setQuantidade(quantidade);
        item.setProduto(produto); 

        
        boolean sucesso = itemDAO.cadastrarItem(item);

        if (sucesso) {
            new TelaMensagens("Item cadastrado com sucesso!",0);
        } else {
            new TelaMensagens("Erro ao cadastrar o Item.",1);
        }
	}
	
	  public void atualizarTabela() {
	        List<Item> itens = itemDAO.listarItens(); 

	        DefaultTableModel tableModel;
	        tableModel = new DefaultTableModel();
	        tableModel.addColumn("Código");
	        tableModel.addColumn("Nome");
	        tableModel.addColumn("Quantidade");
	        tableModel.addColumn("Valor Total");

	        
	        for (Item item : itens) {
	            Produto produto = item.getProduto(); 
	            double valorTotal = item.getQuantidade() * produto.getValor(); 
	            
	            tableModel.addRow(new Object[] {
		            produto.getIdProduto(),       
	                produto.getNomeProduto(),     
	                item.getQuantidade(),  
	                String.format("%.2f", valorTotal)
	            });
	        }

	        
	        telaVenda.getTable().setModel(tableModel); 
	    }

	protected void excluirTudo() {
		System.out.println("Exclui tudo");
		try {
			itemDAO.excluirTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        atualizarTabela();
		
	}

	protected void excluir() {
		int selectedRow = telaVenda.getTable().getSelectedRow(); // Pega a linha selecionada na tabela

	    if (selectedRow != -1) {
	        // Pega o ID do item da tabela (assumindo que o ID do item está na primeira coluna)
	        int idItem = (Integer) telaVenda.getTable().getValueAt(selectedRow, 0);
	        TelaMensagens tm = new TelaMensagens("Você tem certeza que deseja excluir o item com ID: " + idItem + "?");
//funciona ate aq
	        if (tm.getResposta()) {

	            // Chama o método excluirItem do itemDAO para excluir o item do banco
	            boolean excluido = itemDAO.excluirItem(idItem);

	            if (excluido) {
	                new TelaMensagens("Item excluído com sucesso!",0);
	                atualizarTabela(); // Atualiza a tabela após a exclusão
	            } else {
	                new TelaMensagens("Erro ao excluir o Item.",1);
	            }
	        }
	    } else {
	        // Se nenhuma linha for selecionada
	        new TelaMensagens("Selecione um Item para excluir.",3);
	    }
		
	}
}
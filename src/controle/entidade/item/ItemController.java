package controle.entidade.item;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.dao.item.ItemDAO;
import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.item.Item;
import modelo.entidade.produto.Produto;
import visao.TelaVenda; 


public class ItemController { 
	private TelaVenda telaVenda; 
	private ItemDAO itemDAO = new ItemDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public void setTelaVenda(TelaVenda telaVenda) {
		this.telaVenda = telaVenda;
		
		telaVenda.getBtnAdicionarProduto().addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				
				adicionar();
			}
		});
		
		telaVenda.getBtnLimparCarrinho().addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				
				excluirTudo();
			}
		});
		
		telaVenda.getBtnRemoverProduto().addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				
				excluir();
			}
		});
		
		
	}

	protected void adicionar() {
		
		int quantidade = Integer.parseInt(telaVenda.getTxtQuantidade());
        int idProduto = Integer.parseInt(telaVenda.getTxtCodigo());

        // Verificar se o Produto existe no banco de dados pelo nome
        Produto produto = produtoDAO.getId(idProduto);

        if (produto == null) {
            JOptionPane.showMessageDialog(telaVenda, "Produto não encontrado no sistema.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (quantidade <= 0) {
            JOptionPane.showMessageDialog(telaVenda, "Quantidade deve ser maior que zero.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Item item = new Item();
        item.setQuantidade(quantidade);
        item.setProduto(produto); // Associa o Produto existente ao Item

        // Chama o método do DAO para cadastrar o item, associando-o ao produto existente
        boolean sucesso = itemDAO.cadastrarItem(item);

        if (sucesso) {
            JOptionPane.showMessageDialog(telaVenda, "Item cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(telaVenda, "Erro ao cadastrar o Item.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
	}
	

	protected void excluirTudo() {
		System.out.println("Exclui tudo");
		
	}

	protected void excluir() {
		System.out.println("Excluir");
		
	}
}
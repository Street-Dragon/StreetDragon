package controle.entidade.item;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import modelo.dao.item.ItemDAO;
import modelo.entidade.item.Item;
import visao.TelaVenda; 


public class ItemController { 
	private TelaVenda telaVenda; 
	private ItemDAO itemDAO = new ItemDAO();

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
		System.out.println("Adicionado");
		
	}

	protected void excluirTudo() {
		System.out.println("Exclui tudo");
		
	}

	protected void excluir() {
		System.out.println("Excluir");
		
	}
}
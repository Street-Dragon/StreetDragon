package controle.entidade.item;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import modelo.dao.item.ItemDAO;
import modelo.entidade.item.Item;
import visao.TelaVenda; 


public class ItemController { 
	private TelaVenda view; 
	private ItemDAO itemDAO = new ItemDAO();
	
//	public ItemController(VendaView view) {
//		this.view = view;
//		this.itemDAO = new ItemDAO(); 
//		view.addAdicionarButtonListener(new AdicionarButtonListener()); 
//		} 
//	
//	class AdicionarButtonListener implements ActionListener {
//			@Override public void actionPerformed(ActionEvent e) {
//				Item item = new Item();
//				item.setQuantidade(Integer.parseInt(view.getQuantidadeField())); 
//				item.setIdItem(Integer.parseInt(view.getIdItemField()));
//				item.setIdProduto(Integer.parseInt(view.getIdProdutoField())); 
////				itemDAO.adicionarItem(item); view.atualizarTabela(); 
//			}
//		} 
}

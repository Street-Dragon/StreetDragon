package visao;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import utils.Utils;

public class TelaProdutos extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font hkGrotesk;
	private JButton btnCadastrarProd;
	private JButton btnDeletarProd;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TelaProdutos(TelaPrincipal telaPrincipal) {
		hkGrotesk = Utils.loadCustomFont();
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 3 0,grow");
		panel_1.setBorder(new LineBorder(new Color(246, 233, 233), 5));
		panel_1.setBackground(new Color(255, 255, 255));

		// Botão Cadastrar
		btnCadastrarProd = new JButton("Cadastrar");
		btnCadastrarProd.setForeground(new Color(255, 255, 255));
		btnCadastrarProd.setFont(hkGrotesk);
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		btnCadastrarProd.setBackground(new Color(114, 148, 235));
		panel_1.add(btnCadastrarProd, "cell 0 0,grow");

		// Botão Excluir
		btnDeletarProd = new JButton("Excluir");
		btnDeletarProd.setForeground(new Color(255, 255, 255));
		btnDeletarProd.setFont(hkGrotesk);
		btnDeletarProd.setBackground(new Color(255, 0, 0));
		panel_1.add(btnDeletarProd, "cell 0 2,grow");

		btnDeletarProd.setIcon(Utils.carregarIcone("lixo.png", 30, 30));
		btnCadastrarProd.setIcon(Utils.carregarIcone("editar.png", 30, 30));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 4 3,grow");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Valor", "Qnt estoque" }));
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(255, 233, 233));
		scrollPane.setViewportView(table);

	}
}

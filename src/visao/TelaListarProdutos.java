package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utils.Utils;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import controle.entidade.produto.ProdutoControle;
import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.produto.Produto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListarProdutos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldNome;
	private JButton btnNovoProduto;
	private ProdutoControle Pcontrole;
	
	public JButton getbtnNovoProduto(){
		return btnNovoProduto;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarProdutos frame = new TelaListarProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaListarProdutos() {	
		Pcontrole = new ProdutoControle(this);
		setTitle("Lista de Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Utils.loadCustomFont();

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][grow][grow][grow]", "[grow][][grow]"));

		JLabel lblNewLabel = new JLabel("Consultar Nome");
		panel.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Valor", "Qnt estoque" }));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);		

		textFieldNome = new JTextField();
		panel.add(textFieldNome, "cell 1 0 3 1,growx");
		textFieldNome.setColumns(10);
		
		JButton btnNovoProduto_1 = new JButton("Novo Produto");
		btnNovoProduto_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroProdutos tela = new TelaCadastroProdutos();
				tela.setVisible(true);
				dispose();
			}
		});
		panel.add(btnNovoProduto_1, "cell 1 1,growx");
		
	    Pcontrole = new ProdutoControle(this);
		JButton btnDeletarProduto = new JButton("Deletar Produto");
		btnDeletarProduto.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Pcontrole.setTelaListarProdutos(TelaListarProdutos.this);
	            Pcontrole.DeletProduto(TelaListarProdutos.this);
	        }
	    });
		
		
		panel.add(btnDeletarProduto, "cell 2 1,growx");
		Pcontrole.setTelaListarProdutos(this);

		Pcontrole = new ProdutoControle(this);

        JButton btnEditarProduto = new JButton("Editar Produto");
        btnEditarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = table.getSelectedRow();
                if (selectedRowIndex != -1) {
                    int idProduto = (int) table.getValueAt(selectedRowIndex, 0);
                    Produto selectedProduto = Pcontrole.getProdutoById(idProduto);
                    TelaEditarProduto telaE = new TelaEditarProduto(selectedProduto);
                    Pcontrole.setTelaEditarProduto(telaE);
                    telaE.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(TelaListarProdutos.this, "Nenhum produto selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(btnEditarProduto, "cell 3 1,growx");

        Pcontrole.listarProdutosTable();
    }
	

    public JTable gettable() {
        return table;
    }
}
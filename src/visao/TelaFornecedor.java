package visao;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controle.entidade.fornecedorController.fornecedorController;
import modelo.entidade.pessoa.fornecedor.Fornecedor;
import net.miginfocom.swing.MigLayout;
import utils.Utils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaFornecedor extends JPanel {

    private static final long serialVersionUID = 1L;
    private Font hkGrotesk;
    private JButton btnCadastrarfFor;
    private JButton btnDeletarFor;
    private JTable table;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JTextField txtCnpj;
    private JTextField txtRua;
    private JTextField txtCep;
    private JLabel lblNewLabel;
    private JTextField textNome;
    private JButton btnEditarFor;
    
   
 

    public TelaFornecedor(TelaPrincipal telaPrincipal) {
    	
    	fornecedorController fornecedorController;
      
    	
        fornecedorController = new fornecedorController(this);

        setBackground(new Color(255, 255, 255));
        hkGrotesk = Utils.loadCustomFont();
        setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        add(panel, "cell 0 0 3 1,grow");
        panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow]"));
        
        lblNewLabel_2 = new JLabel("Nome");
        lblNewLabel_2.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblNewLabel_2, "cell 0 0,alignx left,growy");

        textNome = new JTextField();
        textNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        textNome.setColumns(10);
        panel.add(textNome, "cell 1 0,growx");

        lblNewLabel = new JLabel("Rua");
        lblNewLabel.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblNewLabel, "cell 3 0,alignx left,growy");

        txtRua = new JTextField();
        txtRua.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(txtRua, "cell 4 0,growx");
        txtRua.setColumns(10);

        lblNewLabel_1 = new JLabel("CNPJ");
        lblNewLabel_1.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblNewLabel_1, "cell 0 1,alignx left,growy");

        txtCnpj = new JTextField();
        txtCnpj.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(txtCnpj, "cell 1 1,growx");
        txtCnpj.setColumns(10);

        lblNewLabel_3 = new JLabel("CEP");
        lblNewLabel_3.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblNewLabel_3, "cell 3 1,alignx left,growy");

        txtCep = new JTextField();
        txtCep.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(txtCep, "cell 4 1,growx");
        txtCep.setColumns(10);

        JPanel panel_1 = new JPanel();
        add(panel_1, "cell 3 0,grow");
        panel_1.setBackground(new Color(255, 255, 255));

        btnCadastrarfFor = new JButton("Cadastrar");
        btnCadastrarfFor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fornecedor fornecedor = capturarDadosFornecedor();
                fornecedorController.cadastrarFornecedor(fornecedor); // Chamando o Controller para cadastrar
            }
        });
        btnCadastrarfFor.setForeground(new Color(255, 255, 255));
        btnCadastrarfFor.setFont(hkGrotesk);
        panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][][grow][grow][grow]"));
        btnCadastrarfFor.setBackground(new Color(114, 148, 235));
        panel_1.add(btnCadastrarfFor, "cell 0 1,grow");

        btnEditarFor = new JButton("Editar Fornecedor");
        btnEditarFor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    Fornecedor fornecedor = capturarDadosFornecedor();
                    fornecedor.setId(id);
                    fornecedorController.editarFornecedor(fornecedor); // Controller para editar
                }
            }
        });
        btnEditarFor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 20));
        btnEditarFor.setForeground(new Color(255, 255, 255));
        btnEditarFor.setBackground(new Color(255, 149, 149));
        panel_1.add(btnEditarFor, "cell 0 5,grow");
        
  

        btnDeletarFor = new JButton("Excluir");
        btnDeletarFor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    fornecedorController.excluirFornecedor(id); // Controller pra Exluir
                    
                }
            }
        });
        btnDeletarFor.setForeground(new Color(255, 255, 255));
        btnDeletarFor.setFont(hkGrotesk);
        btnDeletarFor.setBackground(new Color(255, 0, 0));
        panel_1.add(btnDeletarFor, "cell 0 5,grow");

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 1 4 3,grow");

        table = new JTable();
        table.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "CNPJ", "Rua" }));
        table.setFillsViewportHeight(true);
        table.setBackground(new Color(255, 233, 233));
        scrollPane.setViewportView(table);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		Utils.configTabela(table, scrollPane);
    }

    public Fornecedor capturarDadosFornecedor() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(textNome.getText());
        fornecedor.setCnpj(txtCnpj.getText());
        fornecedor.setCep(Integer.parseInt(txtCep.getText()));
        fornecedor.setRua(txtRua.getText());
        return fornecedor;
    }
    
   

    public void atualizarTabela(List<Fornecedor> fornecedores) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados
        for (Fornecedor fornecedor : fornecedores) {
            model.addRow(new Object[]{
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj(),
                fornecedor.getRua()
               
            });
        }
    }
    
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public void limparCampos() {
        textNome.setText("");
        txtCnpj.setText("");
        txtCep.setText("");
        txtRua.setText("");
    }
    

    
}

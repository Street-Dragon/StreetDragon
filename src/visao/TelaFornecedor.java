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
    private JButton btnCadastrarFor;
    private JButton btnDeletarFor;
    private JButton btnEditarFor;
    private JTable table;
    private JTextField txtCnpj;
    private JTextField txtRua;
    private JTextField txtCep;
    private JTextField textNome;

    private fornecedorController fornecedorController;

    public TelaFornecedor(TelaPrincipal telaPrincipal) {
        // Inicializa o Controller
        fornecedorController = new fornecedorController(this);

        setBackground(new Color(255, 255, 255));
        hkGrotesk = Utils.loadCustomFont();
        setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));

        // Painel de entrada de dados
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        add(panel, "cell 0 0 3 1,grow");
        panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow]"));

        // Componentes de entrada
        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblNome, "cell 0 0,alignx left,growy");

        textNome = new JTextField();
        textNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        textNome.setColumns(10);
        panel.add(textNome, "cell 1 0,growx");

        JLabel lblRua = new JLabel("Rua");
        lblRua.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblRua, "cell 3 0,alignx left,growy");

        txtRua = new JTextField();
        txtRua.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        txtRua.setColumns(10);
        panel.add(txtRua, "cell 4 0,growx");

        JLabel lblCnpj = new JLabel("CNPJ");
        lblCnpj.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblCnpj, "cell 0 1,alignx left,growy");

        txtCnpj = new JTextField();
        txtCnpj.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        txtCnpj.setColumns(10);
        panel.add(txtCnpj, "cell 1 1,growx");

        JLabel lblCep = new JLabel("CEP");
        lblCep.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblCep, "cell 3 1,alignx left,growy");

        txtCep = new JTextField();
        txtCep.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        txtCep.setColumns(10);
        panel.add(txtCep, "cell 4 1,growx");

        // Painel para botões
        JPanel panelButtons = new JPanel();
        add(panelButtons, "cell 3 0,grow");
        panelButtons.setBackground(new Color(255, 255, 255));
        panelButtons.setLayout(new MigLayout("", "[grow]", "[grow][grow][][grow][grow][grow]"));

        // Botão Cadastrar
        btnCadastrarFor = new JButton("Cadastrar");
        btnCadastrarFor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fornecedor fornecedor = capturarDadosFornecedor();
                fornecedorController.cadastrarFornecedor(fornecedor);
            }
        });
        btnCadastrarFor.setForeground(new Color(255, 255, 255));
        btnCadastrarFor.setFont(hkGrotesk);
        btnCadastrarFor.setBackground(new Color(114, 148, 235));
        panelButtons.add(btnCadastrarFor, "cell 0 1,grow");

        // Botão Editar
        btnEditarFor = new JButton("Editar Fornecedor");
        btnEditarFor.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        // Obtendo o ID do fornecedor selecionado na tabela
                        int id = (int) table.getValueAt(selectedRow, 0);

                        // Capturando os dados dos campos de texto
                        Fornecedor fornecedor = capturarDadosFornecedor();
                        fornecedor.setId(id); // Definindo o ID no objeto Fornecedor

                        // Chamando o método do controller
                        fornecedorController.editarFornecedor(fornecedor);
                        
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        
                    }
                } else {
                	
                }
            }
        });
        btnEditarFor.setForeground(new Color(255, 255, 255));
        btnEditarFor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 20));
        btnEditarFor.setBackground(new Color(255, 149, 149));
        panelButtons.add(btnEditarFor, "cell 0 5,grow");

        // Botão Excluir
        btnDeletarFor = new JButton("Excluir");
        btnDeletarFor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    fornecedorController.excluirFornecedor(id);
                } else {
                    exibirMensagem("Selecione um fornecedor para excluir.");
                }
            }
        });
        btnDeletarFor.setForeground(new Color(255, 255, 255));
        btnDeletarFor.setFont(hkGrotesk);
        btnDeletarFor.setBackground(new Color(255, 0, 0));
        panelButtons.add(btnDeletarFor, "cell 0 5,grow");

        // Tabela de fornecedores
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

        // Atualiza a tabela ao carregar a tela
        fornecedorController.atualizarTabela();
    }

    // Captura os dados dos campos da tela
    public Fornecedor capturarDadosFornecedor() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(textNome.getText());
        fornecedor.setCnpj(txtCnpj.getText());
        try {
            fornecedor.setCep(Integer.parseInt(txtCep.getText()));
        } catch (NumberFormatException e) {
            fornecedor.setCep(0); // Definir um valor padrão caso o CEP não seja válido
        }
        fornecedor.setRua(txtRua.getText());
        return fornecedor;
    }


    // Atualiza os dados da tabela
    public void atualizarTabela(List<Fornecedor> fornecedores) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa os dados antigos
        for (Fornecedor fornecedor : fornecedores) {
            model.addRow(new Object[] { fornecedor.getId(), fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getRua() });
        }
    }

    // Exibe uma mensagem para o usuário
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Limpa os campos da tela
    public void limparCampos() {
        textNome.setText("");
        txtCnpj.setText("");
        txtCep.setText("");
        txtRua.setText("");
    }
}

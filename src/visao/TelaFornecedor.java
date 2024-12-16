package visao;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controle.entidade.fornecedorcontrole.fornecedorControle;
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
    public JTextField getTxtCnpj() {
		return txtCnpj;
	}

	public void setTxtCnpj(JTextField txtCnpj) {
		this.txtCnpj = txtCnpj;
	}

	public JTextField getTxtRua() {
		return txtRua;
	}

	public void setTxtRua(JTextField txtRua) {
		this.txtRua = txtRua;
	}

	public JTextField getTxtCep() {
		return txtCep;
	}

	public void setTxtCep(JTextField txtCep) {
		this.txtCep = txtCep;
	}

	public JTextField getTextNome() {
		return textNome;
	}

	public void setTextNome(JTextField textNome) {
		this.textNome = textNome;
	}
	private JTextField txtRua;
    private JTextField txtCep;
    private JTextField textNome;
    private static DefaultTableModel tableModel;

    private fornecedorControle fornecedorControle;

    public TelaFornecedor(TelaPrincipal telaPrincipal) {
        // Inicializa o Controller
        fornecedorControle = new fornecedorControle(this);

        setBackground(new Color(253, 233, 235));
        hkGrotesk = Utils.loadCustomFont();
        setLayout(new MigLayout("", "[75%][25%]", "[35%][65%]"));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        add(panel, "cell 0 0,grow");
        panel.setLayout(new MigLayout("", "[25%][25%][25%][25%]", "[50%][50%]"));

        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblNome, "cell 0 0,alignx left,growy");

        textNome = new JTextField();
        textNome.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        textNome.setColumns(10);
        panel.add(textNome, "cell 1 0,growx");

        JLabel lblRua = new JLabel("Rua");
        lblRua.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblRua, "cell 2 0,alignx left,growy");

        txtRua = new JTextField();
        txtRua.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        txtRua.setColumns(10);
        panel.add(txtRua, "cell 3 0,growx");

        JLabel lblCnpj = new JLabel("CNPJ");
        lblCnpj.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblCnpj, "cell 0 1,alignx left,growy");

        txtCnpj = new JTextField();
        txtCnpj.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        txtCnpj.setColumns(10);
        panel.add(txtCnpj, "cell 1 1,growx");

        JLabel lblCep = new JLabel("CEP");
        lblCep.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        panel.add(lblCep, "cell 2 1,alignx left,growy");

        txtCep = new JTextField();
        txtCep.setFont(new Font("Hanken Grotesk", Font.PLAIN, 30));
        txtCep.setColumns(10);
        panel.add(txtCep, "cell 3 1,growx");

        JPanel panelButtons = new JPanel();
        add(panelButtons, "cell 1 0,grow");
        panelButtons.setBackground(new Color(255, 255, 255));
        panelButtons.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
        
        tableModel = new DefaultTableModel();
		tableModel.addColumn("Id");
		tableModel.addColumn("Nome");
		tableModel.addColumn("CNPJ");
		tableModel.addColumn("Rua");
		tableModel.addColumn("CEP");
		
        
		table = new JTable(tableModel) {
		    // não deixa as células serem editadas
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};

        // Botão Cadastrar
        btnCadastrarFor = new JButton("Cadastrar");
        btnCadastrarFor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fornecedor fornecedor = capturarDadosFornecedor();
                fornecedorControle.cadastrarFornecedor(fornecedor);
            }
        });
        btnCadastrarFor.setForeground(new Color(255, 255, 255));
        btnCadastrarFor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
        btnCadastrarFor.setBackground(new Color(114, 148, 235));
        panelButtons.add(btnCadastrarFor, "cell 0 0,grow");
        btnCadastrarFor.setIcon(Utils.carregarIcone("Add.png",30,30));
                
                // Botão Editar
                btnEditarFor = new JButton("Editar");
                panelButtons.add(btnEditarFor, "cell 0 1,grow");
                btnEditarFor.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table.getSelectedRow();
                        
                        if (selectedRow != -1) {
                          
                            Fornecedor fornecedor = capturarDadosFornecedor();
                            
                            try {
                                int id = (int) table.getValueAt(selectedRow, 0);
                                fornecedor.setId(id); 

                                fornecedorControle.editarFornecedor(fornecedor);

                            } catch (NumberFormatException ex) {
                                ex.printStackTrace(); 
                            }
                        } else {
                         
                            new TelaMensagens("Selecione um fornecedor para editar.", 3);
                        }
                    }
                });        
                btnEditarFor.setForeground(new Color(255, 255, 255));
                btnEditarFor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
                btnEditarFor.setBackground(new Color(253, 175, 175));
                btnEditarFor.setIcon(Utils.carregarIcone("editar.png",30,30));
                
                        // Botão Excluir
                        btnDeletarFor = new JButton("Excluir");
                        btnDeletarFor.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                int selectedRow = table.getSelectedRow();
                                if (selectedRow != -1) {
                                   
                                    int id = (int) table.getValueAt(selectedRow, 0);
                                    fornecedorControle.confirmarExclusaoFornecedor(id);
                                } else {
                                	TelaMensagens Tm = new TelaMensagens("Selecione um fornecedor para excluir.", 3);
                                }
                            }
                        });
                        
                        btnDeletarFor.setForeground(new Color(255, 255, 255));
                        btnDeletarFor.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
                        btnDeletarFor.setBackground(new Color(255, 0, 0));
                        panelButtons.add(btnDeletarFor, "cell 0 2,grow");
                        btnDeletarFor.setIcon(Utils.carregarIcone("lixo.png",30,30));


        
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 1 2 1,grow");
        
        tableModel = new DefaultTableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Nome");
		tableModel.addColumn("CNPJ");
		tableModel.addColumn("Rua");

	
		table = new JTable(tableModel) {
		    // não deixe o samba morrer
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};
        table.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Código", "Nome", "CNPJ", "Rua", "CEP" }));
        table.setFillsViewportHeight(true);
        table.setBackground(new Color(255, 233, 233));
        scrollPane.setViewportView(table);
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
        Utils.configTabela(table, scrollPane);
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1) { 
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        String nome = (String) table.getValueAt(selectedRow, 1);
                        String cnpj = (String) table.getValueAt(selectedRow, 2);
                        String rua = (String) table.getValueAt(selectedRow, 3);
                        int cep = (int) table.getValueAt(selectedRow, 4);

                        Fornecedor fornecedor = new Fornecedor();
                        fornecedor.setId(id);
                        fornecedor.setNome(nome);
                        fornecedor.setCnpj(cnpj);
                        fornecedor.setRua(rua);
                        fornecedor.setCep(cep);

                        preencherCampos(fornecedor);
                    }
                }
            }
        });
    
        
        fornecedorControle.atualizarTabela();
    }

    public Fornecedor capturarDadosFornecedor() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(textNome.getText());
        fornecedor.setCnpj(txtCnpj.getText());
        try {
            fornecedor.setCep(Integer.parseInt(txtCep.getText()));
        } catch (NumberFormatException e) {
            fornecedor.setCep(0); 
        }
        fornecedor.setRua(txtRua.getText());
        return fornecedor;
    }



    public void limparCampos() {
        textNome.setText("");
        txtCnpj.setText("");
        txtCep.setText("");
        txtRua.setText("");
    }
    public void preencherCampos(Fornecedor fornecedor) {
    	textNome.setText(fornecedor.getNome());
    	txtCnpj.setText(fornecedor.getCnpj());
    	txtRua.setText(fornecedor.getRua());
    	txtCep.setText(String.valueOf(fornecedor.getCep()));
    }
    
    public JTable getTable() {
        return table;
    }
}

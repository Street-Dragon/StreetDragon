package controle.entidade.clientecontrole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.dao.cliente.ClienteDAO;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.cliente.Cliente;
import utils.Utils;
import visao.TelaCliente;

public class ClienteControle {

	private TelaCliente cadastroCliente;
	private ClienteDAO clienteDAO = new ClienteDAO();
	private String clienteIdStr;
	
    private void adicionarListeners() {
    	cadastroCliente.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = cadastroCliente.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    clienteIdStr = (String) cadastroCliente.getTable().getValueAt(selectedRow, 0);
                    carregarDadosCliente(clienteIdStr);
                }
            }
        });
    }
	
	public void setTelaCadastroCliente(TelaCliente cadastroCliente) {
		this.cadastroCliente = cadastroCliente;
			atualizarTabela();
			adicionarListeners();
			cadastroCliente.getBtnCadastrar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarCliente();
				atualizarTabela();
				cadastroCliente.limparCampos();
			}
		});
	
			cadastroCliente.getBtnDeletar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
				cadastroCliente.limparCampos();
				atualizarTabela();
				
			}
		});
			cadastroCliente.getBtnEditar().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					editarClienteDAO(clienteIdStr);
					atualizarTabela();
				}
			});
	}
	
	private void cadastrarCliente() {
        String nome = cadastroCliente.getTxtNome();
        String cpf = cadastroCliente.getTxtCpf();
        String email = cadastroCliente.getTxtEmail();
        String telefone = cadastroCliente.getTxtTelefone();
        String compras = cadastroCliente.getTxtNumeroCompras();

        if (nome.isBlank() || cpf.isBlank() || email.isBlank() || telefone.isBlank()) {
            JOptionPane.showMessageDialog(cadastroCliente, "Preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        cpf = cpf.replaceAll("[^0-9]", ""); 
        telefone = telefone.replaceAll("[^0-9]", "");
        
        if (clienteDAO.verificaCpfExistente(cpf)) {
            JOptionPane.showMessageDialog(cadastroCliente, "CPF já cadastrado. Tente outro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!Utils.isValidCPF(cpf)) {
            JOptionPane.showMessageDialog(cadastroCliente, "O CPF informado é inválido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } 
        if (clienteDAO.verificaTelefoneExistente(telefone)) {
            JOptionPane.showMessageDialog(cadastroCliente, "Telefone já cadastrado. Tente outro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        Cliente cliente = new Cliente();
        Contato contato = new Contato();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setNumeroCompras(compras);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        cliente.setContato(contato);
        clienteDAO.cadastrarCliente(cliente);
        atualizarTabela();
        return;
    }

    public void atualizarTabela() {
        List< Cliente> clientes = clienteDAO.listarClientes();
       
        DefaultTableModel tableModel;
		tableModel = new DefaultTableModel();
        tableModel.addColumn("CPF");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Senha");
        tableModel.addColumn("Administrador");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefone");
        
        

        for (Cliente cliente : clientes) {
            Contato contato = cliente.getContato();
            tableModel.addRow(new Object[]{
            		cliente.getCpf(),
            		cliente.getNome(),
                contato.getEmail(),
                contato.getTelefone()
            });
        }
        
        cadastroCliente.getTable().setModel(tableModel);
    }

    private void excluirCliente() {
        int selectedRow = cadastroCliente.getTable().getSelectedRow();
        
        if (selectedRow != -1) {
            String cpfCliente = (String) cadastroCliente.getTable().getValueAt(selectedRow, 0);
            
            int resposta = JOptionPane.showConfirmDialog(cadastroCliente,
                "Você tem certeza que deseja excluir o cliente com CPF: " + cpfCliente + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (resposta == JOptionPane.YES_OPTION) {

                boolean excluido = clienteDAO.excluirCliente(cpfCliente);
                
                if (excluido) {
                    JOptionPane.showMessageDialog(cadastroCliente, "Cliente excluído com sucesso!");
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(cadastroCliente, "Erro ao excluir o Cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(cadastroCliente, "Selecione um Cliente para excluir.", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void editarClienteDAO(String clienteIdStr) {
    	Cliente clienteAlteracao = clienteDAO.carregarDadosCliente(clienteIdStr);
    	
        String nome = cadastroCliente.getTxtNome();
        String cpf = cadastroCliente.getTxtCpf();
        String email = cadastroCliente.getTxtEmail();
        String telefone = cadastroCliente.getTxtTelefone();
        String compras = cadastroCliente.getTxtNumeroCompras();
        
        if (nome.isBlank() || cpf.isBlank() || email.isBlank() || telefone.isBlank()) {
            JOptionPane.showMessageDialog(cadastroCliente, "Preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
   
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(clienteAlteracao.getCpf());
        cliente.setNumeroCompras(compras);

        Contato contato = new Contato();
        contato.setEmail(email);
        contato.setTelefone(telefone);
        cliente.setContato(contato);

        boolean resultado = clienteDAO.editarCliente(cliente);

        if (resultado) {
            JOptionPane.showMessageDialog(cadastroCliente, "Funcionário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(cadastroCliente, "Erro ao atualizar funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
      
    private void carregarDadosCliente(String clienteIdStr) {
        Cliente cliente = clienteDAO.carregarDadosCliente(clienteIdStr);
        if (cliente != null) {
            atualizarCampos(cliente);
        }
    }

    private void atualizarCampos(Cliente cliente) {
    	cadastroCliente.getTextFieldNome().setText(cliente.getNome());
    	cadastroCliente.getTextFieldCpf().setText(cliente.getCpf());
    	cadastroCliente.getTextNumeroCompras().setText(cliente.getNumeroCompras());
    	cadastroCliente.getTextFieldEmail().setText(cliente.getContato().getEmail());
    	cadastroCliente.getTextFieldTelefone().setText(cliente.getContato().getTelefone());
    }

}

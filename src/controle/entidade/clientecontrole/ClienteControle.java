package controle.entidade.clientecontrole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	private boolean selecionado = false;

	public void setTelaCadastroCliente(TelaCliente cadastroCliente) {

		this.cadastroCliente = cadastroCliente;

		atualizarTabela();
		adicionarListeners();

		cadastroCliente.getBtnCadastrar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (selecionado) {
					editarClienteDAO(clienteIdStr);
				} else {
					System.out.println("Botão Cadastrar pressionado");
					cadastrarCliente();
					atualizarTabela();
				}

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
		/*
		 * cadastroCliente.getBtnEditar().addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * editarClienteDAO(clienteIdStr); atualizarTabela(); } });
		 */
	}

	private void adicionarListeners() {
		cadastroCliente.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cadastroCliente.getBtnCadastrar().setText("Editar");
				cadastroCliente.getBtnCadastrar().setIcon(Utils.carregarIcone("editar.png", 30, 30));
				selecionado = true;

				int selectedRow = cadastroCliente.getTable().getSelectedRow();
				if (selectedRow != -1) {
					clienteIdStr = (String) cadastroCliente.getTable().getValueAt(selectedRow, 0);
					carregarDadosCliente(clienteIdStr);
				}
			}
		});

		cadastroCliente.getTable().getSelectionModel()
				.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {
							// Se não houver nenhuma linha selecionada:
							if (cadastroCliente.getTable().getSelectedRowCount() == 0) {

								cadastroCliente.getBtnCadastrar().setText("Cadastrar");
								cadastroCliente.getBtnCadastrar().setIcon(Utils.carregarIcone("Check.png", 30, 30));
								selecionado = false;
							}
						}
					}
				});
	}

	private void cadastrarCliente() {
		String nome = cadastroCliente.getTxtNome();
		String cpf = cadastroCliente.getTxtCpf();
		String email = cadastroCliente.getTxtEmail();
		String telefone = cadastroCliente.getTxtTelefone();

		System.out.println(nome + cpf + email + telefone);
		if (nome.isBlank() || cpf.isBlank() || email.isBlank() || telefone.isBlank()) {
			JOptionPane.showMessageDialog(cadastroCliente, "Preencha todos os campos.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		cpf = cpf.replaceAll("[^0-9]", "");
		telefone = telefone.replaceAll("[^0-9]", "");

		if (clienteDAO.verificaCpfExistente(cpf)) {
			JOptionPane.showMessageDialog(cadastroCliente, "CPF já cadastrado. Tente outro.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!Utils.isValidCPF(cpf)) {
			JOptionPane.showMessageDialog(cadastroCliente, "O CPF informado é inválido.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (clienteDAO.verificaTelefoneExistente(telefone)) {
			JOptionPane.showMessageDialog(cadastroCliente, "Telefone já cadastrado. Tente outro.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Cliente cliente = new Cliente();
		Contato contato = new Contato();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		contato.setEmail(email);
		contato.setTelefone(telefone);
		cliente.setContato(contato);
		clienteDAO.cadastrarCliente(cliente);
		atualizarTabela();
		cadastroCliente.limparCampos();
		return;
	}

	public void atualizarTabela() {
		List<Cliente> clientes = clienteDAO.listarClientes();

		DefaultTableModel tableModel;
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Nome");
		tableModel.addColumn("Email");
		tableModel.addColumn("Número");
		tableModel.addColumn("Cpf");
		tableModel.addColumn("N. Compras");

		for (Cliente cliente : clientes) {
			Contato contato = cliente.getContato();
			tableModel.addRow(new Object[] { cliente.getNome(), contato.getEmail(), contato.getTelefone(),cliente.getCpf(),
					cliente.getNumeroCompras() });
		}

		cadastroCliente.getTable().setModel(tableModel);
	}

	private void excluirCliente() {
		int selectedRow = cadastroCliente.getTable().getSelectedRow();

		if (selectedRow != -1) {
			String cpfCliente = (String) cadastroCliente.getTable().getValueAt(selectedRow, 0);

			int resposta = JOptionPane.showConfirmDialog(cadastroCliente,
					"Você tem certeza que deseja excluir o cliente com CPF: " + cpfCliente + "?", "Confirmar Exclusão",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (resposta == JOptionPane.YES_OPTION) {

				boolean excluido = clienteDAO.excluirCliente(cpfCliente);

				if (excluido) {
					JOptionPane.showMessageDialog(cadastroCliente, "Cliente excluído com sucesso!");
					atualizarTabela();
				} else {
					JOptionPane.showMessageDialog(cadastroCliente, "Erro ao excluir o Cliente.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(cadastroCliente, "Selecione um Cliente para excluir.", "Erro",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void editarClienteDAO(String clienteIdStr) {
		Cliente clienteAlteracao = clienteDAO.carregarDadosCliente(clienteIdStr);

		String nome = cadastroCliente.getTxtNome();
		String cpf = cadastroCliente.getTxtCpf();
		String email = cadastroCliente.getTxtEmail();
		String telefone = cadastroCliente.getTxtTelefone();

		if (nome.isBlank() || cpf.isBlank() || email.isBlank() || telefone.isBlank()) {
			JOptionPane.showMessageDialog(cadastroCliente, "Preencha todos os campos.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(clienteAlteracao.getCpf());

		Contato contato = new Contato();
		contato.setEmail(email);
		contato.setTelefone(telefone);
		cliente.setContato(contato);

		boolean resultado = clienteDAO.editarCliente(cliente);

		if (resultado) {
			JOptionPane.showMessageDialog(cadastroCliente, "Funcionário atualizado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);

			atualizarTabela();
		} else {
			JOptionPane.showMessageDialog(cadastroCliente, "Erro ao atualizar funcionário.", "Erro",
					JOptionPane.ERROR_MESSAGE);
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
		cadastroCliente.getTextFieldEmail().setText(cliente.getContato().getEmail());
		cadastroCliente.getTextFieldTelefone().setText(cliente.getContato().getTelefone());
	}

}

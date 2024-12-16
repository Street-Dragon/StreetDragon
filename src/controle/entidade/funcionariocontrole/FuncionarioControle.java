package controle.entidade.funcionariocontrole;

import modelo.dao.funcionario.FuncionarioDAO;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.funcionario.Funcionario;
import utils.Utils;
import visao.TelaPrincipal;
import visao.TelaProdutos;
import visao.TelaCadastroFuncionario;
import visao.TelaLogin;
import visao.TelaMensagens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class FuncionarioControle {
	private TelaLogin telaLogin;
	private TelaCadastroFuncionario cadastroFuncionario;
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private String funcionarioIdStr;
	private TelaPrincipal telaPrincipal;
	private String cpfUsuarioLogado;
	// cadastroFuncionario.addTableClickListener(this::selecionarFuncionario);

	public void setTelaLogin(TelaLogin telaLogin) {
		this.telaLogin = telaLogin;

		telaLogin.getBtnContinuar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				realizarLogin();
			}
		});
	}

	public void setTelaPrincipal(TelaPrincipal telaPrincipal) {
	    this.telaPrincipal = telaPrincipal;

	    // Remove todos os ActionListeners existentes
	    for (ActionListener al : telaPrincipal.getBtnDeslogar().getActionListeners()) {
	        telaPrincipal.getBtnDeslogar().removeActionListener(al);
	    }

	    // Adiciona um novo ActionListener
	    telaPrincipal.getBtnDeslogar().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            realizarLogout();
	        }
	    });
	}

	public void setTelaCadastroFuncionario(TelaCadastroFuncionario cadastroFuncionario) {
		this.cadastroFuncionario = cadastroFuncionario;
			atualizarTabela();
			adicionarListeners();
			cadastroFuncionario.getBtnCadastrarFuncionario().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarFuncionario();
				atualizarTabela();
				cadastroFuncionario.limparCampos();
			}
		});
	
			cadastroFuncionario.getBtnDeletarFuncionario().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletarFuncionario();
				cadastroFuncionario.limparCampos();
				atualizarTabela();
				
			}
		});
			cadastroFuncionario.getBtnEditarFuncionario().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					editarFuncinarioDAO(funcionarioIdStr);
					atualizarTabela();
				}
			});
	}
	
    private void adicionarListeners() {
    	cadastroFuncionario.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = cadastroFuncionario.getTable().getSelectedRow();
                if (selectedRow != -1) {
                	 // |Tem que parar de converter, passa string direto e ajusta;
                    funcionarioIdStr = (String) cadastroFuncionario.getTable().getValueAt(selectedRow, 0);
                   
                    carregarDadosFuncionarioDAO(funcionarioIdStr);
                }
            }
        });
    }

    
    
	private void realizarLogin() {
		String cpf = telaLogin.getCampoCpf();
		String senha = telaLogin.getCampoSenha();

		if (funcionarioDAO.login(cpf, senha)) {

			if (telaPrincipal == null) {
				telaPrincipal = new TelaPrincipal();
				setTelaPrincipal(telaPrincipal); // Configuração da tela principal
			}
			cpfUsuarioLogado = cpf;
			telaPrincipal.getLblFuncionario()
					.setText("Funcionario: " + funcionarioDAO.nomeFuncionario(cpfUsuarioLogado));
			
			
			boolean adm = funcionarioDAO.funcionarioAdm(cpf);
			if(adm) {
				System.out.println("USUARIO TEM PODERES");
			}
			else {
				System.out.println("usuario não tem poderes");
			}
			telaPrincipal.getBtnFornecedor().setEnabled(adm);
			telaPrincipal.getBtnFuncionarios().setEnabled(adm);
			telaPrincipal.getBtnPromocoes().setEnabled(adm);
			
			
			telaPrincipal.setVisible(true);
			telaLogin.dispose();

			// limpa os campos e checkbox
			telaLogin.getTxtCpf().setText("");
			telaLogin.getTxtSenha().setText("");
			telaLogin.getCkboxMotrarSenha().setSelected(false);

		} else {
			TelaMensagens TM = new TelaMensagens("Credenciais inválidas. Tente novamente.", 1);
		}
	}

	private void realizarLogout() {
		TelaMensagens TM = new TelaMensagens("Deseja realmente deslogar?");
	    System.out.println("Função chamada");
	    if (TM.getResposta()) {
	        System.out.println("Usuário deslogado");
	        cpfUsuarioLogado = null;
	        telaPrincipal.dispose(); // Fecha a tela principal
	        telaLogin.setVisible(true); // Mostra a tela de login novamente
	    }
	}

	private void cadastrarFuncionario() {
        String nome = cadastroFuncionario.getTextNome();
        char[] senhaArray = cadastroFuncionario.getPasswordField();
        String cpf = cadastroFuncionario.getTextCpf();
        boolean isAdm = cadastroFuncionario.getChckbxAdm();
        String email = cadastroFuncionario.getTextEmail();
        String senha = new String(senhaArray);
        String telefone = cadastroFuncionario.getTextTelefone();

        if (nome.isBlank() || cpf.isBlank() || senha.isBlank() || email.isBlank() || telefone.isBlank()) {
        	TelaMensagens TM = new TelaMensagens("Preencha todos os campos.", 3);
            return;
        }

        // Tira oq não é numero antes de começar a validação
        cpf = cpf.replaceAll("[^0-9]", ""); 
        telefone = telefone.replaceAll("[^0-9]", ""); // 
        
        if (funcionarioDAO.verificaCpfExistente(cpf)) {
        	TelaMensagens TM = new TelaMensagens("CPF já cadastrado. Tente outro.", 3);
            return;
        }
        
        if (!Utils.isValidCPF(cpf)) {
        	TelaMensagens TM = new TelaMensagens("O CPF informado é inválido.", 3);
            return;
        } 
        Funcionario funcionario = new Funcionario();
        Contato contato = new Contato();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSenhaFuncionario(senha);
        funcionario.setAdm(isAdm);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        funcionario.setContato(contato);
        funcionarioDAO.cadastrarFuncionario(funcionario);
        atualizarTabela();
        return;
    }
    
    // Método para excluir um funcionário
    private void deletarFuncionario() {
        int selectedRow = cadastroFuncionario.getTable().getSelectedRow();
        
        if (selectedRow != -1) {
            String cpfFuncionario = (String) cadastroFuncionario.getTable().getValueAt(selectedRow, 0);
            
            TelaMensagens TM = new TelaMensagens("Você tem certeza que deseja excluir o funcionário com CPF: " + cpfFuncionario + "?");
            if (TM.getResposta()) {

                boolean excluido = funcionarioDAO.excluirFuncionario(cpfFuncionario);
                
                if (excluido) {
                	TelaMensagens TM2 = new TelaMensagens("Funcionário excluído com sucesso!", 0);
                    atualizarTabela();
                } else {
                	TelaMensagens TM2 = new TelaMensagens("Erro ao excluir o funcionário.", 1);
                }
            }
        } else {
        	TelaMensagens TM2 = new TelaMensagens("Selecione um funcionário para excluir.", 3);
        }
    }
  
    public void atualizarTabela() {
            List<Funcionario> funcionarios = funcionarioDAO.listarFuncionarios();
           
            DefaultTableModel tableModel;
    		tableModel = new DefaultTableModel();
            tableModel.addColumn("CPF");
            tableModel.addColumn("Nome");
            tableModel.addColumn("Senha");
            tableModel.addColumn("Administrador");
            tableModel.addColumn("Email");
            tableModel.addColumn("Telefone");
            
            

            for (Funcionario funcionario : funcionarios) {
                Contato contato = funcionario.getContato();
                tableModel.addRow(new Object[]{
                    funcionario.getCpf(),
                    funcionario.getNome(),
                    funcionario.getSenhaFuncionario(),
                    funcionario.isAdm() ? "Sim" : "Não",
                    contato.getEmail(),
                    contato.getTelefone()
                });
            }
            
            cadastroFuncionario.getTable().setModel(tableModel);
        }
    
    private void carregarDadosFuncionarioDAO(String funcionarioIdStr) {
        Funcionario funcionario = funcionarioDAO.carregarDadosFuncionario(funcionarioIdStr);
        if (funcionario != null) {
        	
            atualizarCampos(funcionario);
        }
    }
    
    private void editarFuncinarioDAO(String funcionarioIdString) {
    	Funcionario funcionarioAlteracao = funcionarioDAO.carregarDadosFuncionario(funcionarioIdStr);
    	//funcionarioDAO.editarFuncionario(funcionario);
    	
    	String nome = cadastroFuncionario.getTextNome();
        String cpf = cadastroFuncionario.getTextCpf();
        char[] senhaArray = cadastroFuncionario.getPasswordField();
        String senha = new String(senhaArray);
        boolean isAdm = cadastroFuncionario.getChckbxAdm();
        String email = cadastroFuncionario.getTextEmail();
        String telefone = cadastroFuncionario.getTextTelefone();
        
        if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
        	TelaMensagens TM = new TelaMensagens("Preencha todos os campos obrigatórios.", 1);
            return;
        }

        
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(funcionarioAlteracao.getCpf());
        funcionario.setSenhaFuncionario(senha);
        funcionario.setAdm(isAdm);

        Contato contato = new Contato();
        contato.setId(funcionarioAlteracao.getContato().getId());
        contato.setEmail(email);
        contato.setTelefone(telefone);
        funcionario.setContato(contato);

        boolean resultado = funcionarioDAO.editarFuncionario(funcionario);

        if (resultado) {
        	TelaMensagens TM = new TelaMensagens("Funcionário atualizado com sucesso!", 0);
            atualizarTabela();
        } else {
        	TelaMensagens TM = new TelaMensagens("Erro ao atualizar funcionário.", 0);
        }
    }
    
    private void atualizarCampos(Funcionario funcionario) {
    	cadastroFuncionario.getTextFieldNome().setText(funcionario.getNome());
    	cadastroFuncionario.getTextFieldCpf().setText(funcionario.getCpf());
    	cadastroFuncionario.getTextFieldSenha().setText(funcionario.getSenhaFuncionario());
    	cadastroFuncionario.getTextFieldEmail().setText(funcionario.getContato().getEmail());
    	cadastroFuncionario.getTextFieldTelefone().setText(funcionario.getContato().getTelefone());
    	
    }
    
    public String getCpfUsuarioLogado() {
    	return cpfUsuarioLogado;
    }

    /*
    public void selecionarFuncionario(int id) {
        Funcionario funcionario = funcionarioDAO.getFuncionario(id);
        if (funcionario != null) {
            view.setFuncionario(funcionario);
        } else {
            JOptionPane.showMessageDialog(view, "Funcionário não encontrado");
        if (!Utils.isValidTelefone(telefone)) {
            JOptionPane.showMessageDialog(cadastroFuncionario, "O telefone informado é inválido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (funcionarioDAO.verificaTelefoneExistente(telefone)) {
            JOptionPane.showMessageDialog(cadastroFuncionario, "Telefone já cadastrado. Tente outro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;

        }
        if (funcionarioDAO.verificaEmailExistente(email)) {
            JOptionPane.showMessageDialog(cadastroFuncionario, "Email já cadastrado. Tente outro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cpf = Utils.formatarDocumentos(cpf, Utils.TipoDocumento.CPF);
        telefone = Utils.formatarDocumentos(telefone, Utils.TipoDocumento.TELEFONE);

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSenhaFuncionario(senha);
		funcionario.setAdm(isAdm);

		Contato contato = new Contato();
		contato.setEmail(email);
		contato.setTelefone(telefone);
		funcionario.setContato(contato);

		if (funcionarioDAO.cadastrarFuncionario(funcionario)) {
			JOptionPane.showMessageDialog(cadastroFuncionario, "Funcionário cadastrado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
	        cadastroFuncionario.setTextNome("");
	        cadastroFuncionario.setPasswordField("");
	        cadastroFuncionario.setTextCpf("");
	        cadastroFuncionario.setChckbxAdm(false);
	        cadastroFuncionario.setChckbxSenha(false);
	        cadastroFuncionario.setTextEmail("");
	        cadastroFuncionario.setTextTelefone("");
		} else {
			JOptionPane.showMessageDialog(cadastroFuncionario, "Erro ao cadastrar funcionário.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	 public void selecionarFuncionario(int id) { Funcionario funcionario =
	 funcionarioDAO.getFuncionario(id); if (funcionario != null) {
	 view.setFuncionario(funcionario); } else {
	 JOptionPane.showMessageDialog(view, "Funcionário não encontrado"); } }

*/
}

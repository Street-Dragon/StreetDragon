package controle.entidade.funcionariocontrole;

import modelo.dao.funcionario.FuncionarioDAO;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.funcionario.Funcionario;
import utils.Utils;
import visao.TelaPrincipal;
import visao.TelaCadastroFuncionario;
import visao.TelaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class FuncionarioControle {
	private TelaLogin telaLogin;
	private TelaCadastroFuncionario cadastroFuncionario;
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private TelaPrincipal telaPrincipal;
	private String cpfUsuarioLogado;

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
		telaPrincipal.getBtnDeslogar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				realizarLogout();
			}
		});
	}

	public void setCadastroFuncionario(TelaCadastroFuncionario cadastroFuncionario) {
		this.cadastroFuncionario = cadastroFuncionario;

		cadastroFuncionario.getBtnCadastrarFuncionario().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarFuncionario();
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
			telaPrincipal.setVisible(true);
			telaLogin.dispose();

			// limpa os campos e checkbox
			telaLogin.getTxtCpf().setText("");
			telaLogin.getTxtSenha().setText("");
			telaLogin.getCkboxMotrarSenha().setSelected(false);

		} else {
			JOptionPane.showMessageDialog(telaLogin, "Credenciais inválidas. Tente novamente.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void realizarLogout() {
        System.out.println("Função chamada");
        int confirmar = JOptionPane.showConfirmDialog(telaPrincipal, "Deseja realmente deslogar?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION || cpfUsuarioLogado != null) {
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
            JOptionPane.showMessageDialog(cadastroFuncionario, "Preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tira oq não é numero antes de começar a validação
        cpf = cpf.replaceAll("[^0-9]", ""); 
        telefone = telefone.replaceAll("[^0-9]", ""); // 
        
        if (funcionarioDAO.verificaCpfExistente(cpf)) {
            JOptionPane.showMessageDialog(cadastroFuncionario, "CPF já cadastrado. Tente outro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Interrompe o cadastro
        }
        
        if (!Utils.isValidCPF(cpf)) {
            JOptionPane.showMessageDialog(cadastroFuncionario, "O CPF informado é inválido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Utils.isValidTelefone(telefone)) {
            JOptionPane.showMessageDialog(cadastroFuncionario, "O telefone informado é inválido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
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

		} else {
			JOptionPane.showMessageDialog(cadastroFuncionario, "Erro ao cadastrar funcionário.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

}

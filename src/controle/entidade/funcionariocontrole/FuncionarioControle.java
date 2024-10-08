package controle.entidade.funcionariocontrole;

import modelo.dao.funcionario.FuncionarioDAO;
import modelo.entidade.contato.Contato;
import modelo.entidade.pessoa.funcionario.Funcionario;
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
    private String  cpfUsuarioLogado;
    
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
        System.out.println("setTelaPrincipal chamada"); 
        
        telaPrincipal.getBtnDeslogar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botão clicado");
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
            telaPrincipal.getLblFuncionario().setText("Funcionario: "+funcionarioDAO.nomeFuncionario(cpfUsuarioLogado));
            telaPrincipal.setVisible(true);
            telaLogin.dispose();
        } else {
            JOptionPane.showMessageDialog(telaLogin, "Credenciais inválidas. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
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
     
        if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(cadastroFuncionario, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
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
            JOptionPane.showMessageDialog(cadastroFuncionario, "Funcionário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           
        } else {
            JOptionPane.showMessageDialog(cadastroFuncionario, "Erro ao cadastrar funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return;
    }
    
}

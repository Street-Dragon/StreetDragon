package controle.entidade.funcionariocontrole;

import modelo.dao.funcionario.FuncionarioDAO;
import visao.TelaLogin;
//import visao.pagina.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class FuncionarioControle {
    private TelaLogin telaLogin;

    public void setTelaLogin(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;

        telaLogin.getBtnContinuar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
    }

    private void realizarLogin() {
        String username = telaLogin.getCampoUsername();
        String password = telaLogin.getCampoPassword();

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        if (funcionarioDAO.login(username, password)) {
          /*  Principal telaPrincipal = new Principal();
            telaPrincipal.setVisible(true); 
            telaLogin.dispose();*/
        } else {
        	JOptionPane.showMessageDialog(telaLogin, "Credenciais inválidas. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

package controle.entidade.funcionariocontrole;

import visao.TelaPrincipal;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipalControle {
	private TelaPrincipal telaPrincipal;

	public TelaPrincipalControle(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;

		// Configuração dos listeners dos botões
		this.telaPrincipal.getBtnVenda().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaVenda");
			}
		});

		this.telaPrincipal.getBtnHistorico().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaHistoricoVenda");
			}
		});

		this.telaPrincipal.getBtnFuncionarios().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("TelaCadastroFuncionario");
			}
		});
	}

	// método para trocar o jpanel atual
	private void trocarTela(String nomeTela) {
		CardLayout cardLayout = (CardLayout) telaPrincipal.getMainPanel().getLayout();
		cardLayout.show(telaPrincipal.getMainPanel(), nomeTela);
	}
}
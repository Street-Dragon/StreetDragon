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

		telaPrincipal.getBtnDeslogar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn deslogar pressionado");
			}
		});
	}

	private void trocarTela(String nomeTela) {
		// Controlador agora faz a troca de telas diretamente
		CardLayout cardLayout = (CardLayout) telaPrincipal.getMainPanel().getLayout();
		cardLayout.show(telaPrincipal.getMainPanel(), nomeTela);
	}
}
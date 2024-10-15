package controle.entidade.funcionariocontrole;

import visao.TelaPrincipal;
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
				telaPrincipal.trocarTela("TelaVenda");
				System.out.println("trocou para a tela de venda");
			}
		});

		this.telaPrincipal.getBtnHistorico().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telaPrincipal.trocarTela("TelaHistoricoVenda");
				System.out.println("Trocou para a tela de historico");
			}
		});

		telaPrincipal.getBtnDeslogar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn deslogar pressionado");
			}
		});
	}
}
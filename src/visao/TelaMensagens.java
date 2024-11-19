package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utils.Cores;
import utils.Utils;

public class TelaMensagens extends JDialog{
	
	private Font hkGrotesk;
	private static final long serialVersionUID = 1L;
	private boolean resposta;
	
	public TelaMensagens(String mensagem, int tipo) {
		setTitle("Mensagem");
		setModal(true);
		setResizable(false);
		Image image;
		Image newimg;
		ImageIcon imageIcon;
		java.net.URL imageURL;
		
		hkGrotesk = Utils.loadCustomFont();
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Cores.COR_ROSA_CLARO);
		getContentPane().add(panel);
		
		JLabel lblMensagem = new JLabel(mensagem, SwingConstants.CENTER);
		lblMensagem.setFont(new Font("Hanken Grotesk", Font.PLAIN, 15));
		panel.add(lblMensagem, BorderLayout.CENTER);
		
		String iconPath;
		switch(tipo) {
		case 0:
			setTitle("Sucesso");
			imageURL = getClass().getResource("/resources/imagens/Check.png");
			imageIcon = new ImageIcon(imageURL);
			image = imageIcon.getImage();
			newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			lblMensagem.setIcon(imageIcon);
			
			break;
		case 1:
			setTitle("Erro");
			imageURL = getClass().getResource("/resources/imagens/Erro.png");
			imageIcon = new ImageIcon(imageURL);
			image = imageIcon.getImage();
			newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			lblMensagem.setIcon(imageIcon);
			break;
		case 2:
			setTitle("Pergunta");
			imageURL = getClass().getResource("/resources/imagens/Pergunta.png");
			imageIcon = new ImageIcon(imageURL);
			image = imageIcon.getImage();
			newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			lblMensagem.setIcon(imageIcon);
			break;
		case 3:
			setTitle("Alerta");
			imageURL = getClass().getResource("/resources/imagens/Alerta.png");
			imageIcon = new ImageIcon(imageURL);
			image = imageIcon.getImage();
			newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			lblMensagem.setIcon(imageIcon);
			break;
		case 10:
			imageURL = getClass().getResource("/resources/imagens/logo.png");
			imageIcon = new ImageIcon(imageURL);
			image = imageIcon.getImage();
			newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			lblMensagem.setIcon(imageIcon);
			break;
			default:
				break;
		}
		
		JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
		painelSul.add(new JPanel());
		painelSul.setBackground(Cores.COR_ROSA_CLARO);
		JButton btnOK = new JButton("OK");
		btnOK.setFont(hkGrotesk);
		btnOK.setBackground(Cores.COR_AZUL);
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		painelSul.add(btnOK);
		painelSul.add(new JPanel());
		panel.add(painelSul, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(btnOK);
		setSize(350,200);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public TelaMensagens(String pergunta) {
		setAlwaysOnTop(true);
		setTitle("Mensagem");
		setModal(true);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);
		panel.setBackground(Cores.COR_ROSA_CLARO );
		
		JLabel labelPergunta = new JLabel(pergunta, SwingConstants.CENTER);
		labelPergunta.setFont(new Font("Hanken Grotesk", Font.PLAIN, 15));
		java.net.URL imageURL = getClass().getResource("/resources/imagens/Pergunta.png");
		ImageIcon imageIcon = new ImageIcon(imageURL);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		labelPergunta.setIcon(imageIcon);
		panel.add(labelPergunta,BorderLayout.CENTER);
		
		JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
		painelSul.setBackground(Cores.COR_ROSA_CLARO);
		
		JButton btnSim = new JButton("Sim");
		btnSim.setFont(hkGrotesk);
		btnSim.setBackground(Cores.COR_AZUL);
		btnSim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resposta = true;
				setVisible(false);
			}
		});
		
		JButton btnNao = new JButton("Não");
		btnSim.setFont(hkGrotesk);
		btnNao.setBackground(Cores.COR_VERMELHO);
		btnNao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resposta = false;
				setVisible(false);
			}
		});
		painelSul.add(btnSim);
		painelSul.add(btnNao);
		painelSul.add(new JPanel());
		panel.add(painelSul, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(btnSim);
		setSize(350,200);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	public boolean getResposta() {
		return resposta;
	}
}
package visao;

import java.awt.BorderLayout;
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

public class TelaMensagens extends JDialog {

    private Font hkGrotesk;
    private static final long serialVersionUID = 1L;
    private boolean resposta;

    // Construtor para mensagens simples
    public TelaMensagens(String mensagem, int tipo) {
        setTitle("Mensagem");
        setModal(true);
        setResizable(false);

        hkGrotesk = Utils.loadCustomFont();

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Cores.COR_ROSA_CLARO);
        getContentPane().add(panel);

        // Label para a mensagem
        JLabel lblMensagem = new JLabel(mensagem, SwingConstants.CENTER);
        lblMensagem.setFont(new Font("Hanken Grotesk", Font.PLAIN, 15));
        panel.add(lblMensagem, BorderLayout.CENTER);

        // Adicionar o ícone baseado no tipo
        ImageIcon icon = getIconByType(tipo);
        if (icon != null) {
            lblMensagem.setIcon(scaleImage(icon, 100, 100)); // Ajustar o tamanho do ícone
        }

        // Painel inferior com botão OK
        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelSul.setBackground(Cores.COR_ROSA_CLARO);
        JButton btnOK = new JButton("OK");
        btnOK.setFont(hkGrotesk);
        btnOK.setBackground(Cores.COR_AZUL);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        painelSul.add(btnOK);
        panel.add(painelSul, BorderLayout.SOUTH);

        // Ajusta o tamanho da janela para se ajustar ao conteúdo
        pack();
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    // Construtor para perguntas (Sim/Não)
    public TelaMensagens(String pergunta) {
        setAlwaysOnTop(true);
        setTitle("Mensagem");
        setModal(true);
        setResizable(false);

        hkGrotesk = Utils.loadCustomFont();

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Cores.COR_ROSA_CLARO);
        getContentPane().add(panel);

        // Label com a pergunta
        JLabel labelPergunta = new JLabel(pergunta, SwingConstants.CENTER);
        labelPergunta.setFont(new Font("Dialog", Font.PLAIN, 15));
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/imagens/Pergunta.png"));
        labelPergunta.setIcon(scaleImage(imageIcon, 100, 100)); // Ajustar o tamanho do ícone
        panel.add(labelPergunta, BorderLayout.CENTER);

        // Painel inferior com botões Sim e Não
        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelSul.setBackground(Cores.COR_ROSA_CLARO);

        JButton btnSim = new JButton("Sim");
        btnSim.setFont(hkGrotesk);
        btnSim.setBackground(Cores.COR_AZUL);
        btnSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resposta = true;
                setVisible(false);
            }
        });

        JButton btnNao = new JButton("Não");
        btnNao.setFont(hkGrotesk);
        btnNao.setBackground(Cores.COR_VERMELHO);
        btnNao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resposta = false;
                setVisible(false);
            }
        });

        painelSul.add(btnSim);
        painelSul.add(btnNao);
        panel.add(painelSul, BorderLayout.SOUTH);

        // Ajusta o tamanho da janela para se ajustar ao conteúdo
        pack();
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    // Função para obter a resposta da janela de pergunta (Sim/Não)
    public boolean getResposta() {
        return resposta;
    }

    // Função para obter o ícone baseado no tipo da mensagem
    private ImageIcon getIconByType(int tipo) {
        ImageIcon imageIcon = null;
        switch (tipo) {
            case 0:
                setTitle("Sucesso");
                imageIcon = new ImageIcon(getClass().getResource("/resources/imagens/Check.png"));
                break;
            case 1:
                setTitle("Erro");
                imageIcon = new ImageIcon(getClass().getResource("/resources/imagens/Erro.png"));
                break;
            case 2:
                setTitle("Pergunta");
                imageIcon = new ImageIcon(getClass().getResource("/resources/imagens/Pergunta.png"));
                break;
            case 3:
                setTitle("Alerta");
                imageIcon = new ImageIcon(getClass().getResource("/resources/imagens/Alerta.png"));
                break;
            case 10:
                imageIcon = new ImageIcon(getClass().getResource("/resources/imagens/logo.png"));
                break;
            default:
                break;
        }
        return imageIcon;
    }

    // Função para redimensionar as imagens para o tamanho adequado
    private ImageIcon scaleImage(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
}

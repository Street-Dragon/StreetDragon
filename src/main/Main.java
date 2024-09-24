package main;

import controle.entidade.conexao.ConexaoBD;
import visao.TelaLogin;

public class Main {
    public static void main(String[] args) {
        ConexaoBD.criarBancoDeDadosETabela(); // Criação do DB e tabela
        TelaLogin telaLogin = new TelaLogin(); // Inicializa a tela de login
        telaLogin.setVisible(true); // Exibe a tela
    }
}

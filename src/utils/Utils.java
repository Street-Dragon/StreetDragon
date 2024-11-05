package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class Utils {

	public static Font loadCustomFont() {
		Font customFont = null;
		try {
			customFont = Font
					.createFont(Font.TRUETYPE_FONT,
							Utils.class.getResourceAsStream("/resources/fontes/HankenGrotesk-VariableFont_wght.ttf"))
					.deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
		} catch (FontFormatException e) {
			System.err.println("Formato de fonte inválido: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo da fonte: " + e.getMessage());
			e.printStackTrace();
		}
		return customFont;
	}

	// Enum para tipos de documento
	public enum TipoDocumento {
		CPF, CNPJ, TELEFONE
	}

	public static String formatarDocumentos(String documento, TipoDocumento tipo) {
		switch (tipo) {
		case CPF:
			if (documento.length() != 11) {
				throw new IllegalArgumentException("CPF deve ter 11 dígitos.");
			}
			return String.format("%s.%s.%s-%s", documento.substring(0, 3), documento.substring(3, 6),
					documento.substring(6, 9), documento.substring(9, 11));

		case CNPJ:
			if (documento.length() != 14) {
				throw new IllegalArgumentException("CNPJ deve ter 14 dígitos.");
			}
			return String.format("%s.%s.%s/%s-%s", documento.substring(0, 2), documento.substring(2, 5),
					documento.substring(5, 8), documento.substring(8, 12), documento.substring(12, 14));

		case TELEFONE:
			if (documento.length() != 10 && documento.length() != 11) {
				throw new IllegalArgumentException("Telefone deve ter 10 ou 11 dígitos.");
			}
			if (documento.length() == 10) {
				return String.format("(%s) %s-%s", documento.substring(0, 2), documento.substring(2, 6),
						documento.substring(6, 10));
			} else {
				return String.format("(%s) %s-%s", documento.substring(0, 2), documento.substring(2, 7),
						documento.substring(7, 11));
			}

		default:
			throw new IllegalArgumentException("Tipo de documento não reconhecido.");
		}
	}

	public static boolean isValidCPF(String cpf) {
		// Remove caracteres não numéricos
		cpf = cpf.replaceAll("[^0-9]", "");

		if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
			return false; // Verifica se o CPF possui 11 dígitos e não é uma sequência repetida
		}

		// Cálculo do primeiro dígito verificador
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += (Character.getNumericValue(cpf.charAt(i)) * (10 - i));
		}
		int primeiroDV = 11 - (soma % 11);
		if (primeiroDV >= 10)
			primeiroDV = 0;

		// Cálculo do segundo dígito verificador
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += (Character.getNumericValue(cpf.charAt(i)) * (11 - i));
		}
		int segundoDV = 11 - (soma % 11);
		if (segundoDV >= 10)
			segundoDV = 0;

		return (primeiroDV == Character.getNumericValue(cpf.charAt(9)))
				&& (segundoDV == Character.getNumericValue(cpf.charAt(10)));
	}

	public static boolean isValidCNPJ(String cnpj) {
		// Remove caracteres não numéricos
		cnpj = cnpj.replaceAll("[^0-9]", "");

		if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
			return false; // Verifica se o CNPJ possui 14 dígitos e não é uma sequência repetida
		}

		int[] pesos1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		int[] pesos2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

		// Cálculo do primeiro dígito verificador
		int soma = 0;
		for (int i = 0; i < 12; i++) {
			soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
		}
		int primeiroDV = 11 - (soma % 11);
		if (primeiroDV >= 10)
			primeiroDV = 0;

		// Cálculo do segundo dígito verificador
		soma = 0;
		for (int i = 0; i < 13; i++) {
			soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
		}
		int segundoDV = 11 - (soma % 11);
		if (segundoDV >= 10)
			segundoDV = 0;

		return (primeiroDV == Character.getNumericValue(cnpj.charAt(12)))
				&& (segundoDV == Character.getNumericValue(cnpj.charAt(13)));
	}

	public static boolean isValidTelefone(String telefone) {
		// Remove caracteres não numéricos
		telefone = telefone.replaceAll("[^0-9]", "");

		if (telefone.length() != 10 && telefone.length() != 11) {
			return false; // Verifica se o telefone possui 10 ou 11 dígitos
		}

		// Prefixos válidos para celulares
		String prefixoCelular = telefone.substring(2, 3);
		return prefixoCelular.equals("9") || prefixoCelular.equals("8") || prefixoCelular.equals("7");
	}

	public static ImageIcon carregarIcone(String caminho, int largura, int altura) {
		ImageIcon iconeOriginal = new ImageIcon(Utils.class.getResource("/resources/imagens/" + caminho));
		Image iconeRedimensionada = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
		return new ImageIcon(iconeRedimensionada);
	}

	public static void configTabela(JTable table, JScrollPane scrollPane) {

		// Definir a fonte da tabela
		table.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(255, 233, 233));
		scrollPane.setViewportView(table);
		table.setRowHeight(40); // Define a altura das linhas da tabela

		// Estiliza o cabeçalho da tabela
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		header.setFont(new Font("Hanken Grotesk", Font.PLAIN, 25));

		// Estiliza as células da tabela
		table.setDefaultRenderer(Object.class, (TableCellRenderer) new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				// Define o fundo branco para as células de dados
				c.setBackground(Color.WHITE);

				// Se a célula estiver selecionada, mudar a cor de fundo
				if (isSelected) {
					c.setBackground(new Color(255, 149, 149)); // Cor de fundo para seleção
				}

				return c;
			}
		});
		
		//não deixa reorganizar as colunas
		table.getTableHeader().setReorderingAllowed(false);
	}

}

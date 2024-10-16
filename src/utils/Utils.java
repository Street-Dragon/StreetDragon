package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Utils {
    
    public static Font loadCustomFont() {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, 
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
    
    public static ImageIcon carregarIcone(String caminho, int largura, int altura) {
        ImageIcon iconeOriginal = new ImageIcon(Utils.class.getResource("/resources/imagens/"+caminho));
        Image iconeRedimensionada = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(iconeRedimensionada);
    }
}

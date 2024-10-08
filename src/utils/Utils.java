package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

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
}

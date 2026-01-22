package meuJogo.modelo;

//funcional

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PainelComImagem extends JPanel {

    private BufferedImage imagemFundo;

    public PainelComImagem(String caminhoImagem) {
        
        try {
            imagemFundo = ImageIO.read(getClass().getResource("/imagens/TelaInicial.jpeg"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Erro ao carregar imagem: " + caminhoImagem);
            e.printStackTrace();
        }
        setOpaque(false);
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

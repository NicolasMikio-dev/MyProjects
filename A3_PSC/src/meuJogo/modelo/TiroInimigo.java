package meuJogo.modelo;

//funcional

import java.awt.Image;
import javax.swing.ImageIcon;

public class TiroInimigo {

    private int x, y;
    private int dx, dy;
    private int largura, altura;
    private boolean visivel;
    private Image imagem;

    public TiroInimigo(int x, int y, int alvoX, int alvoY) {
        this.x = x;
        this.y = y;
        this.visivel = true;

        ImageIcon referencia = new ImageIcon(getClass().getResource("/imagens/TiroTankInimigo.png"));
        imagem = referencia.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        largura = 40;
        altura = 40;

        // Cálculo da direção
        int VELOCIDADE = 12;
        
        
        if (alvoX > x) {
            dx = VELOCIDADE;
        } else {
            dx = -VELOCIDADE;
        }

        dy = 0; 
    }

    public void update() {
        x += dx;
        y += dy;

        if (x < 0 || x > 1024 || y < 0 || y > 1024) {
            visivel = false;
        }
    }

    public boolean isVisivel() {
        return visivel;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
}
    




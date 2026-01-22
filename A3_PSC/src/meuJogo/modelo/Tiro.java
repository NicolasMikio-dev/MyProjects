package meuJogo.modelo;

//funcional
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Tiro {

    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;
    private static final int VELOCIDADE = 12;
    private boolean paraDireita;

    public Tiro(int x, int y, boolean paraDireita) {
        this.x = x;
        this.y = y;
        this.paraDireita = paraDireita;
        isVisivel = true;

        ImageIcon referencia = new ImageIcon(getClass().getResource("/imagens/TiroTank.png"));
        imagem = referencia.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        largura = 40;
        altura = 40;
    }

    public void update() {
        if (paraDireita) {
            x += VELOCIDADE;
        } else {
            x -= VELOCIDADE;
        }

        if (x < 0 || x > 1500) {
            isVisivel = false;
        }
        
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public void setVisivel(boolean visivel) {
        this.isVisivel = visivel;
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
}

package meuJogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entidade {

    protected int x, y;
    protected int largura, altura;
    protected int vida;
    protected Image imagem;

    public abstract void update();

    public void levarDano() {
        vida--;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
}

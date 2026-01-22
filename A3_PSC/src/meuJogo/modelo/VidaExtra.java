package meuJogo.modelo;

//funcional

import java.awt.Image;
import javax.swing.*;
import java.awt.*;

public class VidaExtra {

    private int x, y;
    private boolean visivel;
    private Image imagem;
    private long tempoCriacao;
    private final int largura = 32;
    private final int altura = 32;

    private static final long DURACAO = 7000;

    public VidaExtra(int x, int y) {
        this.x = x;
        this.y = y;
        this.visivel = true;
        this.tempoCriacao = System.currentTimeMillis();

        ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/Vida.png")); 
        imagem = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
    }

    public void coletada() {
        visivel = false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public boolean isVisivel() {
        return visivel && !isExpirada();
    }
    
     public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
    
    public boolean isExpirada() {
        return System.currentTimeMillis() - tempoCriacao > DURACAO;
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

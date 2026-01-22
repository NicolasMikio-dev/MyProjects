package meuJogo.modelo;

//funcional
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Player extends Entidade {

    private int dx, dy;
    private List<Tiro> tiros;
    private long ultimoTiro;
    private long tempoRecarga = 2500; // em milissegundos
    private boolean viradoParaDireita = true;

    public Player() {
        this.x = 100;
        this.y = 500;
        this.vida = 5;
        tiros = new ArrayList<>();
    }

    public void load() {
        ImageIcon referencia = viradoParaDireita
                ? new ImageIcon(getClass().getResource("/imagens/TankPlayerDir.png"))
                : new ImageIcon(getClass().getResource("/imagens/TankPlayerEsq.png"));
        imagem = referencia.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        altura = 150;
        largura = 150;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;

        if (x < 0) {
            x = 0;
        }
        if (x + largura > 1500) {
            x = 1500 - largura;
        }
        if (y < 0) {
            y = 0;
        }
        if (y + altura > 728) {
            y = 728 - altura;
        }
    }

    public void tirosSimples() {
        long agora = System.currentTimeMillis();
        if (agora - ultimoTiro >= tempoRecarga) {
            int posicaoX = viradoParaDireita ? x + largura : x - 40;
            int posicaoY = y + altura / 2 - 5;
            this.tiros.add(new Tiro(posicaoX, posicaoY, viradoParaDireita)); // ajuste vertical
            ultimoTiro = agora;
        }
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_A) {
            dx = -3;
            if (viradoParaDireita) {
                viradoParaDireita = false;
                load(); // atualiza a imagem
            }
        }

        if (codigo == KeyEvent.VK_D) {
            dx = 3;
            if (!viradoParaDireita) {
                viradoParaDireita = true;
                load(); // atualiza a imagem
            }
        }

        if (codigo == KeyEvent.VK_W) {
            dy = -3;
        }
        if (codigo == KeyEvent.VK_S) {
            dy = 3;
        }

        if (codigo == KeyEvent.VK_E) {
            tirosSimples();
        }
    }

    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        switch (codigo) {
            case KeyEvent.VK_W, KeyEvent.VK_S ->
                dy = 0;
            case KeyEvent.VK_A, KeyEvent.VK_D ->
                dx = 0;
        }
    }

    public void levarDano() {
        vida--;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public Rectangle getHitBox() {
        return new Rectangle(x + 10, y + 10, largura - 20, altura - 50); // mais precisa
    }

    public long getUltimoTiro() {
        return ultimoTiro;
    }

    public long getTempoRecarga() {
        return tempoRecarga;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setUltimoTiro(long t) {
        this.ultimoTiro = t;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

}

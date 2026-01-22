package meuJogo.modelo;

//funcional
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Inimigo extends Entidade {

    private int dx, dy;
    private boolean visivel;
    private Image imagemEsquerda;
    private Image imagemDireita;
    private int velocidadeX = 2;
    private List<TiroInimigo> tiros;
    private long ultimoTiro;
    private String direcao = "esquerda";
    private final long tempoRecarga = 5000; // ms

    public Inimigo(int x, int y) {
        this.x = x;
        this.y = y;
        this.vida = 3;
        this.visivel = true;
        this.tiros = new ArrayList<>();

        imagemEsquerda = new ImageIcon(getClass().getResource("/imagens/TankInimigo.png"))
                .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imagemDireita = new ImageIcon(getClass().getResource("/imagens/TankInimigoDir.png"))
                .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

        imagem = imagemEsquerda;
        largura = 150;
        altura = 150;
    }

    @Override
    public void update() {
        
    }

    public void update(int playerX, int playerY) {
        if (!visivel) {
            return;
        }

        double dx = playerX - this.x;
        double dy = playerY - this.y;
        double distancia = Math.sqrt(dx * dx + dy * dy);

        if (distancia > 5) {
            double direcaoX = dx / distancia;
            double direcaoY = dy / distancia;

            x += (int) (direcaoX * velocidadeX);
            y += (int) (direcaoY * velocidadeX);

            imagem = direcaoX >= 0 ? imagemDireita : imagemEsquerda;
            direcao = direcaoX >= 0 ? "direita" : "esquerda";
        }

        if (System.currentTimeMillis() - ultimoTiro > tempoRecarga) {
            atirar(playerX, playerY);
            ultimoTiro = System.currentTimeMillis();
        }

        for (int i = 0; i < tiros.size(); i++) {
            TiroInimigo t = tiros.get(i);
            t.update();
            if (!t.isVisivel()) {
                tiros.remove(i);
            }
        }
    }

    public void renascer() {
        this.x = 800 + (int) (Math.random() * 400); 
        this.y = 100 + (int) (Math.random() * 300); 
        this.vida = 3;
        this.visivel = true;
    }

    public void atirar(int alvoX, int alvoY) {
        TiroInimigo tiro = new TiroInimigo(x + largura / 2, y + altura / 2, alvoX, alvoY);
        tiros.add(tiro);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void levarDano() {
        vida--;
        if (vida <= 0) {
            visivel = false;
        }
    }

    public int getLargura() {
        return largura;
    }

    // Getters
    public String getDirecao() {
        return direcao;
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

    public Image getImagem() {
        return imagem;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public List<TiroInimigo> getTiros() {
        return tiros;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

}

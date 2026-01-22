package meuJogo.modelo;

//funcional
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Image gameOverPrint;
    private Player player;
    private Timer timer;
    private List<Inimigo> inimigos;
    private JButton botaoVoltar;
    private boolean gameOver = false;
    private MenuInicial menu;
    private int inimigosAbatidos = 0;
    private VidaExtra vidaExtra = null;
    private final int VIDA_MAXIMA_PLAYER = 16;
    private JButton botaoPausa;
    private JButton botaoContinuar;
    private JButton botaoSair;
    private JPanel menuPausa;
    private boolean jogoPausado = false;
    private long tempoPausaIniciado = 0;
    private long tempoTotalDePausa;

   public Fase(MenuInicial menu) {
        this.menu = menu;
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon(getClass().getResource("/imagens/FundoCenario.jpg"));
        fundo = referencia.getImage();

        player = new Player();
        player.load();

        inicializaInimigos();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(10, this);
        timer.start();

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            if (menu != null) {
                menu.voltarAoMenu();
            }
        });

        add(botaoVoltar);

        botaoPausa = new JButton("⏸ / ▶");
        botaoPausa.setFocusable(false);
        botaoPausa.setVisible(true);

        botaoPausa.addActionListener(e -> {
            timer.stop(); // pausa o jogo
            menuPausa.setVisible(true); // mostra o menu
        });

        setLayout(null); // usar layout absoluto
        add(botaoPausa);
        setComponentZOrder(botaoPausa, 0);

// Menu de pausa (painel flutuante)
        menuPausa = new JPanel();
        menuPausa.setLayout(new GridLayout(3, 1, 10, 10));
        menuPausa.setBounds(400, 250, 200, 150);
        menuPausa.setBackground(new Color(50, 50, 50, 220)); // semi-transparente

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.addActionListener(e -> alternarPausa());

        JButton btnMenu = new JButton("Menu Inicial");
        btnMenu.addActionListener(e -> {
            tempoTotalDePausa = 0;
            if (menu != null) {
                menu.voltarAoMenu();
            }
        });
        
        String[] opcoes = {"Sim", "Não"}; 
        
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> System.exit(0));

        menuPausa.add(btnContinuar);
        menuPausa.add(btnMenu);
        menuPausa.add(btnSair);

        menuPausa.setVisible(false);
        add(menuPausa);
        setComponentZOrder(menuPausa, 0);

    }

    private void inicializaInimigos() {
        inimigos = new ArrayList<>();
        inimigos.add(new Inimigo(800, 100));

    }

    private void alternarPausa() {
        jogoPausado = !jogoPausado;
        menuPausa.setVisible(jogoPausado);

        if (jogoPausado) {
            tempoPausaIniciado = System.currentTimeMillis();
            timer.stop();
        } else {
            long tempoDespausado = System.currentTimeMillis();
            long tempoDePausa = tempoDespausado - tempoPausaIniciado;

            // Corrige o tempo de recarga do tiro
            long novoUltimoTiro = player.getUltimoTiro() + tempoDePausa;
            player.setUltimoTiro(novoUltimoTiro);

            timer.start();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graficos = (Graphics2D) g;

        //botaoPausa.setBounds(getWidth() - 70, 10, 60, 30);
        int larguraBotao = 100;
        int alturaBotao = 30;
        int posX = (getWidth() - larguraBotao) / 2;
        int posY = 20;
        botaoPausa.setBounds(posX, posY, larguraBotao, alturaBotao);

        graficos.drawImage(fundo, 0, 0, null);

        if (!gameOver) {
            graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

            // Tiros do player
            for (Tiro tiro : player.getTiros()) {
                graficos.drawImage(tiro.getImagem(), tiro.getX(), tiro.getY(), this);
            }

            // Barra de recarga (acima do tanque)
            int vidaX = player.getX() + 25;
            int vidaY = player.getY() - 20;
            int larguraVida = 100;
            int alturaVida = 8;
            int vidaMaxima = 5;
            int vidaAtual = (int) (larguraVida * (player.getVida() / (double) vidaMaxima));

            graficos.setColor(Color.GREEN); // vida atual
            graficos.fillRect(vidaX, vidaY, vidaAtual, alturaVida);

            graficos.setColor(Color.RED); // fundo
            graficos.fillRect(vidaX + vidaAtual, vidaY, larguraVida - vidaAtual, alturaVida);

            graficos.setColor(Color.BLACK); // borda
            graficos.drawRect(vidaX, vidaY, larguraVida, alturaVida);

            // === BARRA DE RECARGA ===
            int barraX = player.getX() + 25;
            int barraY = player.getY() - 32;
            int larguraBarra = 100;
            int alturaBarra = 6;

            long tempoAtual = System.currentTimeMillis();
            long tempoDesdeUltimoTiro = tempoAtual - player.getUltimoTiro();
            long tempoRecarga = player.getTempoRecarga();

            float proporcao = Math.min(1.0f, (float) tempoDesdeUltimoTiro / tempoRecarga);
            int larguraAtual = (int) (larguraBarra * proporcao);

            graficos.setColor(Color.DARK_GRAY); // fundo da barra
            graficos.fillRect(barraX, barraY, larguraBarra, alturaBarra);
            graficos.setColor(Color.YELLOW); // barra de recarga
            graficos.fillRect(barraX, barraY, larguraAtual, alturaBarra);
            graficos.setColor(Color.BLACK); // borda
            graficos.drawRect(barraX, barraY, larguraBarra, alturaBarra);

//_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
            // Inimigos e seus tiros
            for (Inimigo inimigo : inimigos) {

                int vidaInimigoX = inimigo.getX();
                int vidaInimigoY = inimigo.getY() - 10; // acima do inimigo
                int larguraVidaInimigo = 100;
                int alturaVidaInimigo = 6;

                int vidaMaximaInimigo = 3;
                int vidaAtualInimigo = (int) (larguraVidaInimigo * (inimigo.getVida() / (double) vidaMaximaInimigo));

                // Verde: vida atual
                graficos.setColor(Color.GREEN);
                graficos.fillRect(vidaInimigoX, vidaInimigoY, vidaAtualInimigo, alturaVidaInimigo);

                // Vermelho: parte perdida
                graficos.setColor(Color.RED);
                graficos.fillRect(vidaInimigoX + vidaAtualInimigo, vidaInimigoY, larguraVidaInimigo - vidaAtualInimigo, alturaVidaInimigo);

                // Borda
                graficos.setColor(Color.BLACK);
                graficos.drawRect(vidaInimigoX, vidaInimigoY, larguraVidaInimigo, alturaVidaInimigo);

                if (inimigo.isVisivel()) {
                    graficos.drawImage(inimigo.getImagem(), inimigo.getX(), inimigo.getY(), this);
                    for (TiroInimigo tiro : inimigo.getTiros()) {
                        graficos.drawImage(tiro.getImagem(), tiro.getX(), tiro.getY(), this);
                    }
                }
            }

            if (vidaExtra != null && vidaExtra.isVisivel()) {
                graficos.drawImage(vidaExtra.getImagem(), vidaExtra.getX(), vidaExtra.getY(), this);
            }
            // Vida do player
            graficos.setColor(Color.WHITE);
            graficos.drawString("Vida: " + player.getVida(), 10, 20);

            graficos.setFont(new Font("Arial", Font.BOLD, 20));
            graficos.drawString("Inimigos abatidos: " + inimigosAbatidos, 820, 30);
        } else {
            if (gameOverPrint == null) {
                ImageIcon ref = new ImageIcon(getClass().getResource("/imagens/GameOver2.jpg"));
                gameOverPrint = ref.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            }
            graficos.drawImage(gameOverPrint, 0, 0, this);

            int larguraJanelaP = getWidth();
            int alturaJanelaP = getHeight();
            int larguraBotaoP = 200;
            int alturaBotaoP = 40;
            int x = (larguraJanelaP - larguraBotaoP) / 2;
            int y = alturaJanelaP - alturaBotao - 100;

            botaoVoltar.setBounds(x, y, larguraBotao, alturaBotao);

        }

        paintChildren(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jogoPausado || gameOver) {
            return;
        }

        if (player.getVida() <= 0) {
            gameOver = true;
            botaoPausa.setVisible(false); // Esconde o botão de pausa no game over
            menuPausa.setVisible(false);
            timer.stop();
        }

        player.update();

        // Atualizar tiros do player
        List<Tiro> tiros = player.getTiros();
        for (int i = 0; i < tiros.size(); i++) {
            Tiro t = tiros.get(i);
            t.update();
            if (!t.isVisivel()) {
                tiros.remove(i);
                i--; // Ajustar índice após remoção
            }
        }

        // Atualizar inimigos
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);

            if (inimigo.getVida() <= 0 || !inimigo.isVisivel()) {
                inimigosAbatidos++;
                if (inimigosAbatidos % 10 == 0 && vidaExtra == null) {
                    int dropX = inimigo.getX();
                    int dropY = inimigo.getY();
                    vidaExtra = new VidaExtra(dropX, dropY);
                }
                inimigo.setVisivel(false);
                inimigo.renascer(); // Renasce se foi derrotado
                continue; 
            }

            if (!inimigo.isVisivel()) {
                continue;
            }

            inimigo.update(player.getX(), player.getY());

            // Verificar se o inimigo foi atingido por algum tiro
            for (int j = 0; j < tiros.size(); j++) {
                Tiro t = tiros.get(j);
                if (new Rectangle(t.getX(), t.getY(), t.getLargura(), t.getAltura())
                        .intersects(inimigo.getBounds())) {
                    inimigo.levarDano();
                    t.setVisivel(false);
                }
            }

            List<TiroInimigo> tirosInimigos = inimigo.getTiros();
            for (int j = 0; j < tirosInimigos.size(); j++) {
                TiroInimigo ti = tirosInimigos.get(j);
                if (new Rectangle(ti.getX(), ti.getY(), ti.getLargura(), ti.getAltura())
                        .intersects(player.getHitBox())) {
                    
                    player.levarDano();
                    tirosInimigos.remove(j);
                    j--; 
                }
            }
        }

        if (vidaExtra != null && vidaExtra.isVisivel()) {
            if (player.getHitBox().intersects(vidaExtra.getBounds())) {
                vidaExtra.coletada();
                if (player.getVida() < VIDA_MAXIMA_PLAYER) {
                    player.setVida(player.getVida() + 1);
                }
                vidaExtra = null;
            }
        }

        if (vidaExtra != null) {
            if (vidaExtra.isVisivel()) {
                if (vidaExtra.getBounds().intersects(player.getHitBox())) {
                    if (player.getVida() < VIDA_MAXIMA_PLAYER) {
                        player.setVida(player.getVida() + 1);
                    }
                    vidaExtra.setVisivel(false); // some ao encostar
                } else if (vidaExtra.isExpirada()) {
                    vidaExtra.setVisivel(false); // some após o tempo
                }
            }
        }

        repaint();
    }

    public void resetarEstado() {
        tempoTotalDePausa = 0;
        tempoPausaIniciado = 0;
        inimigosAbatidos = 0;
        gameOver = false;
        vidaExtra = null;
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}

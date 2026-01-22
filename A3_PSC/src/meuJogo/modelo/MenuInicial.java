package meuJogo.modelo;

//funcional
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuInicial extends JFrame {

    private JPanel painelMenu;

    public MenuInicial() {
        
        setTitle("Meu Jogo - Menu Inicial");
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setResizable(false);

        painelMenu = new PainelComImagem("/imagens/TelaInicial.jpeg");
        painelMenu.setLayout(new GridBagLayout());

        JButton botaoIniciar = new JButton("Iniciar Jogo");
        JButton botaoSair = new JButton("Sair");

        Dimension tamanhoBotao = new Dimension(200, 50);

        botaoIniciar.setPreferredSize(tamanhoBotao);
        botaoSair.setPreferredSize(tamanhoBotao);
        botaoIniciar.addActionListener(e -> iniciarJogo());

        String[] opcoes = {"Sim", "Não"};

        botaoSair.addActionListener(e -> {
            int resposta = JOptionPane.showOptionDialog(
                    null,
                    "Tem certeza que deseja sair do jogo?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[1]
            );

            if (resposta == 0) {
                System.exit(0);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 0;
        painelMenu.add(Box.createVerticalStrut(450), gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        painelMenu.add(botaoIniciar, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        painelMenu.add(botaoSair, gbc);

        add(painelMenu);
        setVisible(true);
    }

    public void iniciarJogo() {
        getContentPane().removeAll(); 
        Fase fase = new Fase(this);
        fase.resetarEstado(); 
        add(fase);                  
        revalidate();
        repaint();
        fase.requestFocusInWindow();
    }

    public void voltarAoMenu() {
        getContentPane().removeAll(); 
        add(painelMenu);              
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuInicial::new);
        
    }

}

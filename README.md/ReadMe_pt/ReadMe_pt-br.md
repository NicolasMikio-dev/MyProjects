# PROJETO TANK SHOOTER

#### Vídeo de Demonstração: [Assistir no YouTube](https://youtu.be/FBt-e3F8tac)

---
## Aviso
Para executar este jogo, você precisará do MySQL Server instalado e das bibliotecas localizadas na pasta lib do projeto: AbsoluteLayout.jar e mysql-connector-j-9.2.0.jar.

### Configuração no VS Code
Pressione F1 ou Ctrl + Shift + P no teclado.

Busque por "Java: Configure Classpath".

Na aba Sources, clique em "+ Add Source Root" e selecione a pasta A3_PSC/src.

Na aba Libraries, clique em "+ Add Library" e selecione os arquivos AbsoluteLayout.jar e mysql-connector-j-9.2.0.jar contidos na pasta lib.

### Configuração do Banco de Dados

O projeto utiliza o MySQL para gerenciar o login dos usuários. Execute o script abaixo no seu terminal ou editor SQL para preparar o ambiente:

CREATE DATABASE jogo_db;

USE jogo_db;

CREATE TABLE usuario (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nome_usuario VARCHAR(50) NOT NULL,
  senha_usuario VARCHAR(50) NOT NULL
);
                            
## Descrição

Este não é apenas um simples jogo arcade; é uma demonstração abrangente de desenvolvimento full-stack em Java, combinando jogabilidade 2D dinâmica com um sistema de autenticação de usuários persistente.
Construído inteiramente em Java, este projeto exibe uma arquitetura limpa, integração de banco de dados JDBC e princípios de design de jogos orientados a objetos.
Cada recurso foi projetado para reforçar boas práticas de programação, modularidade e escalabilidade, resultando em uma base de código coesa e fácil de manter.

---

# Jogo de Batalha de Tanques 2D com Sistema de Login (Java + MySQL)

Este projeto é um **jogo de batalha de tanques 2D construído em Java**, apresentando uma interface gráfica usando **Swing** e integração com **MySQL** para cadastro e login de usuários.
O jogo combina **ação, persistência e programação orientada a objetos**, oferecendo movimento fluido, mecânicas de tiro, IA de inimigos, funcionalidade de pausa e itens de vida extra — tudo dentro de um ambiente responsivo e dinâmico.

O objetivo deste projeto é simular um cenário de software do mundo real, integrando conceitos de **frontend e backend** de uma forma divertida e interativa.
Ele serve como uma demonstração prática de como o Java pode ser usado não apenas para aplicações empresariais, mas também para lógica de jogos 2D em tempo real.

---

## Principais Recursos

### Sistema do Jogador
- Mover em todas as direções (**W, A, S, D**).
- Atirar usando a tecla **E** com uma barra de recarga visível.
- Barra de vida exibida acima do tanque.
- Movimento do jogador restrito aos limites do jogo.

#### Estrutura de arquivos
- **A3_PSC/src/meuJoogo/modelo** esta pasta contém:
  - `Player.java`: define teclas, vida do jogador, tempo de recarga e animação de tiro.
  - `Tiro.java`: define tamanho da bala, imagem e velocidade.
  - `Entidade.java`: define funções de dano para jogadores e inimigos.

Além disso, o sistema do jogador inclui detecção de colisão com inimigos, balas e itens de vida extra.
Os controles foram ajustados para proporcionar uma experiência fluida mesmo em máquinas menos potentes.

---

### Inimigos
- Seguem automaticamente a posição do jogador usando uma IA simples.
- Disparam projéteis em intervalos regulares.
- Cada inimigo possui uma barra de vida individual.
- Renascem em posições aleatórias após serem derrotados.

#### Estrutura de arquivos
- **A3_PSC/src/meuJogo/modelo** esta pasta contém:
  - `TiroInimigo.java`: define tamanho da bala, imagem de referência e velocidade.
  - `Inimigo.java`: define imagem de referência do inimigo, vida, tempo de carga, animação de tiro, função de dano, função de renascimento e tamanho.

O sistema de inimigos foi implementado usando lógica baseada em tempo, garantindo comportamento previsível independentemente da taxa de quadros (frame rate).
Melhorias futuras podem incluir pathfinding (busca de caminhos) e diferentes tipos de inimigos.

---

### Itens de Vida Extra
- Aparecem após o jogador derrotar um número específico de inimigos.
- Desaparecem após um curto período ou quando coletados.
- Restauram um ponto de vida (até um máximo definido).

#### Estrutura de arquivos
- **A3_PSC/src/meuJogo/modelo** esta pasta contém:
  - `VidaExtra.java`: define tempo de criação da vida extra, tamanho, imagem e função de coleta.

Esses itens adicionam profundidade estratégica ao jogo, recompensando o desempenho consistente e aumentando a rejogabilidade.

---

### Sistema de Pausa
- Menu interativo com três opções:
  - **Continuar** – retoma o jogo e ajusta os temporizadores de recarga corretamente.
  - **Menu Principal** – retorna à tela inicial.
  - **Sair do Jogo** – fecha a aplicação.
- Implementado usando uma sobreposição de `JPanel` semitransparente.

#### Estrutura de arquivos
- **A3_PSC/src/meuJogo/modelo** esta pasta contém:
  - `Fase.java`: exibe botões para pausar, continuar ou retornar ao menu principal.

O sistema de pausa garante que a temporização do jogo (especialmente temporizadores de recarga e lógica de respawn) permaneça consistente mesmo após interrupções.

---

### Sistema de Login e Cadastro
- **Tela de cadastro** que armazena novos usuários no banco de dados.
- **Tela de login** para autenticação com nome de usuário e senha.
- Conectado ao **MySQL** usando **JDBC**.
- Após o login bem-sucedido, o usuário é redirecionado para o menu principal.

#### Estrutura de Arquivos
- **A3_PSC/src/meuJoogo/modelo**
  - `MenuInicial.java`: tela para iniciar ou sair do jogo.

- **A3_PSC/src/dao**
  - `Conexao.java`: conecta o usuário ao banco de dados.
  - `UsuarioDAO.java`: registra e valida usuários.

- **A3_PSC/src/view**
  - `FormCadastroView.form`: formulário para cadastro de usuários.
  - `FormCadastroView.java`: cria o formulário de cadastro.
  - `LoginView.form`: formulário de login para usuários.
  - `LoginView.java`: cria a interface de login.

O sistema de autenticação valida as credenciais do usuário de forma segura por meio de consultas parametrizadas, prevenindo injeção de SQL.
Ele também garante que as sessões sejam isoladas, com feedback claro para credenciais inválidas.

---

## Estrutura do Projeto

| Classe / Pacote | Descrição |
|-----------------|-----------|
| **FormCadastroControler** | Lida com o formulário de cadastro de usuário. Lê a entrada da view e salva os dados via `UsuarioDAO`. |
| **LoginControler** | Gerencia a autenticação. Abre o menu principal (`MenuInicial`) quando o login é bem-sucedido. |
| **Conexao** | Estabelece uma conexão JDBC com o banco de dados MySQL (`jogo_db`). |
| **UsuarioDAO** | Realiza operações CRUD e validação de usuário para login. |
| **MenuInicial** | Janela do menu principal com botões de **Iniciar Jogo** e **Sair**. |
| **Fase** | Cena central do jogo que gerencia atualizações, colisões, HUD e estados do jogo (pausa, game over). |
| **Player** | Controla o movimento, direção e lógica de tiro do jogador. |
| **Inimigo** | Define o comportamento do inimigo, movimento da IA em direção ao jogador e tiros. |
| **Tiro / TiroInimigo** | Classes para projéteis disparados pelo jogador e pelos inimigos. |
| **VidaExtra** | Colecionável de vida extra que aparece temporariamente após derrotar inimigos. |
| **Entidade** | Classe base abstrata compartilhada pelo jogador e inimigos (posição, tamanho, vida, imagem, colisão). |
| **PainelComImagem** | `JPanel` personalizado usado para renderizar imagens de fundo para menus e cenas. |

Esta estrutura modular promove a reutilização de código e clareza, tornando atualizações futuras — como adicionar novas armas, inimigos ou power-ups — diretas.

---
## Como Executar
Siga estes passos para compilar e executar o projeto em sua máquina:

- Abra o projeto na sua IDE (por exemplo, IntelliJ IDEA, Eclipse ou NetBeans).
- Configure o banco de dados MySQL usando o script SQL fornecido abaixo.
- Em `Conexao.java`, atualize as seguintes linhas com suas credenciais locais do banco de dados:
    - `private static final String URL = "jdbc:mysql://localhost:3306/jogo_db";`
    - `private static final String USER = "root";`
    - `private static final String PASSWORD = "sua_senha";`
- Compile e execute o projeto.
- Use a tela de cadastro para criar uma nova conta, depois faça login para acessar o menu principal.
- Clique em Iniciar Jogo para começar a jogar.



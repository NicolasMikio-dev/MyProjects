# TANK SHOOTER PROJECT

#### Video Demo: [Watch on YouTube](https://youtu.be/FBt-e3F8tac)

---

## Pay attention

### Prerequisites and Setup
To run this game on your machine, you must have MySQL Server installed and include the following libraries (found in the lib folder) in your project's classpath: AbsoluteLayout.jar and mysql-connector-j-9.2.0.jar.

### VS Code Configuration
Press F1 or use the shortcut Ctrl + Shift + P.

Search for "Java: Configure Classpath".

In the Sources tab, click "+ Add Source Root" and select the folder A3_PSC/src.

In the Libraries tab, click "+ Add Library" and select the files AbsoluteLayout.jar and mysql-connector-j-9.2.0.jar from the lib directory.

### Database Configuration
This project uses MySQL to store user credentials (username and password). Before running the application, create the database and the necessary table using the following SQL script:

CREATE DATABASE jogo_db;
USE jogo_db;

CREATE TABLE usuario (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nome_usuario VARCHAR(50) NOT NULL,
  senha_usuario VARCHAR(50) NOT NULL
);

## Description

This isn't just a simple arcade game; it's a comprehensive demonstration of full-stack Java development, combining dynamic 2D gameplay with a persistent user authentication system.
Built entirely in Java, this project showcases clean architecture, JDBC database integration, and object-oriented game design principles.
Every feature was designed to reinforce good programming practices, modularity, and scalability, resulting in a cohesive and maintainable codebase.

---

#  2D Tank Battle Game with Login System (Java + MySQL)

This project is a **2D tank battle game built in Java**, featuring a graphical interface using **Swing** and integration with **MySQL** for user registration and login.
The game combines **action, persistence, and object-oriented programming**, offering smooth movement, shooting mechanics, enemy AI, pause functionality, and extra-life items — all within a responsive and dynamic environment.

The goal of this project is to simulate a real-world software scenario, integrating both **frontend and backend concepts** in a fun and interactive way.
It serves as a practical demonstration of how Java can be used not only for business applications but also for real-time 2D game logic.

---

## Main Features

### Player System
- Move in all directions (**W, A, S, D**).
- Shoot using the **E** key with a visible reload bar.
- Health bar displayed above the tank.
- Player movement restricted within the game boundaries.

#### File structure
- **A3_PSC/src/meuJoogo/modelo** this folder holds:
  - `Player.java`: sets keys, player’s life, reload time, and shooting animation.
  - `Tiro.java`: defines bullet size, image, and speed.
  - `Entidade.java`: defines damage functions for players and enemies.

In addition to these, the player system includes collision detection with enemies, bullets, and extra life items.
The controls were tuned to provide a smooth experience even on lower-end machines.

---

### Enemies
- Automatically follow the player’s position using simple AI.
- Shoot projectiles at regular intervals.
- Each enemy has an individual health bar.
- Respawn in random positions after being defeated.

#### File structure
- **A3_PSC/src/meuJogo/modelo** this folder holds:
  - `TiroInimigo.java`: defines bullet size, reference image, and speed.
  - `Inimigo.java`: sets enemy reference image, life, load time, shooting animation, damage function, respawn function, and size.

The enemy system was implemented using time-based logic, ensuring predictable behavior regardless of frame rate.
Future improvements may include pathfinding and different enemy types.

---

### Extra Life Items
- Spawn after the player defeats a specific number of enemies.
- Disappear after a short duration or when collected.
- Restore one health point (up to a defined maximum).

#### File structure
- **A3_PSC/src/meuJogo/modelo** this folder holds:
  - `VidaExtra.java`: defines extra life creation time, size, image, and collect function.

These items add strategic depth to the game, rewarding consistent performance and increasing replayability.

---

### Pause System
- Interactive menu with three options:
  - **Continue** – resumes gameplay and adjusts shot reload timers correctly.
  - **Main Menu** – returns to the initial screen.
  - **Exit Game** – closes the application.
- Implemented using a semi-transparent `JPanel` overlay.

#### File structure
- **A3_PSC/src/meuJogo/modelo** this folder holds:
  - `Fase.java`: displays buttons to pause, continue, or return to the main menu.

The pause system ensures that the gameplay timing (especially reload timers and spawn logic) remains consistent even after interruptions.

---

### Login and Registration System
- **Registration screen** that stores new users in the database.
- **Login screen** for authentication with username and password.
- Connected to **MySQL** using **JDBC**.
- Upon successful login, the user is redirected to the main menu.

#### Files Structure
- **A3_PSC/src/meuJoogo/modelo**
  - `MenuInicial.java`: screen to start or exit the game.

- **A3_PSC/src/dao**
  - `Conexao.java`: connects the user to the database.
  - `UsuarioDAO.java`: registers and validates users.

- **A3_PSC/src/view**
  - `FormCadastroView.form`: form for user registration.
  - `FormCadastroView.java`: creates the registration form.
  - `LoginView.form`: login form for users.
  - `LoginView.java`: creates the login interface.

The authentication system validates user credentials securely through parameterized queries, preventing SQL injection.
It also ensures that sessions are isolated, with clear feedback for invalid credentials.

---

## Project Structure

| Class / Package | Description |
|-----------------|--------------|
| **FormCadastroControler** | Handles the user registration form. Reads input from the view and saves data via `UsuarioDAO`. |
| **LoginControler** | Manages authentication. Opens the main menu (`MenuInicial`) when login succeeds. |
| **Conexao** | Establishes a JDBC connection to the MySQL database (`jogo_db`). |
| **UsuarioDAO** | Performs CRUD operations and user validation for login. |
| **MenuInicial** | Main menu window with **Start Game** and **Exit** buttons. |
| **Fase** | Core gameplay scene that manages updates, collisions, HUD, and game states (pause, game over). |
| **Player** | Controls player movement, direction, and shooting logic. |
| **Inimigo** | Defines enemy behavior, AI movement toward the player, and shooting. |
| **Tiro / TiroInimigo** | Classes for projectiles fired by the player and by enemies. |
| **VidaExtra** | Extra-life collectible that temporarily appears after defeating enemies. |
| **Entidade** | Abstract base class shared by player and enemies (position, size, health, image, collision). |
| **PainelComImagem** | Custom `JPanel` used to render background images for menus and scenes. |

This modular structure promotes code reuse and clarity, making future updates—like adding new weapons, enemies, or power-ups—straightforward.

---
## How to Run
Follow these steps to compile and execute the project on your machine:

- Open the project in your IDE (e.g., IntelliJ IDEA, Eclipse, or NetBeans).
- Set up the MySQL database using the SQL script provided below.
- In Conexao.java, update the following lines with your local database credentials:
    - private static final String URL = "jdbc:mysql://localhost:3306/jogo_db";
    - private static final String USER = "root";
    - private static final String PASSWORD = "your_password";
- Compile and run the project.
- Use the registration screen to create a new account, then log in to access the main menu.
- Click Start Game to begin playing.


## Database Setup

The project uses **MySQL** to store user data (username and password).
Before running the program, create the database and table with the following SQL script:

```sql
CREATE DATABASE jogo_db;

USE jogo_db;

CREATE TABLE usuario (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nome_usuario VARCHAR(50) NOT NULL,
  senha_usuario VARCHAR(50) NOT NULL
);

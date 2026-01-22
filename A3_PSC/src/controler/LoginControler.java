/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import meuJogo.modelo.MenuInicial;
import model.Usuario;
import view.LoginView;

/**
 *
 * @author mikio
 */
public class LoginControler {

    private LoginView view;

    public LoginControler(LoginView view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = new String(view.getjPasswordFieldSenha().getPassword());

        Usuario usuarioAutenticar = new Usuario(usuario, senha);

        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);

        boolean existe = usuarioDao.existeNoBancoPorUsuarioESenha(usuarioAutenticar);

        if (existe) {
            // Aqui está o uso correto:
            MenuInicial menuInicial = new MenuInicial();
            menuInicial.setVisible(true);
            view.dispose(); // Fecha a janela de login
        } else {
            JOptionPane.showMessageDialog(view, "Usuário ou senha inválidos!");
        }
    }

}
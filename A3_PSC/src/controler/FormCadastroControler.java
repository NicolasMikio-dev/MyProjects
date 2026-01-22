package controler;

import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import view.FormCadastroView;

/**
 *
 * @author mikio
 */
public class FormCadastroControler {

    private FormCadastroView view;

    public FormCadastroControler(FormCadastroView view) {
        this.view = view;
    }

    public void salvaUsuario() {

        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();

        Usuario usuarioVictor = new Usuario(usuario, senha);

        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insert(usuarioVictor);

            JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso");
            
            view.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(FormCadastroView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

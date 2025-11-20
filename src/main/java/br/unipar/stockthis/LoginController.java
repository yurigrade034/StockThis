package br.unipar.stockthis;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblMensagem;

    @FXML
    private void fazerLogin() {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            lblMensagem.setText("Informe email e senha.");
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.login(email, senha);

        if (u != null) {
            HelloApplication.setUsuarioLogado(u);
            HelloApplication.mudarCena("Estoque");
        } else {
            lblMensagem.setText("Email ou senha inválidos.");
        }
    }

    @FXML
    private void irParaCadastro() {
        HelloApplication.mudarCena("Cadastro");
    }

    @FXML
    private void irParaRedefinirSenha() {
        HelloApplication.mudarCena("Redefinir_senha");
    }
}

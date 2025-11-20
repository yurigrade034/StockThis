package br.unipar.stockthis;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RedefinirSenhaController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtNovaSenha;

    @FXML
    private Label lblMensagem;

    @FXML
    private void alterarSenha() {
        String email = txtEmail.getText().trim();
        String novaSenha = txtNovaSenha.getText().trim();

        if (email.isEmpty() || novaSenha.isEmpty()) {
            lblMensagem.setText("Preencha email e nova senha.");
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        boolean ok = dao.alterarSenha(email, novaSenha);

        if (ok) {
            lblMensagem.setText("Senha alterada com sucesso!");
        } else {
            lblMensagem.setText("Email não encontrado.");
        }
    }

    @FXML
    private void voltarLogin() {
        HelloApplication.mudarCena("Login");
    }
}

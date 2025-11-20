package br.unipar.stockthis;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Label lblMensagem;

    @FXML
    private void cadastrar() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            if (lblMensagem != null) {
                lblMensagem.setText("Preencha todos os campos.");
            }
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        boolean ok = dao.cadastrar(nome, email, senha);

        if (ok) {
            if (lblMensagem != null) {
                lblMensagem.setText("Usuário cadastrado com sucesso!");
            }
            HelloApplication.mudarCena("Login");
        } else {
            if (lblMensagem != null) {
                lblMensagem.setText("Erro ao cadastrar. Email já existe?");
            }
        }
    }

    @FXML
    private void voltarLogin() {
        HelloApplication.mudarCena("Login");
    }
}

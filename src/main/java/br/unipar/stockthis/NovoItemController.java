package br.unipar.stockthis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoItemController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtPreco;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Label lblMensagem;

    private Produto produtoCriado;

    public Produto getProdutoCriado() {
        return produtoCriado;
    }

    @FXML
    private void adicionar() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nome = txtNome.getText();
            String categoria = txtCategoria.getText();
            double preco = Double.parseDouble(txtPreco.getText().replace(",", "."));

            if (nome.isEmpty() || categoria.isEmpty()) {
                lblMensagem.setText("Preencha todos os campos.");
                return;
            }

            produtoCriado = new Produto(codigo, nome, categoria, 0, preco);
            fechar();

        } catch (NumberFormatException e) {
            lblMensagem.setText("Código/Preço inválidos.");
        }
    }

    private void fechar() {
        Stage stage = (Stage) btnAdicionar.getScene().getWindow();
        stage.close();
    }
}
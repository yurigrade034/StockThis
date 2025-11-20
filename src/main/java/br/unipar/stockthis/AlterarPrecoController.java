package br.unipar.stockthis;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlterarPrecoController {

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtPrecoAntigo;

    @FXML
    private TextField txtPrecoNovo;

    @FXML
    private Label lblMensagem;

    private Produto produto;

    public void setProduto(Produto produto) {
        this.produto = produto;
        lblTitulo.setText(produto.getNome());
        txtPrecoAntigo.setText(String.format("%.2f", produto.getPreco()));
    }

    @FXML
    private void confirmar() {
        try {
            double novo = Double.parseDouble(txtPrecoNovo.getText().replace(",", "."));
            if (novo <= 0) {
                lblMensagem.setText("Preço deve ser maior que zero.");
                return;
            }
            produto.setPreco(novo);
            fechar();
        } catch (NumberFormatException e) {
            lblMensagem.setText("Valor inválido.");
        }
    }

    @FXML
    private void cancelar() {
        fechar();
    }

    private void fechar() {
        Stage stage = (Stage) txtPrecoNovo.getScene().getWindow();
        stage.close();
    }
}
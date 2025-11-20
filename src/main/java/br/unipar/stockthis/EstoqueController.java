package br.unipar.stockthis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Collectors;

public class EstoqueController {

    @FXML
    private TextField txtBusca;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private TableView<Produto> tblProdutos;

    @FXML
    private TableColumn<Produto, Integer> colCodigo;

    @FXML
    private TableColumn<Produto, String> colNome;

    @FXML
    private TableColumn<Produto, String> colCategoria;

    @FXML
    private TableColumn<Produto, Integer> colQuantidade;

    @FXML
    private TableColumn<Produto, Double> colPreco;

    @FXML
    private TableColumn<Produto, Void> colAcao;

    private final ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();
    private final ObservableList<Produto> listaFiltrada = FXCollections.observableArrayList();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private Usuario usuarioLogado;

    @FXML
    public void initialize() {
        usuarioLogado = HelloApplication.getUsuarioLogado();

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        if (usuarioLogado != null) {
            listaProdutos.setAll(produtoDAO.listarPorUsuario(usuarioLogado.getId()));
        }

        atualizarCategorias();
        aplicarFiltro();

        txtBusca.textProperty().addListener((obs, o, n) -> aplicarFiltro());
        cbCategoria.valueProperty().addListener((obs, o, n) -> aplicarFiltro());

        configurarColunaAcoes();
    }

    private void atualizarCategorias() {
        var categorias = listaProdutos.stream()
                .map(Produto::getCategoria)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        cbCategoria.getItems().setAll(categorias);
    }

    private void aplicarFiltro() {
        String texto = txtBusca.getText() != null ? txtBusca.getText().toLowerCase() : "";
        String categoria = cbCategoria.getValue();

        listaFiltrada.setAll(
                listaProdutos.stream()
                        .filter(p -> p.getNome().toLowerCase().contains(texto)
                                || String.valueOf(p.getCodigo()).contains(texto))
                        .filter(p -> categoria == null || categoria.isEmpty()
                                || p.getCategoria().equalsIgnoreCase(categoria))
                        .sorted(Comparator.comparingInt(Produto::getCodigo))
                        .collect(Collectors.toList())
        );

        tblProdutos.setItems(listaFiltrada);
    }

    private void configurarColunaAcoes() {
        colAcao.setCellFactory(col -> new TableCell<>() {
            private final Button btnMais = new Button("+");
            private final Button btnMenos = new Button("-");
            private final Button btnPreco = new Button("$");
            private final Button btnExcluir = new Button("X");
            private final HBox box = new HBox(4, btnPreco, btnMais, btnMenos, btnExcluir);

            {
                btnMais.setStyle("-fx-background-color: #3bb54a; -fx-text-fill: white;");
                btnMenos.setStyle("-fx-background-color: #e45b3c; -fx-text-fill: white;");
                btnPreco.setStyle("-fx-background-color: #ff9b27; -fx-text-fill: white;");
                btnExcluir.setStyle("-fx-background-color: #d11a2a; -fx-text-fill: white;");

                btnMais.setOnAction(e -> alterarQuantidade(+1));
                btnMenos.setOnAction(e -> alterarQuantidade(-1));
                btnPreco.setOnAction(e -> abrirAlterarPreco());
                btnExcluir.setOnAction(e -> excluirProduto());
            }

            private void alterarQuantidade(int delta) {
                Produto p = getTableView().getItems().get(getIndex());
                int novaQtd = Math.max(0, p.getQuantidade() + delta);
                p.setQuantidade(novaQtd);
                tblProdutos.refresh();

                if (usuarioLogado != null) {
                    produtoDAO.atualizarQuantidade(usuarioLogado.getId(), p.getCodigo(), novaQtd);
                }
            }

            private void abrirAlterarPreco() {
                Produto p = getTableView().getItems().get(getIndex());
                EstoqueController.this.abrirAlterarPreco(p);
            }

            private void excluirProduto() {
                Produto p = getTableView().getItems().get(getIndex());
                listaProdutos.remove(p);
                aplicarFiltro();

                // remove do banco
                if (usuarioLogado != null) {
                    produtoDAO.excluir(usuarioLogado.getId(), p.getCodigo());
                }
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(box);
                }
            }
        });
    }

    @FXML
    private void abrirNovoItem() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("NovoItem.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Novo Item");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            NovoItemController controller = loader.getController();
            stage.showAndWait();

            Produto novo = controller.getProdutoCriado();
            if (novo != null) {
                listaProdutos.add(novo);
                atualizarCategorias();
                aplicarFiltro();

                if (usuarioLogado != null) {
                    produtoDAO.inserir(usuarioLogado.getId(), novo);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void abrirAlterarPreco(Produto produto) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("AlterarPreco.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Alterar preço");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            AlterarPrecoController controller = loader.getController();
            controller.setProduto(produto);

            stage.showAndWait();
            tblProdutos.refresh();

            if (usuarioLogado != null) {
                produtoDAO.atualizarPreco(usuarioLogado.getId(), produto.getCodigo(), produto.getPreco());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irParaDashboard() {
        HelloApplication.mudarCena("Dashboard");
    }

}

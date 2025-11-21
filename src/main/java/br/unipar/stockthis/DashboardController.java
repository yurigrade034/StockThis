package br.unipar.stockthis;

import br.unipar.stockthis.BANCO_DADOS.ProdutoDAO;
import br.unipar.stockthis.CADASTRO.Usuario;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardController {

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblQtdProdutos;

    @FXML
    private Label lblValorTotal;

    @FXML
    private AreaChart<String, Number> chartEstoque;

    @FXML
    private PieChart chartFinanceiro;

    private ProdutoDAO produtoDAO;
    private Usuario usuarioLogado;

    @FXML
    public void initialize() {
        usuarioLogado = HelloApplication.getUsuarioLogado();
        produtoDAO = new ProdutoDAO();

        if (usuarioLogado != null && lblUsuario != null) {
            lblUsuario.setText("Olá, " + usuarioLogado.getNome());
        }

        carregarResumo();
        atualizarGraficos();
    }

    private List<Produto> carregarProdutos() {
        if (usuarioLogado == null) {
            return List.of();
        }
        return produtoDAO.listarPorUsuario(usuarioLogado.getId());
    }

    private void carregarResumo() {
        List<Produto> produtos = carregarProdutos();

        int totalQtd = produtos.stream()
                .mapToInt(Produto::getQuantidade)
                .sum();

        double valorTotal = produtos.stream()
                .mapToDouble(p -> p.getQuantidade() * p.getPreco())
                .sum();

        lblQtdProdutos.setText(String.valueOf(totalQtd));
        lblValorTotal.setText(String.format("R$ %.2f", valorTotal));
    }

    private void atualizarGraficos() {
        List<Produto> produtos = carregarProdutos();

        chartEstoque.getData().clear();

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Quantidade em estoque");

        for (Produto p : produtos) {
            serie.getData().add(new XYChart.Data<>(p.getNome(), p.getQuantidade()));
        }

        chartEstoque.getData().add(serie);
        chartEstoque.setLegendVisible(false);

        chartFinanceiro.getData().clear();

        Map<String, Double> mapaCategoria = new HashMap<>();
        for (Produto p : produtos) {
            double valor = p.getQuantidade() * p.getPreco();
            mapaCategoria.merge(p.getCategoria(), valor, Double::sum);
        }

        for (Map.Entry<String, Double> entry : mapaCategoria.entrySet()) {
            chartFinanceiro.getData().add(
                    new PieChart.Data(entry.getKey(), entry.getValue())

            );
            chartFinanceiro.setLegendVisible(false);

        }
    }

    @FXML
    private void irParaEstoque() {
        HelloApplication.mudarCena("Estoque");
    }

}

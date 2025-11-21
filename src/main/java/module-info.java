module br.unipar.stockthis {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.unipar.stockthis to javafx.fxml;
    exports br.unipar.stockthis;
    exports br.unipar.stockthis.BANCO_DADOS;
    opens br.unipar.stockthis.BANCO_DADOS to javafx.fxml;
    exports br.unipar.stockthis.CADASTRO;
    opens br.unipar.stockthis.CADASTRO to javafx.fxml;
    exports br.unipar.stockthis.ESTOQUE;
    opens br.unipar.stockthis.ESTOQUE to javafx.fxml;
}
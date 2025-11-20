module br.unipar.stockthis {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.unipar.stockthis to javafx.fxml;
    exports br.unipar.stockthis;
}
package br.unipar.stockthis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;
    private static Usuario usuarioLogado;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        mudarCena("Login");
    }

    public static void setUsuarioLogado(Usuario u) {
        usuarioLogado = u;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void mudarCena(String nomeFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource(nomeFxml + ".fxml")
            );
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("StockThis - " + nomeFxml);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

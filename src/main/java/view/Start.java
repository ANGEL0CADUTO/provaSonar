package view;

import bean.UtenteBean;
import dao.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));


        UtenteBean utente = new UtenteBean(); //


        loader.setControllerFactory(c-> new Demo(utente));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("MangaInk");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}

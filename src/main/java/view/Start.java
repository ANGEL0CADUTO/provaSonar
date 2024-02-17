package view;

import bean.UtenteBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));

        UtenteBean utente = new UtenteBean();
        loader.setControllerFactory(c-> new Demo(utente));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("MangaInk");
        primaryStage.setScene(scene);
        primaryStage.show();

        FXMLLoader loader2= new FXMLLoader(getClass().getResource("HomePage.fxml"));
        UtenteBean utente2 = new UtenteBean();
        loader2.setControllerFactory(c-> new Demo(utente2));
        Stage primaryStage2 = new Stage();
        Parent root2 = loader2.load();
        Scene scene2 = new Scene(root2);

        primaryStage2.setResizable(false);
        primaryStage2.setTitle("MangaInk");
        primaryStage2.setScene(scene2);
        primaryStage2.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

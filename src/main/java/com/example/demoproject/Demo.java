package com.example.demoproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;


public class Demo extends Application {

    @FXML
    private Button loginHomePage;

    @FXML
    private Button registratiHomePage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demoproject/HomePage.fxml"));
        //Group root = new Group(); //Group rappresenta un gruppo di nodi grafici
        Scene scene = new Scene(root, Color.BLUE);//CREO LA SCENA

        /* Circle circle1 = new Circle(350,350,50);//creo il primo cerchio
        circle1.setFill(Color.AZURE);//riempio il cerchio

        Circle circle2 = new Circle(350,100,50);//secondo cerchio
        circle2.setFill(Color.GRAY);

        root.getChildren().addAll(circle1,circle2);//renderizzo i cerchi inserendoli nel group node

        Image icon = new Image("MangaInk Logo.png"); //linko l'immagine all'oggetto Image

        ImageView imageView = new ImageView("MangaInk Logo.png");

        imageView.setFitHeight(150);
        imageView.setFitWidth(200);

        root.getChildren().add(imageView);

        primaryStage.getIcons().add(icon); //AGGIUNGO LA ICON COME LOGO IN ALTO A SINISTRA

         */


        primaryStage.setResizable(false);
        primaryStage.setTitle("MangaInk"); //SETTO IL TITOLO APP
        primaryStage.setScene(scene);//SETTO LA SCENA NELLO STAGE
        primaryStage.show();//MOSTRO LO STAGE


    }


    public void changeScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginHomePage.getScene().getWindow();
        stage.setScene(scene);

    }

    public static void main(String[] args) {
        launch(args);

    }
}
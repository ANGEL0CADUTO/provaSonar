package com.example.demoproject.view;

import com.example.demoproject.bean.UtenteBean;
import com.example.demoproject.view.LoginGrafico;
import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.paint.Color;

import javafx.stage.Stage;

import java.io.IOException;


public class Demo extends Application {

    @FXML
    private Button loginHomePage;

    @FXML
    private Button registratiHomePage;

    @FXML
    private Button profiloHomePage;

    @FXML
    private Button logoutHomePage;

    private UtenteBean utente;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demoproject/HomePage.fxml"));
        //Group root = new Group(); //Group rappresenta un gruppo di nodi grafici
        Scene scene = new Scene(root, Color.BLUE);//CREO LA SCENA



        primaryStage.setResizable(false);
        primaryStage.setTitle("MangaInk"); //SETTO IL TITOLO APP
        primaryStage.setScene(scene);//SETTO LA SCENA NELLO STAGE
        primaryStage.show();//MOSTRO LO STAGE

        //Creo il bean che si passeranno tutti i grafici per mantenere la sessione


    }

    public void initialize(){
        if(utente == null){
            utente = new UtenteBean();
        }

    }

    public void setUtenteBean(UtenteBean bean){
        this.utente= bean;
    }

    public void changeScene() throws IOException {

        if(utente.isLogged()){
            System.out.println("sei già loggato");
        }
        else{
            utente = new UtenteBean();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/Login.fxml"));
            Parent root = loader.load();
            LoginGrafico controller = loader.getController();
            controller.setUtenteBean(utente);


            Scene scene = new Scene(root);
            Stage stage = (Stage) loginHomePage.getScene().getWindow();
            stage.setScene(scene);

        }



        //StupidTesting
        //CopiaMangaDAO cm = new CopiaMangaDAO();
        //cm.getCopieMangaListByUserID();

    }

    public void registrazione() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demoproject/Registra.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) registratiHomePage.getScene().getWindow();
        stage.setScene(scene);
    }




    public void gotoLibreria() throws IOException{

        Parent root  = FXMLLoader.load(getClass().getResource(("/com/example/demoproject/LibreriaUtente.fxml")));
    }

    public void goToProfiloUtente() throws IOException{

       /*carico l'interfaccia utente
       // FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/ProfiloUtente.fxml"));

     //   loader.setControllerFactory(c-> new ProfiloUtenteGrafico(utente));

        // Parent root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demoproject/ProfiloUtente.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) registratiHomePage.getScene().getWindow();

        stage.setScene(scene);*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/ProfiloUtente.fxml"));

        loader.setControllerFactory(c-> new ProfiloUtenteGrafico(utente));

        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) profiloHomePage.getScene().getWindow();

        stage.setScene(scene);



    }


    //da testare se funziona anche post login
    public void logout(){
        utente.setLogged(false);
    }



    public static void main(String[] args) {
        launch(args);
    }


}



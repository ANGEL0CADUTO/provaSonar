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


public class Demo extends BaseController {

    @FXML
    private Button loginHomePage;

    @FXML
    private Button registratiHomePage;

    @FXML
    private Button profiloHomePage;

    @FXML
    private Button logoutHomePage;





    protected Demo(UtenteBean bean) {
        super(bean);
        System.out.println("Controller Demo istanziato con UtenteBean: " + bean);

    }


    public void initializeData(){
        if(utenteBean == null){
            System.out.println("porcatroia");
            utenteBean = new UtenteBean();
        }

    }

    public void initialize(){
        initializeData();
        System.out.println(utenteBean);

    }

    public void setUtenteBean(UtenteBean bean){
        this.utenteBean= bean;
    }

    public void changeScene() throws IOException {

        if(utenteBean.isLogged()){
            System.out.println("sei già loggato");
        }
        else{


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/Login.fxml"));
            loader.setControllerFactory(c -> new LoginGrafico(utenteBean));


            Parent root = loader.load();
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

        loader.setControllerFactory(c-> new ProfiloUtenteGrafico(utenteBean));

        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) profiloHomePage.getScene().getWindow();

        stage.setScene(scene);



    }


    //da testare se funziona anche post login
    public void logout(){
        utenteBean.setLogged(false);
    }


}



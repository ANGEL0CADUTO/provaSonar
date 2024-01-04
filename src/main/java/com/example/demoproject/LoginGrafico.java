package com.example.demoproject;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;


public class LoginGrafico {

    @FXML
    private Button login;

    @FXML
    private TextField enteredEmail;

    @FXML
    private PasswordField enteredPassword;

    @FXML
    private Label wrongLogin;

    private static final Logger logger = Logger.getLogger(LoginGrafico.class.getName());

    private UtenteBean utente;

    public void setUtenteBean(UtenteBean bean){
        this.utente = bean;
    }




    public void userLogin(){
        LoginApplicativo lg = new LoginApplicativo();

        utente.setEmail("Angelo@gmail.com");
        utente.setPassword("1234");

//        utente.setEmail(enteredEmail.getText());
//        utente.setPassword(enteredPassword.getText());

        boolean esitoLogin = lg.login(utente);

        if(esitoLogin){
            //UtenteBean utenteLoggato = new UtenteBean(EnteredEmail); VOGLIO PASSARE STO CAZZO DI BEAN PER MANTENERE LA SESSIONE
            //DOVRO IMPLEMENTARE UNA FUNZIONA CHE DALLA EMAIL MI PESCA QUELLO DI CUI HO BISOGNO

            wrongLogin.setText("Hai effettuato l'accesso!");
            utente.setLogged(true);
            System.out.println(utente.getIdUtente() + " " + utente.getUsername()+ " " + utente.getPassword()+ " " + utente.getEmail()+ " " + utente.getCredito());

            //cambio view ((LO FACCIO COL TRY CATCH MA DEVO CAPIRE SE E BENE O MALE))
            try{


                FXMLLoader loader = new FXMLLoader(getClass().getResource("LibreriaUtente.fxml"));


                Parent root = loader.load();
                //Istanzio il rafico e chiamo la funzione per settare il bean(nella quale viene istanziata anche la tabella)
                LibreriaUtenteControllerGrafico libreriaUtenteController = loader.getController();
                libreriaUtenteController.setUtenteBean(utente);
                /*loader.setControllerFactory(c -> { LibreriaUtenteControllerGrafico controller = new LibreriaUtenteControllerGrafico(utente);
                controller.initializeData();
                return controller;
                });*/





                Scene scene = new Scene(root);
                Stage stage = (Stage) login.getScene().getWindow();
                stage.setScene(scene);


            } catch(IOException e) {
                logger.severe("BROOO QUA STANNO LANCIANDO EXCEPTION ((login grafico cambio view))");
                logger.info("info");
            }

        }
        else{
            wrongLogin.setText("Credenziali sbagliate");
        }

    }





}

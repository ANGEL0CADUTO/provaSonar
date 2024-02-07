package com.example.demoproject.view;


import com.example.demoproject.controllerapplicativo.LoginApplicativo;
import com.example.demoproject.bean.UtenteBean;
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

    public LoginGrafico(UtenteBean utenteBean) {
        this.utente = utenteBean;
    }


    public void setUtenteBean(UtenteBean bean){
        this.utente = bean;
    }




    public void userLogin(){
        LoginApplicativo lg = new LoginApplicativo();

        utente.setEmail("Utente2");
        utente.setPassword("1234");
       /* utente.setEmail("Angelo@gmail.com");
        utente.setPassword("1234");*/

        boolean esitoLogin = lg.login(utente);

        if(esitoLogin){

            wrongLogin.setText("Hai effettuato l'accesso!");
            utente.setLogged(true);
            System.out.println("LOGIN GRAFICO : " + utente.getIdUtente() + " " + utente.getUsername()+ " " + utente.getPassword()+ " " + utente.getEmail()+ " " + utente.getCredito());

            //cambio view ((LO FACCIO COL TRY CATCH MA DEVO CAPIRE SE E BENE O MALE))
            try{


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/LibreriaUtente.fxml"));

                loader.setControllerFactory(c-> new LibreriaUtenteControllerGrafico(utente));

                Parent root = loader.load();

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

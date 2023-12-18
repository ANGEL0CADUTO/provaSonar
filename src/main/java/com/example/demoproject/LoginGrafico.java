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


    public void userLogin(){
        LoginApplicativo lg = new LoginApplicativo();
        UtenteBean bean = new UtenteBean();
        bean.setEmail(enteredEmail.getText());
        bean.setPassword(enteredPassword.getText());

        boolean esitoLogin = lg.login(bean);


        //DEVO FAR ARRIVARE ANCHE L'USERNAME E LA PASSWORD
        // (ecco perchè andava creato a prescindere e popolato dai tizi che la ricevono)





        if(esitoLogin){
            //UtenteBean utenteLoggato = new UtenteBean(EnteredEmail); VOGLIO PASSARE STO CAZZO DI BEAN PER MANTENERE LA SESSIONE
            //DOVRO IMPLEMENTARE UNA FUNZIONA CHE DALLA EMAIL MI PESCA QUELLO DI CUI HO BISOGNO

            wrongLogin.setText("Hai effettuato l'accesso!");
            System.out.println(bean.getIdUtente() + " " + bean.getUsername()+ " " + bean.getPassword()+ " " + bean.getEmail()+ " " + bean.getCredito());


            //cambio view ((LO FACCIO COL TRY CATCH MA DEVO CAPIRE SE E BENE O MALE))
            try{

                Parent root = FXMLLoader.load(getClass().getResource("LibreriaUtente.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) login.getScene().getWindow();
                stage.setScene(scene);

            } catch(IOException e) {
                logger.severe("BROOO QUA STANNO LANCIANDO EXCEPTION ((login grafico cambio view))");
            }

        }
        else{
            wrongLogin.setText("Credenziali sbagliate");
        }

    }





}

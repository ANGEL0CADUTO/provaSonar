package com.example.demoproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginGrafico {

    @FXML
    private Button login;

    @FXML
    private TextField enteredEmail;

    @FXML
    private PasswordField enteredPassword;

    @FXML
    private Label wrongLogin;

    public void userLogin(ActionEvent event) throws IOException {
        LoginApplicativo lg = new LoginApplicativo();
        boolean esitoLogin = lg.login(enteredEmail.getText().toString(),enteredPassword.getText().toString());




        if(esitoLogin){
            //UtenteBean utenteLoggato = new UtenteBean(EnteredEmail); VOGLIO PASSARE STO CAZZO DI BEAN PER MANTENERE LA SESSIONE
            //DOVRO IMPLEMENTARE UNA FUNZIONA CHE DALLA EMAIL MI PESCA QUELLO DI CUI HO BISOGNO
            wrongLogin.setText("Hai effettuato l'accesso!");
        }
        else{
            wrongLogin.setText("Credenziali sbagliate");
        }
        /*
        if(enteredEmail.getText().toString().equals("Angelo") && enteredPassword.getText().toString().equals("1234")){
            wrongLogin.setText("Hai effettuato l'accesso!");
        }
        else if(enteredEmail.getText().toString().isEmpty() && enteredPassword.getText().toString().isEmpty()){
            wrongLogin.setText("Bro ma che cazzo fai");
        }
        else{
            wrongLogin.setText("Credenziali errate!");
        }*/
    }





}

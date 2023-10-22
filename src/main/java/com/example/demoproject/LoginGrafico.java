package com.example.demoproject;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class LoginGrafico {

    @FXML
    private Button login;

    @FXML
    private TextField enteredEmail;

    @FXML
    private PasswordField enteredPassword;

    @FXML
    private Label wrongLogin;

    public void userLogin(){
        LoginApplicativo lg = new LoginApplicativo();
        boolean esitoLogin = lg.login(enteredEmail.getText(),enteredPassword.getText());




        if(esitoLogin){
            //UtenteBean utenteLoggato = new UtenteBean(EnteredEmail); VOGLIO PASSARE STO CAZZO DI BEAN PER MANTENERE LA SESSIONE
            //DOVRO IMPLEMENTARE UNA FUNZIONA CHE DALLA EMAIL MI PESCA QUELLO DI CUI HO BISOGNO
            wrongLogin.setText("Hai effettuato l'accesso!");
        }
        else{
            wrongLogin.setText("Credenziali sbagliate");
        }
    }





}

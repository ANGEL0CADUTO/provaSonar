package com.example.demoproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistraGrafico {

    @FXML
    private Button registra;

    @FXML
    private TextField enteredEmail;

    @FXML
    private TextField enteredUsername;

    @FXML
    private TextField enteredIndirizzo;

    @FXML
    private TextField enteredCivico;

    @FXML
    private TextField enteredCap;

    @FXML
    private PasswordField enteredPassword;

    @FXML
    private Label wrongSignUp;

    public void userRegistra(){
        UtenteBean bean = new UtenteBean();
        DatiUtenteBean bean1 = new DatiUtenteBean();
        RegistraApplicativo ra = new RegistraApplicativo();

        bean.setEmail(enteredEmail.getText());
        bean.setPassword(enteredPassword.getText());
        bean.setUsername(enteredUsername.getText());
        ra.registra(bean);


        bean1.setIndirizzo(enteredIndirizzo.getText());
        bean1.setCap(Integer.parseInt(enteredCivico.getText()));
        bean1.setCivico(Integer.parseInt(enteredCap.getText()));
        ra.registraDati(bean1);
    }


}

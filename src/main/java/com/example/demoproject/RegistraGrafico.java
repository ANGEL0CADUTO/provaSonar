package com.example.demoproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private PasswordField enteredPassword;

    public void userRegistra(){
        UtenteBean bean = new UtenteBean();
        bean.setEmail(enteredEmail.getText());
        bean.setPassword(enteredPassword.getText());
        bean.setUsername(enteredUsername.getText());



        RegistraApplicativo ra = new RegistraApplicativo();

        ra.registra(bean);





    }


}

package com.example.demoproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ProfiloUtenteGrafico extends BaseController {

    @FXML
    private Label nomeUtente;

    protected ProfiloUtenteGrafico(UtenteBean utente){
        super(utente);
    }


    @FXML
    private void initialize(){
        System.out.println(utenteBean.getIdUtente() + " " + utenteBean.getEmail());
        if(utenteBean.isLogged()) {
            nomeUtente.setText("Benvenuto " + utenteBean.getUsername() + " questo è il tuo profilo");
        }
    }

}

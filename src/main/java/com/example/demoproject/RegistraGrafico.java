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

        bean1.setIndirizzo(enteredIndirizzo.getText());
        bean1.setCap(Integer.parseInt(enteredCivico.getText()));
        bean1.setCivico(Integer.parseInt(enteredCap.getText()));


//       bean.setEmail("leom@gmail.com");
//       bean.setPassword("123");
//        bean.setUsername("lello");
//        bean1.setIndirizzo("via Roma");
//        bean1.setCap(00137);
//        bean1.setCivico(4);





    //PROBEMA: SE INSERISCO I DATI E NON I DATIUTENTE UNO LO SALVA LO STESSO DA AGGIUSTARE
        boolean esitoRegistra = ra.registra(bean);
        boolean esitoRegistraDati= ra.registraDati(bean1);
        ra.informazioniUtente(bean);
        //CONTROLLO SU EMAIl-PASSWORS-USERNAME-INDIRIZZO-CAP-CIVICO HA INSERITO CAMPI NON VOUTI FUNGE
        if(esitoRegistra && esitoRegistraDati){
            wrongSignUp.setText("Hai effettuato la registrazione!");}
        else{wrongSignUp.setText("Devi inserire tutti i campi");}
    }


}

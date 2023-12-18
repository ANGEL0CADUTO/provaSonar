package com.example.demoproject;

public class RegistraApplicativo {

    public boolean registra(UtenteBean bean){
        UtenteDAO registraUtente = new UtenteDAO();
        return registraUtente.addUser(bean);


    }
}

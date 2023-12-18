package com.example.demoproject;

public class LoginApplicativo {



    public boolean login(UtenteBean bean){
        UtenteDAO ricercaUtente = new UtenteDAO();

        return ricercaUtente.searchUser(bean);


    }


}

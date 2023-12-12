package com.example.demoproject;

public class LoginApplicativo {



    public boolean login(String email, String password, UtenteBean bean){
        UtenteDAO ricercaUtente = new UtenteDAO();

        return ricercaUtente.searchUser(email, password, bean);


    }


}

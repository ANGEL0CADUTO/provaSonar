package com.example.demoproject;

public class LoginApplicativo {



    public boolean login(String email,String password){
        UtenteDAO ricercaUtente = new UtenteDAO();


        // UtenteBean utenteLoggato = new UtenteBean();
        //utenteLoggato.setEmail(email);
        return ricercaUtente.searchUser(email, password);


    }


}

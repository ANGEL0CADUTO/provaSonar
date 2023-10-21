package com.example.demoproject;

public class LoginApplicativo {



    public boolean login(String email,String password){
        UtenteDAO ricercaUtente = new UtenteDAO();


        if (ricercaUtente.searchUser(email, password)) {
           // UtenteBean utenteLoggato = new UtenteBean();
            //utenteLoggato.setEmail(email);

            return true;
        } else {
            return false;
        }


    }


}

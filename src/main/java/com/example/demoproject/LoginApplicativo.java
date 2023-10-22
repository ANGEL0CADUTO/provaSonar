package com.example.demoproject;

public class LoginApplicativo {



    public boolean login(String email,String password){
        UtenteDAO ricercaUtente = new UtenteDAO();



        return ricercaUtente.searchUser(email, password);


    }


}

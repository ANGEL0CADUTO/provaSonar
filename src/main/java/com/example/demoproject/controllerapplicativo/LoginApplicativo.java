package com.example.demoproject.controllerapplicativo;

import com.example.demoproject.bean.UtenteBean;
import com.example.demoproject.dao.UtenteDAO;

public class LoginApplicativo {



    public boolean login(UtenteBean bean){
        UtenteDAO ricercaUtente = new UtenteDAO();

        return ricercaUtente.searchUser(bean);


    }


}

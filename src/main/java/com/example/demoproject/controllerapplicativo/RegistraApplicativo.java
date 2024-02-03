package com.example.demoproject.controllerapplicativo;

import com.example.demoproject.bean.utentebean.DatiUtenteBean;
import com.example.demoproject.dao.DatiUtenteDao;
import com.example.demoproject.bean.utentebean.UtenteBean;
import com.example.demoproject.dao.UtenteDAO;

public class RegistraApplicativo {

    public boolean registra(UtenteBean bean){
        UtenteDAO registraUtente = new UtenteDAO();
         return registraUtente.addUser(bean);


    }

    public boolean informazioniUtente(UtenteBean bean){
        UtenteDAO registraUtente = new UtenteDAO();

        return registraUtente.informazioniUtente(bean);
    }

    public boolean registraDati(DatiUtenteBean bean1){
        DatiUtenteDao registraDatiUtente = new DatiUtenteDao();
         return registraDatiUtente.addDatiUser(bean1);

    }
}

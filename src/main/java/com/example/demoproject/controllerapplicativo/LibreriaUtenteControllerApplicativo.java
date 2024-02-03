package com.example.demoproject.controllerapplicativo;

import com.example.demoproject.model.CopiaMangaCollectionModel;
import com.example.demoproject.dao.CopiaMangaDAO;
import com.example.demoproject.bean.UtenteBean;
import com.example.demoproject.model.UtenteModel;

public class LibreriaUtenteControllerApplicativo {



    public CopiaMangaCollectionModel showUserManga(UtenteBean bean){

        CopiaMangaDAO dao = new CopiaMangaDAO();

        UtenteModel utente = new UtenteModel();

        utente.setIdUtente(bean.getIdUtente());
        utente.setEmail(bean.getEmail());
        utente.setPassword(bean.getPassword());
        utente.setUsername(bean.getPassword());
        utente.setVotoRecensioni(bean.getVotoRecensione());
        utente.setCredito(bean.getCredito());

        return dao.getCopieMangaListByUserID(utente);

    }
}

package com.example.demoproject;

import java.util.Date;

public class AnnuncioControllerApplicativo {
    public boolean inserisciAnnuncio(UtenteBean utenteBean,int prezzo, String dataFormattata) {
        AnnuncioDAO creaAnnuncio = new AnnuncioDAO();

       /* CopiaMangaDAO dao = new CopiaMangaDAO();
        UtenteModel utente = new UtenteModel();

        utente.setIdUtente(utenteBean.getIdUtente());
        dao.getCopieMangaListByUserID(utente);*/

        return creaAnnuncio.addAnnuncio(utenteBean,prezzo,dataFormattata);
    }
}

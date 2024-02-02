package com.example.demoproject;

import java.util.Date;

public class AnnuncioControllerApplicativo {
    public boolean inserisciAnnuncio(CopiaMangaBean copiaMangaBean,int prezzo, String dataFormattata) {
        AnnuncioDAO creaAnnuncio = new AnnuncioDAO();

       /* CopiaMangaDAO dao = new CopiaMangaDAO();
        UtenteModel utente = new UtenteModel();

        utente.setIdUtente(utenteBean.getIdUtente());
        dao.getCopieMangaListByUserID(utente);*/
      //POPOLA IL MODEL DAL BEAN CopiaMANGA E LO PASSO AL DAO
        CopiaMangaModel copiaMangaModel = new CopiaMangaModel();
        copiaMangaModel.setIdCopiaManga(copiaMangaBean.getIdManga());


        return creaAnnuncio.addAnnuncio(copiaMangaModel,prezzo,dataFormattata);
    }
}

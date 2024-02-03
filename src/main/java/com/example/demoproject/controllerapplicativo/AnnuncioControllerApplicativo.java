package com.example.demoproject.controllerapplicativo;

import com.example.demoproject.dao.AnnuncioDAO;
import com.example.demoproject.bean.mangabean.CopiaMangaBean;
import com.example.demoproject.model.CopiaMangaModel;

public class AnnuncioControllerApplicativo {
    public boolean inserisciAnnuncio(CopiaMangaBean copiaMangaBean, int prezzo, String dataFormattata) {
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

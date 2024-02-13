package controllerapplicativo;

import bean.UtenteBean;
import dao.AnnuncioDAO;
import bean.CopiaMangaBean;
import model.CopiaMangaModel;
import model.UtenteModel;

import java.math.BigDecimal;

public class AnnuncioControllerApplicativo {
    public boolean inserisciAnnuncio(UtenteBean utenteBean, CopiaMangaBean copiaMangaBean, BigDecimal prezzo, String dataFormattata) {
        AnnuncioDAO creaAnnuncio = new AnnuncioDAO();


      //POPOLA IL MODEL DAL BEAN CopiaMANGA E LO PASSO AL DAO
        CopiaMangaModel copiaMangaModel = new CopiaMangaModel();
        copiaMangaModel.setIdCopiaManga(copiaMangaBean.getIdManga());
        UtenteModel utenteModel = new UtenteModel();
        utenteModel.setIdUtente(utenteBean.getIdUtente());


        return creaAnnuncio.addAnnuncio( utenteModel,copiaMangaModel,prezzo,dataFormattata);
    }

    public boolean cercaAnnuncio(CopiaMangaBean copiaMangaBean){
        AnnuncioDAO creaAnnuncio = new AnnuncioDAO();

        CopiaMangaModel copiaMangaModel1 = new CopiaMangaModel();
        copiaMangaModel1.setIdCopiaManga(copiaMangaBean.getIdManga());


        return creaAnnuncio.isAnnuncioPresente(copiaMangaModel1);
    }



}



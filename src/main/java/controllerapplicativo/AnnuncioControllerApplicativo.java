package controllerapplicativo;

import dao.AnnuncioDAO;
import bean.CopiaMangaBean;
import model.CopiaMangaModel;

import java.math.BigDecimal;

public class AnnuncioControllerApplicativo {
    public boolean inserisciAnnuncio(CopiaMangaBean copiaMangaBean, BigDecimal prezzo, String dataFormattata) {
        AnnuncioDAO creaAnnuncio = new AnnuncioDAO();


      //POPOLA IL MODEL DAL BEAN CopiaMANGA E LO PASSO AL DAO
        CopiaMangaModel copiaMangaModel = new CopiaMangaModel();
        copiaMangaModel.setIdCopiaManga(copiaMangaBean.getIdManga());


        return creaAnnuncio.addAnnuncio(copiaMangaModel,prezzo,dataFormattata);
    }

    public boolean cercaAnnuncio(CopiaMangaBean copiaMangaBean){
        AnnuncioDAO creaAnnuncio = new AnnuncioDAO();

        CopiaMangaModel copiaMangaModel1 = new CopiaMangaModel();
        copiaMangaModel1.setIdCopiaManga(copiaMangaBean.getIdManga());


        return creaAnnuncio.isAnnuncioPresente(copiaMangaModel1);
    }



}



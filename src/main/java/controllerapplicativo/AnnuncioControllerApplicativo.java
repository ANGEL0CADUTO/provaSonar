package controllerapplicativo;

import dao.AnnuncioDAO;
import bean.CopiaMangaBean;
import model.CopiaMangaModel;

import java.math.BigDecimal;

public class AnnuncioControllerApplicativo {
    public boolean inserisciAnnuncio(CopiaMangaBean copiaMangaBean, BigDecimal prezzo, String dataFormattata,String username) {
        AnnuncioDAO creaAnnuncio = new AnnuncioDAO();


      //POPOLA IL MODEL DAL BEAN CopiaMANGA E LO PASSO AL DAO
        CopiaMangaModel copiaMangaModel = new CopiaMangaModel();
        copiaMangaModel.setIdCopiaManga(copiaMangaBean.getIdCopiaManga());
        copiaMangaModel.setTitolo(copiaMangaBean.getTitolo());
        System.out.println("VEDIAMO QUI L ID QUANTO VALE(ANNUNCIOCONTROLLERAPPLICATIVO) : " + copiaMangaModel.getIdCopiaManga());
        copiaMangaModel.setVolume(copiaMangaBean.getVolume());
        copiaMangaModel.setIdUtente(copiaMangaBean.getIdUtente());

        return creaAnnuncio.addAnnuncio(copiaMangaModel,prezzo,dataFormattata,username);
    }

    public boolean cercaAnnuncio(CopiaMangaBean copiaMangaBean){
        AnnuncioDAO creaAnnuncio = new AnnuncioDAO();

        CopiaMangaModel copiaMangaModel1 = new CopiaMangaModel();
        copiaMangaModel1.setIdCopiaManga(copiaMangaBean.getIdCopiaManga());


        return creaAnnuncio.isAnnuncioPresente(copiaMangaModel1);
    }



}



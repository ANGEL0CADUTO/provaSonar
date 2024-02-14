package controllerapplicativo;

import dao.AnnuncioDAO;
import bean.CopiaMangaBean;
import dao.CopiaMangaDAO;
import model.CopiaMangaModel;

import java.math.BigDecimal;

public class AnnuncioControllerApplicativo {
    public boolean inserisciAnnuncio(CopiaMangaBean copiaMangaBean, BigDecimal prezzo, String dataFormattata,String username) {
        AnnuncioDAO dao= new AnnuncioDAO();


      //POPOLA IL MODEL DAL BEAN CopiaMANGA E LO PASSO AL DAO
        CopiaMangaModel copiaMangaModel = new CopiaMangaModel();
        copiaMangaModel.setIdCopiaManga(copiaMangaBean.getIdCopiaManga());
        copiaMangaModel.setTitolo(copiaMangaBean.getTitolo());
        copiaMangaModel.setVolume(copiaMangaBean.getVolume());
        copiaMangaModel.setIdUtente(copiaMangaBean.getIdUtente());

        if(dao.addAnnuncio(copiaMangaModel,prezzo,dataFormattata,username)){
            CopiaMangaDAO dao2 = new CopiaMangaDAO();
            return dao2.setStatoInVenditaByCopiaMangaID(copiaMangaModel.getIdCopiaManga());
        }
        return false;
    }

    public boolean cercaAnnuncio(CopiaMangaBean copiaMangaBean){
        AnnuncioDAO creaAnnuncio = new AnnuncioDAO();

        CopiaMangaModel copiaMangaModel1 = new CopiaMangaModel();
        copiaMangaModel1.setIdCopiaManga(copiaMangaBean.getIdCopiaManga());


        return creaAnnuncio.isAnnuncioPresente(copiaMangaModel1);
    }



}



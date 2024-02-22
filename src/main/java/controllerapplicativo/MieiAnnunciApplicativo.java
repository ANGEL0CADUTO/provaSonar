package controllerapplicativo;

import dao.AnnuncioDAO;

import dao.OffertaDAO;
import model.AnnuncioModel;



import java.util.List;

public class MieiAnnunciApplicativo {

    public List<AnnuncioModel> getMyAnnunci(int id){
        AnnuncioDAO dao = new AnnuncioDAO();
        List<AnnuncioModel> list = dao.getMyAnnunci(id);
        OffertaDAO dao2 = new OffertaDAO();
        for(AnnuncioModel a : list){
            a.setNumeroOfferte(dao2.getNumeroOfferteRicevuteByAnnuncioID(a.getIdAnnuncio()));
        }

        return list;

    }

}

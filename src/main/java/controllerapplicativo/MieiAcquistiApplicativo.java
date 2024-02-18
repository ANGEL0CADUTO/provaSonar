package controllerapplicativo;

import dao.AnnuncioDAO;
import dao.OffertaDAO;
import model.AnnuncioModel;
import model.OffertaModel;

import java.util.ArrayList;
import java.util.List;

public class MieiAcquistiApplicativo {

    public List<OffertaModel> getMyOfferteAccettate(int id){

        List<OffertaModel> array;
        OffertaDAO dao = new OffertaDAO();
        array = dao.getDatiOffertaAccettataByUtenteID(id);

        for(OffertaModel o : array) {

            AnnuncioDAO dao2 = new AnnuncioDAO();

            AnnuncioModel annuncioModel;
            annuncioModel = dao2.getDatiAnnuncioByAnnuncioID(o.getAnnuncioID());
            o.setTitoloManga(annuncioModel.getNomeManga());
            o.setVolumeManga(annuncioModel.getVolume());
            o.setUtenteVenditoreID(annuncioModel.getUtenteVenditoreID());
        }

        return array;

    }
}

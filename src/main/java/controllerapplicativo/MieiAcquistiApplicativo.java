package controllerapplicativo;

import dao.AnnuncioDAO;
import dao.OffertaDAO;
import model.AnnuncioModel;
import model.OffertaModel;

import java.util.ArrayList;

public class MieiAcquistiApplicativo {

    public ArrayList<OffertaModel> getMyOfferteAccettate(int id){

        ArrayList<OffertaModel> array;
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

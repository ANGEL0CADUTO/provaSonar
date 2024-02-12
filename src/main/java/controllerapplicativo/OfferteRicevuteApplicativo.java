package controllerapplicativo;

import dao.OffertaDAO;
import model.OffertaRicevuta;

import java.util.ArrayList;

public class OfferteRicevuteApplicativo {

    public ArrayList<OffertaRicevuta> getOfferteRicevuteByAnnuncioID(int id){
        OffertaDAO dao = new OffertaDAO();
        return dao.getOfferteRicevuteByAnnuncioID(id);

    }
    public boolean accettaOffertaByOffertaID(int idOfferta, int idAnnuncio){
        OffertaDAO dao = new OffertaDAO();
        return dao.accettaOfferta(idOfferta,idAnnuncio);
    }
}

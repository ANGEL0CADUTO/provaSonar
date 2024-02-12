package controllerapplicativo;

import dao.AnnuncioDAO;
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
        if(dao.accettaOfferta(idOfferta,idAnnuncio)){
            AnnuncioDAO dao2 = new AnnuncioDAO();
            return dao2.setStatoAccettatoByAnnuncioID(idAnnuncio);
        }
        return false;
    }
}

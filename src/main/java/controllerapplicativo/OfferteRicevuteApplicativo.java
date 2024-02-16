package controllerapplicativo;

import bean.OffertaBean;
import dao.AnnuncioDAO;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.OffertaModel;
import model.OffertaRicevuta;
import model.UtenteModel;

import java.util.ArrayList;

public class OfferteRicevuteApplicativo {

    public ArrayList<OffertaRicevuta> getOfferteRicevuteByAnnuncioID(int id){
        OffertaDAO dao = new OffertaDAO();
        ArrayList<OffertaRicevuta> array = dao.getOfferteRicevuteByAnnuncioID(id);
        UtenteDAO dao2 = new UtenteDAO();
        for(OffertaRicevuta o : array ){
            o.setVotoRecensioni(dao2.getVotoByUtenteID(o.getUtenteOfferenteID()));
        }
        return array;
    }

    public boolean accettaOffertaByOffertaID(OffertaBean offerta, int idUtenteVenditore){
        OffertaModel offertaModel = new OffertaModel();
        offertaModel.setIdOfferta(offerta.getIdOfferta());
        offertaModel.setAnnuncioID(offerta.getAnnuncioID());
        offertaModel.setUtenteOfferenteID(offerta.getUtenteOfferenteID());
        offertaModel.setOffertaPrezzo(offerta.getOffertaPrezzo());
        OffertaDAO dao = new OffertaDAO();

        if(dao.accettaOfferta(offertaModel)){
            UtenteDAO dao2 = new UtenteDAO();
            UtenteModel utenteModel = new UtenteModel();
            utenteModel.setIdUtente(idUtenteVenditore);

            if(dao2.userDeposit(utenteModel,offerta.getOffertaPrezzo().toString())){
                utenteModel.setIdUtente(offerta.getUtenteOfferenteID());
                if(dao2.userPreliev(utenteModel,offerta.getOffertaPrezzo().toString())){
                    AnnuncioDAO dao3 = new AnnuncioDAO();
                    return dao3.setStatoAccettatoByAnnuncioID(offertaModel.getAnnuncioID());
                }
            }
        }
        return false;
    }
}

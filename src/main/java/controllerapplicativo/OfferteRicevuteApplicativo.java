package controllerapplicativo;

import Pattern.OffertaFacade;
import bean.OffertaBean;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.OffertaModel;
import model.OffertaRicevuta;

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

    public boolean accettaOffertaByOffertaID(OffertaBean bean, int idUtenteVenditore) {

        OffertaFacade facade = new OffertaFacade();
        OffertaModel model =  new OffertaModel();

        model.setAnnuncioID(bean.getAnnuncioID());
        model.setCopiaMangaID(bean.getCopiaMangaID());
        model.setUtenteOfferenteID(bean.getUtenteOfferenteID());
        model.setIdOfferta(bean.getIdOfferta());
        model.setOffertaPrezzo(bean.getOffertaPrezzo());

        return facade.accettaOffertaByOffertaID(model, idUtenteVenditore);
    }
}

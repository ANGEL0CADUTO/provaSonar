package controllerapplicativo;

import Pattern.OffertaFacade;
import bean.OffertaBean;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.OffertaModel;
import model.OffertaRicevuta;

import java.util.List;

public class OfferteRicevuteApplicativo {

    public List<OffertaRicevuta> getOfferteRicevuteByAnnuncioID(int id){
        OffertaDAO dao = new OffertaDAO();
        List<OffertaRicevuta> array = dao.getOfferteRicevuteByAnnuncioID(id);
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

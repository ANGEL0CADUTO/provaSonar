package controllerapplicativo;

import pattern.OffertaFacade;
import bean.OffertaBean;
import dao.AnnuncioDAO;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.AnnuncioModel;
import model.CopiaMangaModel;
import model.OffertaModel;
import model.OffertaRicevuta;

import java.time.LocalDateTime;
import java.util.List;

public class OfferteRicevuteApplicativo {

    public List<OffertaRicevuta> getOfferteRicevuteByAnnuncioID(int id){
        OffertaDAO dao = new OffertaDAO();
        List<OffertaRicevuta> array = dao.getOfferteRicevuteByAnnuncioID(id);
        UtenteDAO dao2 = new UtenteDAO();
        AnnuncioDAO dao3 = new AnnuncioDAO();
        for(OffertaRicevuta o : array ){
            o.setVotoRecensioni(dao2.getVotoByUtenteID(o.getUtenteOfferenteID()));
            AnnuncioModel annuncio = dao3.getDatiAnnuncioByAnnuncioID(o.getAnnuncioID());
            o.setVolumeManga(annuncio.getVolume());
            o.setTitoloManga(annuncio.getNomeManga());

        }
        return array;
    }

    public boolean accettaOffertaByOffertaID(OffertaBean bean, int idUtenteVenditore) {

        OffertaFacade facade = new OffertaFacade();
        OffertaModel model =  new OffertaModel();

        model.setAnnuncioID(bean.getIdAnnuncio());
        model.setCopiaMangaID(bean.getCopiaMangaID());
        model.setTitoloManga(bean.getTitoloManga());
        model.setVolumeManga(bean.getVolumeManga());
        model.setUtenteOfferenteID(bean.getUtenteOfferenteID());
        model.setIdOfferta(bean.getIdOfferta());
        model.setOffertaPrezzo(bean.getOffertaPrezzo());
        model.setDataOfferta(LocalDateTime.now());
        model.setUtenteVenditoreID(idUtenteVenditore);

        CopiaMangaModel copia = new CopiaMangaModel();
        copia.setIdUtente(bean.getUtenteOfferenteID());
        copia.setTitolo(bean.getTitoloManga());
        copia.setVolume(bean.getVolumeManga());
        copia.setDataAcquisto(LocalDateTime.now());
        copia.setIdCopiaManga(bean.getCopiaMangaID());

        return facade.accettaOffertaByOffertaID(model, copia);
    }
}

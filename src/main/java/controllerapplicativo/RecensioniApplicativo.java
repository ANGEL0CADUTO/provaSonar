package controllerapplicativo;

import dao.AnnuncioDAO;
import dao.OffertaDAO;
import dao.RecensioneDAO;
import model.AnnuncioModel;
import model.OffertaModel;
import model.OffertaRicevuta;
import model.Recensione;

import java.util.List;

public class RecensioniApplicativo {

    public List<Recensione> getMyRecensioniRicevute(int id){
        RecensioneDAO dao = new RecensioneDAO();
        List<Recensione> list = dao.getRecensioniRicevuteByUtenteID(id);
        OffertaDAO dao2 = new OffertaDAO();
        AnnuncioDAO dao3 = new AnnuncioDAO();

        for(Recensione r : list){
            OffertaRicevuta o = dao2.getDatiOffertaByOffertaID(r.getOffertaID());
            r.setPrezzoFinale(o.getOffertaPrezzo());
            r.setDataAcquisto(o.getDataVendita());
            AnnuncioModel annuncio = dao3.getDatiAnnuncioByAnnuncioID(o.getAnnuncioID());
            r.setTitoloRecensito(annuncio.getNomeManga());
            r.setPrezzoIniziale(annuncio.getPrezzo());
            r.setVolumeRecensito(annuncio.getVolume());
        }
        return list;
    }
}

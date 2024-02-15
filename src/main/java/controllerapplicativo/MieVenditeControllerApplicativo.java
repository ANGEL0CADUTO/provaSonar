package controllerapplicativo;

import dao.AnnuncioDAO;
import dao.OffertaDAO;
import model.AnnuncioModel;
import model.OffertaModel;
import model.OffertaRicevuta;

import java.util.ArrayList;

public class MieVenditeControllerApplicativo {

    public ArrayList<OffertaModel> getMyVendite(int id) {
        AnnuncioDAO annuncioDAO = new AnnuncioDAO();
        ArrayList<AnnuncioModel> array = annuncioDAO.getMyAnnunciVendutiByUtenteID(id);


        ArrayList<OffertaModel> arrayList = new ArrayList<>();
        OffertaDAO dao = new OffertaDAO();

        for (AnnuncioModel a: array ){
            OffertaModel offertaModel = dao.getDatiOffertaAccettataByAnnuncioID(a.getIdAnnuncio());
            offertaModel.setTitoloManga(a.getNomeManga());
            offertaModel.setVolumeManga(a.getVolume());
            arrayList.add(offertaModel);
                    }



        return arrayList;


    }
}

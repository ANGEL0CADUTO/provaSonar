package controllerapplicativo;

import dao.AnnuncioDAO;
import dao.OffertaDAO;
import model.AnnuncioModel;
import model.OffertaModel;


import java.util.ArrayList;
import java.util.List;

public class MieVenditeControllerApplicativo {

    public List<OffertaModel> getMyVendite(int id) {
        AnnuncioDAO annuncioDAO = new AnnuncioDAO();
        List<AnnuncioModel> array = annuncioDAO.getMyAnnunciVendutiByUtenteID(id);


        List<OffertaModel> arrayList = new ArrayList<>();
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

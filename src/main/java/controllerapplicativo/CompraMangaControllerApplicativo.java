package controllerapplicativo;

import bean.AnnuncioBean;
import dao.AnnuncioDAO;
import dao.UtenteDAO;

import model.AnnuncioModel;


import java.util.List;

public class CompraMangaControllerApplicativo {
    public List<AnnuncioModel> showAnnunce(int id, String name)  {
     AnnuncioDAO dao = new AnnuncioDAO();
     List<AnnuncioModel> array = dao.getAnnunci(id,name);
     UtenteDAO dao2 = new UtenteDAO();

     for(AnnuncioModel a : array){
         a.setVotoUtente(dao2.getVotoByUtenteID(a.getUtenteVenditoreID()));
     }
     return array;

    }


    public AnnuncioBean getAnnuncioById(int idAnnuncio)  {
        AnnuncioDAO dao = new AnnuncioDAO();
        AnnuncioModel model = dao.getDatiAnnuncioByAnnuncioID(idAnnuncio);

        if (model != null) {
            AnnuncioBean bean = new AnnuncioBean();
            bean.setIdAnnuncio(model.getIdAnnuncio());
            bean.setNomeManga(model.getNomeManga());
            bean.setCopiaMangaID(model.getCopiaMangaID());
            bean.setUtenteVenditoreID(model.getUtenteVenditoreID());
            bean.setNomeUtente(model.getNomeUtente());
            bean.setVolume(model.getVolume());
            bean.setPrezzo(model.getPrezzo());
            bean.setDataAnnuncio(model.getDataAnnuncio());

            UtenteDAO dao2 = new UtenteDAO();
            bean.setVotoUtente(dao2.getVotoByUtenteID(model.getUtenteVenditoreID()));

            return bean;
        } else {
            return null;
        }
    }

}

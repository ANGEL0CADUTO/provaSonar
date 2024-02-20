package controllerapplicativo;

import bean.AnnuncioBean;
import bean.OffertaBean;
import dao.AnnuncioDAO;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.AnnuncioModel;
import model.OffertaModel;

import java.time.LocalDateTime;
import java.util.List;

public class OffertaControllerApplicativo {
    public boolean doOfferta(OffertaBean offertaBean) {
        //prendo il session bean dell'utente
        //istanzi il model dell'offerta
        //

        System.out.println("Nell'offertaControllerApplicativo : " + offertaBean.getAnnuncioID());
        OffertaModel offertaModel  = new OffertaModel();
        offertaModel.setOffertaPrezzo(offertaBean.getOffertaPrezzo());
        offertaModel.setAnnuncioID(offertaBean.getAnnuncioID());
        offertaModel.setCopiaMangaID(offertaBean.getCopiaMangaID());
        offertaModel.setUsernameOfferente(offertaBean.getUsernameOfferente());
        offertaModel.setOffertaPrezzo(offertaBean.getOffertaPrezzo());
        offertaModel.setUtenteOfferenteID(offertaBean.getUtenteOfferenteID());
        offertaModel.setDataOfferta(LocalDateTime.now());
        System.out.println("Controllo " + offertaBean.getCopiaMangaID());
        offertaModel.setUtenteOfferenteID(offertaBean.getUtenteOfferenteID());

        AnnuncioDAO annuncioDAO= new AnnuncioDAO();
        AnnuncioModel annuncioModel= annuncioDAO.getDatiAnnuncioByAnnuncioID(offertaBean.getAnnuncioID());

        List<AnnuncioModel> annunci = annuncioDAO.getMyAnnunci(annuncioModel.getUtenteVenditoreID());

        //qua dovrei fare il setState

        offertaModel.setState(offertaModel);



        UtenteDAO dao = new UtenteDAO();
        if(dao.checkCreditoSufficienteByUtenteID(offertaModel.getUtenteOfferenteID(),offertaModel.getOffertaPrezzo()))
        {
            OffertaDAO dao2 = new OffertaDAO();
          //  return dao2.insertOfferta(offertaModel);

          //  COME DEVO CAMBIARE PER FARE UPDATE DEL CAMBIAMENTO
             boolean b = dao2.insertOfferta(offertaModel);
             if(b){
                 offertaModel.notificaCambiamentiAObservers();


             }
             return b;


        }

        return false;

    }

    public AnnuncioBean annuncioByOffertaID(OffertaBean offertaBean) {
         AnnuncioBean annuncioBean = new AnnuncioBean();
         AnnuncioDAO annuncioDAO = new AnnuncioDAO();
         AnnuncioModel annuncioModel= annuncioDAO.getDatiAnnuncioByAnnuncioID(offertaBean.getAnnuncioID());

        annuncioBean.setNomeManga(annuncioModel.getNomeManga());
        annuncioBean.setVolume(annuncioModel.getVolume());
        annuncioBean.setNomeUtente(annuncioModel.getNomeUtente());

        return annuncioBean;
    }






}

package controllerapplicativo;

import Observer.ConcreteObserver;
import Observer.OffertaSubject;
import bean.OffertaBean;
import dao.AnnuncioDAO;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.AnnuncioModel;
import model.OffertaModel;

import java.time.LocalDateTime;

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

        String nomeVenditore = annuncioModel.getNomeUtente();


        ConcreteObserver aggiungiOffertaModelMandaNotifica = new ConcreteObserver(offertaModel,nomeVenditore);


        UtenteDAO dao = new UtenteDAO();
        if(dao.checkCreditoSufficienteByUtenteID(offertaModel.getUtenteOfferenteID(),offertaModel.getOffertaPrezzo()))
        {
            OffertaDAO dao2 = new OffertaDAO();
          //  return dao2.insertOfferta(offertaModel);

          //  COME DEVO CAMBIARE PER FARE UPDATE DEL CAMBIAMENTO
             boolean b = dao2.insertOfferta(offertaModel);
             if(b){
                 offertaModel.notificaCambiamentiAObservers();
                 aggiungiOffertaModelMandaNotifica.update();
             }
             return b;


        }

        return false;

    }

}

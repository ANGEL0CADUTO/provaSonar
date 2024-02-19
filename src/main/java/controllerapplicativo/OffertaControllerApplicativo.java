package controllerapplicativo;

import observer.ConcreteObserver;
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

        System.out.println("Nell'offertaControllerApplicativo : " + offertaBean.getIdOfferta());
        OffertaModel offertaModel  = new OffertaModel();
        offertaModel.setOffertaPrezzo(offertaBean.getOffertaPrezzo());
        offertaModel.setAnnuncioID(offertaBean.getIdAnnuncio());
        offertaModel.setCopiaMangaID(offertaBean.getCopiaMangaID());
        offertaModel.setUsernameOfferente(offertaBean.getUsernameOfferente());
        offertaModel.setOffertaPrezzo(offertaBean.getOffertaPrezzo());
        offertaModel.setUtenteOfferenteID(offertaBean.getUtenteOfferenteID());
        offertaModel.setDataOfferta(LocalDateTime.now());

        offertaModel.setUtenteOfferenteID(offertaBean.getUtenteOfferenteID());

        AnnuncioDAO annuncioDAO= new AnnuncioDAO();
        AnnuncioModel annuncioModel= annuncioDAO.getDatiAnnuncioByAnnuncioID(offertaBean.getIdAnnuncio());

        String nomeVenditore = annuncioModel.getNomeUtente();
        String nomeManga = annuncioModel.getNomeManga();
        int volume = annuncioModel.getVolume();


        ConcreteObserver aggiungiOffertaModelMandaNotifica = new ConcreteObserver(offertaModel,nomeVenditore,nomeManga,volume);


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

                 offertaModel.rimuoviObserver(aggiungiOffertaModelMandaNotifica);
             }
             return b;


        }

        return false;

    }

}

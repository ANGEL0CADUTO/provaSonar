package controllerapplicativo;

import bean.AnnuncioBean;
import bean.OffertaBean;
import dao.AnnuncioDAO;
import dao.OffertaDAO;
import dao.UtenteDAO;
import exceptions.CreditoInsufficienteException;
import model.AnnuncioModel;
import model.OffertaModel;
import model.OfferteModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OffertaControllerApplicativo {
    public boolean doOfferta(OffertaBean offertaBean) throws CreditoInsufficienteException {


        System.out.println("Nell'offertaControllerApplicativo : " + offertaBean.getIdAnnuncio());
        OffertaModel offertaModel  = new OffertaModel();
        offertaModel.setOffertaPrezzo(offertaBean.getOffertaPrezzo());
        offertaModel.setAnnuncioID(offertaBean.getIdAnnuncio());
        offertaModel.setCopiaMangaID(offertaBean.getCopiaMangaID());
        offertaModel.setUsernameOfferente(offertaBean.getUsernameOfferente());
        offertaModel.setOffertaPrezzo(offertaBean.getOffertaPrezzo());
        offertaModel.setUtenteOfferenteID(offertaBean.getUtenteOfferenteID());
        offertaModel.setDataOfferta(LocalDateTime.now());
        System.out.println("Controllo " + offertaBean.getCopiaMangaID());
        offertaModel.setUtenteOfferenteID(offertaBean.getUtenteOfferenteID());

        List<OffertaModel> offertaList = new ArrayList<>();
        offertaList.add(offertaModel);


        OfferteModel offerteModelList = OfferteModel.getInstance();
        offerteModelList.setState(offertaList);

        UtenteDAO dao = new UtenteDAO();
        BigDecimal credito = dao.getVotoAndCreditoByUtenteID(offertaModel.getUtenteOfferenteID()).getCredito();

        if(dao.checkCreditoSufficienteByUtenteID(offertaModel.getUtenteOfferenteID(),offertaModel.getOffertaPrezzo()))
        {
            OffertaDAO dao2 = new OffertaDAO();
            BigDecimal pendingMoney = dao2.getPendingMoneyUtenteByUtenteID(offertaModel.getUtenteOfferenteID());

            BigDecimal cifraTotale = offertaModel.getOffertaPrezzo().add(pendingMoney);

            if(credito.compareTo(cifraTotale)<0){
                return false;
            }

             boolean b = dao2.insertOfferta(offertaModel);
             if(b){

              offerteModelList.notificaCambiamentiAObservers();


             }
             return b;


        }

        return false;

    }

    public AnnuncioBean annuncioByOffertaID(OffertaBean offertaBean) {
         AnnuncioBean annuncioBean = new AnnuncioBean();
         AnnuncioDAO annuncioDAO = new AnnuncioDAO();
         AnnuncioModel annuncioModel= annuncioDAO.getDatiAnnuncioByAnnuncioID(offertaBean.getIdAnnuncio());

        annuncioBean.setNomeManga(annuncioModel.getNomeManga());
        annuncioBean.setVolume(annuncioModel.getVolume());
        annuncioBean.setNomeUtente(annuncioModel.getNomeUtente());

        return annuncioBean;
    }






}

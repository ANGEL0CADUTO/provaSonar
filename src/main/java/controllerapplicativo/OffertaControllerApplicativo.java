package controllerapplicativo;

import bean.OffertaBean;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.OffertaModel;

import java.time.LocalDateTime;

public class OffertaControllerApplicativo {
    public boolean doOfferta(OffertaBean offertaBean) {
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

        UtenteDAO dao = new UtenteDAO();
        if(dao.checkCreditoSufficienteByUtenteID(offertaModel.getUtenteOfferenteID(),offertaModel.getOffertaPrezzo()))
        {
            OffertaDAO dao2 = new OffertaDAO();
            return dao2.insertOfferta(offertaModel);
        }

        return false;

    }

}

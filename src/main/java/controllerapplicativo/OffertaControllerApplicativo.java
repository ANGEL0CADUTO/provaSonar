package controllerapplicativo;

import bean.OffertaBean;
import dao.OffertaDAO;
import model.OffertaModel;

import java.time.LocalDateTime;

public class OffertaControllerApplicativo {
    public boolean doOfferta(OffertaBean offertaBean) {
        OffertaModel offertaModel  = new OffertaModel();
        offertaModel.setOffertaPrezzo(offertaBean.getOffertaPrezzo());
        offertaModel.setAnnuncioID(offertaBean.getAnnuncioID());
        offertaModel.setUsernameOfferente(offertaBean.getUsernameOfferente());
        offertaModel.setOffertaPrezzo(offertaBean.getOffertaPrezzo());
        offertaModel.setUtenteOfferenteID(offertaBean.getUtenteOfferenteID());
        offertaModel.setDataOfferta(LocalDateTime.now());
        OffertaDAO dao = new OffertaDAO();
        return dao.insertOfferta(offertaModel);
    }
}

package controllerapplicativo;

import bean.OffertaBean;
import dao.OffertaDAO;
import model.OffertaModel;

public class OffertaControllerApplicativo {
    public boolean doOfferta(OffertaBean offertaBean) {
        OffertaModel offertaModel  = new OffertaModel();
        offertaModel.setOffertaPrezzo(offertaBean.getOffertaPrezzo());
        OffertaDAO dao = new OffertaDAO();
        return dao.insertOfferta(offertaModel);
    }
}

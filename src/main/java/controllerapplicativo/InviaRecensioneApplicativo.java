package controllerapplicativo;

import bean.RecensioneBean;
import dao.RecensioneDAO;
import model.Recensione;

public class InviaRecensioneApplicativo {

    public boolean inviaRecensione(RecensioneBean recensioneBean,String usernameRecensore){
        boolean b = false;
        RecensioneDAO dao = new RecensioneDAO();
        Recensione recensioneModel = new Recensione();
        recensioneModel.setOffertaID(recensioneBean.getIdOfferta());
        recensioneModel.setRecensitoID(recensioneBean.getRecensitoID());
        recensioneModel.setUsernameRecensore(usernameRecensore);

        dao.inviaRecensione(recensioneModel);
        return b;
    }
}


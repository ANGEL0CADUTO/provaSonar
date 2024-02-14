package controllerapplicativo;

import bean.RecensioneBean;
import dao.OffertaDAO;
import dao.RecensioneDAO;
import dao.UtenteDAO;
import model.Recensione;

public class InviaRecensioneApplicativo {

    public boolean inviaRecensione(RecensioneBean recensioneBean,String usernameRecensore){
        boolean b = false;
        RecensioneDAO dao = new RecensioneDAO();

        Recensione recensioneModel = new Recensione();
        recensioneModel.setOffertaID(recensioneBean.getIdOfferta());
        recensioneModel.setRecensitoID(recensioneBean.getRecensitoID());
        recensioneModel.setUsernameRecensore(usernameRecensore);
        recensioneModel.setVoto(recensioneBean.getVotoRecensione());
        recensioneModel.setTesto(recensioneBean.getTesto());

        if(dao.inviaRecensione(recensioneModel)){
            dao.updateRecensito(recensioneModel.getOffertaID());

            int numRecensioni = dao.getNumeroRecensioniByRecensitoID(recensioneModel.getRecensitoID());

            UtenteDAO dao2 = new UtenteDAO();

            double voto = dao2.getVotoByUtenteID(recensioneModel.getRecensitoID());

            double nuovoVoto = (voto*(numRecensioni-1) + recensioneModel.getVoto())/(numRecensioni );

            if(dao2.aggiornaVotoByUtenteID(recensioneModel.getRecensitoID(),nuovoVoto)){

                b = true;
            }

        }
        return b;
    }
}


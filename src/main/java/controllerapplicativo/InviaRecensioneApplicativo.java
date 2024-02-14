package controllerapplicativo;

import bean.RecensioneBean;
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
            System.out.println("DOVREBBE ESSERE 1 L'ID :" + recensioneModel.getRecensitoID());
            int numRecensioni = dao.getNumeroRecensioniByRecensitoID(recensioneModel.getRecensitoID());

            System.out.println("Dovrebbe ridare 2: " + numRecensioni);
            UtenteDAO dao2 = new UtenteDAO();

            double voto = dao2.getVotoByUtenteID(recensioneModel.getRecensitoID());
            System.out.println("Dovrebbe ridare 4: " + voto);
            double nuovoVoto = (voto*(numRecensioni-1) + recensioneModel.getVoto())/(numRecensioni );
            System.out.println("Dovrebbe essere 4.5 : " + nuovoVoto);
            if(dao2.aggiornaVotoByUtenteID(recensioneModel.getRecensitoID(),nuovoVoto)){
                b = true;
            }

        }
        return b;
    }
}


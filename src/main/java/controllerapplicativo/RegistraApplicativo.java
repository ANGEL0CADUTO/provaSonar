package controllerapplicativo;

import bean.DatiUtenteBean;
import dao.DatiUtenteDao;
import bean.UtenteBean;
import dao.UtenteDAO;
import model.UtenteModel;

public class RegistraApplicativo {

    public boolean registra(UtenteBean bean){
        UtenteDAO registraUtente = new UtenteDAO();
        UtenteModel model = new UtenteModel();
        model.setEmail(bean.getEmail());
        model.setUsername(bean.getUsername());
        return registraUtente.addUser(model);


    }

    public boolean informazioniUtente(UtenteBean bean){
        UtenteDAO registraUtente = new UtenteDAO();
        UtenteModel model = new UtenteModel();
        model.setEmail(bean.getEmail());

        return registraUtente.informazioniUtente(model);
    }

    public int registraDati(DatiUtenteBean bean1){
        DatiUtenteDao registraDatiUtente = new DatiUtenteDao();
         return registraDatiUtente.addDatiUser(bean1);

    }
}

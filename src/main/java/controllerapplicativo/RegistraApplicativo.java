package controllerapplicativo;

import bean.DatiUtenteBean;
import dao.DatiUtenteDao;
import bean.UtenteBean;
import dao.UtenteDAO;

public class RegistraApplicativo {

    public boolean registra(UtenteBean bean){
        UtenteDAO registraUtente = new UtenteDAO();
         return registraUtente.addUser(bean);


    }

    public boolean informazioniUtente(UtenteBean bean){
        UtenteDAO registraUtente = new UtenteDAO();

        return registraUtente.informazioniUtente(bean);
    }

    public int registraDati(DatiUtenteBean bean1){
        DatiUtenteDao registraDatiUtente = new DatiUtenteDao();
         return registraDatiUtente.addDatiUser(bean1);

    }
}

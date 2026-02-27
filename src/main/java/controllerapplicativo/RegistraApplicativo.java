package controllerapplicativo;

import bean.DatiUtenteBean;
import dao.DatiUtenteDao;
import bean.UtenteBean;
import dao.DatiUtenteDaoInterface;
import dao.UtenteDAO;
import dao.UtenteDaoInterface;
import engineering.DAOFactory;
import exceptions.UtenteNonRegistratoException;
import model.UtenteModel;

public class RegistraApplicativo {

    public boolean registra(UtenteBean bean)throws UtenteNonRegistratoException{
        //UtenteDAO registraUtente = new UtenteDAO();
        UtenteDaoInterface registraUtente = DAOFactory.getInstance().getUtenteDAO();
        UtenteModel model = new UtenteModel();
        model.setEmail(bean.getEmail());
        model.setUsername(bean.getUsername());
        model.setPassword(bean.getPassword());

        try {
            return registraUtente.addUser(model);
        } catch (UtenteNonRegistratoException e) {
            throw new UtenteNonRegistratoException();
        }


    }

    public boolean informazioniUtente(UtenteBean bean){
       // UtenteDAO registraUtente = new UtenteDAO();
        UtenteDaoInterface registraUtente = DAOFactory.getInstance().getUtenteDAO();
        UtenteModel model = new UtenteModel();
        model.setEmail(bean.getEmail());

        return registraUtente.informazioniUtente(model);
    }

    public int registraDati(DatiUtenteBean bean1){
       // DatiUtenteDao registraDatiUtente = new DatiUtenteDao();
        DatiUtenteDaoInterface registraDatiUtente = DAOFactory.getInstance().getDatiUtenteDAO();
         return registraDatiUtente.addDatiUser(bean1);

    }
}

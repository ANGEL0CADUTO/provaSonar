package controllerapplicativo;

import dao.DatiUtenteDao;
import bean.CredenzialiBean;
import bean.DatiUtenteBean;
import bean.UtenteBean;
import dao.UtenteDAO;
import exceptions.CredenzialiSbagliateException;
import model.Credenziali;
import model.DatiUtente;
import model.UtenteModel;

public class LoginApplicativo {



    public UtenteBean login(CredenzialiBean bean) throws CredenzialiSbagliateException {
        UtenteDAO dao = new UtenteDAO();
        Credenziali credenziali = new Credenziali();
        UtenteModel utente;

        credenziali.setEmail(bean.getEmail());
        credenziali.setPassword(bean.getPassword());

        try{
            utente = dao.searchUser(credenziali);
        }catch(CredenzialiSbagliateException e){
            throw new CredenzialiSbagliateException();
        }

        DatiUtenteDao dao2 = new DatiUtenteDao();
        DatiUtente dati = dao2.getDatiUserByInformazioniUtenteID(utente.getInformazioniUtenteID());
        DatiUtenteBean datiBean = new DatiUtenteBean();
        datiBean.setIdInformazioniUtente(utente.getInformazioniUtenteID());
        datiBean.setIndirizzo(dati.getIndirizzo());
        datiBean.setCivico(dati.getCivico());
        datiBean.setCap(dati.getCap());

        UtenteBean utenteBean = new UtenteBean();

        utenteBean.setIdUtente(utente.getIdUtente());
        utenteBean.setEmail(utente.getEmail());
        utenteBean.setUsername(utente.getUsername());
        utenteBean.setVotoRecensione(utente.getVotoRecensioni());
        utenteBean.setCredito(utente.getCredito());
        utenteBean.setDatiUtente(datiBean);
        utenteBean.setLogged(true);
        return utenteBean;
    }


}

package controllerapplicativo;

import bean.DatiUtenteBean;

import bean.UtenteBean;
import dao.DatiUtenteDao;
import dao.UtenteDAO;


public class ProfiloUtenteApplicativo {
    public boolean modificaDati(DatiUtenteBean bean) {
        DatiUtenteDao dao = new DatiUtenteDao();

        return dao.modificaDatiUser(bean);
    }

    public DatiUtenteBean getDatiUtente(int id){
        DatiUtenteDao dao = new DatiUtenteDao();

        return dao.getDatiUserByInformazioniUtenteID(id);
    }

    public int insertDatiUtente(DatiUtenteBean bean){
        DatiUtenteDao dao = new DatiUtenteDao();
        return dao.addDatiUser(bean);
    }

    public boolean updateInformazioniUtenteID(UtenteBean bean){
        UtenteDAO dao = new UtenteDAO();
        return dao.informazioniUtente(bean);
    }
}


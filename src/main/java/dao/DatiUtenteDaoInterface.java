package dao;
import bean.DatiUtenteBean;
import model.DatiUtente;
public interface DatiUtenteDaoInterface {

    int addDatiUser(DatiUtenteBean bean1);
    DatiUtente getDatiUserByInformazioniUtenteID(int id);
    boolean modificaDatiUser(DatiUtenteBean bean);
















}

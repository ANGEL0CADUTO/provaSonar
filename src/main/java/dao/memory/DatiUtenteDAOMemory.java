package dao.memory;

import bean.DatiUtenteBean;
import dao.DatiUtenteDaoInterface;
import engineering.DAOFactory;
import model.DatiUtente;

import java.util.ArrayList;
import java.util.List;

public class DatiUtenteDAOMemory implements DatiUtenteDaoInterface {

    // Lista statica che simula la tabella informazioniutente
    private static final List<DatiUtente> datiList = new ArrayList<>();
    private static int idCounter = 1;

    @Override
    public int addDatiUser(DatiUtenteBean bean) {
        if(bean.getIndirizzo().isEmpty() || bean.getCivico().isEmpty() || bean.getCap().isEmpty()) {
            return -1;
        }

        DatiUtente nuovoDato = new DatiUtente();
        nuovoDato.setIndirizzo(bean.getIndirizzo());
        nuovoDato.setCivico(bean.getCivico());
        nuovoDato.setCap(bean.getCap());

        // Simuliamo l'id generato (AUTO_INCREMENT)
        int generatedId = idCounter++;
        // Nota: Nel tuo modello DatiUtente potrebbe mancare il campo ID,
        // ma lo usiamo per il ritorno come richiesto dal metodo.
        datiList.add(nuovoDato);

        return generatedId;
    }

    @Override
    public DatiUtente getDatiUserByInformazioniUtenteID(int id) {
        // In una versione Demo semplificata, l'ID è l'indice della lista (id-1)
        if (id > 0 && id <= datiList.size()) {
            return datiList.get(id - 1);
        }
        return null;
    }

    @Override
    public boolean modificaDatiUser(DatiUtenteBean bean) {
        DatiUtente dati = getDatiUserByInformazioniUtenteID(bean.getIdInformazioniUtente());
        if (dati != null) {
            dati.setIndirizzo(bean.getIndirizzo());
            dati.setCivico(bean.getCivico());
            dati.setCap(bean.getCap());
            return true;
        }
        return false;
    }

    // Metodo di utilità per la demo per ottenere l'ultimo ID inserito
    public static int getLastId() {
        return idCounter - 1;
    }

}

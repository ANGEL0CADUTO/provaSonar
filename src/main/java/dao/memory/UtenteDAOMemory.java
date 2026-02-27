package dao.memory;

import dao.UtenteDaoInterface;
import dao.UtenteDAO;
import model.*;
import exceptions.*;

import java.math.BigDecimal;
import java.util.*;
public class UtenteDAOMemory implements UtenteDaoInterface {
    // Il nostro "Database" finto in RAM
    private static final List<UtenteModel> users = new ArrayList<>();

    @Override
    public UtenteModel searchUser(Credenziali login) throws CredenzialiSbagliateException {
        return users.stream()
                .filter(u -> u.getEmail().equals(login.getEmail()) && u.getPassword().equals(login.getPassword()))
                .findFirst()
                .orElseThrow(CredenzialiSbagliateException::new);
    }

    @Override// PER LA REGISTRAZIONE
    public boolean addUser(UtenteModel model) {
        model.setIdUtente(users.size() + 1); // Simuliamo l'auto-increment
        users.add(model);
        return true;
    }


    @Override
    public boolean informazioniUtente(UtenteModel model) {
        // Cerchiamo l'utente nella nostra lista memory
        for (UtenteModel u : users) {
            if (u.getEmail().equals(model.getEmail())) {

                // LOGICA DEMO: Prendiamo l'ultimo ID generato dal DAO dei dati
                int lastInfoId = DatiUtenteDAOMemory.getLastId();

                // Colleghiamo l'utente alle sue informazioni
                u.setInformazioniUtenteID(lastInfoId);
                return true;
            }
        }
        return false;
    }



    // LOGICA DEPOSITO
    @Override
    public boolean userDeposit(UtenteModel utenteModel, String cifraString) {
        for (UtenteModel u : users) {
            if (u.getIdUtente() == utenteModel.getIdUtente()) {
                BigDecimal cifra = new BigDecimal(cifraString);
                // Calcoliamo il nuovo credito: vecchio + nuovo
                u.setCredito(u.getCredito().add(cifra));
                return true;
            }
        }
        return false;
    }


    // LOGICA PRELIEVO
    @Override
    public boolean userPreliev(UtenteModel model, String cifraString) {
        for (UtenteModel u : users) {
            if (u.getIdUtente() == model.getIdUtente()) {
                BigDecimal cifra = new BigDecimal(cifraString);

                // Nota logica: Il controllo sul credito sufficiente
                // lo facciamo qui o nel controller?
                // Inseriamolo qui per sicurezza come nel DAO SQL.
                if (u.getCredito().compareTo(cifra) >= 0) {
                    u.setCredito(u.getCredito().subtract(cifra));
                    return true;
                }
            }
        }
        return false;
    }




}

package dao;

import exceptions.*;
import model.Credenziali;
import model.UtenteModel;
import java.math.BigDecimal;

public interface UtenteDaoInterface {

    UtenteModel searchUser(Credenziali login) throws CredenzialiSbagliateException;
    boolean addUser(UtenteModel model) throws UtenteNonRegistratoException;
    boolean informazioniUtente(UtenteModel model);
    boolean userDeposit(UtenteModel utenteModel, String cifraString);
    boolean userPreliev(UtenteModel model, String cifraString);// fino qua ho implementato
    double getVotoByUtenteID(int id);

    UtenteModel getVotoAndCreditoByUtenteID(int id);
    boolean aggiornaVotoByUtenteID(int id, double votoNuovo);
    boolean checkCreditoSufficienteByUtenteID(int id, BigDecimal cifra) throws CreditoInsufficienteException;

}

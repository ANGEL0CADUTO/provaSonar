package com.example.demoproject;

public class RegistraApplicativo {

    public boolean registra(UtenteBean bean){
        UtenteDAO registraUtente = new UtenteDAO();//DA FINIRE QUA E FARE COMMIT DA ANGELINO
         return registraUtente.addUser(bean);

      //   return registraUtente.informazioniUtente(bean);
    }

    public boolean informazioniUtente(UtenteBean bean){
        UtenteDAO registraUtente = new UtenteDAO();

        return registraUtente.informazioniUtente(bean);
    }

    public boolean registraDati(DatiUtenteBean bean1){
        DatiUtenteDao registraDatiUtente = new DatiUtenteDao();
         return registraDatiUtente.addDatiUser(bean1);

    }
}

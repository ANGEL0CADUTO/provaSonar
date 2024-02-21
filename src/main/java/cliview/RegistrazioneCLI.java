package cliview;

import bean.DatiUtenteBean;
import bean.UtenteBean;
import controllerapplicativo.RegistraApplicativo;
import exceptions.UtenteNonRegistratoException;
import utils.CLIPrinter;

import java.util.Scanner;

public class RegistrazioneCLI {

    private static String richiediInput(String messaggio, String messaggioErrore) {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            CLIPrinter.println(messaggio);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                CLIPrinter.println(messaggioErrore);
            }
        } while (input.isEmpty());
        return input;
    }

    public void initialize() {
        UtenteBean utenteBean = new UtenteBean();

        utenteBean.setEmail(richiediInput("Inserisci la tua email: ", "Email non può essere vuota. Riprova."));
        utenteBean.setUsername(richiediInput("Inserisci il tuo username: ", "Username non può essere vuoto. Riprova."));
        utenteBean.setPassword(richiediInput("Inserisci la tua password: ", "Password non può essere vuota. Riprova."));

        DatiUtenteBean datiUtente = new DatiUtenteBean();

        datiUtente.setIndirizzo(richiediInput("Inserisci il tuo indirizzo: ", "Indirizzo non può essere vuoto. Riprova."));
        datiUtente.setCivico(richiediInput("Inserisci il tuo civico: ", "Civico non può essere vuoto. Riprova."));
        datiUtente.setCap(richiediInput("Inserisci il tuo CAP: ", "CAP non può essere vuoto. Riprova."));

        RegistraApplicativo controllerApp = new RegistraApplicativo();
        int key = controllerApp.registraDati(datiUtente);

        utenteBean.setDatiUtente(datiUtente);
        utenteBean.getDatiUtente().setIdInformazioniUtente(key);

        try {
            if (key != -1 && controllerApp.registra(utenteBean) && controllerApp.informazioniUtente(utenteBean)) {
                CLIPrinter.println("Registrazione effettuata con successo per l'utente: " + utenteBean.getEmail());
            } else {
                CLIPrinter.println("Registrazione fallita. Controlla i dati inseriti e riprova.");
            }
        } catch (UtenteNonRegistratoException e) {
            CLIPrinter.println(e.getMessage());
        }
    }
}

package cliview;

import bean.DatiUtenteBean;
import bean.UtenteBean;
import controllerapplicativo.RegistraApplicativo;

import java.util.Scanner;

public class RegistrazioneCLI {

    public void initialize(){
        Scanner scanner = new Scanner(System.in);
        UtenteBean utenteBean = new UtenteBean();

        // Validazione email
        String email;
        do {
            System.out.println("Inserisci la tua email: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email non può essere vuota. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per l'email
                utenteBean.setEmail(email);
            }
        } while (email.isEmpty());

        // Validazione username
        String username;
        do {
            System.out.println("Inserisci il tuo username: ");
            username = scanner.nextLine().trim();
            if (username.isEmpty()) {
                System.out.println("Username non può essere vuoto. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per lo username
                utenteBean.setUsername(username);
            }
        } while (username.isEmpty());

        // Validazione password
        String password;
        do {
            System.out.println("Inserisci la tua password: ");
            password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Password non può essere vuota. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per la password
                utenteBean.setPassword(password);
            }
        } while (password.isEmpty());

        DatiUtenteBean datiUtente = new DatiUtenteBean();

        // Validazione indirizzo
        String indirizzo;
        do {
            System.out.println("Inserisci il tuo indirizzo: ");
            indirizzo = scanner.nextLine().trim();
            if (indirizzo.isEmpty()) {
                System.out.println("Indirizzo non può essere vuoto. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per l'indirizzo
                datiUtente.setIndirizzo(indirizzo);
            }
        } while (indirizzo.isEmpty());

        // Validazione civico
        String civico;
        do {
            System.out.println("Inserisci il tuo civico: ");
            civico = scanner.nextLine().trim();
            if (civico.isEmpty()) {
                System.out.println("Civico non può essere vuoto. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per il civico
                datiUtente.setCivico(civico);
            }
        } while (civico.isEmpty());

        // Validazione CAP
        String cap;
        do {
            System.out.println("Inserisci il tuo CAP: ");
            cap = scanner.nextLine().trim();
            if (cap.isEmpty()) {
                System.out.println("CAP non può essere vuoto. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per il CAP
                datiUtente.setCap(cap);
            }
        } while (cap.isEmpty());

        RegistraApplicativo controllerApp = new RegistraApplicativo();
        int key = controllerApp.registraDati(datiUtente);

        utenteBean.setDatiUtente(datiUtente);
        utenteBean.getDatiUtente().setIdInformazioniUtente(key);
        if (key != -1 && controllerApp.registra(utenteBean) && controllerApp.informazioniUtente(utenteBean)) {
            System.out.println("Registrazione effettuata con successo per l'utente: " + utenteBean.getEmail());
        } else {
            System.out.println("Registrazione fallita. Controlla i dati inseriti e riprova.");
        }


    }
}

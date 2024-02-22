package cliview;

import bean.RecensioneBean;
import bean.UtenteBean;
import controllerapplicativo.InviaRecensioneApplicativo;
import utils.CLIPrinter;

import java.util.Scanner;

public class InviaRecensioneCLI {
    private UtenteBean utenteBean;
    private RecensioneBean datiRecensione;

    public InviaRecensioneCLI(UtenteBean utente, RecensioneBean dati) {
        this.utenteBean = utente;
        this.datiRecensione = dati;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        boolean invioRiuscito = false;

        do {
            int voto;
            do {
                CLIPrinter.println("*************************************");
                CLIPrinter.println("Ci troviamo in HomePage/Libreria/MieiAcquisti/INVIA RECENSIONE");
                CLIPrinter.println("Inserisci il voto (da 1 a 5): ");
                voto = scanner.nextInt();


                if (voto < 1 || voto > 5) {
                    CLIPrinter.println("Il voto deve essere compreso tra 1 e 5. Riprova.");
                }
            } while (voto < 1 || voto > 5);

            scanner.nextLine();

            CLIPrinter.println("Inserisci una descrizione della recensione: ");
            String descrizione = scanner.nextLine();

            datiRecensione.setVotoRecensione(voto);
            datiRecensione.setTesto(descrizione);

            InviaRecensioneApplicativo controllerApp = new InviaRecensioneApplicativo();
            invioRiuscito = controllerApp.inviaRecensione(datiRecensione, utenteBean.getUsername());

            if (!invioRiuscito) {
                CLIPrinter.println("Errore nell'invio della recensione. Riprova.");
            } else {
                CLIPrinter.println("Recensione inviata con successo!");
            }
        } while (!invioRiuscito);
    }
}

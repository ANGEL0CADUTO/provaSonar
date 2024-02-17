package view2;

import bean.RecensioneBean;
import bean.UtenteBean;
import controllerapplicativo.InviaRecensioneApplicativo;

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
                System.out.println("*************************************");
                System.out.println("Ci troviamo in HomePage/Libreria/MieiAcquisti/INVIA RECENSIONE");
                System.out.println("Inserisci il voto (da 1 a 5): ");
                voto = scanner.nextInt();

                // Validazione del voto
                if (voto < 1 || voto > 5) {
                    System.out.println("Il voto deve essere compreso tra 1 e 5. Riprova.");
                }
            } while (voto < 1 || voto > 5);

            scanner.nextLine(); // Consuma il newline lasciato dal precedente nextInt

            System.out.println("Inserisci una descrizione della recensione: ");
            String descrizione = scanner.nextLine();


            datiRecensione.setVotoRecensione(voto);
            datiRecensione.setTesto(descrizione);


            // Esempio di chiamata a InviaRecensioneApplicativo:
            InviaRecensioneApplicativo controllerApp = new InviaRecensioneApplicativo();
            invioRiuscito = controllerApp.inviaRecensione(datiRecensione, utenteBean.getUsername());

            if (!invioRiuscito) {
                System.out.println("Errore nell'invio della recensione. Riprova.");
            } else {
                System.out.println("Recensione inviata con successo!");
            }
        } while (!invioRiuscito);
    }
}

package view2;

import bean.RecensioneBean;
import bean.UtenteBean;
import controllerapplicativo.InviaRecensioneApplicativo;

import java.util.Scanner;

public class InviaRecensioneCLI{
    private UtenteBean utenteBean;
    private int idOfferta;
    public InviaRecensioneCLI(UtenteBean utente, int id) {
        this.utenteBean = utente;
        this.idOfferta = id;
    }


    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        int voto;
        do {
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

        RecensioneBean datiRecensione = new RecensioneBean();
        datiRecensione.setVotoRecensione(voto);
        datiRecensione.setTesto(descrizione);

        // Esempio di chiamata a InviaRecensioneApplicativo:
        InviaRecensioneApplicativo controllerApp = new InviaRecensioneApplicativo();
        controllerApp.inviaRecensione(datiRecensione, utenteBean.getUsername());

        System.out.println("Recensione inviata con successo!");
    }
}

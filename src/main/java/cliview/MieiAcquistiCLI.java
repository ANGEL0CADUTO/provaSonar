package cliview;

import bean.RecensioneBean;
import bean.UtenteBean;
import controllerapplicativo.MieiAcquistiApplicativo;
import model.OffertaModel;
import utils.CLIPrinter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MieiAcquistiCLI {
    private UtenteBean utenteBean;

    public MieiAcquistiCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            CLIPrinter.println("*************************************");
            CLIPrinter.println("Ci troviamo in HomePage/Libreria/MIEI ACQUISTI");

            CLIPrinter.println("Scegli un'opzione:");
            CLIPrinter.println("0. Torna indietro");
            CLIPrinter.println("1. Visualizza Acquisti");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    visualizzaAcquisti();
                    break;
                case 0:
                    CLIPrinter.println("Tornando indietro...");
                    break;
                default:
                    CLIPrinter.println("Scelta non valida. Riprova.");
                    break;
            }

        } while (choice != 0);
    }

    private void visualizzaAcquisti() {
        MieiAcquistiApplicativo controller = new MieiAcquistiApplicativo();
        List<OffertaModel> array = controller.getMyOfferteAccettate(utenteBean.getIdUtente());

        for (OffertaModel offerta : array) {
            CLIPrinter.println("IdAcquisto : " + offerta.getIdOfferta());
            CLIPrinter.println("Titolo Manga: " + offerta.getTitoloManga() + " Volume Manga: " + offerta.getVolumeManga());
            CLIPrinter.println("Prezzo: " + offerta.getOffertaPrezzo());
            CLIPrinter.println("Data Offerta: " + offerta.getDataOfferta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            // Aggiungi il bottone solo se l'offerta non è stata ancora recensita
            if (offerta.getRecensito() == 0) {
                CLIPrinter.println("Non hai ancora recensito questo acquisto, vuoi farlo? (y/n): ");
                Scanner scanner = new Scanner(System.in);
                String risposta = scanner.nextLine().toLowerCase();
                if (risposta.equals("y")) {
                    CLIPrinter.println("Navigazione alla pagina recensione...");
                    RecensioneBean recensioneBean = new RecensioneBean();
                    recensioneBean.setIdOfferta(offerta.getIdOfferta());
                    recensioneBean.setRecensitoID(offerta.getUtenteVenditoreID());
                    recensisci(utenteBean, recensioneBean);
                }
            } else {
                CLIPrinter.println("Recensisci: Già Recensito");
            }

            CLIPrinter.println("------------------------------");
        }
    }

    private void recensisci(UtenteBean utenteBean, RecensioneBean recensioneBean) {
        InviaRecensioneCLI inviaRecensioneCLI = new InviaRecensioneCLI(utenteBean, recensioneBean);
        inviaRecensioneCLI.initialize();
    }
}

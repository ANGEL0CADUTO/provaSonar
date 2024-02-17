package view2;

import bean.UtenteBean;
import controllerapplicativo.MieiAcquistiApplicativo;
import model.OffertaModel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            System.out.println("Scegli un'opzione:");
            System.out.println("0. Torna indietro");
            System.out.println("1. Visualizza Acquisti");
            System.out.println("2. Recensisci");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    visualizzaAcquisti();
                    break;
                case 2:
                    recensisci();
                    break;
                case 0:
                    System.out.println("Tornando indietro...");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }

        } while (choice != 0);
    }

    private void visualizzaAcquisti() {
        MieiAcquistiApplicativo controller = new MieiAcquistiApplicativo();
        ArrayList<OffertaModel> array = controller.getMyOfferteAccettate(utenteBean.getIdUtente());

        for (OffertaModel offerta : array) {
            System.out.println("IdAcquisto : " + offerta.getIdOfferta());
            System.out.println("Titolo Manga: " + offerta.getTitoloManga() + " Volume Manga: " + offerta.getVolumeManga());
            System.out.println("Prezzo: " + offerta.getOffertaPrezzo());
            System.out.println("Data Offerta: " + offerta.getDataOfferta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            // Aggiungi il bottone solo se l'offerta non è stata ancora recensita
            if (offerta.getRecensito() == 0) {
                System.out.println("Recensisci: Disponibile");
            } else {
                System.out.println("Recensisci: Già Recensito");
            }

            System.out.println("------------------------------");
        }
    }

    private void recensisci() {
        // Implementa la logica per recensire un acquisto
        System.out.println("Funzione di recensione non ancora implementata.");
    }
}

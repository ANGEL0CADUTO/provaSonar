package cliview;

import bean.CopiaMangaBean;
import bean.UtenteBean;
import controllerapplicativo.LibreriaUtenteControllerApplicativo;
import model.CopiaMangaModel;
import utils.CLIPrinter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class LibreriaCLI {
    private UtenteBean utenteBean;

    public LibreriaCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            CLIPrinter.println("*************************************");
            CLIPrinter.println("Ci troviamo in HomePage/LIBRERIA");
            CLIPrinter.println("0. Torna indietro");
            CLIPrinter.println("1. Visualizza Manga");
            CLIPrinter.println("2. Aggiungi Manga");
            CLIPrinter.println("3. Mie vendite");
            CLIPrinter.println("4. Miei Acquisti");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    visualizzaManga();
                    break;
                case 2:
                    aggiungiManga();
                    break;
                case 3:
                    mieVendite();
                    break;
                case 4:
                    mieiAcquisti();
                    break;
                case 0:
                    CLIPrinter.println("Tornando indietro.");
                    break;
                default:
                    CLIPrinter.println("Scelta non valida. Riprova.");
                    break;
            }

        } while (choice != 0);
    }

    public void visualizzaManga() {
        LibreriaUtenteControllerApplicativo controller = new LibreriaUtenteControllerApplicativo();
        List<CopiaMangaModel> risultati = controller.showUserManga(utenteBean);

        for (CopiaMangaModel risultato : risultati) {
            CLIPrinter.println("ID: " + risultato.getIdCopiaManga());
            CLIPrinter.println("Titolo: " + risultato.getTitolo());
            CLIPrinter.println("Volume: " + risultato.getVolume());
            CLIPrinter.println("Data Acquisto: " + risultato.getDataAcquisto());
            CLIPrinter.println("Stato: " + risultato.getStatoCopiaManga());
            CLIPrinter.println("--------------");
        }
    }

    public void aggiungiManga() {
        Scanner scanner = new Scanner(System.in);

        CLIPrinter.println("Inserisci il titolo del manga:");
        String titolo = scanner.nextLine();

        CLIPrinter.println("Inserisci il volume del manga:");
        int volumeNumero = 0;
        try {
            volumeNumero = Integer.parseInt(scanner.nextLine());
            if (volumeNumero <= 0) {
                CLIPrinter.println("Inserire un volume valido.");
                return;
            }
        } catch (NumberFormatException e) {
            CLIPrinter.println("Inserire un volume valido.");
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        CopiaMangaBean copiaBean = new CopiaMangaBean();
        copiaBean.setIdUtente(utenteBean.getIdUtente());
        copiaBean.setTitolo(titolo);
        copiaBean.setVolume(volumeNumero);
        copiaBean.setDataAcquisto(now);

        LibreriaUtenteControllerApplicativo controllerApp = new LibreriaUtenteControllerApplicativo();
        if (controllerApp.aggiungiManga(copiaBean) != -1) {
            CLIPrinter.println("Manga aggiunto con successo!");
        } else {
            CLIPrinter.println("C'è stato un errore");
        }
    }

    public void mieVendite() {
        MieVenditeCLI mieVenditeCLI = new MieVenditeCLI(utenteBean);
        mieVenditeCLI.initialize();
    }

    public void mieiAcquisti() {
        MieiAcquistiCLI mieiAcquistiCLI = new MieiAcquistiCLI(utenteBean);
        mieiAcquistiCLI.initialize();
    }
}

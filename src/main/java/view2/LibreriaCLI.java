package view2;

import bean.CopiaMangaBean;
import bean.UtenteBean;
import controllerapplicativo.LibreriaUtenteControllerApplicativo;
import model.CopiaMangaModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            System.out.println("*************************************");
            System.out.println("Ci troviamo in HomePage/LIBRERIA");
            System.out.println("0. Torna indietro");
            System.out.println("1. Visualizza Manga");
            System.out.println("2. Aggiungi Manga");
            System.out.println("3. Mie vendite");
            System.out.println("4. Miei Acquisti");

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
                    System.out.println("Tornando indietro.");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }

        } while (choice != 0);
    }

    public void visualizzaManga() {
        LibreriaUtenteControllerApplicativo controller = new LibreriaUtenteControllerApplicativo();
        List<CopiaMangaModel> risultati = controller.showUserManga(utenteBean);

        for (CopiaMangaModel risultato : risultati) {
            System.out.println("ID: " + risultato.getIdCopiaManga());
            System.out.println("Titolo: " + risultato.getTitolo());
            System.out.println("Volume: " + risultato.getVolume());
            System.out.println("Data Acquisto: " + risultato.getDataAcquisto());
            System.out.println("Stato: " + risultato.getStatoCopiaManga());
            System.out.println("--------------");
        }
    }

    public void aggiungiManga() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il titolo del manga:");
        String titolo = scanner.nextLine();

        System.out.println("Inserisci il volume del manga:");
        int volumeNumero = 0;
        try {
            volumeNumero = Integer.parseInt(scanner.nextLine());
            if (volumeNumero <= 0) {
                System.out.println("Inserire un volume valido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Inserire un volume valido.");
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        CopiaMangaBean copiaBean = new CopiaMangaBean();
        copiaBean.setIdUtente(utenteBean.getIdUtente());
        copiaBean.setTitolo(titolo);
        copiaBean.setVolume(volumeNumero);
        copiaBean.setDataAcquisto(now);

        LibreriaUtenteControllerApplicativo controllerApp = new LibreriaUtenteControllerApplicativo();
        if( controllerApp.aggiungiManga(copiaBean) != -1) {
            System.out.println("Manga aggiunto con successo!");
        }
        else{
            System.out.println("C'è stato un errore");
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

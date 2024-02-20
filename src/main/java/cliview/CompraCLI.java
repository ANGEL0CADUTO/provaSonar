package cliview;

import bean.AnnuncioBean;
import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.CompraMangaControllerApplicativo;
import controllerapplicativo.OffertaControllerApplicativo;
import model.AnnuncioModel;

import java.math.BigDecimal;

import java.util.List;
import java.util.Scanner;

public class CompraCLI {
    private UtenteBean utenteBean;
    public CompraCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("*************************************");
            System.out.println("Ci trovimo in HomePage/COMPRA:");
            System.out.println("0. Torna indietro");
            System.out.println("1. Visualizza Manga");
            System.out.println("2. Acquista");


            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    visualizzaAnnunci();
                    break;
                case 2:
                    acquista();
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

    public void visualizzaAnnunci(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il nome del manga da cercare:");
        String nomeManga = scanner.nextLine();

        CompraMangaControllerApplicativo controllerApp = new CompraMangaControllerApplicativo();
        List<AnnuncioModel> annunci = controllerApp.showAnnunce(utenteBean.getIdUtente(),nomeManga);

        System.out.println("Elenco degli annunci per il manga " + nomeManga + ":");

        for (AnnuncioModel annuncio : annunci) {
            System.out.println("ID Annuncio: " + annuncio.getIdAnnuncio());
            System.out.println("Nome Utente: " + annuncio.getNomeUtente() + " Voto : " + annuncio.getVotoUtente());
            System.out.println("Nome Manga: " + annuncio.getNomeManga() + " Volume : " + annuncio.getVolume());
            System.out.println("Prezzo: " + annuncio.getPrezzo());
            System.out.println("--------------------------------");
        }

    }

    public void acquista(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci l'ID dell'annuncio che desideri acquistare:");
        int idAnnuncio = scanner.nextInt();

        CompraMangaControllerApplicativo controllerApp = new CompraMangaControllerApplicativo();
        AnnuncioBean annuncio = controllerApp.getAnnuncioById(idAnnuncio);

        if (annuncio != null) {
            System.out.println("------------------------------");
            System.out.println("Annuncio trovato:");
            System.out.println("ID Annuncio: " + annuncio.getIdAnnuncio());
            System.out.println("Nome Utente: " + annuncio.getNomeUtente() + " Voto : " + annuncio.getVotoUtente());
            System.out.println("Nome Manga: " + annuncio.getNomeManga() + " Volume : " + annuncio.getVolume());
            System.out.println("Prezzo: " + annuncio.getPrezzo());
            System.out.println("------------------------------");

            // Ora chiedi all'utente di fare un'offerta
            System.out.println("Vuoi fare un'offerta per questo annuncio? (y/n)");
            String risposta = scanner.next();

            if (risposta.equalsIgnoreCase("y")) {
                // Richiama la funzione per fare un'offerta
                faiOfferta(annuncio);
            } else {
                System.out.println("Acquisto annullato.");
            }
        } else {
            System.out.println("Annuncio non trovato con ID: " + idAnnuncio);
        }
    }

    private void faiOfferta(AnnuncioBean annuncio) {
        Scanner scanner = new Scanner(System.in);
        OffertaBean offertaBean = new OffertaBean();
        offertaBean.setIdAnnuncio(annuncio.getIdAnnuncio());
        offertaBean.setCopiaMangaID(annuncio.getCopiaMangaID());
        System.out.println("COMPRACLI ricevere da annuncio id copia pari a : "+ annuncio.getCopiaMangaID());
        offertaBean.setUsernameOfferente(annuncio.getNomeUtente());
        offertaBean.setDataOfferta(annuncio.getDataAnnuncio());

        offertaBean.setUtenteOfferenteID(annuncio.getUtenteVenditoreID());



        System.out.println("Inserisci l'importo dell'offerta:");
        BigDecimal importoOfferta = scanner.nextBigDecimal();
        offertaBean.setOffertaPrezzo(importoOfferta);

        // Aggiungi ulteriori informazioni all'offerta se necessario

        var controllerOfferta = new OffertaControllerApplicativo();
        boolean esitoOfferta = controllerOfferta.doOfferta(offertaBean);

        if (esitoOfferta) {
            System.out.println("Offerta inviata con successo!");
        } else {
            System.out.println("Errore nell'invio dell'offerta. Assicurati di avere credito sufficiente.");
        }
    }
}

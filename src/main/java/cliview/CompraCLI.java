package cliview;

import bean.AnnuncioBean;
import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.CompraMangaControllerApplicativo;
import controllerapplicativo.OffertaControllerApplicativo;
import exceptions.CreditoInsufficienteException;
import model.AnnuncioModel;
import utils.CLIPrinter;

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
            CLIPrinter.println("*************************************");
            CLIPrinter.println("Ci trovimo in HomePage/COMPRA:");
            CLIPrinter.println("0. Torna indietro");
            CLIPrinter.println("1. Visualizza Manga");
            CLIPrinter.println("2. Acquista");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    visualizzaAnnunci();
                    break;
                case 2:
                    acquista();
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

    public void visualizzaAnnunci() {
        Scanner scanner = new Scanner(System.in);
        CLIPrinter.println("Inserisci il nome del manga da cercare:");
        String nomeManga = scanner.nextLine();

        CompraMangaControllerApplicativo controllerApp = new CompraMangaControllerApplicativo();
        List<AnnuncioModel> annunci = controllerApp.showAnnunce(utenteBean.getIdUtente(), nomeManga);

        CLIPrinter.println("Elenco degli annunci per il manga " + nomeManga + ":");

        for (AnnuncioModel annuncio : annunci) {
            CLIPrinter.println("ID Annuncio: " + annuncio.getIdAnnuncio());
            CLIPrinter.println("Nome Utente: " + annuncio.getNomeUtente() + " Voto : " + annuncio.getVotoUtente());
            CLIPrinter.println("Nome Manga: " + annuncio.getNomeManga() + " Volume : " + annuncio.getVolume());
            CLIPrinter.println("Prezzo: " + annuncio.getPrezzo());
            CLIPrinter.println("--------------------------------");
        }
    }

    public void acquista() {
        Scanner scanner = new Scanner(System.in);
        CLIPrinter.println("Inserisci l'ID dell'annuncio che desideri acquistare:");
        int idAnnuncio = scanner.nextInt();

        CompraMangaControllerApplicativo controllerApp = new CompraMangaControllerApplicativo();
        AnnuncioBean annuncio = controllerApp.getAnnuncioById(idAnnuncio);

        if (annuncio != null) {
            CLIPrinter.println("------------------------------");
            CLIPrinter.println("Annuncio trovato:");
            CLIPrinter.println("ID Annuncio: " + annuncio.getIdAnnuncio());
            CLIPrinter.println("Nome Utente: " + annuncio.getNomeUtente() + " Voto : " + annuncio.getVotoUtente());
            CLIPrinter.println("Nome Manga: " + annuncio.getNomeManga() + " Volume : " + annuncio.getVolume());
            CLIPrinter.println("Prezzo: " + annuncio.getPrezzo());
            CLIPrinter.println("------------------------------");

            // Ora chiedi all'utente di fare un'offerta
            CLIPrinter.println("Vuoi fare un'offerta per questo annuncio? (y/n)");
            String risposta = scanner.next();

            if (risposta.equalsIgnoreCase("y")) {
                // Richiama la funzione per fare un'offerta
                try {
                    faiOfferta(annuncio);
                } catch (CreditoInsufficienteException e) {
                    CLIPrinter.println("Errore exception personalizzata : " + e.getMessage());
                }
            } else {
                CLIPrinter.println("Acquisto annullato.");
            }
        } else {
            CLIPrinter.println("Annuncio non trovato con ID: " + idAnnuncio);
        }
    }

    private void faiOfferta(AnnuncioBean annuncio) throws CreditoInsufficienteException {
        Scanner scanner = new Scanner(System.in);
        OffertaBean offertaBean = new OffertaBean();
        offertaBean.setIdAnnuncio(annuncio.getIdAnnuncio());
        offertaBean.setCopiaMangaID(annuncio.getCopiaMangaID());
        CLIPrinter.println("COMPRACLI ricevere da annuncio id copia pari a : " + annuncio.getCopiaMangaID());
        offertaBean.setUsernameOfferente(annuncio.getNomeUtente());
        offertaBean.setDataOfferta(annuncio.getDataAnnuncio());

        offertaBean.setUtenteOfferenteID(annuncio.getUtenteVenditoreID());

        CLIPrinter.println("Inserisci l'importo dell'offerta:");
        BigDecimal importoOfferta = scanner.nextBigDecimal();
        offertaBean.setOffertaPrezzo(importoOfferta);

        // Aggiungi ulteriori informazioni all'offerta se necessario

        var controllerOfferta = new OffertaControllerApplicativo();
        boolean esitoOfferta = controllerOfferta.doOfferta(offertaBean);

        if (esitoOfferta) {
            CLIPrinter.println("Offerta inviata con successo!");
        } else {
            CLIPrinter.println("Errore nell'invio dell'offerta. Assicurati di avere credito sufficiente.");
        }
    }
}

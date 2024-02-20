package cliview;

import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.OfferteRicevuteApplicativo;
import model.OffertaRicevuta;
import utils.CLIPrinter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class OfferteRicevuteCLI {
    private UtenteBean utenteBean;
    private int idAnnuncio;

    public OfferteRicevuteCLI(UtenteBean utente, int id) {
        this.utenteBean = utente;
        this.idAnnuncio = id;
    }

    public void initialize() {
        CLIPrinter.println("*************************************");
        CLIPrinter.println("Ci troviamo in HomePage/Miei Annunci/OFFERTE RICEVUTE:");

        OfferteRicevuteApplicativo controller = new OfferteRicevuteApplicativo();
        List<OffertaRicevuta> array = controller.getOfferteRicevuteByAnnuncioID(this.idAnnuncio);

        if (array.isEmpty()) {
            CLIPrinter.println("///////////////////////////////////////");
            CLIPrinter.println("NON CI SONO OFFERTE PER QUESTO ANNUNCIO");
            CLIPrinter.println("///////////////////////////////////////");
            return;
        }
        for (OffertaRicevuta offerta : array) {

            CLIPrinter.println("Username Offerente: " + offerta.getUsernameOfferente());
            CLIPrinter.println("Voto Offerente: " + offerta.getVotoRecensioni());
            CLIPrinter.println("Prezzo Offerta: " + offerta.getOffertaPrezzo());
            CLIPrinter.println("Data Offerta: " + offerta.getDataOfferta());

            // Chiedi all'utente se desidera accettare l'offerta
            CLIPrinter.println("Vuoi accettare questa offerta? (y/n): ");
            Scanner scanner = new Scanner(System.in);
            String risposta = scanner.nextLine().toLowerCase();

            if (risposta.equals("y")) {
                OffertaBean offertaBean = new OffertaBean();
                offertaBean.setIdAnnuncio(offerta.getAnnuncioID());
                offertaBean.setCopiaMangaID(offerta.getCopiaMangaID());
                offertaBean.setTitoloManga(offerta.getTitoloManga());
                offertaBean.setVolumeManga(offerta.getVolumeManga());
                offertaBean.setUtenteOfferenteID(offerta.getUtenteOfferenteID());
                offertaBean.setIdOfferta(offerta.getIdOfferta());
                offertaBean.setOffertaPrezzo(offerta.getOffertaPrezzo());
                offertaBean.setDataOfferta(LocalDateTime.now());
                controller.accettaOffertaByOffertaID(offertaBean, utenteBean.getIdUtente());
                CLIPrinter.println("Offerta accettata con successo!");
                return;
            } else {
                CLIPrinter.println("Azione annullata.");
            }

            CLIPrinter.println("------------------------------");
            CLIPrinter.println("------------------------------");

        }
        CLIPrinter.println("*************************************");
        CLIPrinter.println("OFFERTE PER QUESTO ANNUNCIO TERMINATE");
        CLIPrinter.println("*************************************");
    }
}

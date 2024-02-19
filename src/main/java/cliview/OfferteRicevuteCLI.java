package cliview;

import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.OfferteRicevuteApplicativo;
import model.OffertaRicevuta;

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
        System.out.println("*************************************");
        System.out.println("Ci troviamo in HomePage/Miei Annunci/OFFERTE RICEVUTE:");

        OfferteRicevuteApplicativo controller = new OfferteRicevuteApplicativo();
        List<OffertaRicevuta> array = controller.getOfferteRicevuteByAnnuncioID(this.idAnnuncio);

        if(array.isEmpty()){
            System.out.println("///////////////////////////////////////");
            System.out.println("NON CI SONO OFFERTE PER QUESTO ANNUNCIO");
            System.out.println("///////////////////////////////////////");
            return;
        }
        for (OffertaRicevuta offerta : array) {

            System.out.println("Username Offerente: " + offerta.getUsernameOfferente());
            System.out.println("Voto Offerente: " + offerta.getVotoRecensioni());
            System.out.println("Prezzo Offerta: " + offerta.getOffertaPrezzo());
            System.out.println("Data Offerta: " + offerta.getDataOfferta());

            // Chiedi all'utente se desidera accettare l'offerta
            System.out.println("Vuoi accettare questa offerta? (y/n): ");
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
                System.out.println("Offerta accettata con successo!");
                return;
            } else {
                System.out.println("Azione annullata.");
            }

            System.out.println("------------------------------");
            System.out.println("------------------------------");

        }
        System.out.println("*************************************");
        System.out.println("OFFERTE PER QUESTO ANNUNCIO TERMINATE");
        System.out.println("*************************************");

    }

}

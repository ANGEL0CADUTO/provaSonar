package view2;

import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.OfferteRicevuteApplicativo;
import model.OffertaRicevuta;

import java.util.ArrayList;
import java.util.Scanner;

public class OfferteRicevuteCLI {
    private UtenteBean utenteBean;
    private int idAnnuncio;
    public OfferteRicevuteCLI(UtenteBean utente, int id) {
        this.utenteBean = utente;
        this.idAnnuncio = id;
    }

    public void initialize() {
        OfferteRicevuteApplicativo controller = new OfferteRicevuteApplicativo();
        ArrayList<OffertaRicevuta> array = controller.getOfferteRicevuteByAnnuncioID(this.idAnnuncio);

        // Iterate over the array and print the details of each received offer
        for (OffertaRicevuta offerta : array) {
            System.out.println("Username Offerente: " + offerta.getUsernameOfferente());
            System.out.println("Voto Offerente: " + offerta.getVotoRecensioni());
            System.out.println("Prezzo Offerta: " + offerta.getOffertaPrezzo());
            System.out.println("Data Offerta: " + offerta.getDataOfferta());

            // Chiedi all'utente se desidera accettare l'offerta
            System.out.println("Vuoi accettare questa offerta? (Sì/No): ");
            Scanner scanner = new Scanner(System.in);
            String risposta = scanner.nextLine().toLowerCase();

            if (risposta.equals("sì")) {
                OffertaBean offertaBean = new OffertaBean();
                offertaBean.setAnnuncioID(offerta.getAnnuncioID());
                offertaBean.setCopiaMangaID(offerta.getCopiaMangaID());
                offertaBean.setUtenteOfferenteID(offerta.getUtenteOfferenteID());
                offertaBean.setIdOfferta(offerta.getIdOfferta());
                offertaBean.setOffertaPrezzo(offerta.getOffertaPrezzo());
                controller.accettaOffertaByOffertaID(offertaBean, utenteBean.getIdUtente());
                System.out.println("Offerta accettata con successo!");
            } else {
                System.out.println("Azione annullata.");
            }

            System.out.println("------------------------------");
        }
        System.out.println("*************************************");
        System.out.println("OFFERTE PER QUESTO ANNUNCIO TERMINATE");
        System.out.println("*************************************");

    }

}

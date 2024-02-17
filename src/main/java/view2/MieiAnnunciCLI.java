package view2;

import bean.UtenteBean;
import controllerapplicativo.MieiAnnunciApplicativo;
import model.AnnuncioModel;
import model.OffertaRicevuta;
import view.OfferteRicevuteGrafico;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MieiAnnunciCLI {

    private UtenteBean utenteBean;

    public MieiAnnunciCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }

    public void initialize() {
        MieiAnnunciApplicativo controller = new MieiAnnunciApplicativo();
        ArrayList<AnnuncioModel> array = controller.getMyAnnunci(utenteBean.getIdUtente());

        for (AnnuncioModel annuncio : array) {


            System.out.println("Nome Manga: " + annuncio.getNomeManga());
            System.out.println("Prezzo: " + annuncio.getPrezzo());
            System.out.println("Data Annuncio: " + annuncio.getDataAnnuncio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            System.out.println("Volume: " + annuncio.getVolume());


            // Chiedi all'utente se desidera navigare alle offerte ricevute
            System.out.println("Vuoi navigare alle offerte ricevute per questo annuncio? (y/n): ");
            Scanner scanner = new Scanner(System.in);
            String risposta = scanner.nextLine().toLowerCase();

            if (risposta.equals("y")) {
                System.out.println("Navigazione alle offerte ricevute per questo annuncio...");

                OfferteRicevuteCLI offerteRicevuteCLI = new OfferteRicevuteCLI(utenteBean, annuncio.getIdAnnuncio());

                // Implementa la navigazione alle offerte ricevute se necessario
                // Esempio: goToOfferteRicevute(annuncio.getIdAnnuncio());

            } else {
                System.out.println("Azione annullata.");
            }

            System.out.println("------------------------------");
        }
    }
}

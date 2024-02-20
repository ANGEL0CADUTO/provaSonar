package cliview;

import bean.UtenteBean;
import controllerapplicativo.MieiAnnunciApplicativo;
import model.AnnuncioModel;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MieiAnnunciCLI {

    private UtenteBean utenteBean;

    public MieiAnnunciCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }

    public void initialize() {
        System.out.println("*************************************");
        System.out.println("Ci troviamo in HomePage/MIEI ANNUNCI:");
        MieiAnnunciApplicativo controller = new MieiAnnunciApplicativo();
        List<AnnuncioModel> array = controller.getMyAnnunci(utenteBean.getIdUtente());

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
                visualizzaOfferteRicevute(utenteBean, annuncio.getIdAnnuncio());
            } else {
                System.out.println("Azione annullata.");
            }

            System.out.println("------------------------------");
        }
    }

    public void visualizzaOfferteRicevute(UtenteBean utenteBean, int id){
        OfferteRicevuteCLI offerteRicevuteCLI = new OfferteRicevuteCLI(utenteBean,id);
        offerteRicevuteCLI.initialize();

    }
}

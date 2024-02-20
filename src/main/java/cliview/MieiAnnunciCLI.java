package cliview;

import bean.UtenteBean;
import controllerapplicativo.MieiAnnunciApplicativo;
import model.AnnuncioModel;
import utils.CLIPrinter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MieiAnnunciCLI {

    private UtenteBean utenteBean;

    public MieiAnnunciCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }

    public void initialize() {
        CLIPrinter.println("*************************************");
        CLIPrinter.println("Ci troviamo in HomePage/MIEI ANNUNCI:");
        MieiAnnunciApplicativo controller = new MieiAnnunciApplicativo();
        List<AnnuncioModel> array = controller.getMyAnnunci(utenteBean.getIdUtente());

        for (AnnuncioModel annuncio : array) {

            CLIPrinter.println("Nome Manga: " + annuncio.getNomeManga());
            CLIPrinter.println("Prezzo: " + annuncio.getPrezzo());
            CLIPrinter.println("Data Annuncio: " + annuncio.getDataAnnuncio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            CLIPrinter.println("Volume: " + annuncio.getVolume());

            // Chiedi all'utente se desidera navigare alle offerte ricevute
            CLIPrinter.println("Vuoi navigare alle offerte ricevute per questo annuncio? (y/n): ");
            Scanner scanner = new Scanner(System.in);
            String risposta = scanner.nextLine().toLowerCase();

            if (risposta.equals("y")) {
                CLIPrinter.println("Navigazione alle offerte ricevute per questo annuncio...");
                visualizzaOfferteRicevute(utenteBean, annuncio.getIdAnnuncio());
            } else {
                CLIPrinter.println("Azione annullata.");
            }

            CLIPrinter.println("------------------------------");
        }
    }

    public void visualizzaOfferteRicevute(UtenteBean utenteBean, int id){
        OfferteRicevuteCLI offerteRicevuteCLI = new OfferteRicevuteCLI(utenteBean, id);
        offerteRicevuteCLI.initialize();
    }
}

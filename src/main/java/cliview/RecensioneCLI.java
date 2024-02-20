package cliview;

import bean.UtenteBean;
import controllerapplicativo.RecensioniApplicativo;
import model.Recensione;
import utils.CLIPrinter;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RecensioneCLI {

    private UtenteBean utenteBean;

    public RecensioneCLI(UtenteBean utenteBean){
        this.utenteBean = utenteBean;
    }

    public void initialize() {
        CLIPrinter.println("*************************************");
        CLIPrinter.println("Ci troviamo in HomePage/Libreria/Miei Acquisti/RECENSIONE");

        RecensioniApplicativo controller = new RecensioniApplicativo();
        List<Recensione> array = controller.getMyRecensioniRicevute(utenteBean.getIdUtente());
        if(array.isEmpty()){
            CLIPrinter.println("////////////////////////");
            CLIPrinter.println("NON CI SONO RECENSIONI!");
            CLIPrinter.println("////////////////////////");
        }

        for (Recensione recensione : array) {
            CLIPrinter.println("Recensione per " + recensione.getTitoloRecensito() + " volume " + recensione.getVolumeRecensito() +
                    "\ncon VOTO: " + recensione.getVoto());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDataAcquisto = recensione.getDataAcquisto().format(formatter);

            CLIPrinter.println("Utente recensore: " + recensione.getUsernameRecensore() +
                    "\nPrezzo annuncio: " + recensione.getPrezzoIniziale() +
                    "\nPrezzo venduto: " + recensione.getPrezzoFinale() +
                    "\nData vendita: " + formattedDataAcquisto +
                    "\nDescrizione: " + recensione.getTesto());
            CLIPrinter.println("---------------");
        }
    }
}

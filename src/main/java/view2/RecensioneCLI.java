package view2;

import bean.UtenteBean;
import controllerapplicativo.RecensioniApplicativo;
import model.Recensione;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RecensioneCLI {

    private UtenteBean utenteBean;

    public RecensioneCLI(UtenteBean utenteBean){
        this.utenteBean = utenteBean;
    }
    public void initialize() {
        RecensioniApplicativo controller = new RecensioniApplicativo();
        ArrayList<Recensione> array = controller.getMyRecensioniRicevute(utenteBean.getIdUtente());

        for (Recensione recensione : array) {
            System.out.println("Recensione per " + recensione.getTitoloRecensito() + " volume " + recensione.getVolumeRecensito() +
                    "\ncon VOTO: " + recensione.getVoto());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDataAcquisto = recensione.getDataAcquisto().format(formatter);

            System.out.println("Utente recensore: " + recensione.getUsernameRecensore() +
                    "\nPrezzo annuncio: " + recensione.getPrezzoIniziale() +
                    "\nPrezzo venduto: " + recensione.getPrezzoFinale() +
                    "\nData vendita: " + formattedDataAcquisto +
                    "\nDescrizione: " + recensione.getTesto());
            System.out.println("---------------");
        }
    }
}
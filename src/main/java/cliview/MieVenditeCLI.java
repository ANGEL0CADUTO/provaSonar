package cliview;

import bean.UtenteBean;
import controllerapplicativo.MieVenditeControllerApplicativo;
import model.OffertaModel;
import utils.CLIPrinter;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MieVenditeCLI {
    private UtenteBean utenteBean;

    public MieVenditeCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }

    public void initialize() {
        CLIPrinter.println("*************************************");
        CLIPrinter.println("Ci troviamo in HomePage/Libreria/MIE VENDITE");
        MieVenditeControllerApplicativo controller = new MieVenditeControllerApplicativo();
        List<OffertaModel> arrayList = controller.getMyVendite(utenteBean.getIdUtente());

        for (OffertaModel o : arrayList) {
            CLIPrinter.println("Titolo Manga: " + o.getTitoloManga() + " Volume Manga: " + o.getVolumeManga());
            CLIPrinter.println("Username Offerente: " + o.getUsernameOfferente());
            CLIPrinter.println("Offerta Prezzo: " + o.getOffertaPrezzo());
            CLIPrinter.println("Data Offerta: " + o.getDataOfferta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            CLIPrinter.println("------------------------------");
        }
    }
}

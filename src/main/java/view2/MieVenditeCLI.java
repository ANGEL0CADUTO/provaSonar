package view2;

import bean.UtenteBean;
import controllerapplicativo.MieVenditeControllerApplicativo;
import model.OffertaModel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MieVenditeCLI {
    private UtenteBean utenteBean;
    public MieVenditeCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }


    public void initialize() {
        System.out.println("*************************************");
        System.out.println("Ci troviamo in HomePage/Libreria/MIE VENDITE");
        MieVenditeControllerApplicativo controller = new MieVenditeControllerApplicativo();
        List<OffertaModel> arrayList = controller.getMyVendite(utenteBean.getIdUtente());

        for (OffertaModel o : arrayList) {
            System.out.println("Titolo Manga: " + o.getTitoloManga() + " Volume Manga: " + o.getVolumeManga());
            System.out.println("Username Offerente: " + o.getUsernameOfferente());
            System.out.println("Offerta Prezzo: " + o.getOffertaPrezzo());
            System.out.println("Data Offerta: " + o.getDataOfferta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            System.out.println("------------------------------");
        }

    }




}

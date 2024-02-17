package view2;

import bean.UtenteBean;
import controllerapplicativo.MieVenditeControllerApplicativo;
import model.OffertaModel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MieVenditeCLI {
    private UtenteBean utenteBean;
    public MieVenditeCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }


    public void initialize() {
        MieVenditeControllerApplicativo controller = new MieVenditeControllerApplicativo();
        ArrayList<OffertaModel> arrayList = controller.getMyVendite(utenteBean.getIdUtente());

        for (OffertaModel o : arrayList) {
            System.out.println("Titolo Manga: " + o.getTitoloManga() + " Volume Manga: " + o.getVolumeManga());
            System.out.println("Username Offerente: " + o.getUsernameOfferente());
            System.out.println("Offerta Prezzo: " + o.getOffertaPrezzo());
            System.out.println("Data Offerta: " + o.getDataOfferta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            System.out.println("------------------------------");
        }

    }




}

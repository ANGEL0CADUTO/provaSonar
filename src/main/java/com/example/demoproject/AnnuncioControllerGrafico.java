package com.example.demoproject;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AnnuncioControllerGrafico {



    @FXML
    private Button homePageButton;

    @FXML
    private Button inserisciAnnuncio;
    private UtenteBean utenteBean;

    private CopiaMangaBean mangaBean;

    public void setUtenteBean(UtenteBean utenteBean) {
        this.utenteBean = utenteBean;
    }
    public void setCopiaMangaBean(CopiaMangaBean mangaBean) {this.mangaBean = mangaBean;}


public void userAnnunce(){
     int prezzo= 12;
    Date dataCorrente = new Date();
    SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dataFormattata = formatoData.format(dataCorrente);

    AnnuncioControllerApplicativo an = new AnnuncioControllerApplicativo();

  //POPOLA BEAN MANGA CHE POI PASSO ALL'APPLICATIVO
    CopiaMangaBean copiaMangaBean= new CopiaMangaBean();
    MangaDAO mangaDAO = new MangaDAO();
    CopiaMangaDAO copiaMangaDAO = new CopiaMangaDAO();
    UtenteModel utenteModel = new UtenteModel();
    utenteModel.setIdUtente(utenteBean.getIdUtente());
    //

    //OTTENGO LA LISTA DI COPIE MANGA PER L'UTENTE
    CopiaMangaCollectionModel copieManga = copiaMangaDAO.getCopieMangaListByUserID(utenteModel);

    CopiaMangaModel primaCopiaManga = copieManga.getListaManga().getFirst();
    copiaMangaBean.setIdManga(primaCopiaManga.getIdManga());




    System.out.println("AO " + utenteBean.getIdUtente());
    
    System.out.println("AOOO " + copiaMangaBean.getIdManga());

    boolean esitoAnnuncio = an.inserisciAnnuncio(copiaMangaBean,prezzo,dataFormattata);
    if (esitoAnnuncio) {
        System.out.println("INSERIMENTO ANNUNCIO ANDATO A BUON FINE");
    } else {
        System.out.println("L'ANNUNCIO TE LO TIRI IN FACCIA");
    }
}








    public void goToHomePage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) homePageButton.getScene().getWindow();
        stage.setScene(scene);
    }



}

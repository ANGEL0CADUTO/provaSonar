package com.example.demoproject.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demoproject.bean.CopiaMangaBean;
import com.example.demoproject.bean.UtenteBean;
import com.example.demoproject.controllerapplicativo.AnnuncioControllerApplicativo;
import com.example.demoproject.dao.CopiaMangaDAO;
import com.example.demoproject.model.CopiaMangaCollectionModel;
import com.example.demoproject.model.CopiaMangaModel;
import com.example.demoproject.model.UtenteModel;
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


public void userAnnunce(CopiaMangaBean copiaMangaBean){
     int prezzo= 12;
    Date dataCorrente = new Date();
    SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dataFormattata = formatoData.format(dataCorrente);

    AnnuncioControllerApplicativo an = new AnnuncioControllerApplicativo();

  //POPOLA BEAN MANGA CHE POI PASSO ALL'APPLICATIVO
   // CopiaMangaBean copiaMangaBean= new CopiaMangaBean();//

    CopiaMangaDAO copiaMangaDAO = new CopiaMangaDAO();

    System.out.println("MA POOO " + this.utenteBean.getIdUtente());

    UtenteModel utenteModel = new UtenteModel();
    utenteModel.setIdUtente(this.utenteBean.getIdUtente());
    //

    //OTTENGO LA LISTA DI COPIE MANGA PER L'UTENTE
    //CopiaMangaCollectionModel copieManga = copiaMangaDAO.getCopieMangaListByUserID(utenteModel);

   // CopiaMangaModel primaCopiaManga = copieManga.getListaManga().getFirst();//MI RIDA' IL PRIMO MANGA, LO DOVRO' CAMBIARE CON SCENEBUILDER
    //copiaMangaBean.setIdManga(primaCopiaManga.getIdManga());



    boolean esitoAnnuncio = an.inserisciAnnuncio(copiaMangaBean,prezzo,dataFormattata);
    if (esitoAnnuncio) {
        System.out.println("INSERIMENTO ANNUNCIO ANDATO A BUON FINE");
    } else {
        System.out.println("L'ANNUNCIO TE LO TIRI IN FACCIA");
    }
}








    public void goToHomePage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demoproject/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) homePageButton.getScene().getWindow();
        stage.setScene(scene);
    }



}

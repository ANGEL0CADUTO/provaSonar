package com.example.demoproject.view;

import java.math.BigDecimal;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AnnuncioControllerGrafico {



    @FXML
    private Button homePageButton;

    @FXML
    private Button inserisciAnnuncio;

    @FXML
    private TextField inserisciPrezzo;

    @FXML
    private Label wrongPrice;

    BigDecimal prezzo;
    private UtenteBean utenteBean;

    private CopiaMangaBean copiaMangaBean;

    public void setUtenteBean(UtenteBean utenteBean) {
        this.utenteBean = utenteBean;
    }
    public void setCopiaMangaBean(CopiaMangaBean copiamangaBean) {this.copiaMangaBean = copiamangaBean;}

public void userAnnunce(){

    if(inserisciPrezzo.getText().isEmpty()){wrongPrice.setText("Inserisci un prezzo");}
    else {
        try {
            prezzo = BigDecimal.valueOf(Integer.parseInt(String.valueOf(inserisciPrezzo)));
        } catch (NumberFormatException ex) {
            wrongPrice.setText("Inserisci un prezzo valido");
        }
    }



    Date dataCorrente = new Date();
    SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dataFormattata = formatoData.format(dataCorrente);
    AnnuncioControllerApplicativo an = new AnnuncioControllerApplicativo();

  //POPOLA BEAN MANGA CHE POI PASSO ALL'APPLICATIVO

    CopiaMangaDAO copiaMangaDAO = new CopiaMangaDAO();


    UtenteModel utenteModel = new UtenteModel();
    utenteModel.setIdUtente(utenteBean.getIdUtente());
    //


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

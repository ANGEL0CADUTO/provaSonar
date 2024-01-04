package com.example.demoproject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class LibreriaUtenteControllerGrafico  {

    private UtenteBean utenteBean;


    @FXML
    private TableView table;
    @FXML
    private TableColumn<CopiaMangaModel, String> idColumn;

    @FXML
    private TableColumn<CopiaMangaModel, String> provaColumn;
    @FXML
    private TableColumn<CopiaMangaModel, String> nomeColumn;

    @FXML
    private Button homePageButton;

    @FXML
    private Button depositaPreleva;


    public void setUtenteBean(UtenteBean bean) {
        this.utenteBean = bean;
        initializeData(); //Devo istanziarne subito la tabella per evitare problemi di sincronizzazione
    }
/*
    protected LibreriaUtenteControllerGrafico(UtenteBean bean){
        super(bean);
    }
*/
    @FXML
    private void initialize() {
        //L'ho svuotato perchè ho avuto problemi di sincronizzazione(popolare tabella prima ancora di aver preso i dati)
        //initializeData();
    }


    //Funzione per istanziare in anticipo le cose per evitare problemi di sincronizzazione(La chiamo nel grafico prima)
    public void initializeData() {
        LibreriaUtenteControllerApplicativo controller = new LibreriaUtenteControllerApplicativo();
        CopiaMangaCollectionModel collezione = controller.showUserManga(utenteBean);
        SimpleDateFormat myDateFormat = new SimpleDateFormat();
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdManga())));
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        provaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(myDateFormat.format(cellData.getValue().getDataAcquisto())));
        table.getItems().addAll(collezione.getListaManga());
    }


    public void goToHomePage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();

        Demo controller = loader.getController();
        controller.setUtenteBean(utenteBean);



        Scene scene = new Scene(root);
        Stage stage = (Stage) homePageButton.getScene().getWindow();
        stage.setScene(scene);

    }

    public void goToDeposit() throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("DepositaEPreleva.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) depositaPreleva.getScene().getWindow();
//        stage.setScene(scene);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DepositaEPreleva.fxml"));
        Parent root = loader.load();

         DepositaEPrelevaGrafico controller = loader.getController();//PER FARE IL CAST DEVO USARE TIPI COMPATIBILI
        controller.setUtenteBean(utenteBean);



        Scene scene = new Scene(root);
        Stage stage = (Stage) depositaPreleva.getScene().getWindow();
        stage.setScene(scene);

    }



}






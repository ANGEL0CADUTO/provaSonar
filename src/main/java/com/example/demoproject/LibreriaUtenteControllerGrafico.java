package com.example.demoproject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;

public class LibreriaUtenteControllerGrafico {

    private UtenteBean utenteBean;


    @FXML
    private TableView table;
    @FXML
    private TableColumn<CopiaMangaModel, String> idColumn;

    @FXML
    private TableColumn<CopiaMangaModel, String> provaColumn;
    @FXML
    private TableColumn<CopiaMangaModel, String> nomeColumn;

    public void setUtenteBean(UtenteBean bean){
        this.utenteBean = bean;
        initializeData(); //Devo istanziarne subito la tabella per evitare problemi di sincronizzazione
    }


    @FXML
    private void initialize(){
        //L'ho svuotato perchè ho avuto problemi di sincronizzazione(popolare tabella prima ancora di aver preso i dati)
        }


        //Funzione per istanziare in anticipo le cose per evitare problemi di sincronizzazione(La chiamo nel grafico prima)
    private void initializeData() {
        LibreriaUtenteControllerApplicativo controller = new LibreriaUtenteControllerApplicativo();
        CopiaMangaCollectionModel collezione = controller.showUserManga(utenteBean);
        SimpleDateFormat myDateFormat = new SimpleDateFormat();
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdManga())));
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        provaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(myDateFormat.format(cellData.getValue().getDataAcquisto())));
        table.getItems().addAll(collezione.getListaManga());
    }

}







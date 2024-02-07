package com.example.demoproject.view;

import com.example.demoproject.bean.UtenteBean;
import com.example.demoproject.controllerapplicativo.CompraMangaControllerApplicativo;
import com.example.demoproject.model.AnnunciModel;
import com.example.demoproject.model.CopiaMangaModel;
import com.example.demoproject.model.UtenteModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

public class CompraMangaControllerGrafico {


    @FXML
    private TableView tableCompra;
    @FXML
    private TableColumn<Object[], String> utenteColumn;

    @FXML
    private TableColumn<Object[], String> nomeMangaColumn;
    @FXML
    private TableColumn<Object[], String> prezzoColumn;

    //COLONNA PER IL TASTO AUTOCREATO
    @FXML
    private TableColumn<CopiaMangaModel, String> compraColumn;


    @FXML
    private Button homePageButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    private UtenteBean utenteBean;

    public void setUtenteBean(UtenteBean utenteBean) {
        this.utenteBean = utenteBean;
    }

    @FXML
    private void initialize() {
        InizializzaDati();
    }

    public void InizializzaDati(){
        CompraMangaControllerApplicativo controller = new CompraMangaControllerApplicativo();
        AnnunciModel annunciModel = controller.showAnnunce();
        // Imposta i valori delle celle nelle colonne
        utenteColumn.setCellValueFactory(data -> {
            int rowIndex = data.getTableView().getItems().indexOf(data.getValue());
            Object[] rowData = annunciModel.getListaDiAnnunci().get(rowIndex);
            return new SimpleStringProperty((String) rowData[0]);
        });

        nomeMangaColumn.setCellValueFactory(data -> {
            int rowIndex = data.getTableView().getItems().indexOf(data.getValue());
            Object[] rowData = annunciModel.getListaDiAnnunci().get(rowIndex);
            return new SimpleStringProperty((String) rowData[1]);
        });

        prezzoColumn.setCellValueFactory(data -> {
            int rowIndex = data.getTableView().getItems().indexOf(data.getValue());
            Object[] rowData = annunciModel.getListaDiAnnunci().get(rowIndex);
            return new SimpleStringProperty(String.valueOf(rowData[2]));
        });

        tableCompra.getItems().addAll(annunciModel.getListaDiAnnunci());
    }










    public void goToHomePage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/HomePage.fxml"));
        Parent root = loader.load();

        Demo controller = loader.getController();
        controller.setUtenteBean(utenteBean);




        Scene scene = new Scene(root);
        Stage stage = (Stage) homePageButton.getScene().getWindow();
        stage.setScene(scene);

    }
}

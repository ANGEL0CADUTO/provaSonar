package view;


import bean.UtenteBean;
import controllerapplicativo.OfferteRicevuteApplicativo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.OffertaRicevuta;
import utils.BottoneAccettaOfferta;


import java.time.format.DateTimeFormatter;

import java.util.List;

public class OfferteRicevuteGrafico extends UserGuiController{


    private int idAnnuncio;

    @FXML
    private TableView<OffertaRicevuta> offerteTable;
    @FXML
    private TableColumn<OffertaRicevuta, String> utenteColumn;
    @FXML
    private TableColumn<OffertaRicevuta,String> votoUtenteColumn;

    @FXML
    private TableColumn<OffertaRicevuta,String> prezzoColumn;
    @FXML
    private TableColumn<OffertaRicevuta, String> dataOffertaColumn;

    @FXML
    private TableColumn<OffertaRicevuta, String> buttonColumn;


    protected OfferteRicevuteGrafico(UtenteBean bean) {
        super(bean);
    }
    public OfferteRicevuteGrafico(UtenteBean bean, int idAnnuncio) {
        super(bean);  // Chiamata al costruttore della classe base con il parametro UtenteBean
        this.idAnnuncio = idAnnuncio;
    }


    public void initialize() {
        OfferteRicevuteApplicativo controller = new OfferteRicevuteApplicativo();
        List<OffertaRicevuta> array = controller.getOfferteRicevuteByAnnuncioID(this.idAnnuncio);
        ObservableList<OffertaRicevuta> data = FXCollections.observableArrayList(array);
        DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        utenteColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getUsernameOfferente()));
        votoUtenteColumn.setCellValueFactory(cellData -> {
            double voto = data.get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getVotoRecensioni();
            double votoTroncato = Math.floor(voto) + Math.ceil((voto - Math.floor(voto)) * 10) / 10;
            return new SimpleStringProperty(String.valueOf(votoTroncato));
        });
        prezzoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getOffertaPrezzo())));
        dataOffertaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(myDateTimeFormatter.format(cellData.getValue().getDataOfferta())));
        buttonColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(""));

        //DEVO CAPIRE BENE COME PASSARE I DATI
        buttonColumn.setCellFactory(cellData -> new BottoneAccettaOfferta(data, utenteBean, myAnchorPane));


        offerteTable.setItems(data);
    }

}


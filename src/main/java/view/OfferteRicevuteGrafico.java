package view;

import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.OfferteRicevuteApplicativo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.OffertaRicevuta;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.logging.Logger;

public class OfferteRicevuteGrafico extends UserGuiController{
    private static final Logger logger= Logger.getLogger(OfferteRicevuteGrafico.class.getName());


    private int idAnnuncio;

    @FXML
    private TableView offerteTable;
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
        buttonColumn.setCellFactory(cellData -> new TableCell<OffertaRicevuta, String>() {
            private int index;
            private final Button bottone = new Button("Accetta ");

            {
                // Gestisci l'evento di clic del bottone
                bottone.setOnAction(event -> {

                    // Ora puoi eseguire un'azione basata su questo elemento
                    try {
                        OffertaBean offertaBean = new OffertaBean();
                        offertaBean.setAnnuncioID(array.get(index).getAnnuncioID());
                        offertaBean.setCopiaMangaID(array.get(index).getCopiaMangaID());
                        offertaBean.setUtenteOfferenteID(array.get(index).getUtenteOfferenteID());
                        offertaBean.setIdOfferta(array.get(index).getIdOfferta());
                        offertaBean.setOffertaPrezzo(array.get(index).getOffertaPrezzo());
                        offertaBean.setTitoloManga(array.get(index).getTitoloManga());
                        offertaBean.setVolumeManga(array.get(index).getVolumeManga());

                        OfferteRicevuteApplicativo controllerApp = new OfferteRicevuteApplicativo();
                        controllerApp.accettaOffertaByOffertaID(offertaBean, utenteBean.getIdUtente());

                        goToMieiAnnunci();
                    } catch (IOException e) {
                        logger.severe("Errore nel cambio pagina " + e.getMessage());
                    }

                });
            }

            // Aggiungi un override per aggiornare il valore del pulsante quando la cella viene aggiornata
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(bottone);
                }
            }
        });

        offerteTable.setItems(data);
    }

    // ...
}


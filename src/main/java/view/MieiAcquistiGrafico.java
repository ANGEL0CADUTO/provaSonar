package view;

import bean.RecensioneBean;
import bean.UtenteBean;
import controllerapplicativo.MieiAcquistiApplicativo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.OffertaModel;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MieiAcquistiGrafico extends UserGuiController{

    @FXML
    private TableView<OffertaModel> mieiAcquistiTable;

    @FXML
    private TableColumn<OffertaModel, String> mangaColumn;

    @FXML
    private TableColumn<OffertaModel, String>  prezzoColumn;

    @FXML
    private TableColumn<OffertaModel, String>  dataColumn;

    @FXML
    private TableColumn<OffertaModel, String>  recensisciColumn;

    protected MieiAcquistiGrafico(UtenteBean bean) {
        super(bean);
    }

    public void initialize(){
        MieiAcquistiApplicativo controller = new MieiAcquistiApplicativo();
        ArrayList<OffertaModel> array = controller.getMyOfferteAccettate(utenteBean.getIdUtente());

        ObservableList<OffertaModel> data = FXCollections.observableArrayList(array);
        DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        mangaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitoloManga() + " " + cellData.getValue().getVolumeManga()));
        prezzoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getOffertaPrezzo())));
        dataColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(myDateTimeFormatter.format(cellData.getValue().getDataOfferta())));


        recensisciColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(""));

        //DEVO CAPIRE BENE COME PASSARE I DATI
        recensisciColumn.setCellFactory(cellData -> new TableCell<OffertaModel, String>() {
            private int index;
            private final Button bottone = new Button("Recensisci");

            {
                // Gestisci l'evento di clic del bottone
                bottone.setOnAction(event -> {
                    // Ora puoi eseguire un'azione basata su questo elemento
                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Recensione.fxml"));

                        RecensioneBean dati = new RecensioneBean();
                        dati.setIdOfferta(array.get(index).getIdOfferta());
                        dati.setRecensitoID(array.get(index).getUtenteVenditoreID());

                        loader.setControllerFactory(c -> new InviaRecensioneGrafico(utenteBean,dati));
                        Parent root = loader.load();

                        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            // Implementazione del metodo updateItem

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty && getTableRow() != null && getTableRow().getItem() != null) {
                    OffertaModel offerta = (OffertaModel) getTableRow().getItem();

                    // Aggiungi il bottone solo se il valore di Recensito è 0
                    if (offerta.getRecensito() == 0) {
                        setGraphic(bottone);
                    } else {
                        setGraphic(null);
                    }
                } else {
                    setGraphic(null);
                }
            }
        });



                mieiAcquistiTable.setItems(data);

            }
            }






package view;

import bean.UtenteBean;
import controllerapplicativo.MieiAnnunciApplicativo;
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
import model.AnnuncioModel;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.logging.Logger;


public class MieiAnnunciGrafico extends UserGuiController {
    private static final Logger logger= Logger.getLogger(MieiAnnunciGrafico.class.getName());


    @FXML
        private TableView<AnnuncioModel> mieiAnnunciTable;

        @FXML
        private TableColumn<AnnuncioModel, String> nomeMangaColumn;

        @FXML
        private TableColumn<AnnuncioModel, String> prezzoColumn;

        @FXML
        private TableColumn<AnnuncioModel, String> dataColumn;

        @FXML
        private TableColumn<AnnuncioModel, String> nOfferteColumn;

        @FXML
        private TableColumn<AnnuncioModel, String> offerteButtonColumn;
        @FXML
        private TableColumn<AnnuncioModel, String> volumeColumn;


        protected MieiAnnunciGrafico(UtenteBean bean) {
            super(bean);
        }


        public void initialize() {
            MieiAnnunciApplicativo controller = new MieiAnnunciApplicativo();
            List<AnnuncioModel> array = controller.getMyAnnunci(utenteBean.getIdUtente());



            // Popola la tabella con i dati dall'array
            ObservableList<AnnuncioModel> data = FXCollections.observableArrayList(array);
            DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            nomeMangaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNomeManga()));
            prezzoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getPrezzo())));
            dataColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(myDateTimeFormatter.format(cellData.getValue().getDataAnnuncio())));
            volumeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getVolume())));

            offerteButtonColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(""));

            //DEVO CAPIRE BENE COME PASSARE I DATI
            offerteButtonColumn.setCellFactory(cellData -> new TableCell<AnnuncioModel, String>() {
                private int index;
                private final Button bottone = new Button("Naviga ");

                {
                    // Gestisci l'evento di clic del bottone
                    bottone.setOnAction(event -> {

                        // Ora puoi eseguire un'azione basata su questo elemento
                        try {

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("OfferteRicevute.fxml"));
                            loader.setControllerFactory(c -> new OfferteRicevuteGrafico(utenteBean,array.get(index).getIdAnnuncio()));
                            Parent root = loader.load();
                            loader.getController();
                            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                        } catch (IOException e) {
                            logger.severe("Errore in MieiAnnunciGrafico nel cambio pagina " +e.getMessage());
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
                        index = getIndex();
                    }
                }
            });

            mieiAnnunciTable.setItems(data);
        }


    }


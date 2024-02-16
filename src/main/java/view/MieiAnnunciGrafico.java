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
import javafx.util.Callback;
import model.AnnuncioModel;
import model.CopiaMangaModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



    public class MieiAnnunciGrafico extends UserGuiController {

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
            ArrayList<AnnuncioModel> array = controller.getMyAnnunci(utenteBean.getIdUtente());

           /*
            for(AnnuncioModel a : array){
                NON SO SE IMPLEMENTARE QUESTA COSA DEL NUMERO DI OFFERTE RICEVUTE
            }

            */

            // Popola la tabella con i dati dall'array
            ObservableList<AnnuncioModel> data = FXCollections.observableArrayList(array);
            DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            nomeMangaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNomeManga()));
            prezzoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getPrezzo())));
            dataColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(myDateTimeFormatter.format(cellData.getValue().getDataAnnuncio())));
            volumeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getVolume())));

            //nOfferteColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getNOfferte())));
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


                            //goToOfferta(array.get(index).getIdAnnuncio());
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("OfferteRicevute.fxml"));
                            loader.setControllerFactory(c -> new OfferteRicevuteGrafico(utenteBean,array.get(index).getIdAnnuncio()));
                            Parent root = loader.load();
                            OfferteRicevuteGrafico controller = loader.getController();
                            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
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

        // ...
    }


package view;

import bean.UtenteBean;
import controllerapplicativo.MieiAnnunciApplicativo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import model.AnnuncioModel;
import utils.BottoneToOfferteRicevute;

import java.time.format.DateTimeFormatter;

import java.util.List;



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
                private BottoneToOfferteRicevute bottone;

                @Override
                protected void updateItem(String str, boolean empty) {
                    super.updateItem(str, empty);

                    if (empty || str == null) {
                        setGraphic(null);
                    } else {
                        if (bottone == null) {
                            bottone = new BottoneToOfferteRicevute(array, getIndex(), myAnchorPane, utenteBean);
                            bottone.setText("Naviga");
                        }
                        setGraphic(bottone);
                    }
                }
            });

            mieiAnnunciTable.setItems(data);
        }


    }


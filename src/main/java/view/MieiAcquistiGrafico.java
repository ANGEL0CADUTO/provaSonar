package view;


import bean.UtenteBean;
import controllerapplicativo.MieiAcquistiApplicativo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import model.OffertaModel;
import utils.RecensioneButtonTableCell;

import java.time.format.DateTimeFormatter;
import java.util.List;

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
    private TableColumn<OffertaModel, OffertaModel> recensisciColumn;

    @FXML
    private TableColumn<OffertaModel,String> volumeColumn;

    protected MieiAcquistiGrafico(UtenteBean bean) {
        super(bean);
    }

    public void initialize(){
        MieiAcquistiApplicativo controller = new MieiAcquistiApplicativo();
        List<OffertaModel> array = controller.getMyOfferteAccettate(utenteBean.getIdUtente());

        ObservableList<OffertaModel> data = FXCollections.observableArrayList(array);
        DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        mangaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitoloManga() ));
        volumeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getVolumeManga() )));
        prezzoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getOffertaPrezzo())));
        dataColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(myDateTimeFormatter.format(cellData.getValue().getDataOfferta())));


        recensisciColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue()));

        //DEVO CAPIRE BENE COME PASSARE I DATI
        recensisciColumn.setCellFactory(cellData -> new RecensioneButtonTableCell(data,utenteBean,myAnchorPane));

                mieiAcquistiTable.setItems(data);
            }
}






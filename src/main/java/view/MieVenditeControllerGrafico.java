package view;

import bean.UtenteBean;
import controllerapplicativo.MieVenditeControllerApplicativo;
import controllerapplicativo.MieiAcquistiApplicativo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.OffertaModel;
import model.OffertaRicevuta;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MieVenditeControllerGrafico extends UserGuiController {


    @FXML
    private TableView<OffertaModel> mieVenditeTable;

    @FXML
    private TableColumn<OffertaModel,String> mangaColumn;

    @FXML
    private TableColumn<OffertaModel,String>  prezzoColumn;

    @FXML
    private TableColumn<OffertaModel,String>  dataVenditaColumn;

    @FXML
    private TableColumn<OffertaModel,String>  utenteColumn;


    protected MieVenditeControllerGrafico(UtenteBean bean) {super(bean);}




@FXML
    public void initialize() {
        MieVenditeControllerApplicativo controller = new MieVenditeControllerApplicativo();
        ArrayList<OffertaModel> arrayList = controller.getMyVendite(utenteBean.getIdUtente());

        for(OffertaModel o : arrayList){

            System.out.printf("YAO " +o.getTitoloManga());
        }
        DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        mangaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitoloManga() + " " + cellData.getValue().getVolumeManga()));
        utenteColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getUsernameOfferente()));
        prezzoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getOffertaPrezzo())));
        dataVenditaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(myDateTimeFormatter.format(cellData.getValue().getDataOfferta())));

        mieVenditeTable.getItems().setAll(arrayList);


    }


}

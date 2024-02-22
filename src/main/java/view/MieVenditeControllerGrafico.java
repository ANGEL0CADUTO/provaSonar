package view;

import bean.UtenteBean;
import controllerapplicativo.MieVenditeControllerApplicativo;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.OffertaModel;

import java.time.format.DateTimeFormatter;

import java.util.List;


//CONTROLLORE GRAFICO PER L'INTERFACCIA GRAFICA RELATIVA AI MANGA VENDUTI DALL'UTENTE
public class MieVenditeControllerGrafico extends UserGuiController {


    @FXML
    private TableView<OffertaModel> mieVenditeTable;

    @FXML
    private TableColumn<OffertaModel, String> mangaColumn;

    @FXML
    private TableColumn<OffertaModel, String> prezzoColumn;

    @FXML
    private TableColumn<OffertaModel, String> dataVenditaColumn;

    @FXML
    private TableColumn<OffertaModel, String> utenteColumn;
    @FXML
    private TableColumn<OffertaModel, String> volumeColumn;


    protected MieVenditeControllerGrafico(UtenteBean bean) {
        super(bean);
    }


    @FXML
    public void initialize() {
        MieVenditeControllerApplicativo controller = new MieVenditeControllerApplicativo();
        List<OffertaModel> arrayList = controller.getMyVendite(utenteBean.getIdUtente());


        DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        mangaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitoloManga() ));
        volumeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getVolumeManga())));

        utenteColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getUsernameOfferente()));
        prezzoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getOffertaPrezzo())));
        dataVenditaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(myDateTimeFormatter.format(cellData.getValue().getDataOfferta())));

        mieVenditeTable.getItems().setAll(arrayList);


    }


}

package view;

import bean.UtenteBean;
import controllerapplicativo.CompraMangaControllerApplicativo;
import model.AnnunciModel;
import model.CopiaMangaModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CompraMangaControllerGrafico extends UserGuiController{


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
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    protected CompraMangaControllerGrafico(UtenteBean bean) {
        super(bean);
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











}

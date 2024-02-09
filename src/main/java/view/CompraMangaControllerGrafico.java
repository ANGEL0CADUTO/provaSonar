package view;

import bean.CopiaMangaBean;
import bean.UtenteBean;
import controllerapplicativo.CompraMangaControllerApplicativo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AnnunciModel;
import model.CopiaMangaModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import java.io.IOException;

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


        utenteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(annunciModel.getListaDiAnnunci().get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getNomeUtente())));
        nomeMangaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(annunciModel.getListaDiAnnunci().get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getNomeManga())));
        prezzoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(annunciModel.getListaDiAnnunci().get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getPrezzo())));

        compraColumn.setCellFactory(param -> new TableCell<CopiaMangaModel,String>() {
            private int index;
            private final Button bottone = new Button("Compra");

            {
                // Gestisci l'evento di clic del bottone
                bottone.setOnAction(event -> {

                    // Ora puoi eseguire un'azione basata su questo elemento


                    try {
                        goToOfferta(annunciModel.getListaDiAnnunci().get(index).getIdAnnuncio());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            @Override  //
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                // Verifica se la riga è vuota
                if (empty) {
                    setGraphic(null);
                } else { index=getIndex();
                    setGraphic(bottone);
                }
            }
        });


                tableCompra.getItems().addAll(annunciModel.getListaDiAnnunci());
    }











}

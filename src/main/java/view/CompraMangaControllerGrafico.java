package view;

import bean.CopiaMangaBean;
import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.CompraMangaControllerApplicativo;
import controllerapplicativo.OffertaControllerApplicativo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AnnunciModel;
import model.AnnuncioModel;
import model.CopiaMangaModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CompraMangaControllerGrafico extends UserGuiController{


    @FXML
    private TableView tableCompra;
    @FXML
    private TableColumn<Object[], String> utenteColumn;

    @FXML
    private TableColumn<Object[], String> nomeMangaColumn;
    @FXML
    private TableColumn<Object[], String> prezzoColumn;
    @FXML
    private TableColumn<Object[], String> volumeColumn;


    //COLONNA PER IL TASTO AUTOCREATO
    @FXML
    private TableColumn<CopiaMangaModel, String> compraColumn;
    @FXML
    private TableColumn<CopiaMangaModel, String> votoColumn;




    @FXML
    private TextField searchTextField;


    @FXML
    private Button offerta1;

    @FXML
    private Button searchButton;
    @FXML
    private ToolBar toolbar;

    @FXML
    private TextField offertaTextField;
    @FXML
    private Label wrongOfferta;

    private OffertaBean offertaBean;

    protected CompraMangaControllerGrafico(UtenteBean bean) {
        super(bean);
    }


    @FXML
    private void initialize() {
        InizializzaDati();
    }

    public void cercaPerNome(ActionEvent event) {
        // Richiama il metodo per inizializzare i dati
        InizializzaDati();
    }

    public void InizializzaDati(){

        CompraMangaControllerApplicativo controller = new CompraMangaControllerApplicativo();
        ArrayList<AnnuncioModel> arrayAnnunci= controller.showAnnunce(utenteBean.getIdUtente(),searchTextField.getText());
        tableCompra.getItems().clear();


        utenteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(arrayAnnunci.get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getNomeUtente())));
        nomeMangaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(arrayAnnunci.get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getNomeManga())));
        prezzoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(arrayAnnunci.get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getPrezzo())));
        votoColumn.setCellValueFactory(cellData -> {
            double voto = arrayAnnunci.get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getVotoUtente();
            double votoTroncato = Math.floor(voto) + Math.ceil((voto - Math.floor(voto)) * 10) / 10;
            return new SimpleStringProperty(String.valueOf(votoTroncato));
        });
        volumeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(arrayAnnunci.get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getVolume())));
        compraColumn.setCellFactory(param -> new TableCell<CopiaMangaModel,String>() {
            private int index;
            private final Button bottone = new Button("Compra");

            {
                // Gestisci l'evento di clic del bottone
                bottone.setOnAction(event -> {

                    // Ora puoi eseguire un'azione basata su questo elemento

                    toolbar.setVisible(true);
                    offertaBean = new OffertaBean();
                    offertaBean.setAnnuncioID(arrayAnnunci.get(index).getIdAnnuncio());
                    offertaBean.setCopiaMangaID(arrayAnnunci.get(index).getCopiaMangaID());


                        //goToOfferta();

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




                tableCompra.getItems().addAll(arrayAnnunci);
    }

    @FXML
    private void doOfferta(ActionEvent event) {
        wrongOfferta.setText("");


        /*if (offertaBean.getOffertaPrezzo()getText().isEmpty()) {
            wrongOfferta.setText("Fai un offerta");
        } else {
            try {
                prezzoOfferta = BigDecimal.valueOf(Integer.parseInt(String.valueOf(offertaTextField.getText())));
            } catch (NumberFormatException ex) {
                wrongOfferta.setText("Inserisci un offerta Valida");
            }*/



        BigDecimal prezzo = new BigDecimal(offertaTextField.getText());
        offertaBean.setOffertaPrezzo(prezzo);
        offertaBean.setUsernameOfferente(utenteBean.getUsername());

        offertaBean.setUtenteOfferenteID(utenteBean.getIdUtente());
        offertaBean.setDataOfferta(LocalDateTime.now());



        OffertaControllerApplicativo of = new OffertaControllerApplicativo();
        boolean esitoOfferta = of.doOfferta(offertaBean);
        if(!esitoOfferta){
            wrongOfferta.setText("Credito insufficiente");
        }
        toolbar.setVisible(false);
    }


}

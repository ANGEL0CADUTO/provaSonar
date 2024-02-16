package view;

import Pattern.OffertaFacade;
import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.MieiAnnunciApplicativo;
import controllerapplicativo.OfferteRicevuteApplicativo;
import javafx.beans.property.SimpleStringProperty;
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
import model.OffertaRicevuta;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class OfferteRicevuteGrafico extends UserGuiController{
    private static final Logger logger = Logger.getLogger(OfferteRicevuteGrafico.class.getName());


    private int idAnnuncio;

    @FXML
    private TableView offerteTable;
    @FXML
    private TableColumn<OffertaRicevuta, String> utenteColumn;
    @FXML
    private TableColumn<OffertaRicevuta,String> votoUtenteColumn;

    @FXML
    private TableColumn<OffertaRicevuta,String> prezzoColumn;
    @FXML
    private TableColumn<OffertaRicevuta, String> dataOffertaColumn;

    @FXML
    private TableColumn<OffertaRicevuta, String> buttonColumn;


    protected OfferteRicevuteGrafico(UtenteBean bean) {
        super(bean);
    }
    public OfferteRicevuteGrafico(UtenteBean bean, int idAnnuncio) {
        super(bean);  // Chiamata al costruttore della classe base con il parametro UtenteBean
        this.idAnnuncio = idAnnuncio;
    }


    public void initialize() {
        OfferteRicevuteApplicativo controller = new OfferteRicevuteApplicativo();
        ArrayList<OffertaRicevuta> array = controller.getOfferteRicevuteByAnnuncioID(this.idAnnuncio);
           /*
            for(AnnuncioModel a : array){
                NON SO SE IMPLEMENTARE QUESTA COSA DEL NUMERO DI OFFERTE RICEVUTE
            }

            */

        // Popola la tabella con i dati dall'array
        ObservableList<OffertaRicevuta> data = FXCollections.observableArrayList(array);
        DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        utenteColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getUsernameOfferente()));
        votoUtenteColumn.setCellValueFactory(cellData -> {
            double voto = data.get(cellData.getTableView().getItems().indexOf(cellData.getValue())).getVotoRecensioni();
            double votoTroncato = Math.floor(voto) + Math.ceil((voto - Math.floor(voto)) * 10) / 10;
            return new SimpleStringProperty(String.valueOf(votoTroncato));
        });
        prezzoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getOffertaPrezzo())));
        dataOffertaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(myDateTimeFormatter.format(cellData.getValue().getDataOfferta())));
        buttonColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(""));

        //DEVO CAPIRE BENE COME PASSARE I DATI
        buttonColumn.setCellFactory(cellData -> new TableCell<OffertaRicevuta, String>() {
            private int index;
            private final Button bottone = new Button("Accetta ");

            {
                // Gestisci l'evento di clic del bottone
                bottone.setOnAction(event -> {

                    // Ora puoi eseguire un'azione basata su questo elemento
                    try {
                        System.out.println("LOGGATO :" +utenteBean.getEmail() + " Offerente"+ array.get(index).getUsernameOfferente());
                        OffertaBean offertaBean = new OffertaBean();
                        offertaBean.setAnnuncioID(array.get(index).getAnnuncioID());
                        offertaBean.setUtenteOfferenteID(array.get(index).getUtenteOfferenteID());
                        offertaBean.setIdOfferta(array.get(index).getIdOfferta());
                        offertaBean.setOffertaPrezzo(array.get(index).getOffertaPrezzo());
                        System.out.println("IL PREZZO è :" + array.get(index).getOffertaPrezzo());

                        OffertaFacade facade = new OffertaFacade();
                        boolean b = facade.accettaOffertaByOffertaID(offertaBean,utenteBean.getIdUtente());

                        if(b){
                            System.out.println("ACCETTATA CON SUCCESSO!");
                        }
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MieiAnnunci.fxml"));
                        loader.setControllerFactory(c -> new Demo(utenteBean));
                        Parent root = loader.load();
                        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                    } catch (IOException e) {
                        logger.severe("Errore nel cambio pagina");
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
                }
            }
        });

        offerteTable.setItems(data);
    }

    // ...
}


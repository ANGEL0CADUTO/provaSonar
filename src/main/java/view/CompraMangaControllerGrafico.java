package view;

import bean.AnnuncioBean;
import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.CompraMangaControllerApplicativo;
import controllerapplicativo.OffertaControllerApplicativo;
import exceptions.CreditoInsufficienteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.google.gson.*;
import model.AnnuncioModel;
import model.CopiaMangaModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import model.OffertaModel;
import model.OfferteModel;
import pattern.observer.OffertaObserver;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CompraMangaControllerGrafico extends UserGuiController implements OffertaObserver {


    @FXML
    private TableView<AnnuncioModel> tableCompra;
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

    private OffertaModel offertaModel ;

    private  String nomeManga;
    private  int volume;
    private String nomeVenditore;

    private OfferteModel offerteList;
    private List<OffertaModel> offertaList = new ArrayList<>();





    public CompraMangaControllerGrafico(UtenteBean bean) {
        super(bean);
    }



    @FXML
    private void initialize() {
        inizializzaDati();
    }

    public void cercaPerNome(ActionEvent event) {
        // Richiama il metodo per inizializzare i dati
        inizializzaDati();
    }

    public void inizializzaDati(){

        CompraMangaControllerApplicativo controller = new CompraMangaControllerApplicativo();
        List<AnnuncioModel> arrayAnnunci= controller.showAnnunce(utenteBean.getIdUtente(),searchTextField.getText());



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
            private int index ;
            private final Button bottone = new Button("Compra");

            {
                // Gestisci l'evento di clic del bottone
                bottone.setOnAction(event -> {

                    // Ora puoi eseguire un'azione basata su questo elemento

                    toolbar.setVisible(true);
                    offertaBean = new OffertaBean();
                    offertaBean.setIdAnnuncio(arrayAnnunci.get(index).getIdAnnuncio());
                    offertaBean.setCopiaMangaID(arrayAnnunci.get(index).getCopiaMangaID());


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
    private void doOfferta(ActionEvent event) throws IOException {
        wrongOfferta.setText("");
        BigDecimal prezzo = new BigDecimal(offertaTextField.getText());
        offertaBean.setOffertaPrezzo(prezzo);
        offertaBean.setUsernameOfferente(utenteBean.getUsername());

        offertaBean.setUtenteOfferenteID(utenteBean.getIdUtente());
        offertaBean.setDataOfferta(LocalDateTime.now());

        /*OBSERVER*/
        offerteList = OfferteModel.getInstance();
        offerteList.aggiungiObserver(this);

        OffertaControllerApplicativo of = new OffertaControllerApplicativo();
        boolean esitoOfferta = false;
        try {
            esitoOfferta = of.doOfferta(offertaBean);
        } catch (CreditoInsufficienteException e) {
            wrongOfferta.setText(e.getMessage());
        }
        if(esitoOfferta){






                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Notifiche.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();


        }
        toolbar.setVisible(false);
    }


    @Override
    public void update() {
        String notifica = creaNotifica();
        saveToJsonFile(notifica, "NotificaFile.json");
    }

    private String creaNotifica() {
        List<OffertaModel> offerteModelList = new ArrayList<>();
        offerteModelList = offerteList.getState();
        OffertaControllerApplicativo offertaApplicativo = new OffertaControllerApplicativo();
        AnnuncioBean annuncioBean = offertaApplicativo.annuncioByOffertaID(offertaBean);

        Map<String, Object> notificaMap = new HashMap<>();
        Map<String, Object> notifica = new HashMap<>();
        for (OffertaModel o :offerteModelList){


            notifica.put("utente", o.getUsernameOfferente());
            notifica.put("manga", annuncioBean.getNomeManga());
            notifica.put("volume", annuncioBean.getVolume());
            notifica.put("prezzo_offerta", o.getOffertaPrezzo());
            notifica.put("venditore", annuncioBean.getNomeUtente());
            notificaMap.put("notifica", notifica);

        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(notificaMap);

    }

    private void saveToJsonFile(String notifica, String filePath) {

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(notifica);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del file JSON: " + e.getMessage());
            e.printStackTrace();
        }

    }


    }





package view;

import bean.CopiaMangaBean;
import bean.UtenteBean;
import controllerapplicativo.LibreriaUtenteControllerApplicativo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CopiaMangaModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;
import utils.AnnuncioButtonTableCell;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.logging.Logger;


//CONTROLLORE GRAFICO PER L'INTERFACCIA GRAFICA RELATIVA ALLA LIBRERIA DELL'UTENTE, VISUALIZZATI IN UNA TABELLA
public class LibreriaUtenteControllerGrafico extends UserGuiController  {

    private static final Logger logger= Logger.getLogger(LibreriaUtenteControllerGrafico.class.getName());

    @FXML
    private TableView<CopiaMangaModel> table;
    @FXML
    private TableColumn<CopiaMangaModel, String> idColumn;

    @FXML
    private TableColumn<CopiaMangaModel, String> provaColumn;
    @FXML
    private TableColumn<CopiaMangaModel, String> nomeColumn;

    //COLONNA PER IL TASTO AUTOCREATO
    @FXML
    private TableColumn<CopiaMangaModel, String> annuncioColumn;
    @FXML
    private TableColumn<CopiaMangaModel, String> volumeColumn;

    @FXML
    private Button aggiungiButton;
    @FXML
    private Button confermaButton;
    @FXML
    private Label titoloLabel;
    @FXML
    private Label volumeLabel;
    @FXML
    private Label wrongLabel;
    @FXML
    private TextField titoloTextField;
    @FXML
    private TextField volumeTextField;

    @FXML
    private Button acquistiButton;
    @FXML
    private Button vendutiButton;





    @FXML
    private Button cercaManga;

    @FXML
    private Button depositaPreleva;
    private boolean isUpdatingTableView = true;


    private ObservableList<CopiaMangaModel> observableList;

   protected LibreriaUtenteControllerGrafico(UtenteBean utente){
       super(utente);
   }





    @FXML
    private void initialize() {

        inizializzaDati();
    }

    public void goToMieVendite() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MieVendite.fxml"));
            loader.setControllerFactory(c -> new MieVenditeControllerGrafico(utenteBean));

            Parent root = null;
            root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            logger.severe("Errore nel cambio pagina in LibreriaUtenteControllerGrafico " + e.getMessage());
        }
    }

    //Funzione per istanziare in anticipo le cose per evitare problemi di sincronizzazione(La chiamo nel grafico prima)
    public void inizializzaDati() {
        nascondiElementiGrafici();
        inizializzaTabella();
        aggiungiListener();
        inizializzaColonneTabella();
        inizializzaColonnaAzioni();
    }

    private void nascondiElementiGrafici() {
        titoloTextField.setVisible(false);
        volumeTextField.setVisible(false);
        volumeLabel.setVisible(false);
        titoloLabel.setVisible(false);
        confermaButton.setVisible(false);
    }

    private void inizializzaTabella() {

        LibreriaUtenteControllerApplicativo controller = new LibreriaUtenteControllerApplicativo();
        List<CopiaMangaModel> collezione = controller.showUserManga(utenteBean);
        observableList = FXCollections.observableList(collezione);
        table.setItems(observableList);
    }

    private void aggiungiListener() {
        observableList.addListener((ListChangeListener<CopiaMangaModel>) change -> {
            while (change.next()) {
                if (change.wasAdded() && isUpdatingTableView) {
                    isUpdatingTableView = false;
                    isUpdatingTableView = true;
                } else if (change.wasRemoved() && isUpdatingTableView) {
                    logger.info("Elementi rimossi: " + change.getRemoved());
                    isUpdatingTableView = false;
                    table.getItems().removeAll(change.getRemoved());
                    isUpdatingTableView = true;
                }
            }
        });
    }

    private void inizializzaColonneTabella() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
        volumeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVolume())));
        provaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataAcquisto().format(formatter)));
    }

    private void inizializzaColonnaAzioni() {
        annuncioColumn.setCellFactory(param -> new AnnuncioButtonTableCell(utenteBean, myAnchorPane));

    }



    public void setVisible(){
        titoloTextField.setVisible(true);
        volumeTextField.setVisible(true);
        volumeLabel.setVisible(true);
        titoloLabel.setVisible(true);
        confermaButton.setVisible(true);
    }

    public void aggiungiManga(){

        String titolo = titoloTextField.getText();
        String volumeText = volumeTextField.getText();
        int volumeNumero = 0;

        // Controllo che il campo titolo non sia vuoto
        if (titoloTextField.getText().isEmpty() || volumeTextField.getText().isEmpty()) {
            logger.info("Il campo titolo non può essere vuoto.");
            return;
        }


        try {
            volumeNumero = Integer.parseInt(volumeText);
            if(volumeNumero <= 0){
                wrongLabel.setText("Inserire i campi correttamente");
                return;
            }
        } catch (NumberFormatException e) {
            wrongLabel.setText("Inserire i campi correttamente");
        }
        CopiaMangaBean copiaBean = new CopiaMangaBean();
        copiaBean.setIdUtente(utenteBean.getIdUtente());
        copiaBean.setTitolo(titolo);
        copiaBean.setVolume(volumeNumero);
        LocalDateTime now = LocalDateTime.now();
        copiaBean.setDataAcquisto(now);


        LibreriaUtenteControllerApplicativo controllerApp = new LibreriaUtenteControllerApplicativo();
        int chiave = controllerApp.aggiungiManga(copiaBean);
        CopiaMangaModel model = new CopiaMangaModel();
        model.setIdCopiaManga(chiave);
        model.setIdUtente(copiaBean.getIdUtente());
        model.setTitolo(copiaBean.getTitolo());
        model.setVolume(copiaBean.getVolume());
        model.setDataAcquisto(now);
        model.setStatoCopiaManga(1);

        observableList.add(model);


        titoloTextField.setVisible(false);
        volumeTextField.setVisible(false);
        volumeLabel.setVisible(false);
        titoloLabel.setVisible(false);
        confermaButton.setVisible(false);

        

    }

    public void mostraAcquisti(){
       //Da implementare




    }


}






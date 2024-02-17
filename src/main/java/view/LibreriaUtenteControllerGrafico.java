package view;

import bean.CopiaMangaBean;
import bean.UtenteBean;
import controllerapplicativo.LibreriaUtenteControllerApplicativo;
import dao.AnnuncioDAO;
import javafx.beans.Observable;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class LibreriaUtenteControllerGrafico extends UserGuiController  {



    private CopiaMangaBean copiaMangaBean;//MANGA


    @FXML
    private TableView table;
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
    private static final Logger logger = Logger.getLogger(LibreriaUtenteControllerGrafico.class.getName());

    private ObservableList<CopiaMangaModel> observableList;

    /* public void setCopiaMangaBean(CopiaMangaBean mangaBean) {
        this.copiaMangaBean = mangaBean;
    }*///NON CREDO MI SERVA
   protected LibreriaUtenteControllerGrafico(UtenteBean utente){
       super(utente);
   }





    @FXML
    private void initialize() {
        System.out.println(utenteBean);
        //L'ho svuotato perchè ho avuto problemi di sincronizzazione(popolare tabella prima ancora di aver preso i dati)
        initializeData();
    }

    public void goToMieVendite() {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MieVendite.fxml"));
        loader.setControllerFactory(c -> new MieVenditeControllerGrafico(utenteBean));

        Parent root = null;

            root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
        stage.setScene(scene);}
        catch (IOException e) {
            throw new RuntimeException(e);}
    }

    //Funzione per istanziare in anticipo le cose per evitare problemi di sincronizzazione(La chiamo nel grafico prima)
    public void initializeData() {
        titoloTextField.setVisible(false);
        volumeTextField.setVisible(false);
        volumeLabel.setVisible(false);
        titoloLabel.setVisible(false);
        confermaButton.setVisible(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LibreriaUtenteControllerApplicativo controller = new LibreriaUtenteControllerApplicativo();

        ArrayList<CopiaMangaModel> collezione = controller.showUserManga(utenteBean);
        observableList = FXCollections.observableList(collezione);
        table.setItems(observableList);

        // Aggiunta di un listener di modifica al ObservableList
        observableList.addListener((ListChangeListener<CopiaMangaModel>) change -> {
            while (change.next()) {
                if (change.wasAdded() && isUpdatingTableView) { ///// non accade mai #########
                    logger.info("Elementi aggiunti: "+ change.getAddedSubList());
                    isUpdatingTableView = false;
                    //table.getItems().addAll(change.getAddedSubList());
                    isUpdatingTableView = true;
                    System.out.println("CIAOCIAOCIAO");
                } else if (change.wasRemoved() && isUpdatingTableView) {
                    logger.info("Elementi rimossi: " +change.getRemoved());
                    isUpdatingTableView = false;
                    table.getItems().removeAll(change.getRemoved());
                    isUpdatingTableView = true;
                }
            }
        });
        SimpleDateFormat myDateFormat = new SimpleDateFormat();

        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdCopiaManga())));
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
        volumeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVolume())));
        provaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataAcquisto().format(formatter)));
       //COLONNA AZIONI CON BOTTONE

        System.out.println("LibreriaUtenteControllerGrafico : "+ utenteBean.getIdUtente() + " " + utenteBean.getUsername()+ " " + utenteBean.getPassword()+ " " + utenteBean.getEmail()+ " " + utenteBean.getCredito());

        annuncioColumn.setCellFactory(param -> new TableCell<CopiaMangaModel,String>() {
            private final Label label = new Label("In vendita");
            private final Button bottone = new Button("Vendi");

            {
                // Gestisci l'evento di clic del bottone
                bottone.setOnAction(event -> {
                  CopiaMangaModel copiaMangaModel = getTableView().getItems().get(getIndex());

                  //DA QUI IN GIU STO CORREGENDO(quello sopra da controllare)
                  CopiaMangaBean copiaMangaBean = new CopiaMangaBean();
                  copiaMangaBean.setIdCopiaManga(copiaMangaModel.getIdCopiaManga());
                  copiaMangaBean.setTitolo(copiaMangaModel.getTitolo());
                  copiaMangaBean.setVolume(copiaMangaModel.getVolume());
                  copiaMangaBean.setIdUtente(utenteBean.getIdUtente());

                    System.out.println("METTI IN VENDITA " + copiaMangaBean.getTitolo() + " " + copiaMangaBean.getIdUtente());



                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("InserisciAnnuncio.fxml"));
                        loader.setControllerFactory(c -> new AnnuncioControllerGrafico(utenteBean,copiaMangaBean));

                        Parent root = loader.load();

                        Scene scene = new Scene(root);
                        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
                        stage.setScene(scene);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
            }

            @Override  //
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    // Verifica se lo statoCopiaManga è 1, altrimenti non mostrare il bottone
                    if (getTableView().getItems().get(getIndex()).getStatoCopiaManga() == 1) {
                        setGraphic(bottone);
                    } else {
                        setGraphic(label);
                    }
                }
            }
        });


        //table.getItems().addAll(collezione);
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
            System.out.println("Il campo titolo non può essere vuoto.");
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




    }


}






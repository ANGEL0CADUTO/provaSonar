package com.example.demoproject.view;

import com.example.demoproject.bean.CopiaMangaBean;
import com.example.demoproject.bean.UtenteBean;
import com.example.demoproject.controllerapplicativo.LibreriaUtenteControllerApplicativo;
import com.example.demoproject.model.CopiaMangaCollectionModel;
import com.example.demoproject.model.CopiaMangaModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;

public class LibreriaUtenteControllerGrafico extends BaseController  {



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
    private Button cercaManga;

    @FXML
    private Button depositaPreleva;

   /* public void setCopiaMangaBean(CopiaMangaBean mangaBean) {
        this.copiaMangaBean = mangaBean;
    }*///NON CREDO MI SERVA
   protected LibreriaUtenteControllerGrafico(UtenteBean utente){
       super(utente);
   }

/*
    public void setUtenteBean(UtenteBean bean) {
        this.utenteBean = bean;
        initializeData(); //Devo istanziarne subito la tabella per evitare problemi di sincronizzazione
    }
*/


    @FXML
    private void initialize() {
        System.out.println(utenteBean);
        //L'ho svuotato perchè ho avuto problemi di sincronizzazione(popolare tabella prima ancora di aver preso i dati)
        initializeData();
    }


    //Funzione per istanziare in anticipo le cose per evitare problemi di sincronizzazione(La chiamo nel grafico prima)
    public void initializeData() {
        LibreriaUtenteControllerApplicativo controller = new LibreriaUtenteControllerApplicativo();
        CopiaMangaCollectionModel collezione = controller.showUserManga(utenteBean);
        SimpleDateFormat myDateFormat = new SimpleDateFormat();
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdManga())));
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        provaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(myDateFormat.format(cellData.getValue().getDataAcquisto())));
       //COLONNA AZIONI CON BOTTONE

        System.out.println("LibreriaUtenteControllerGrafico : "+ utenteBean.getIdUtente() + " " + utenteBean.getUsername()+ " " + utenteBean.getPassword()+ " " + utenteBean.getEmail()+ " " + utenteBean.getCredito());

        annuncioColumn.setCellFactory(param -> new TableCell<CopiaMangaModel,String>() {
            private final Button bottone = new Button("Vendi");

            {
                // Gestisci l'evento di clic del bottone
                bottone.setOnAction(event -> {
                  CopiaMangaModel copiaMangaModel = getTableView().getItems().get(getIndex());
                  CopiaMangaBean copiaMangaBean1 = new CopiaMangaBean();
                  copiaMangaBean1.setIdManga(copiaMangaModel.getIdManga());

                    System.out.println("METTI IN VENDITA " +copiaMangaBean1.getIdManga());



                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/InserisciAnnuncio.fxml"));
                        Parent root = loader.load();

                        AnnuncioControllerGrafico controller = loader.getController();
                        controller.setUtenteBean(utenteBean);
                        controller.setCopiaMangaBean(copiaMangaBean1);



                        Scene scene = new Scene(root);
                        Stage stage = (Stage) bottone.getScene().getWindow();
                        stage.setScene(scene);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                   /* AnnuncioControllerGrafico an = new AnnuncioControllerGrafico();
                    an.userAnnunce();//copiaMangaBean1 lo dovrò passare qua*/
                });
            }

            @Override  //
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                // Verifica se la riga è vuota
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(bottone);
                }
            }
        });


        table.getItems().addAll(collezione.getListaManga());
    }

/*
    public void goToHomePage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/HomePage.fxml"));
        Parent root = loader.load();

        Demo controller = loader.getController();
        controller.setUtenteBean(utenteBean);




        Scene scene = new Scene(root);
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.setScene(scene);

    }*/

    public void goToDeposit() throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("DepositaEPreleva.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) depositaPreleva.getScene().getWindow();
//        stage.setScene(scene);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/DepositaEPreleva.fxml"));
        loader.setControllerFactory(c-> new DepositaEPrelevaGrafico(utenteBean));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
        stage.setScene(scene);





    }

    //goToAnnuncio LO FACCIO DIRETTAMENTE DAL TASTO NELLA TABELLA DELLA LIBRERIA
    /*public void goToAnnuncio() throws IOException {



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/InserisciAnnuncio.fxml"));
        Parent root = loader.load();

        AnnuncioControllerGrafico controller = loader.getController();//PER FARE IL CAST DEVO USARE TIPI COMPATIBILI
        controller.setUtenteBean(utenteBean);
        controller.setCopiaMangaBean(copiaMangaBean);//FORSE DA LEVARE



        Scene scene = new Scene(root);
        Stage stage = (Stage) annuncio.getScene().getWindow();
        stage.setScene(scene);

    }*/

    public void goToCompra() throws IOException {//DA FARE!!!



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/Compra.fxml"));
        Parent root = loader.load();

        CompraMangaControllerGrafico controller = loader.getController();
        controller.setUtenteBean(utenteBean);
       // controller.setCopiaMangaBean(copiaMangaBean);VEDIAMO SE MI SERVE



        Scene scene = new Scene(root);
        Stage stage = (Stage)cercaManga.getScene().getWindow();
        stage.setScene(scene);

    }



}






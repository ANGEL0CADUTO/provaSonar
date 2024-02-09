package view;

import bean.DatiUtenteBean;
import controllerapplicativo.RegistraApplicativo;
import bean.UtenteBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistraGrafico {

    @FXML
    private Button registra;

    @FXML
    private Button home;

    @FXML
    private TextField enteredEmail;

    @FXML
    private TextField enteredUsername;

    @FXML
    private TextField enteredIndirizzo;

    @FXML
    private TextField enteredCivico;

    @FXML
    private TextField enteredCap;

    @FXML
    private PasswordField enteredPassword;

    @FXML
    private Label wrongSignUp;
    @FXML
    private AnchorPane myAnchorPane;



    public void goToHomePage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demoproject/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) home.getScene().getWindow();
        stage.setScene(scene);
    }



    public void userRegistra() throws IOException {
        UtenteBean bean = new UtenteBean();
        DatiUtenteBean bean1 = new DatiUtenteBean();
        RegistraApplicativo ra = new RegistraApplicativo();

        bean.setEmail(enteredEmail.getText());
        bean.setPassword(enteredPassword.getText());
        bean.setUsername(enteredUsername.getText());

        bean1.setIndirizzo(enteredIndirizzo.getText());
        bean1.setCap(Integer.parseInt(enteredCivico.getText()));
        bean1.setCivico(Integer.parseInt(enteredCap.getText()));



    //PROBEMA: SE INSERISCO I DATI E NON I DATIUTENTE UNO LO SALVA LO STESSO (CREDO DI AVERLO GIA RIPARATO RICONTROLLARE)
        boolean esitoRegistra = ra.registra(bean);
        boolean esitoRegistraDati= ra.registraDati(bean1);
        ra.informazioniUtente(bean);
        //CONTROLLO SU EMAIl-PASSWORS-USERNAME-INDIRIZZO-CAP-CIVICO HA INSERITO CAMPI NON VOUTI FUNGE
        if(esitoRegistra && esitoRegistraDati){
            wrongSignUp.setText("Hai effettuato la registrazione!");

            // CAMBIO SCENA E VADO ALLA LIBRERIA UTENTE SOLO DOPO AVER EFFETTUATO LA REGISTRAZIONE

            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            loader.setControllerFactory(c -> new CompraMangaControllerGrafico(bean));
            Parent root = loader.load();
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        else{wrongSignUp.setText("Devi inserire tutti i campi");}
    }


}
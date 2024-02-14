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

public class RegistraGrafico extends BaseController{

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

    protected RegistraGrafico(UtenteBean bean) {
        super(bean);
    }


    public void userRegistra() throws IOException {
        UtenteBean bean = new UtenteBean();
        DatiUtenteBean bean1 = new DatiUtenteBean();
        RegistraApplicativo ra = new RegistraApplicativo();

        bean.setEmail(enteredEmail.getText());
        bean.setPassword(enteredPassword.getText());
        bean.setUsername(enteredUsername.getText());
        bean1.setIndirizzo(enteredIndirizzo.getText());
        bean1.setCap(enteredCivico.getText());
        bean1.setCivico(enteredCap.getText());



    //PROBEMA: SE INSERISCO I DATI E NON I DATIUTENTE UNO LO SALVA LO STESSO (CREDO DI AVERLO GIA RIPARATO RICONTROLLARE)
        boolean esitoRegistra = ra.registra(bean);
        int esitoRegistraDati= ra.registraDati(bean1);
        ra.informazioniUtente(bean);
        //CONTROLLO SU EMAIl-PASSWORS-USERNAME-INDIRIZZO-CAP-CIVICO HA INSERITO CAMPI NON VOUTI FUNGE
        if(esitoRegistra && esitoRegistraDati != -1){
            wrongSignUp.setText("Hai effettuato la registrazione!");

            // CAMBIO SCENA E VADO ALLA LIBRERIA UTENTE SOLO DOPO AVER EFFETTUATO LA REGISTRAZIONE


            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            loader.setControllerFactory(c-> new Demo(utenteBean));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) registra.getScene().getWindow();
            stage.setScene(scene);
        }
        else{wrongSignUp.setText("Devi inserire tutti i campi");}
    }


}

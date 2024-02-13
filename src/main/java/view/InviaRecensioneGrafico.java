package view;

import bean.RecensioneBean;
import bean.UtenteBean;
import controllerapplicativo.InviaRecensioneApplicativo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class InviaRecensioneGrafico extends UserGuiController {

    @FXML
    private Button inviaRecensione;

    @FXML
    private ComboBox<Integer> votoComboBox;  // Assicurati di avere il tipo corretto per la ComboBox

    @FXML
    private TextArea descrizioneTextArea;

    private RecensioneBean datiRecensione;

    protected InviaRecensioneGrafico(UtenteBean bean, RecensioneBean datiRecensione) {
        super(bean);
        this.datiRecensione = datiRecensione;
    }

    @FXML
    public void initialize() {
        // Inizializza la ComboBox con i valori desiderati (da 1 a 5)
        votoComboBox.getItems().addAll(1, 2, 3, 4, 5);
        // Puoi impostare un valore predefinito se necessario
        votoComboBox.setValue(1);
    }

    @FXML
    public void inviaRecensione() {
        datiRecensione.setVotoRecensione(votoComboBox.getValue());
        datiRecensione.setTesto(descrizioneTextArea.getText());

        // Utilizza votoSelezionato e descrizione come necessario
        InviaRecensioneApplicativo controllerApp = new InviaRecensioneApplicativo();
        controllerApp.inviaRecensione(datiRecensione, utenteBean.getUsername());
    }
}

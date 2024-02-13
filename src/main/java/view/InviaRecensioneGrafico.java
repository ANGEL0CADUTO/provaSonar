package view;

import bean.RecensioneBean;
import bean.UtenteBean;
import controllerapplicativo.InviaRecensioneApplicativo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class InviaRecensioneGrafico extends UserGuiController{
    @FXML
    private Button inviaRecensione;
    @FXML
    private TextArea descrizioneTextArea;

    private RecensioneBean datiRecensione;
    protected InviaRecensioneGrafico(UtenteBean bean, RecensioneBean datiRecensione) {
        super(bean);
        this.datiRecensione = datiRecensione;
    }


    public void inviaRecensione(){
        InviaRecensioneApplicativo controllerApp = new InviaRecensioneApplicativo();

        controllerApp.inviaRecensione(datiRecensione,utenteBean.getUsername());





    }


}

package view;

import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.OffertaControllerApplicativo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.nio.Buffer;
import java.time.LocalDateTime;


public class OffertaControllerGrafico extends UserGuiController{

    @FXML
   private TextField offertaTextField;

    @FXML
    private Label wrongOffert;

    @FXML
    private Button offerta;

    private OffertaBean offertaBean;



    protected OffertaControllerGrafico(UtenteBean utente,OffertaBean offertaBean){

        super(utente);
        this.offertaBean = offertaBean;
    }


    @FXML
    private void doOfferta() {

        BigDecimal prezzoOfferta = null;
        if (offertaTextField.getText().isEmpty()) {
            wrongOffert.setText("Fai un offerta");
        } else {
            try {
                prezzoOfferta = BigDecimal.valueOf(Integer.parseInt(String.valueOf(offertaTextField.getText())));
            } catch (NumberFormatException ex) {
                wrongOffert.setText("Inserisci un offerta Valida");
            }
        }

        offertaBean.setUsernameOfferente(utenteBean.getUsername());
        offertaBean.setOffertaPrezzo(prezzoOfferta);
        offertaBean.setUtenteOfferenteID(utenteBean.getIdUtente());
        offertaBean.setDataOfferta(LocalDateTime.now());



        OffertaControllerApplicativo of = new OffertaControllerApplicativo();
        boolean esitoOfferta = of.doOfferta(offertaBean);
    }


}

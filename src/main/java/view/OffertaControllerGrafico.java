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


public class OffertaControllerGrafico extends UserGuiController{

    @FXML
   private TextField offertaTextField;

    @FXML
    private Label wrongOffert;

    @FXML
    private Button offerta;

    private int idAnnuncio;

    public void setNum(int id){this.idAnnuncio=id;
        System.out.printf("id ANNUNCIO "+ idAnnuncio);}


    protected OffertaControllerGrafico(UtenteBean utente){
        super(utente);
    }
//NEL DAO CI STA INSERISCI OFFERTA(OffertaModel offerta)

    private void userOffert() {

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
        OffertaBean offertaBean = new OffertaBean();
        offertaBean.setOffertaPrezzo(prezzoOfferta);
        offertaBean.setUtenteOfferenteID(this.utenteBean.getIdUtente());



        OffertaControllerApplicativo of = new OffertaControllerApplicativo();
        boolean esitoOfferta = of.doOfferta(offertaBean);


    }


}

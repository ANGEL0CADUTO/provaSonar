package view;

import controllerapplicativo.DepositaEPrelevaApplicativo;
import bean.UtenteBean;
import exceptions.CreditoInsufficienteException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

//CONTROLLORE GRAFICO PER L'INTERFACCIA GRAFICA RELATIVA AL DEPOSITA E PRELEVA
public class DepositaEPrelevaGrafico extends UserGuiController {



    @FXML
    private TextField deposita;

    @FXML
    private TextField preleva;

    @FXML
    private Button depositaCredito;

    @FXML
    private Button prelevaCredito;
    @FXML
    private Label depositaLabel;

    @FXML
    private Label prelevaLabel;


    protected DepositaEPrelevaGrafico(UtenteBean bean) {
        super(bean);
    }


    @FXML
    public void userDeposita() {
        prelevaLabel.setText("");
        depositaLabel.setText("");
        try {
            if (deposita.getText().isEmpty()) {
                depositaLabel.setText("Devi inserire una cifra");

            } else if (deposita.getText().compareTo(String.valueOf(BigDecimal.ZERO)) < 0) {

                depositaLabel.setText("Devi depositare cifre positive");

            } else {


                String cifraString = deposita.getText();

                DepositaEPrelevaApplicativo dp = new DepositaEPrelevaApplicativo();

                boolean esitoDeposito = dp.deposita(utenteBean, cifraString);
                if (esitoDeposito) {
                    BigDecimal cifra = new BigDecimal(cifraString);
                    utenteBean.setCredito(utenteBean.getCredito().add(cifra));

                    depositaLabel.setText("Deposito andato a buon fine ");

                } else {
                    depositaLabel.setText("Errore");

                }


            }
        } catch(NumberFormatException e){depositaLabel.setText("Devi inserire un valore valido");}
    }


    @FXML
    public void userPreleva() {
        prelevaLabel.setText("");
        depositaLabel.setText("");
        try{
        if (preleva.getText().isEmpty()) {
            prelevaLabel.setText("Devi inserire una cifra");
            return;
        } else if (preleva.getText().compareTo(String.valueOf(BigDecimal.ZERO)) < 0) {
            prelevaLabel.setText("Devi depositare cifre positive");
            return;
        }
        String cifraString = preleva.getText();

            boolean esitoPrelievo = esito(cifraString);

            if (esitoPrelievo) {
            BigDecimal cifra = new BigDecimal(cifraString);
            utenteBean.setCredito(utenteBean.getCredito().subtract(cifra));

            prelevaLabel.setText("Prelievo andato a buon fine");

        }
    }catch(NumberFormatException e){depositaLabel.setText("Devi inserire un valore valido");}

    }

    public boolean esito(String cifraString){
        DepositaEPrelevaApplicativo pr = new DepositaEPrelevaApplicativo();
        boolean b = false;
        try {
            b = pr.preleva(utenteBean, cifraString);
        } catch (CreditoInsufficienteException e) {
            prelevaLabel.setText(e.getMessage());
        }
        return b;
    }


}









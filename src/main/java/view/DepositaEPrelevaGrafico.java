package view;

import controllerapplicativo.DepositaEPrelevaApplicativo;
import bean.UtenteBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

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
        System.out.println("Istanziato correttamente il bean N: " + utenteBean);
    }


//  QUESTO MI SERVE PIU AVANTI PER CAMBIARE VIEW
//    public void goToHomePage(){
//    FXMLLoader loader = new FXMLLoader(getClass().getResources(nome.f.xml));
//    Parent root = loader.load();
//    NOmeCOntroller N= Loader. get controller();
//    N.setUtenteBean(utenteBean);
//    }


    @FXML
    public void userDeposita() {
        prelevaLabel.setText("");
        depositaLabel.setText("");
        if (deposita.getText().isEmpty()) {
            depositaLabel.setText("Devi inserire una cifra");
            return;
        } else if (deposita.getText().compareTo(String.valueOf(BigDecimal.ZERO)) < 0) {
            depositaLabel.setText("Devi depositare cifre positive");
            return;
        } else {
            String cifraString = deposita.getText();

            DepositaEPrelevaApplicativo dp = new DepositaEPrelevaApplicativo();

            boolean esitoDeposito = dp.Deposita(utenteBean,cifraString);
            if (esitoDeposito) {
                BigDecimal cifra = new BigDecimal(cifraString);
                utenteBean.setCredito(utenteBean.getCredito().add(cifra));
                System.out.println(utenteBean.getCredito());
                depositaLabel.setText("Deposito andato a buon fine ");

            } else {
                depositaLabel.setText("Errore");

            }
        }
    }


    @FXML
    public void userPreleva() {
        prelevaLabel.setText("");
        depositaLabel.setText("");
        if (preleva.getText().isEmpty()) {
            prelevaLabel.setText("Devi inserire una cifra");
            return;
        } else if(preleva.getText().compareTo(String.valueOf(BigDecimal.ZERO)) < 0){
            prelevaLabel.setText("Devi depositare cifre positive");
            return;
        } else{

        }
        String cifraString = preleva.getText();

        DepositaEPrelevaApplicativo pr = new DepositaEPrelevaApplicativo();

        boolean esitoPrelievo = pr.Preleva(utenteBean,cifraString);
        if (esitoPrelievo) {
            BigDecimal cifra = new BigDecimal(cifraString);
            utenteBean.setCredito(utenteBean.getCredito().subtract(cifra));
            System.out.println(utenteBean.getCredito());
            prelevaLabel.setText("Prelievo andato a buon fine");

        } else {
            prelevaLabel.setText("Credito insufficiente");
        }
    }


}









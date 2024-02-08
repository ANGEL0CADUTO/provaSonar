package view;

import controllerapplicativo.DepositaEPrelevaApplicativo;
import bean.UtenteBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class DepositaEPrelevaGrafico extends UserGuiController{

    @FXML
    private TextField deposita;

    @FXML
    private TextField preleva;

    @FXML
    private Button depositaCredito;

    @FXML
    private Button prelevaCredito;




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
        if(deposita.getText().isEmpty()){this.utenteBean.setCredito(BigDecimal.valueOf(0));}
        else{
            try{
                this.utenteBean.setCredito(BigDecimal.valueOf(Integer.parseInt(deposita.getText())));
            }catch (NumberFormatException ex){
                this.utenteBean.setCredito(BigDecimal.valueOf(0));
            }
        }

  //      this.utenteBean.setCredito(BigDecimal.valueOf(Double.parseDouble(deposita.getText())));PER FAR INSERIRE CIFRE CON LA VIRGOLA
//        System.out.print("YOOOOO");
//        System.out.println(utenteBean);
        DepositaEPrelevaApplicativo dp = new DepositaEPrelevaApplicativo();

        boolean esitoDeposito = dp.Deposita(utenteBean);
        if (esitoDeposito) {
            System.out.println("DEPOSITO ANDATO A BUON FINE");
        } else {
            System.out.println("STAI SENZA UNA LIRA");
        }
    }



    @FXML
    public void userPreleva() {
        if(preleva.getText().isEmpty()){this.utenteBean.setCredito(BigDecimal.valueOf(0));}
        else{
            try{
                this.utenteBean.setCredito(BigDecimal.valueOf(Integer.parseInt(preleva.getText())));
            }catch (NumberFormatException ex){
                this.utenteBean.setCredito(BigDecimal.valueOf(0));
            }
        }


        DepositaEPrelevaApplicativo pr = new DepositaEPrelevaApplicativo();

        boolean esitoPrelievo = pr.Preleva(utenteBean);
        if (esitoPrelievo) {
            System.out.println("PRELIEVO ANDATO A BUON FINE");
        } else {
            System.out.println("NON HAI NULLA DA PRELEVARE");
        }
    }


}









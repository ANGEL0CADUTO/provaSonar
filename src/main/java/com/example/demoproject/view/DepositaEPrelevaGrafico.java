package com.example.demoproject.view;

import com.example.demoproject.controllerapplicativo.DepositaEPrelevaApplicativo;
import com.example.demoproject.bean.UtenteBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

public class DepositaEPrelevaGrafico {

    @FXML
    private TextField deposita;

    @FXML
    private TextField preleva;

    @FXML
    private Button depositaCredito;

    @FXML
    private Button prelevaCredito;

    @FXML
    private Button homePageButton;
    private UtenteBean utenteBean;

    //CREARE UN METODO SET UTENTE BEAN
    public void setUtenteBean(UtenteBean utenteBean) {
        this.utenteBean = utenteBean;
    }



//  QUESTO MI SERVE PIU AVANTI PER CAMBIARE VIEW
//    public void goToHomePage(){
//    FXMLLoader loader = new FXMLLoader(getClass().getResources(nome.f.xml));
//    Parent root = loader.load();
//    NOmeCOntroller N= Loader. get controller();
//    N.setUtenteBean(utenteBean);
//    }

    public void goToHomePage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demoproject/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) homePageButton.getScene().getWindow();
        stage.setScene(scene);
    }

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









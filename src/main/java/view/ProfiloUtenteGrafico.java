package view;

import bean.UtenteBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfiloUtenteGrafico extends UserGuiController {

    @FXML
    private Label nomeUtente;

    protected ProfiloUtenteGrafico(UtenteBean utente){
        super(utente);
    }


    @FXML
    private void initialize(){
        System.out.println(utenteBean.getIdUtente() + " " + utenteBean.getEmail());
        if(utenteBean.isLogged()) {
            nomeUtente.setText("Benvenuto " + utenteBean.getUsername() + " questo è il tuo profilo");
        }
    }

}
package view;

import bean.UtenteBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProfiloUtenteGrafico extends UserGuiController {

    @FXML
    private Label nomeUtente;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField creditoTextField;

    @FXML
    private TextField votoTextField;

    protected ProfiloUtenteGrafico(UtenteBean utente){
        super(utente);
    }


    @FXML
    private void initialize(){
        emailTextField.setText(utenteBean.getEmail());
        emailTextField.setEditable(false);
        usernameTextField.setText(utenteBean.getUsername());
        usernameTextField.setEditable(false);
        creditoTextField.setText(utenteBean.getCredito().toString());
        creditoTextField.setEditable(false);
        votoTextField.setText(Double.toString(utenteBean.getVotoRecensione()));
        votoTextField.setEditable(false);
        }



}


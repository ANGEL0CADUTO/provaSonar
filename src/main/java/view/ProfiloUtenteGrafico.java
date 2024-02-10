package view;

import bean.DatiUtenteBean;
import bean.UtenteBean;
import controllerapplicativo.ProfiloUtenteApplicativo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private TextField indirizzoTextField;
    @FXML
    private TextField civicoTextField;
    @FXML
    private TextField capTextField;


    @FXML
    private Button modificaButton;
    @FXML
    private Button inviaButton;



    protected ProfiloUtenteGrafico(UtenteBean utente){
        super(utente);
    }


    @FXML
    private void initialize(){
        inviaButton.setVisible(false);

        emailTextField.setText(utenteBean.getEmail());
        emailTextField.setEditable(false);
        usernameTextField.setText(utenteBean.getUsername());
        usernameTextField.setEditable(false);
        creditoTextField.setText(utenteBean.getCredito().toString());
        creditoTextField.setEditable(false);
        votoTextField.setText(Double.toString(utenteBean.getVotoRecensione()));
        votoTextField.setEditable(false);

        indirizzoTextField.setText(utenteBean.getDatiUtente().getIndirizzo());
        indirizzoTextField.setEditable(false);
        civicoTextField.setText(utenteBean.getDatiUtente().getCivico());
        civicoTextField.setEditable(false);
        capTextField.setText(utenteBean.getDatiUtente().getCap());
        capTextField.setEditable(false);

        }
        @FXML
        private void abilitaModificaDati(){
            indirizzoTextField.setEditable(true);
            civicoTextField.setEditable(true);
            capTextField.setEditable(true);
            inviaButton.setVisible(true);

        }

        //abbellire questo codice
        @FXML
        private void modificaDati(){
            boolean b = false;


            if(!indirizzoTextField.getText().isEmpty() && !civicoTextField.getText().isEmpty() && !capTextField.getText().isEmpty()){
                DatiUtenteBean bean = new DatiUtenteBean();
                bean.setIndirizzo(indirizzoTextField.getText());
                bean.setCivico(civicoTextField.getText());
                bean.setCap(capTextField.getText());

                ProfiloUtenteApplicativo pu = new ProfiloUtenteApplicativo();

                b = pu.modificaDati(bean);

                if(b){

                    utenteBean.setDatiUtente(pu.getDatiUtente(utenteBean.getIdUtente()));

                    System.out.println("complimenti è andato a buon fine");
                }

            }else{
                //correggere creando una label che notifichi l'utente
                System.out.println("I campi erano vuoti");
            }

            indirizzoTextField.setEditable(false);
            civicoTextField.setEditable(false);
            capTextField.setEditable(false);
            inviaButton.setVisible(false);
        }





}


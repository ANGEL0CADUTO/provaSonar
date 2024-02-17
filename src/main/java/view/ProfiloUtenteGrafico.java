package view;

import bean.DatiUtenteBean;
import bean.UtenteBean;
import controllerapplicativo.ProfiloUtenteApplicativo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.UtenteModel;

import java.text.DecimalFormat;

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
        ProfiloUtenteApplicativo controllerApp = new ProfiloUtenteApplicativo();
        UtenteBean bean2 = controllerApp.updateVotoAndCreditoByUtenteID(utenteBean.getIdUtente());
        utenteBean.setVotoRecensione(bean2.getVotoRecensione());
        utenteBean.setCredito(bean2.getCredito());

        inviaButton.setVisible(false);
        emailTextField.setText(utenteBean.getEmail());
        emailTextField.setEditable(false);
        usernameTextField.setText(utenteBean.getUsername());
        usernameTextField.setEditable(false);
        creditoTextField.setText(utenteBean.getCredito().toString());
        creditoTextField.setEditable(false);

        double votoRecensione = utenteBean.getVotoRecensione();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String votoArrotondatoString = decimalFormat.format(votoRecensione);
        votoTextField.setText(votoArrotondatoString);

        votoTextField.setEditable(false);

        if (utenteBean.getDatiUtente() != null) {
            indirizzoTextField.setText(utenteBean.getDatiUtente().getIndirizzo());
            civicoTextField.setText(utenteBean.getDatiUtente().getCivico());
            capTextField.setText(utenteBean.getDatiUtente().getCap());
        }
            indirizzoTextField.setEditable(false);
            civicoTextField.setEditable(false);
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
            boolean a = false;

            if(!indirizzoTextField.getText().isEmpty() && !civicoTextField.getText().isEmpty() && !capTextField.getText().isEmpty()){
                DatiUtenteBean bean = new DatiUtenteBean();
                bean.setIndirizzo(indirizzoTextField.getText());
                bean.setCivico(civicoTextField.getText());
                bean.setCap(capTextField.getText());
                ProfiloUtenteApplicativo pu = new ProfiloUtenteApplicativo();


                if(utenteBean.getDatiUtente() != null) {
                    bean.setIdInformazioniUtente(utenteBean.getDatiUtente().getIdInformazioniUtente());
                    b = pu.modificaDati(bean);
                }
                else{
                    int i  = pu.insertDatiUtente(bean);
                    utenteBean.setDatiUtente(bean);
                    utenteBean.getDatiUtente().setIdInformazioniUtente(i);
                    pu.updateInformazioniUtenteID(utenteBean);
                    if(bean.getIdInformazioniUtente() != -1){
                        a = true;
                    }
                }


                if(b){
                    utenteBean.getDatiUtente().setIndirizzo(indirizzoTextField.getText());
                    utenteBean.getDatiUtente().setCap(capTextField.getText());
                    utenteBean.getDatiUtente().setCivico(civicoTextField.getText());

                    System.out.println("complimenti è andato a buon fine");
                }
                if(a){
                    utenteBean.getDatiUtente().setIndirizzo(indirizzoTextField.getText());
                    utenteBean.getDatiUtente().setCap(capTextField.getText());
                    utenteBean.getDatiUtente().setCivico(civicoTextField.getText());
                    System.out.println("Inserimento dati : andato a buon fine\n");
                    System.out.println("Vediamo cosa c'è nel bean, ID: " + utenteBean.getDatiUtente().getIdInformazioniUtente() + " Civico : "+ utenteBean.getDatiUtente().getCivico());



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


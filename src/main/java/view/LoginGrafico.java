package view;


import controllerapplicativo.LoginApplicativo;
import bean.UtenteBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;


public class LoginGrafico extends UserGuiController {

    @FXML
    private Button login;

    @FXML
    private TextField enteredEmail;

    @FXML
    private PasswordField enteredPassword;

    @FXML
    private Label wrongLogin;

    private static final Logger logger = Logger.getLogger(LoginGrafico.class.getName());

    //private UtenteBean utente;

    public LoginGrafico(UtenteBean utenteBean) {
        super(utenteBean);
    }

    //public void setUtenteBean(UtenteBean bean){this.utente = bean;}




    public void userLogin(ActionEvent event){
        LoginApplicativo lg = new LoginApplicativo();

        utenteBean.setEmail("Utente2");
        utenteBean.setPassword("1234");
       /* utente.setEmail("Angelo@gmail.com");
        utente.setPassword("1234");*/

        boolean esitoLogin = lg.login(utenteBean);

        if(esitoLogin){
            wrongLogin.setText("Hai effettuato l'accesso!");
            utenteBean.setLogged(true);
            System.out.println("LOGIN GRAFICO : " + utenteBean.getIdUtente() + " " + utenteBean.getUsername()+ " " + utenteBean.getPassword()+ " " + utenteBean.getEmail()+ " " + utenteBean.getCredito());
            System.out.println("voto : " + utenteBean.getVotoRecensione());
            try{

                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                loader.setControllerFactory(c-> new Demo(utenteBean));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) login.getScene().getWindow();
                stage.setScene(scene);

            } catch(IOException e) {
                logger.severe("BROOO QUA STANNO LANCIANDO EXCEPTION ((login grafico cambio view))");
                logger.info("info");
            }

        }
        else{
            wrongLogin.setText("Credenziali sbagliate");
        }

    }





}

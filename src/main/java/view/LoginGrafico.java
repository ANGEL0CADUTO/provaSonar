package view;


import controllerapplicativo.LoginApplicativo;
import bean.CredenzialiBean;
import bean.UtenteBean;

import exceptions.CredenzialiSbagliateException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;


//CONTROLLORE GRAFICO PER IL LOGIN
public class LoginGrafico extends BaseController {
    private static final Logger logger= Logger.getLogger(LoginGrafico.class.getName());


    @FXML
    private Button login;

    @FXML
    private TextField enteredEmail;

    @FXML
    private PasswordField enteredPassword;

    @FXML
    private Label wrongLogin;


    public LoginGrafico(UtenteBean utenteBean) {
        super(utenteBean);
    }





    public void userLogin(){
        LoginApplicativo lg = new LoginApplicativo();
        CredenzialiBean credenzialiBean = new CredenzialiBean();

        if(enteredEmail.getText().isEmpty() || enteredPassword.getText().isEmpty()) {
            wrongLogin.setText("I campi non possono essere vuoti");
        }
        else{
            credenzialiBean.setEmail(enteredEmail.getText());
            credenzialiBean.setPassword(enteredPassword.getText());
        }



        try {
            utenteBean = lg.login(credenzialiBean);
        } catch (CredenzialiSbagliateException e) {
            wrongLogin.setText(e.getMessage());
        }

        if(utenteBean.isLogged()){
            wrongLogin.setText("Hai effettuato l'accesso!");

            try{

                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                loader.setControllerFactory(c-> new HomePage(utenteBean));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) login.getScene().getWindow();
                stage.setScene(scene);

            } catch(IOException e) {
                logger.severe("errore in LoginGrafico in login "+ e.getMessage());

            }

        }


    }





}

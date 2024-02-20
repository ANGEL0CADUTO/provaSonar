package view;

import bean.UtenteBean;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;


public class HomePage extends UserGuiController {

    @FXML
    private Button loginHomePage;

    @FXML
    private Button registratiHomePage;

    @FXML
    private Button mieiAnnunci;
    @FXML
    private HBox logoutBox;

    @FXML
    private Text home;


    protected HomePage(UtenteBean bean) {
        super(bean);

    }


    public void initializeData() {
        if (utenteBean == null) {
            utenteBean = new UtenteBean();

        }

    }

    public void initialize() {
        initializeData();
        if (!utenteBean.isLogged()) {
            logoutBox.setVisible(false);
        }
        else{
            registratiHomePage.setVisible(false);
            loginHomePage.setVisible(false);
        }

    }

    public void setUtenteBean(UtenteBean bean) {
        this.utenteBean = bean;
    }








    //da testare se funziona anche post login
    public void logout() {
        utenteBean = new UtenteBean();
    }


}



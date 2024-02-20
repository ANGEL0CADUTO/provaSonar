package view;

import bean.UtenteBean;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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

    public void changeScene() throws IOException {

        if (!utenteBean.isLogged()) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            loader.setControllerFactory(c -> new LoginGrafico(utenteBean));


            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginHomePage.getScene().getWindow();
            stage.setScene(scene);

        }
    }




    public void registrazione() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Registra.fxml"));
        loader.setControllerFactory(c -> new RegistraGrafico(utenteBean));
        Parent root = loader.load();
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    //da testare se funziona anche post login
    public void logout() {
        utenteBean = new UtenteBean();
    }


}



package view;

import bean.UtenteBean;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


//CONTROLLORE GRAFICO RELATIVO ALLA HOMEPAGE
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







}



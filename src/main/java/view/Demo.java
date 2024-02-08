package view;

import bean.UtenteBean;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.io.IOException;


public class Demo extends UserGuiController {
    @FXML
    private AnchorPane myAnchorPane;

    @FXML
    private Button loginHomePage;

    @FXML
    private Button registratiHomePage;

    @FXML
    private Button depositaEPrelevaButton;

    @FXML
    private Button profiloButton;
    @FXML
    private Button libreriaButton;
    @FXML
    private Button compraButton;


    @FXML
    private Button logoutHomePage;





    protected Demo(UtenteBean bean) {
        super(bean);
        System.out.println("Controller Demo istanziato con UtenteBean: " + bean);

    }


    public void initializeData(){
        if(utenteBean == null){
            System.out.println("porcatroia");
            utenteBean = new UtenteBean();
        }

    }

    public void initialize(){
        initializeData();
        System.out.println(utenteBean);

    }

    public void setUtenteBean(UtenteBean bean){
        this.utenteBean= bean;
    }

    public void changeScene() throws IOException {

        if(utenteBean.isLogged()){
            System.out.println("sei già loggato");
        }
        else{


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/Login.fxml"));
            loader.setControllerFactory(c -> new LoginGrafico(utenteBean));


            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginHomePage.getScene().getWindow();
            stage.setScene(scene);

        }



        //StupidTesting
        //CopiaMangaDAO cm = new CopiaMangaDAO();
        //cm.getCopieMangaListByUserID();

    }

    public void registrazione() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demoproject/Registra.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) registratiHomePage.getScene().getWindow();
        stage.setScene(scene);
    }





    //da testare se funziona anche post login
    public void logout(){
        utenteBean = new UtenteBean();
    }


}



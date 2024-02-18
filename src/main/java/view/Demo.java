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
    private Button mieiAnnunci;


    @FXML
    private Button logoutHomePage;





    protected Demo(UtenteBean bean) {
        super(bean);
        System.out.println("Controller Demo istanziato con UtenteBean: " + bean);

    }


    public void initializeData(){
        if(utenteBean == null){

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

        if (utenteBean.isLogged()) {
            System.out.println("sei già loggato");
        } else {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            loader.setControllerFactory(c -> new LoginGrafico(utenteBean));


            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginHomePage.getScene().getWindow();
            stage.setScene(scene);

        }
    }
        public void goToRecensioni() throws IOException{

            if(utenteBean.isLogged()){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Recensioni.fxml"));
                loader.setControllerFactory(c -> new RecensioniGrafico(utenteBean));


                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) myAnchorPane.getScene().getWindow();
                stage.setScene(scene);
            }
            else{
                goToLogin();
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
    public void goToMieiAnnunci ()throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("MieiAnnunci.fxml"));
        loader.setControllerFactory(c -> new MieiAnnunciGrafico(utenteBean));
        Parent root = loader.load();
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }





    //da testare se funziona anche post login
    public void logout(){
        utenteBean = new UtenteBean();
    }


}



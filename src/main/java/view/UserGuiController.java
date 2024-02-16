package view;

import bean.UtenteBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

//Tutte le classi al di fuori di login e resgistrati condividono una barra di ricerca che contiene i bottoni:
//DEPOSITA/PRELEVA, PROFILO, COMPRA,LIBRERIA
public class UserGuiController extends BaseController{

    @FXML
    protected ImageView profiloImage;

    @FXML
    protected Button profiloButton;
    @FXML
    protected Button compraButton;
    @FXML
    protected Button libreriaButton;
    @FXML
    protected Button depositaEPrelevaButton;

    protected UserGuiController(UtenteBean bean) {
        super(bean);
    }

    @FXML
    void goToLogin() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        loader.setControllerFactory(c -> new LoginGrafico(utenteBean));
        Parent root = loader.load();
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);


    }


    @FXML
    void goToProfilo(ActionEvent event) throws IOException {
        //The Home Page button onAction method
        if(utenteBean.getUsername() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfiloUtenteProva.fxml"));
            loader.setControllerFactory(c -> new ProfiloUtenteGrafico(utenteBean));
            Parent root = loader.load();
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        else{
            goToLogin();

        }
    }

    @FXML
    void goToLibreria(ActionEvent event) throws IOException {
        if(utenteBean.getUsername() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LibreriaUtente.fxml"));
            loader.setControllerFactory(c -> new LibreriaUtenteControllerGrafico(utenteBean));
            Parent root = loader.load();
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        else{
            goToLogin();
        }
    }
    @FXML
    void goToDepositaEPreleva(ActionEvent event) throws IOException {
        if(utenteBean.getUsername() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DepositaEPreleva.fxml"));
            loader.setControllerFactory(c -> new DepositaEPrelevaGrafico(utenteBean));
            Parent root = loader.load();
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        else{
            goToLogin();
        }
    }
    @FXML
    void goToCompra(ActionEvent event) throws IOException {

        if(utenteBean.getUsername() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Compra.fxml"));
            loader.setControllerFactory(c -> new CompraMangaControllerGrafico(utenteBean));
            Parent root = loader.load();
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        else{
            goToLogin();
        }
    }





    @FXML
    void goToMieiAnnunci() throws IOException {
        if(utenteBean.getUsername() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MieiAnnunci.fxml"));
            loader.setControllerFactory(c -> new MieiAnnunciGrafico(utenteBean));
            Parent root = loader.load();
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);}
        else{
            goToLogin();
        }
    }

    @FXML
    void goToMieiAcquisti() throws IOException{

        if(utenteBean.isLogged()){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MieiAcquisti.fxml"));
            loader.setControllerFactory(c -> new MieiAcquistiGrafico(utenteBean));


            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            stage.setScene(scene);
        }
        else{
            goToLogin();
        }
    }








}



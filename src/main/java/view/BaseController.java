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


//CONTROLLORE GRAFICO DI BASE PER IMPLEMENTARE UNA SOLA VOLTA IL METODO PER ANDARE ALLA HOMEPAGE, FUNZIONE CONDIVISA DA TUTTI
//I CONTROLLORI GRAFICI
public class BaseController {


    @FXML
    protected AnchorPane myAnchorPane;

    @FXML
    protected Button homeButton;

    protected UtenteBean utenteBean;

    protected BaseController(UtenteBean bean){
        this.utenteBean = bean;
    }



    @FXML
      void goToHomePage() throws IOException {
         //The Home Page button onAction method

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        loader.setControllerFactory(c-> new HomePage(utenteBean));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
        stage.setScene(scene);
     }

}

package view;

import bean.UtenteBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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
      void goToHomePage(ActionEvent event) throws IOException {
         //The Home Page button onAction method

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        loader.setControllerFactory(c-> new Demo(utenteBean));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();
        stage.setScene(scene);
     }

}

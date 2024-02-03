package com.example.demoproject.view;

import com.example.demoproject.bean.UtenteBean;
import com.example.demoproject.view.Demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/example/demoproject/HomePage.fxml"));
         loader.setControllerFactory(c -> new Demo());

         Parent root=loader.load();
         myAnchorPane.getChildren().setAll(root);
     }

}

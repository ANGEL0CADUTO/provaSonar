package com.example.demoproject.view;

import com.example.demoproject.bean.UtenteBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CompraMangaControllerGrafico {


    @FXML
    private Button homePageButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    private UtenteBean utenteBean;

    public void setUtenteBean(UtenteBean utenteBean) {
        this.utenteBean = utenteBean;
    }











    public void goToHomePage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoproject/HomePage.fxml"));
        Parent root = loader.load();

        Demo controller = loader.getController();
        controller.setUtenteBean(utenteBean);




        Scene scene = new Scene(root);
        Stage stage = (Stage) homePageButton.getScene().getWindow();
        stage.setScene(scene);

    }
}

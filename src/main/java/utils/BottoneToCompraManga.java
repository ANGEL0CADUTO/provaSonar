package utils;

import dati.OffertaBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AnnuncioModel;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class BottoneToCompraManga extends Button {


    private static final Logger logger = Logger.getLogger(BottoneToCompraManga.class.getName());

    public BottoneToCompraManga(List<AnnuncioModel> array, int index, AnchorPane myAnchorPane, ToolBar toolbar) {


        // Gestisci l'evento di clic del bottone
        this.setOnAction(event -> {
            // Ora puoi eseguire un'azione basata su questo elemento
            toolbar.setVisible(true);

        });
    }


}

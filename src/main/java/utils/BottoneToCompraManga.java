package utils;


import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

import model.AnnuncioModel;



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

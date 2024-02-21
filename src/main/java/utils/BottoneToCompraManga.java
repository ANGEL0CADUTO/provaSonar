package utils;


import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

import model.AnnuncioModel;



import java.util.List;

public class BottoneToCompraManga extends Button {



    public BottoneToCompraManga(List<AnnuncioModel> array, int index, AnchorPane myAnchorPane, ToolBar toolbar) {


        // Gestisci l'evento di clic del bottone
        this.setOnAction(event -> {
            // Ora puoi eseguire un'azione basata su questo elemento
            toolbar.setVisible(true);

        });
    }


}

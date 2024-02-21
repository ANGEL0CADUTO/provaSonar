package utils;


import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;


public class BottoneToCompraManga extends Button {



    public BottoneToCompraManga( ToolBar toolbar) {


        // Gestisci l'evento di clic del bottone
        this.setOnAction(event ->
            // Ora puoi eseguire un'azione basata su questo elemento
            toolbar.setVisible(true));
    }


}

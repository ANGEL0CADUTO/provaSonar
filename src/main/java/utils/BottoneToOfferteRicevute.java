package utils;

import bean.UtenteBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AnnuncioModel;
import view.OfferteRicevuteGrafico;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class BottoneToOfferteRicevute extends Button {

    private static final Logger logger = Logger.getLogger(BottoneToOfferteRicevute.class.getName());

    public BottoneToOfferteRicevute(List<AnnuncioModel> array, int index, AnchorPane myAnchorPane, UtenteBean utenteBean) {


        // Gestisci l'evento di clic del bottone
        this.setOnAction(event -> {
            // Ora puoi eseguire un'azione basata su questo elemento
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OfferteRicevute.fxml"));
                loader.setControllerFactory(c -> new OfferteRicevuteGrafico(utenteBean, array.get(index).getIdAnnuncio()));
                Parent root = loader.load();
                Stage stage = (Stage) myAnchorPane.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                logger.info("Errore in MieiAnnunciGrafico nel cambio pagina " + e.getMessage());
            }
        });
    }
}

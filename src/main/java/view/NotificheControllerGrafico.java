package view;

import controllerapplicativo.NotificheControllerApplicativo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.NotificaModel;

public class NotificheControllerGrafico {

    @FXML
    private Label notificationLabel;

    private NotificheControllerApplicativo applicativo = new NotificheControllerApplicativo();

    public void initialize() {
        // Modifica il tipo della variabile notifica da String a NotificheModel
        NotificaModel notifica = applicativo.getNotifica();

        // Aggiorna l'etichetta con la notifica
        if (notifica != null) {
            String testoNotifica = String.format(
                    "Utente: %s\nVolume: %d\nVenditore: %s\nManga: %s\nPrezzo Offerta: %d",
                    notifica.getUtente(),
                    notifica.getVolume(),
                    notifica.getVenditore(),
                    notifica.getManga(),
                    notifica.getPrezzoOfferta()
            );
            notificationLabel.setText(testoNotifica);
        } else {
            notificationLabel.setText("Nessuna notifica disponibile");
        }
    }
}

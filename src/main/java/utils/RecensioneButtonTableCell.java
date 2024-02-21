package utils;


import bean.RecensioneBean;
import bean.UtenteBean;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import model.OffertaModel;

import view.InviaRecensioneGrafico;


import java.io.IOException;
import java.util.logging.Logger;

public class RecensioneButtonTableCell extends TableCell<OffertaModel, OffertaModel> {

    private final Button bottone = new Button("Recensisci");
    private static final Logger logger = Logger.getLogger(RecensioneButtonTableCell.class.getName());

    public RecensioneButtonTableCell(ObservableList<OffertaModel> array, UtenteBean utenteBean, AnchorPane myAnchorPane) {
        bottone.setOnAction(event -> {
            int index = getIndex();
            if (index >= 0 && index < array.size()) {
                try {
                    OffertaModel offerta = array.get(index);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Recensione.fxml"));
                    RecensioneBean dati = new RecensioneBean();
                    dati.setIdOfferta(offerta.getIdOfferta());
                    dati.setRecensitoID(offerta.getUtenteVenditoreID());
                    loader.setControllerFactory(c -> new InviaRecensioneGrafico(utenteBean, dati));
                    Parent root = null;
                    root = loader.load();
                    Stage stage = (Stage) myAnchorPane.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    logger.severe("Errore cambio pagina in RecensioneButtonTableCell: " + e.getMessage());
                }

            }
        });
    }

    // Aggiungi un override per aggiornare il valore del pulsante quando la cella viene aggiornata
    @Override
    protected void updateItem(OffertaModel item, boolean empty) {
        super.updateItem(item, empty);

        if (!empty && item != null) {
            // Aggiungi il bottone solo se il valore di Recensito è 0
            if (item.getRecensito() == 0) {
                setGraphic(bottone);
                setText("Recensisci");  // Imposta il testo della cella
            } else {
                setGraphic(null);
                setText("Recensito");
            }
        } else {
            setGraphic(null);
            setText(null);
        }
    }
}





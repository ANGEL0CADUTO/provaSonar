package utils;

import bean.OffertaBean;
import bean.UtenteBean;
import controllerapplicativo.OfferteRicevuteApplicativo;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.OffertaRicevuta;
import view.MieiAnnunciGrafico;


import java.io.IOException;
import java.util.logging.Logger;

public class BottoneAccettaOfferta extends TableCell<OffertaRicevuta, String> {
    private final Button bottone = new Button("Accetta");


    private static final Logger logger = Logger.getLogger(BottoneAccettaOfferta.class.getName());

    public BottoneAccettaOfferta(ObservableList<OffertaRicevuta> array, UtenteBean utenteBean, AnchorPane myAnchorPane) {


        // Gestisci l'evento di clic del bottone
        bottone.setOnAction(event -> {
            int index = getIndex();
            if (index >= 0 && index < array.size()) {
                try {
                    OffertaBean offertaBean = new OffertaBean();
                    // Popola offertaBean con i dati necessari utilizzando 'array', 'utenteBean', ecc.
                    offertaBean.setIdAnnuncio(array.get(index).getAnnuncioID());
                    offertaBean.setCopiaMangaID(array.get(index).getCopiaMangaID());
                    offertaBean.setUtenteOfferenteID(array.get(index).getUtenteOfferenteID());
                    offertaBean.setIdOfferta(array.get(index).getIdOfferta());
                    offertaBean.setOffertaPrezzo(array.get(index).getOffertaPrezzo());
                    offertaBean.setTitoloManga(array.get(index).getTitoloManga());
                    offertaBean.setVolumeManga(array.get(index).getVolumeManga());

                    OfferteRicevuteApplicativo controllerApp = new OfferteRicevuteApplicativo();
                    controllerApp.accettaOffertaByOffertaID(offertaBean, utenteBean.getIdUtente());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MieiAnnunci.fxml"));
                    loader.setControllerFactory(c -> new MieiAnnunciGrafico(utenteBean));
                    Parent root = loader.load();
                    Stage stage = (Stage) myAnchorPane.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    logger.severe("Errore nel cambio pagina " + e.getMessage());
                }
            }
        });
    }

    // Aggiungi un override per aggiornare il valore del pulsante quando la cella viene aggiornata
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            setGraphic(bottone);
        }
    }
}


package utils;


import bean.CopiaMangaBean;
import bean.UtenteBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import model.CopiaMangaModel;
import view.AnnuncioControllerGrafico;

import java.io.IOException;
import java.util.logging.Logger;

public class AnnuncioButtonTableCell extends TableCell<CopiaMangaModel, String> {

    private final Label label = new Label("In vendita");
    private final Button bottone = new Button("Vendi");
    private static final Logger logger = Logger.getLogger(AnnuncioButtonTableCell.class.getName());
    private UtenteBean utenteBean;
    private AnchorPane myAnchorPane;

    public AnnuncioButtonTableCell(UtenteBean utenteBean, AnchorPane myAnchorPane) {
        this.utenteBean = utenteBean;
        this.myAnchorPane = myAnchorPane;

        bottone.setOnAction(event -> gestisciClicBottone());
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            visualizzaAzioneBottone();
        }
    }

    private void gestisciClicBottone() {
        CopiaMangaModel copiaMangaModel = getTableView().getItems().get(getIndex());
        CopiaMangaBean copiaMangaBean = creaCopiaMangaBean(copiaMangaModel);
        apriFinestraInserisciAnnuncio(copiaMangaBean);
    }

    private void visualizzaAzioneBottone() {
        if (getTableView().getItems().get(getIndex()).getStatoCopiaManga() == 1) {
            setGraphic(bottone);
        } else {
            setGraphic(label);
        }
    }

    private CopiaMangaBean creaCopiaMangaBean(CopiaMangaModel copiaMangaModel) {
        CopiaMangaBean copiaMangaBean = new CopiaMangaBean();
        copiaMangaBean.setIdCopiaManga(copiaMangaModel.getIdCopiaManga());
        copiaMangaBean.setTitolo(copiaMangaModel.getTitolo());
        copiaMangaBean.setVolume(copiaMangaModel.getVolume());
        copiaMangaBean.setIdUtente(utenteBean.getIdUtente());
        return copiaMangaBean;
    }

    private void apriFinestraInserisciAnnuncio(CopiaMangaBean copiaMangaBean) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InserisciAnnuncio.fxml"));
            loader.setControllerFactory(c -> new AnnuncioControllerGrafico(utenteBean, copiaMangaBean));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            logger.severe("Errore in AnnuncioButtonTableCell: " + e.getMessage());
        }
    }


}

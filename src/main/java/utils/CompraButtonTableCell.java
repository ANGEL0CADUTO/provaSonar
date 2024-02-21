package utils;

import bean.OffertaBean;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.ToolBar;
import model.AnnuncioModel;
import model.CopiaMangaModel;

import java.util.List;

public class CompraButtonTableCell extends TableCell<CopiaMangaModel, String> {
    private final Button bottone = new Button("Compra");
    private final ToolBar toolbar;


    public CompraButtonTableCell(OffertaBean offertaBean, ToolBar toolbar, List<AnnuncioModel> arrayAnnunci) {
        this.toolbar = toolbar;

        // Gestisci l'evento di clic del bottone
        bottone.setOnAction(event -> {
            int index = getIndex();
            if (index >= 0 && index < arrayAnnunci.size()) {
                // Ora puoi eseguire un'azione basata su questo elemento
                toolbar.setVisible(true);

                AnnuncioModel annuncioModel = arrayAnnunci.get(index);
                if (annuncioModel != null) {
                    offertaBean.setIdAnnuncio(annuncioModel.getIdAnnuncio());
                    offertaBean.setCopiaMangaID(annuncioModel.getCopiaMangaID());
                }
            }
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        // Verifica se la riga è vuota
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(bottone);
        }
    }
}


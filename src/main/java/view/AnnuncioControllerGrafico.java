package view;

import bean.CopiaMangaBean;
import bean.UtenteBean;
import controllerapplicativo.AnnuncioControllerApplicativo;
import exceptions.AnnuncioNonInseritoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class AnnuncioControllerGrafico extends UserGuiController{

    private static final Logger LOGGER = Logger.getLogger(AnnuncioControllerGrafico.class.getName());



    @FXML
    private Button inserisciAnnuncio;//LO USO PER CONTROLLARE GLI ANNUNCI

    @FXML
    private TextField inserisciPrezzo;

    @FXML
    private Label wrongPrice;


    private CopiaMangaBean copiaMangaBean;

    protected AnnuncioControllerGrafico(UtenteBean bean,CopiaMangaBean copiaMangaBean) {
        super(bean);
        this.copiaMangaBean = copiaMangaBean;
    }

    @FXML

    public void userAnnunce(ActionEvent event) {
        BigDecimal prezzo = null;

        if (inserisciPrezzo.getText().isEmpty()) {
            wrongPrice.setText("Inserisci un prezzo");
        } else {
            try {
                prezzo = BigDecimal.valueOf(Integer.parseInt(String.valueOf(inserisciPrezzo.getText())));
            } catch (NumberFormatException ex) {
                wrongPrice.setText("Inserisci un prezzo valido");
            }
        }
        Date dataCorrente = new Date();
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataFormattata = formatoData.format(dataCorrente);
        AnnuncioControllerApplicativo an = new AnnuncioControllerApplicativo();



           boolean esitoRicercaAnuncio = an.cercaAnnuncio(copiaMangaBean);


           if (esitoRicercaAnuncio) {
               LOGGER.info("Esiste gia un annuncio per questa copia");
           } else {
               try{
               boolean esitoAnnuncio = an.inserisciAnnuncio(copiaMangaBean, prezzo, dataFormattata,utenteBean.getUsername());
               if (esitoAnnuncio) {
                   try {
                       goToLibreria(event);
                   } catch (IOException e) {
                       LOGGER.severe("Errore cambioPagina AnnuncioControllerGrafico in UserAnnounce :" + e.getMessage());
                   }
                   LOGGER.info("Inserimento annuncio andato a buon fine");
               } else {
                   LOGGER.info("Inserimento annuncio fallito");
               }
               }catch (AnnuncioNonInseritoException e){e.getMessage();}
           }




    }





}

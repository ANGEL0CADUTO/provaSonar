package view;

import bean.UtenteBean;
import controllerapplicativo.RecensioniApplicativo;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Pagination;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.Recensione;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


//CONTROLLORE GRAFICO PER LE RECENSIONI RICEVUTE DA UN UTENTE
public class RecensioniGrafico extends UserGuiController {

    @FXML
    private Pagination pagination;

    protected RecensioniGrafico(UtenteBean bean) {
        super(bean);
    }

    @FXML
    public void initialize() {
        // Simula il caricamento delle recensioni
        // Qui dovresti ottenere le recensioni dal tuo sistema o database
        RecensioniApplicativo controller = new RecensioniApplicativo();
        List<Recensione> array = controller.getMyRecensioniRicevute(utenteBean.getIdUtente());
        ArrayList<Recensione> arrayList = new ArrayList<>(array);
        int numeroRecensioni = array.size();

        int recensioniPerPage = 5;
        pagination.setPageCount((int) Math.ceil((double) numeroRecensioni / recensioniPerPage));
        pagination.setPageFactory(pageIndex -> createPage(pageIndex, arrayList));
    }

    private AnchorPane createPage(int pageIndex, ArrayList<Recensione> recensioni) {
        AnchorPane anchorPane = new AnchorPane();

        int recensioniPerPage = 5;
        int startIndex = pageIndex * recensioniPerPage;
        int endIndex = Math.min(startIndex + recensioniPerPage, recensioni.size());

        Accordion accordion = new Accordion();

        for (int i = startIndex; i < endIndex; i++) {
            Recensione recensione = recensioni.get(i);
            TitledPane titledPane = createTitledPane(recensione);
            accordion.getPanes().add(titledPane);
        }

        anchorPane.getChildren().add(accordion);
        AnchorPane.setTopAnchor(accordion, 10.0);
        AnchorPane.setBottomAnchor(accordion, 10.0);
        AnchorPane.setLeftAnchor(accordion, 10.0);
        AnchorPane.setRightAnchor(accordion, 10.0);

        // Imposta l'AnchorPane per espandersi in modo appropriato nel Pagination
        anchorPane.setMinHeight(Region.USE_PREF_SIZE);
        anchorPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        anchorPane.setMaxHeight(Region.USE_PREF_SIZE);

        return anchorPane;
    }

    private TitledPane createTitledPane(Recensione recensione) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText("Recensione per " + recensione.getTitoloRecensito() + " volume " + recensione.getVolumeRecensito() + "\ncon VOTO:" + recensione.getVoto()) ;

        AnchorPane anchorPane = new AnchorPane();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Formatta LocalDateTime in una stringa usando il formato specificato
        String formattedDataAcquisto = recensione.getDataAcquisto().format(formatter);
        Text testoContenuto = new Text("Utente recensore: " + recensione.getUsernameRecensore()+ "\n" + "Prezzo annuncio : " + recensione.getPrezzoIniziale() + "\nPrezzo venduto : " + recensione.getPrezzoFinale() +"\nData vendita : "+ formattedDataAcquisto + "\nDescrizione: "+recensione.getTesto());
        testoContenuto.setWrappingWidth(400);
        anchorPane.getChildren().add(testoContenuto);

        // Assicurati che l'AnchorPane si estenda per riempire il TitledPane
        AnchorPane.setTopAnchor(testoContenuto, 10.0);
        AnchorPane.setBottomAnchor(testoContenuto, 10.0);
        AnchorPane.setLeftAnchor(testoContenuto, 10.0);
        AnchorPane.setRightAnchor(testoContenuto, 10.0);

        titledPane.setContent(anchorPane);

        return titledPane;
    }
}

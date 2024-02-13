package view;

import bean.UtenteBean;
import controllerapplicativo.MieiAnnunciApplicativo;
import controllerapplicativo.RecensioniApplicativo;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.AnnuncioModel;
import model.Recensione;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        ArrayList<Recensione> array = controller.getMyRecensioniRicevute(utenteBean.getIdUtente());
        int numeroRecensioni = array.size();

        int recensioniPerPage = 3;
        pagination.setPageCount((int) Math.ceil((double) numeroRecensioni / recensioniPerPage));
        pagination.setPageFactory(pageIndex -> createPage(pageIndex, array));
    }

    private AnchorPane createPage(int pageIndex, ArrayList<Recensione> recensioni) {
        AnchorPane anchorPane = new AnchorPane();

        int recensioniPerPage = 5;
        int startIndex = pageIndex * recensioniPerPage;
        int endIndex = Math.min(startIndex + recensioniPerPage, recensioni.size());

        Accordion accordion = new Accordion();

        for (int i = startIndex; i < endIndex; i++) {
            Recensione recensione = recensioni.get(i);


            String titoloRecensione = recensione.getUsernameRecensore();
            String contenutoRecensione = recensione.getTesto();


            TitledPane titledPane = createTitledPane(recensione);
            accordion.getPanes().add(titledPane);
        }

        anchorPane.getChildren().add(accordion);
        AnchorPane.setTopAnchor(accordion, 10.0);
        AnchorPane.setBottomAnchor(accordion, 10.0);
        AnchorPane.setLeftAnchor(accordion, 10.0);
        AnchorPane.setRightAnchor(accordion, 10.0);

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
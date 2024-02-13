package view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.CopiaMangaBean;
import bean.UtenteBean;
import controllerapplicativo.AnnuncioControllerApplicativo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AnnuncioControllerGrafico extends UserGuiController{



    @FXML
    private Button homePageButton;

    @FXML
    private Button inserisciAnnuncio;//LO USO PER CONTROLLARE GLI ANNUNCI

    @FXML
    private TextField inserisciPrezzo;

    @FXML
    private Label wrongPrice;

    BigDecimal prezzo;
    private UtenteBean utenteBean;
    private int idCopiaManga;

    private CopiaMangaBean copiaMangaBean;

    protected AnnuncioControllerGrafico(UtenteBean bean, int idCopiaManga) {
        super(bean);
        this.idCopiaManga = idCopiaManga;
        this.utenteBean=bean;
    }


   /* public void setUtenteBean(UtenteBean utenteBean) {
        this.utenteBean = utenteBean;
    }*/
    public void setCopiaMangaBean(CopiaMangaBean copiamangaBean) {this.copiaMangaBean = copiamangaBean;}
  
    @FXML
public void userAnnunce(){


    if(inserisciPrezzo.getText().isEmpty()){wrongPrice.setText("Inserisci un prezzo");}
    else {
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

  //POPOLA BEAN MANGA CHE POI PASSO ALL'APPLICATIVO

  CopiaMangaBean copiaMangaBean = new CopiaMangaBean();
        copiaMangaBean.setIdManga(idCopiaManga);


        System.out.println("INSERISCI ANNUNCIO UTENTE BEAN "+  utenteBean.getIdUtente());


    boolean esitoRicercaAnuncio= an.cercaAnnuncio(copiaMangaBean);


    if(esitoRicercaAnuncio){
        System.out.println("ESISTE GIA' UN ANNUNCIO PER QUESTO MANGA");
    }else{boolean esitoAnnuncio = an.inserisciAnnuncio(utenteBean,copiaMangaBean,prezzo,dataFormattata);
    if (esitoAnnuncio) {
        System.out.println("INSERIMENTO ANNUNCIO ANDATO A BUON FINE");
    } else {
        System.out.println("L'ANNUNCIO TE LO TIRI IN FACCIA");
    }
    }
}








 /*   public void goToHomePage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) homePageButton.getScene().getWindow();
        stage.setScene(scene);
    }*/



}


import controllerapplicativo.AnnuncioControllerApplicativo;
import exceptions.AnnuncioNonInseritoException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import bean.CopiaMangaBean;
import static org.junit.jupiter.api.Assertions.*;


//TEST FATTO DA ANGELO ROMANO
class TestInserisciAnnuncio {
    //TEST DI INSERIMENTO ANNUNCIO PER UN UTENTE CHE NE POSSIEDE LA COPIA
   /* @Test
    public void testInserisciAnnuncio() throws AnnuncioNonInseritoException {

        CopiaMangaBean copiaMangaBean = new CopiaMangaBean();
        copiaMangaBean.setIdCopiaManga(55);
        copiaMangaBean.setIdUtente(2);
        copiaMangaBean.setTitolo("jo");
        copiaMangaBean.setVolume(8);

        BigDecimal prezzo = BigDecimal.valueOf(20.0);
        String dataFormattata = "2023-02-21 12:33:11";
        String username = "Utente2";

        AnnuncioControllerApplicativo annuncioController = new AnnuncioControllerApplicativo();




        // Esecuzione del test
        boolean result = annuncioController.inserisciAnnuncio(copiaMangaBean, prezzo, dataFormattata, username);

        // Verifica dell'output
        assertFalse(result);
    }
*/

}

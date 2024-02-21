
import controllerapplicativo.AnnuncioControllerApplicativo;
import exceptions.AnnuncioNonInseritoException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import bean.CopiaMangaBean;
import static org.junit.jupiter.api.Assertions.*;


//TEST FATTO DA ANGELO ROMANO
class TestInserisciAnnuncio {

    //TEST DI INSERIMENTO ANNUNCIO PER UN UTENTE CHE NE POSSIEDE LA COPIA
    @Test
    public void testInserisciAnnuncio() throws AnnuncioNonInseritoException {

        CopiaMangaBean copiaMangaBean = new CopiaMangaBean();
        copiaMangaBean.setIdCopiaManga(45);
        copiaMangaBean.setIdUtente(1);
        copiaMangaBean.setTitolo("Berserk 12");
        copiaMangaBean.setVolume(11);

        BigDecimal prezzo = BigDecimal.valueOf(20.0);
        String dataFormattata = "2023-02-21 12:33:11";
        String username = "Angel";

        AnnuncioControllerApplicativo annuncioController = new AnnuncioControllerApplicativo();

        // Esecuzione del test
        boolean result = annuncioController.inserisciAnnuncio(copiaMangaBean, prezzo, dataFormattata, username);

        // Verifica dell'output
        assertTrue(result);
    }


}

import controllerapplicativo.AnnuncioControllerApplicativo;
import org.junit.jupiter.api.Test;

import bean.CopiaMangaBean;
import static org.junit.jupiter.api.Assertions.*;

//TEST FATTO DA ANGELO ROMANO

public class TestCercaAnnuncio {

    @Test
    public void testCercaAnnuncio() {
        CopiaMangaBean copiaMangaBean = new CopiaMangaBean();
        copiaMangaBean.setIdCopiaManga(45);

        AnnuncioControllerApplicativo annuncioController = new AnnuncioControllerApplicativo();

        boolean result = annuncioController.cercaAnnuncio(copiaMangaBean);

        // Verifica dell'output
        assertTrue(result);
    }
}

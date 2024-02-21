

import controllerapplicativo.OffertaControllerApplicativo;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.logging.Logger;


import static org.junit.jupiter.api.Assertions.*;
import bean.OffertaBean;
import exceptions.CreditoInsufficienteException;


//TEST FATTO DA ANGELO ROMANO
class TestDoOfferta {

        private static final Logger logger = Logger.getLogger(TestDoOfferta.class.getName());

        @Test
        public void testDoOfferta() {

            OffertaControllerApplicativo offertaController = new OffertaControllerApplicativo();

            OffertaBean offertaBean = new OffertaBean();
            offertaBean.setIdAnnuncio(38);
            offertaBean.setCopiaMangaID(45);
            offertaBean.setUsernameOfferente("utente2");
            offertaBean.setUtenteOfferenteID(2);
            BigDecimal cifra = new BigDecimal("70.0");
            offertaBean.setOffertaPrezzo(cifra);

            try {

                boolean result = offertaController.doOfferta(offertaBean);

                assertTrue(result);
            } catch (CreditoInsufficienteException e) {
                logger.info(e.getMessage());

            }
        }
}

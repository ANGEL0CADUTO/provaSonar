import bean.UtenteBean;
import controllerapplicativo.DepositaEPrelevaApplicativo;
import exceptions.CreditoInsufficienteException;
import org.junit.jupiter.api.Test;
import view.DepositaEPrelevaGrafico;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepositaEPrelevaTesting {

   @Test
   public void depositaWorkCorrectlyWhidValidValue(){
      int result = 0;
       UtenteBean utenteBean = new UtenteBean();
       utenteBean.setIdUtente(1);
       utenteBean.setUsername("leo");
       utenteBean.setCredito(BigDecimal.valueOf(0));

       BigDecimal creditoIniziale= utenteBean.getCredito();

       DepositaEPrelevaApplicativo depositaEPrelevaApplicativo = new DepositaEPrelevaApplicativo();
       String cifraDaDepostiare ="12" ;
       Boolean b = depositaEPrelevaApplicativo.deposita(utenteBean,cifraDaDepostiare);
       BigDecimal creditoFinale = new BigDecimal(cifraDaDepostiare);
       if(b){
           utenteBean.setCredito(utenteBean.getCredito().add(creditoFinale));
           if (utenteBean.getCredito().equals(creditoIniziale.add(creditoFinale))){
               result = 1;
           }
       }else{result =0;}

       assertEquals(1, result);

   }

    @Test
    public void prelevaWorkCorrectlyWhidNoCredit() {
        UtenteBean utenteBean = new UtenteBean();
        utenteBean.setIdUtente(1);
        utenteBean.setUsername("leo");
        utenteBean.setCredito(BigDecimal.valueOf(0));

        String cifraDaPrelevare = "7";

        DepositaEPrelevaApplicativo prelevaApplicativo = new DepositaEPrelevaApplicativo();

        // Verifica che venga lanciata l'eccezione CreditoInsufficienteException
        assertThrows(CreditoInsufficienteException.class, () -> {
            prelevaApplicativo.preleva(utenteBean, cifraDaPrelevare);
        });
    }







    }



import bean.UtenteBean;
import controllerapplicativo.DepositaEPrelevaApplicativo;
import exceptions.CreditoInsufficienteException;
import org.junit.jupiter.api.Test;
import view.DepositaEPrelevaGrafico;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepositaEPrelevaTesting {

/*Leonardo Mancuso 0287194*/

    @Test
   public void depositaWorkCorrectlyWithValidValue(){
      int result = 0;
       UtenteBean utenteBean = new UtenteBean();
       utenteBean.setIdUtente(1);
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
    public void exceptionPrelevaWorkCorrectly() {
        int result = -1;
        UtenteBean utenteBean = new UtenteBean();
        utenteBean.setIdUtente(2);


        String cifraDaPrelevare ="12" ;

        DepositaEPrelevaApplicativo prelevaApplicativo = new DepositaEPrelevaApplicativo();

        try{
           Boolean b= prelevaApplicativo.preleva(utenteBean,cifraDaPrelevare);
            if(b){result =0;}

        }catch (CreditoInsufficienteException e){result = 1;}

        assertEquals(1, result);

    }

    @Test
    public void prelevaWorkCorrectly(){
        int result = -1;
        UtenteBean utenteBean = new UtenteBean();
        utenteBean.setIdUtente(2);


        String cifraDaPrelevare ="8" ;

        DepositaEPrelevaApplicativo prelevaApplicativo = new DepositaEPrelevaApplicativo();

        try{
            Boolean b= prelevaApplicativo.preleva(utenteBean,cifraDaPrelevare);
            if(b){result =1;}

        }catch (CreditoInsufficienteException e){result = 0;}

        assertEquals(1, result);



    }








    }



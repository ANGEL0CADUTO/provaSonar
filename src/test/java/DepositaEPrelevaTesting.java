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
/*
    @Test//Questo test controlla il funzionamento del deposito del credito.
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
               result = 1; //Se il credito dopo il deposito corrisponde alla somma del cerdito iniziale e del credito finale il test ha succeso
           }
       }else{result =0;} // in caso il credito non corrisponda il test fallisce

       assertEquals(1, result);

   }

    @Test//Questo è un test che controlla il corretto funzionamento dell'eccezione durante il prelievo credito
    public void exceptionPrelevaWorkCorrectly() {
        int result = -1;
        UtenteBean utenteBean = new UtenteBean();
        utenteBean.setIdUtente(2);


        String cifraDaPrelevare ="10000" ;

        DepositaEPrelevaApplicativo prelevaApplicativo = new DepositaEPrelevaApplicativo();

        try{
           Boolean b= prelevaApplicativo.preleva(utenteBean,cifraDaPrelevare);
            if(b){result =0;}//Se il prelievo va a buon fine il test fallisce.

        }catch (CreditoInsufficienteException e){result = 1;}//Se si verifica l'eccezione il test ha successo.

        assertEquals(1, result);

    }

    @Test//Questo test controlla il funzionamento del prelievo del credito.
    public void prelevaWorkCorrectly(){
        int result = -1;
        UtenteBean utenteBean = new UtenteBean();
        utenteBean.setIdUtente(2);


        String cifraDaPrelevare ="8" ;

        DepositaEPrelevaApplicativo prelevaApplicativo = new DepositaEPrelevaApplicativo();

        try{
            Boolean b= prelevaApplicativo.preleva(utenteBean,cifraDaPrelevare);
            if(b){result =1;} //Se il prelievo va a buon fine il test ha successo.

        }catch (CreditoInsufficienteException e){result = 0;} //Se si verifica l'eccezione il test fallisce.

        assertEquals(1, result);



    }*/








    }



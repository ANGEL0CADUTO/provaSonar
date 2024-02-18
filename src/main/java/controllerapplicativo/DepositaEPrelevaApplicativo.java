package controllerapplicativo;

import bean.UtenteBean;
import dao.UtenteDAO;
import model.UtenteModel;

import java.math.BigDecimal;

public class DepositaEPrelevaApplicativo {

    public boolean deposita(UtenteBean bean, String cifra ){
        UtenteDAO depositaCredito = new UtenteDAO();
        UtenteModel model = new UtenteModel();
        model.setIdUtente(bean.getIdUtente());

        return depositaCredito.userDeposit(model,cifra);
    }

    public boolean preleva(UtenteBean bean, String cifraString){
        UtenteDAO prelevaCredito = new UtenteDAO();
        UtenteModel model = new UtenteModel();
        model.setIdUtente(bean.getIdUtente());

        BigDecimal cifra = new BigDecimal(cifraString);
        if(prelevaCredito.checkCreditoSufficienteByUtenteID(model.getIdUtente(),cifra)){
            return prelevaCredito.userPreliev(model,cifraString);
        }
        return false;
    }


}

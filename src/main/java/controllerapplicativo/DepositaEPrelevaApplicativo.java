package controllerapplicativo;

import bean.UtenteBean;
import dao.UtenteDAO;
import model.UtenteModel;

public class DepositaEPrelevaApplicativo {

    public boolean Deposita(UtenteBean bean,String cifra ){
        UtenteDAO depositaCredito = new UtenteDAO();
        UtenteModel model = new UtenteModel();
        model.setIdUtente(bean.getIdUtente());

        return depositaCredito.userDeposit(model,cifra);
    }

    public boolean Preleva(UtenteBean bean,String cifraString){
        UtenteDAO prelevaCredito = new UtenteDAO();
        UtenteModel model = new UtenteModel();
        model.setIdUtente(bean.getIdUtente());
        return prelevaCredito.userPreliev(model,cifraString);
    }


}

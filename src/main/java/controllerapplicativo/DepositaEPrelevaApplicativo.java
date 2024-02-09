package controllerapplicativo;

import bean.UtenteBean;
import dao.UtenteDAO;

public class DepositaEPrelevaApplicativo {

    public boolean Deposita(UtenteBean bean){
        UtenteDAO depositaCredito = new UtenteDAO();
        return depositaCredito.userDeposit(bean);
    }

    public boolean Preleva(UtenteBean bean){
        UtenteDAO prelevaCredito = new UtenteDAO();
        return prelevaCredito.userPreliev(bean);
    }


}
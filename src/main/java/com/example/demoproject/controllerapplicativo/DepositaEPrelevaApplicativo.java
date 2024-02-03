package com.example.demoproject.controllerapplicativo;

import com.example.demoproject.bean.UtenteBean;
import com.example.demoproject.dao.UtenteDAO;

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

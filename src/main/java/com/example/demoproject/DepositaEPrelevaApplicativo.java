package com.example.demoproject;

public class DepositaEPrelevaApplicativo {

    public boolean Deposita(UtenteBean bean){
        UtenteDAO depositaCredito = new UtenteDAO();
        return depositaCredito.userDeposit(bean);//METODO NEL DAO SI STA FACENDO
    }


}
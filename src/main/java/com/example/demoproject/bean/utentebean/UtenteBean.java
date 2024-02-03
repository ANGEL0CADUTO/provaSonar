package com.example.demoproject.bean.utentebean;

import com.example.demoproject.adapter.ValutaAdapter;

import java.math.BigDecimal;

public class UtenteBean {

    private int idUtente;//NON VOGLIO CAMBIARE IL DB ADESSO, DA RIMUOVERE E METTERE COME CHIAVE PRIMARIA DIRETTAMENTE L'EMAIL RICORDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    private String username;
    private String email;
    private String password;

    private double votoRecensione;

    private BigDecimal credito;

    private ValutaAdapter adattatore;//IMPLEMENTAZIONE ADAPTER
    private boolean isLogged;
    public UtenteBean() {//DA USARE PER SESSIONE
        this.isLogged = false;    }

    public UtenteBean(ValutaAdapter adattatore){this.adattatore=adattatore;}// PER ACCETTARE ADATTATORE



    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }



    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getVotoRecensione() {
        return votoRecensione;
    }

    public void setVotoRecensione(double votoRecensione) {
        this.votoRecensione = votoRecensione;
    }

    public BigDecimal getCredito() {
        return credito ;
    }// ADAPTER CONVERTE adattatore.convertiCredito(credito)

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }
}


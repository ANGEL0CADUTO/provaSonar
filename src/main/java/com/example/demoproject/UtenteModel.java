package com.example.demoproject;

import java.math.BigDecimal;

public class UtenteModel {

    private int idUtente;
    private String email;
    private String username;
    private String password;
    private Double votoRecensioni;
    private BigDecimal credito;

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getVotoRecensioni() {
        return votoRecensioni;
    }

    public void setVotoRecensioni(Double votoRecensioni) {
        this.votoRecensioni = votoRecensioni;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }
}

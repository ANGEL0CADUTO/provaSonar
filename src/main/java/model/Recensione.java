package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Recensione {

    private int idRecensioni;

    private double voto;
    private String testo;
    private String titoloRecensito;
    private int volumeRecensito;
    private LocalDateTime dataAcquisto;

    private BigDecimal prezzoIniziale;
    private BigDecimal prezzoFinale;

    public String getTitoloRecensito() {
        return titoloRecensito;
    }

    public void setTitoloRecensito(String titoloRecensito) {
        this.titoloRecensito = titoloRecensito;
    }

    public int getVolumeRecensito() {
        return volumeRecensito;
    }

    public void setVolumeRecensito(int volumeRecensito) {
        this.volumeRecensito = volumeRecensito;
    }

    public LocalDateTime getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDateTime dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public BigDecimal getPrezzoIniziale() {
        return prezzoIniziale;
    }

    public void setPrezzoIniziale(BigDecimal prezzoIniziale) {
        this.prezzoIniziale = prezzoIniziale;
    }

    public BigDecimal getPrezzoFinale() {
        return prezzoFinale;
    }

    public void setPrezzoFinale(BigDecimal prezzoFinale) {
        this.prezzoFinale = prezzoFinale;
    }

    private int utenteRecensoreID;
    private String usernameRecensore;
    private int stato;

    public int getIdRecensioni() {
        return idRecensioni;
    }

    public void setIdRecensioni(int idRecensioni) {
        this.idRecensioni = idRecensioni;
    }


    public double getVoto() {
        return voto;
    }

    public void setVoto(double voto) {
        this.voto = voto;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getUtenteRecensoreID() {
        return utenteRecensoreID;
    }

    public void setUtenteRecensoreID(int utenteRecensoreID) {
        this.utenteRecensoreID = utenteRecensoreID;
    }

    public String getUsernameRecensore() {
        return usernameRecensore;
    }

    public void setUsernameRecensore(String usernameRecensore) {
        this.usernameRecensore = usernameRecensore;
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }
}

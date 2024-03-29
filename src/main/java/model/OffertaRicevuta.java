package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


//MODEL PER UNA OFFERTA RICEVUTA PER UN ANNUNCIO
public class OffertaRicevuta {

    private int idOfferta;
    private int annuncioID;
    private int utenteOfferenteID;
    private int copiaMangaID;

    private String titoloManga;
    private int volumeManga;

    public String getTitoloManga() {
        return titoloManga;
    }

    public void setTitoloManga(String titoloManga) {
        this.titoloManga = titoloManga;
    }

    public int getVolumeManga() {
        return volumeManga;
    }

    public void setVolumeManga(int volumeManga) {
        this.volumeManga = volumeManga;
    }

    private String usernameOfferente;
    private BigDecimal offertaPrezzo;
    private double votoRecensioni;

    private LocalDateTime dataOfferta;
    private LocalDateTime dataVendita;

    public int getUtenteOfferenteID() {
        return utenteOfferenteID;
    }

    public void setUtenteOfferenteID(int utenteOfferenteID) {
        this.utenteOfferenteID = utenteOfferenteID;
    }

    public int getCopiaMangaID() {
        return copiaMangaID;
    }

    public void setCopiaMangaID(int copiaMangaID) {
        this.copiaMangaID = copiaMangaID;
    }


    public LocalDateTime getDataVendita() {
        return dataVendita;
    }

    public void setDataVendita(LocalDateTime dataVendita) {
        this.dataVendita = dataVendita;
    }

    public int getIdOfferta() {
        return idOfferta;
    }

    public void setIdOfferta(int idOfferta) {
        this.idOfferta = idOfferta;
    }


    public double getVotoRecensioni() {
        return votoRecensioni;
    }

    public void setVotoRecensioni(double votoRecensioni) {
        this.votoRecensioni = votoRecensioni;
    }


    public int getAnnuncioID() {
        return annuncioID;
    }

    public void setAnnuncioID(int annuncioID) {
        this.annuncioID = annuncioID;
    }

    public String getUsernameOfferente() {
        return usernameOfferente;
    }

    public void setUsernameOfferente(String usernameOfferente) {

        this.usernameOfferente = usernameOfferente;
    }

    public BigDecimal getOffertaPrezzo() {
        return offertaPrezzo;
    }

    public void setOffertaPrezzo(BigDecimal offertaPrezzo) {
        this.offertaPrezzo = offertaPrezzo;
    }

    public LocalDateTime getDataOfferta() {
        return dataOfferta;
    }

    public void setDataOfferta(LocalDateTime dataOfferta) {
        this.dataOfferta = dataOfferta;
    }


}



package model;

import java.util.Date;

public class OffertaModel {

    private int annuncioID;
    private int utenteOfferenteID;
    private double offertaPrezzo;

    private String dataOfferta;
    private int statoOfferta;

    public int getAnnuncioID() {
        return annuncioID;
    }

    public void setAnnuncioID(int annuncioID) {
        this.annuncioID = annuncioID;
    }

    public int getUtenteOfferenteID() {
        return utenteOfferenteID;
    }

    public void setUtenteOfferenteID(int utenteOfferenteID) {
        this.utenteOfferenteID = utenteOfferenteID;
    }

    public double getOffertaPrezzo() {
        return offertaPrezzo;
    }

    public void setOffertaPrezzo(double offertaPrezzo) {
        this.offertaPrezzo = offertaPrezzo;
    }

    public String getDataOfferta() {
        return dataOfferta;
    }

    public void setDataOfferta(String dataOfferta) {
        this.dataOfferta = dataOfferta;
    }

    public int getStatoOfferta() {
        return statoOfferta;
    }

    public void setStatoOfferta(int statoOfferta) {
        this.statoOfferta = statoOfferta;
    }
}

package model;

import java.math.BigDecimal;
import java.util.Date;

public class OffertaModel {

    private int annuncioID;
    private int utenteOfferenteID;
    private BigDecimal offertaPrezzo;

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

    public BigDecimal getOffertaPrezzo() {
        return offertaPrezzo;
    }

    public void setOffertaPrezzo(BigDecimal offertaPrezzo) {
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

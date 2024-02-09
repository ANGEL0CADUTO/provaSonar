package bean;

import java.math.BigDecimal;

public class OffertaBean {

    private int idAnnuncio;
    private int utenteOfferenteID;

    private BigDecimal offertaPrezzo;


    public int getAnnuncioID() {
        return idAnnuncio;
    }

    public void setAnnuncioID(int annuncioID) {
        this.idAnnuncio = annuncioID;
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

    /*public String getDataOfferta() {
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
    }*/





}

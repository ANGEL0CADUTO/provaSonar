package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AnnuncioModel {
    private int idAnnuncio;

    public int getUtenteVenditoreID() {
        return utenteVenditoreID;
    }

    public void setUtenteVenditoreID(int utenteVenditoreID) {
        this.utenteVenditoreID = utenteVenditoreID;
    }

    private int utenteVenditoreID;
    private String nomeUtente;
    private String nomeManga;
    private int volume;
    private BigDecimal prezzo;
    private LocalDateTime dataAnnuncio;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }



    public LocalDateTime getDataAnnuncio() {
        return dataAnnuncio;
    }

    public void setDataAnnuncio(LocalDateTime dataAnnuncio) {
        this.dataAnnuncio = dataAnnuncio;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getNomeManga() {
        return nomeManga;
    }

    public void setNomeManga(String nomeManga) {
        this.nomeManga = nomeManga;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }




    public int getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setIdAnnuncio(int idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
    }
}

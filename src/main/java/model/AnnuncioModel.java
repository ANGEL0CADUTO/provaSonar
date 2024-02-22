package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//MODEL PER GESTIRE ANNUNCI DI VENDITA DI COPIAMANGA
public class AnnuncioModel {
    private int idAnnuncio;
    private String nomeUtente;
    private int copiaMangaID;
    private int volume;
    private double votoUtente;

    private int utenteVenditoreID;

    private String nomeManga;

    private int numeroOfferte;


    private BigDecimal prezzo;

    public double getVotoUtente() {
        return votoUtente;
    }

    public void setVotoUtente(double votoUtente) {
        this.votoUtente = votoUtente;
    }


    private LocalDateTime dataAnnuncio;

    public int getNumeroOfferte() {
        return numeroOfferte;
    }

    public void setNumeroOfferte(int numeroOfferte) {
        this.numeroOfferte = numeroOfferte;
    }


    public int getCopiaMangaID() {
        return copiaMangaID;
    }

    public void setCopiaMangaID(int copiaMangaID) {
        this.copiaMangaID = copiaMangaID;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getUtenteVenditoreID() {
        return utenteVenditoreID;
    }

    public void setUtenteVenditoreID(int utenteVenditoreID) {
        this.utenteVenditoreID = utenteVenditoreID;
    }


    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
    public void setDataAnnuncio(LocalDateTime dataAnnuncio) {
        this.dataAnnuncio = dataAnnuncio;
    }
    public LocalDateTime getDataAnnuncio() {
        return dataAnnuncio;
    }


    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
    public String getNomeUtente() {
        return nomeUtente;
    }
    public String getNomeManga() {
        return nomeManga;
    }

    public void setNomeManga(String nomeManga) {
        this.nomeManga = nomeManga;
    }






    public int getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setIdAnnuncio(int idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
    }
}

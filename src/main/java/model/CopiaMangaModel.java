package model;

import java.util.Date;

public class CopiaMangaModel {

    private int idCopiaManga;


    //FK da passare a MANGADAO
    private int idUtente;

    private String titolo;
    private int volume;
    private Date dataAcquisto;
    private Date dataVendita;

    private int statoCopiaManga;


    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getIdCopiaManga() {
        return idCopiaManga;
    }

    public void setIdCopiaManga(int idCopiaManga) {
        this.idCopiaManga = idCopiaManga;
    }


    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public Date getDataVendita() {
        return dataVendita;
    }

    public void setDataVendita(Date dataVendita) {
        this.dataVendita = dataVendita;
    }

    public int getStatoCopiaManga() {
        return statoCopiaManga;
    }

    public void setStatoCopiaManga(int statoCopiaManga) {
        this.statoCopiaManga = statoCopiaManga;
    }
}

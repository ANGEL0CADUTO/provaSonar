package model;

import java.time.LocalDateTime;

public class CopiaMangaModel {

    private int idCopiaManga;


    //FK da passare a MANGADAO
    private int idUtente;

    public LocalDateTime getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDateTime dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public LocalDateTime getDataVendita() {
        return dataVendita;
    }

    public void setDataVendita(LocalDateTime dataVendita) {
        this.dataVendita = dataVendita;
    }

    private String titolo;
    private int volume;
    private LocalDateTime dataAcquisto;
    private LocalDateTime dataVendita;

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




    public int getStatoCopiaManga() {
        return statoCopiaManga;
    }

    public void setStatoCopiaManga(int statoCopiaManga) {
        this.statoCopiaManga = statoCopiaManga;
    }
}

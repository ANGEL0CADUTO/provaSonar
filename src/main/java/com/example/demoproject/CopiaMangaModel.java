package com.example.demoproject;

import java.util.Date;

public class CopiaMangaModel {

    private int idCopiaManga;


    //FK da passare a MANGADAO
    private int idManga;

    private String nome;
    private Date dataAcquisto;
    private Date dataVendita;

    //FK da passare al **futuro** statoCopiaMangaDAO
    private int statoCopiaManga;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCopiaManga() {
        return idCopiaManga;
    }

    public void setIdCopiaManga(int idCopiaManga) {
        this.idCopiaManga = idCopiaManga;
    }

    public int getIdManga() {
        return idManga;
    }

    public void setIdManga(int idManga) {
        this.idManga = idManga;
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

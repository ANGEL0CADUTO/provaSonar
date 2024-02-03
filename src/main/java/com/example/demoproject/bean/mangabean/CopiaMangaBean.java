package com.example.demoproject.bean.mangabean;

import java.util.Date;

//DA UTILIZZARE QUANDO UTENTE CLICCA SU UNA COPIAMANGA SPECIFICA X FARE QUALCOSA(COMPRARE,VENDERE)//

public class CopiaMangaBean {
    private int idMangaUtente;

    private int idManga;

    private Date dataAcquisto;

    private Date dataVendita;

    public int getIdMangaUtente() {
        return idMangaUtente;
    }

    public void setIdMangaUtente(int idMangaUtente) {
        this.idMangaUtente = idMangaUtente;
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
}

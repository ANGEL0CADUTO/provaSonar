package com.example.demoproject.bean;


//RAPPRESENTA IL BEAN PER LA SERIE MANGA ( BERSERK, JOJO) MA I SUOI USI SONO PER ORA SBAGLIATI, DOVREBBE ESSERE MODEL
public class MangaBean {

    private int idManga;
    private String nome;
    private String autore;

    private int volume;

    public int getIdManga() {
        return idManga;
    }

    public void setIdManga(int idManga) {
        this.idManga = idManga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}

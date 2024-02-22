package model;
import com.google.gson.annotations.SerializedName;


//MODEL PER GESTIRE LA NOTIFICA
public class NotificaModel {
    @SerializedName("utente")
    private String utente;

    @SerializedName("volume")
    private int volume;

    @SerializedName("venditore")
    private String venditore;

    @SerializedName("manga")
    private String manga;

    @SerializedName("prezzo_offerta")
    private int prezzoOfferta;

    // Costruttore, getter e setter...

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getVenditore() {
        return venditore;
    }

    public void setVenditore(String venditore) {
        this.venditore = venditore;
    }

    public String getManga() {
        return manga;
    }

    public void setManga(String manga) {
        this.manga = manga;
    }

    public int getPrezzoOfferta() {
        return prezzoOfferta;
    }

    public void setPrezzoOfferta(int prezzoOfferta) {
        this.prezzoOfferta = prezzoOfferta;
    }
}

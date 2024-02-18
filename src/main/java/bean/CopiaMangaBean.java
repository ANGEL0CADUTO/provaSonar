package bean;

import java.time.LocalDateTime;
import java.util.Date;

//DA UTILIZZARE QUANDO UTENTE CLICCA SU UNA COPIAMANGA SPECIFICA X FARE QUALCOSA(COMPRARE,VENDERE)//

public class CopiaMangaBean {

    private int idCopiaManga;
    private int idUtente;

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    private String titolo;
    private int volume;

    private LocalDateTime dataAcquisto;

    private Date dataVendita;

    public int getIdCopiaManga() {
        return idCopiaManga;
    }

    public void setIdCopiaManga(int idCopiaManga) {
        this.idCopiaManga = idCopiaManga;
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

    public LocalDateTime getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDateTime dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public Date getDataVendita() {
        return dataVendita;
    }

    public void setDataVendita(Date dataVendita) {
        this.dataVendita = dataVendita;
    }
}

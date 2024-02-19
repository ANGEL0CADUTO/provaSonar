package bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OffertaBean {

    private int idOfferta;



    private int copiaMangaID;
    private String titoloManga;
    private int idAnnuncio;
    private int utenteOfferenteID;

    private int volumeManga;

    public String getTitoloManga() {
        return titoloManga;
    }

    public void setTitoloManga(String titoloManga) {
        this.titoloManga = titoloManga;
    }
    public int getIdOfferta() {
        return idOfferta;
    }

    public void setIdOfferta(int idOfferta) {
        this.idOfferta = idOfferta;
    }
    public int getVolumeManga() {
        return volumeManga;
    }



    public int getCopiaMangaID() {
        return copiaMangaID;
    }
    public void setVolumeManga(int volumeManga) {
        this.volumeManga = volumeManga;
    }
    public void setCopiaMangaID(int copiaMangaID) {
        this.copiaMangaID = copiaMangaID;
    }

    private String usernameOfferente;
    private LocalDateTime dataOfferta;

    public int getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setIdAnnuncio(int idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
    }

    public String getUsernameOfferente() {
        return usernameOfferente;
    }

    public void setUsernameOfferente(String usernameOfferente) {
        this.usernameOfferente = usernameOfferente;
    }

    public LocalDateTime getDataOfferta() {
        return dataOfferta;
    }

    public void setDataOfferta(LocalDateTime dataOfferta) {
        this.dataOfferta = dataOfferta;
    }

    private BigDecimal offertaPrezzo;




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

}

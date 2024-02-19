package model;

import observer.OffertaSubject;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OffertaModel extends OffertaSubject {//CONCRETE SUBJECT
    /*mantiene lo stato del soggetto osservato e notifica gli observer in caso di un cambio di stato
 Invoca le operazioni di notifica ereditate dal Subject, quando devono essere informati i ConcreteObserver.*/

    private int idOfferta;
    private int annuncioID;
    private int copiaMangaID;


    private int utenteOfferenteID;
    private String usernameOfferente;
    private BigDecimal offertaPrezzo;

    private String titoloManga;

    private int volumeManga;

    private LocalDateTime dataOfferta;

    private int recensito;
    private int statoOfferta;

    private int utenteVenditoreID;
    public int getCopiaMangaID() {
        return copiaMangaID;
    }

    public void notificaCambiamentiAObservers(){
        super.notificaObservers();}

    public void setCopiaMangaID(int copiaMangaID) {
        this.copiaMangaID = copiaMangaID;
    }

    public int getUtenteVenditoreID() {
        return utenteVenditoreID;
    }

    public void setUtenteVenditoreID(int utenteVenditoreID) {
        this.utenteVenditoreID = utenteVenditoreID;
    }

    public int getIdOfferta() {
        return idOfferta;
    }

    public void setIdOfferta(int idOfferta) {
        this.idOfferta = idOfferta;
    }


    public String getTitoloManga() {
        return titoloManga;
    }

    public void setTitoloManga(String titoloManga) {
        this.titoloManga = titoloManga;
    }

    public int getVolumeManga() {
        return volumeManga;
    }

    public void setVolumeManga(int volumeManga) {
        this.volumeManga = volumeManga;
    }



    public int getRecensito() {
        return recensito;
    }

    public void setRecensito(int recensito) {
        this.recensito = recensito;
    }


    public String getUsernameOfferente() {
        return usernameOfferente;
    }

    public void setUsernameOfferente(String usernameOfferente) {
        this.usernameOfferente = usernameOfferente;
    }



    public int getAnnuncioID() {
        return annuncioID;
    }

    public void setAnnuncioID(int annuncioID) {
        this.annuncioID = annuncioID;
    }

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

    public LocalDateTime getDataOfferta() {
        return dataOfferta;
    }

    public void setDataOfferta(LocalDateTime dataOfferta) {
        this.dataOfferta = dataOfferta;
    }

    public int getStatoOfferta() {
        return statoOfferta;
    }

    public void setStatoOfferta(int statoOfferta) {
        this.statoOfferta = statoOfferta;
    }


}

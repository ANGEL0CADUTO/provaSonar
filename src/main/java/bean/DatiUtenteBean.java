package bean;

public class DatiUtenteBean {
    // DOVE METTO TUTTI I DATI RELATIVI ALL'UTENTE.

    private String indirizzo;
    private String civico;
    private String cap;

    private int idInformazioniUtente;





    public void setIndirizzo(String indirizzo){
         this.indirizzo=indirizzo;}

    public String getIndirizzo() {
        return indirizzo;}

    public void setCivico(String civico){
        this.civico=civico;}

    public String getCivico() {
        return civico;}

    public void setCap(String cap){
        this.cap=cap;
    }

    public String getCap() {
        return cap;}

    public int getIdInformazioniUtente() {
        return idInformazioniUtente;
    }

    public void setIdInformazioniUtente(int idInformazioniUtente) {
        this.idInformazioniUtente = idInformazioniUtente;
    }






}

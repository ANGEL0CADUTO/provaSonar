package model;

//MODEL PER GESTIRE I DATI DELL'UTENTE
public class DatiUtente {

        private String cap;

        private int idInformazioniUtente;

        private String indirizzo;
        private String civico;

        public int getIdInformazioniUtente() {
            return idInformazioniUtente;
        }

        public void setIdInformazioniUtente(int idInformazioniUtente) {
            this.idInformazioniUtente = idInformazioniUtente;
        }

        public void setCivico(String civico){
            this.civico=civico;}

        public String getCivico() {
            return civico;}

        public void setCap(String cap){
            this.cap=cap;
        }

        public void setIndirizzo(String indirizzo){
            this.indirizzo=indirizzo;}

        public String getIndirizzo() {
            return indirizzo;}



        public String getCap() {
            return cap;}




}

package bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//BEAN PER GESTIRE ANNUNCI DI VENDITA DI COPIAMANGA
public class AnnuncioBean {

        private int idAnnuncio;


        private int numeroOfferte;
        private int utenteVenditoreID;
        private String nomeUtente;
        private String nomeManga;
        private int volume;
        private int copiaMangaID;
        private double votoUtente;

        private BigDecimal prezzo;

        public double getVotoUtente() {
            return votoUtente;
        }

        public void setVotoUtente(double votoUtente) {
            this.votoUtente = votoUtente;
        }

        private LocalDateTime dataAnnuncio;

        public int getUtenteVenditoreID() {
            return utenteVenditoreID;
        }

        public void setUtenteVenditoreID(int utenteVenditoreID) {
            this.utenteVenditoreID = utenteVenditoreID;
        }


        public int getCopiaMangaID() {
            return copiaMangaID;
        }

        public void setCopiaMangaID(int copiaMangaID) {
            this.copiaMangaID = copiaMangaID;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }


        public int getNumeroOfferte() {
            return numeroOfferte;
        }

        public void setNumeroOfferte(int numeroOfferte) {
            this.numeroOfferte = numeroOfferte;
        }


        public void setDataAnnuncio(LocalDateTime dataAnnuncio) {
            this.dataAnnuncio = dataAnnuncio;
        }
        public LocalDateTime getDataAnnuncio() {
        return dataAnnuncio;
    }
        public String getNomeUtente() {
            return nomeUtente;
        }

        public void setNomeUtente(String nomeUtente) {
            this.nomeUtente = nomeUtente;
        }

        public String getNomeManga() {
            return nomeManga;
        }

        public void setNomeManga(String nomeManga) {
            this.nomeManga = nomeManga;
        }

        public BigDecimal getPrezzo() {
            return prezzo;
        }

        public void setPrezzo(BigDecimal prezzo) {
            this.prezzo = prezzo;
        }






        public void setIdAnnuncio(int idAnnuncio) {
            this.idAnnuncio = idAnnuncio;
        }

        public int getIdAnnuncio() {
        return idAnnuncio;
    }
}

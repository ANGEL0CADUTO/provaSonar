package bean;


//BEAN PER GESTIRE LA RECENSIONE AD UN UTENTE
public class RecensioneBean {
    private int idOfferta;
    private int recensitoID;
    private int votoRecensione;
    private String testo;

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getVotoRecensione() {
        return votoRecensione;
    }

    public void setVotoRecensione(int votoRecensione) {
        this.votoRecensione = votoRecensione;
    }

    public int getIdOfferta() {
        return idOfferta;
    }

    public void setIdOfferta(int idOfferta) {
        this.idOfferta = idOfferta;
    }

    public int getRecensitoID() {
        return recensitoID;
    }

    public void setRecensitoID(int recensitoID) {
        this.recensitoID = recensitoID;
    }
}

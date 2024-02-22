package model;



import java.math.BigDecimal;


//MODEL PER GESTIRE LE INFORMAZIONI DELL' UTENTE
public class UtenteModel {

    public int getInformazioniUtenteID() {
        return informazioniUtenteID;
    }

    public void setInformazioniUtenteID(int informazioniUtenteID) {
        this.informazioniUtenteID = informazioniUtenteID;
    }

    public UtenteModel(int idUtente, String email, String username, Double votoRecensioni, BigDecimal credito, int id) {
        this.idUtente = idUtente;
        this.email = email;
        this.username = username;
        this.votoRecensioni = votoRecensioni;
        this.credito = credito;
        this.informazioniUtenteID = id;
    }
    public UtenteModel() {

    }

    private int idUtente;
    private String email;
    private String username;

    private String password;



    private Double votoRecensioni;
    private BigDecimal credito;
    private int informazioniUtenteID;

    private DatiUtente datiUtente;

    public DatiUtente getDatiUtente() {
        return datiUtente;
    }

    public void setDatiUtente(DatiUtente datiUtente) {
        this.datiUtente = datiUtente;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Double getVotoRecensioni() {
        return votoRecensioni;
    }

    public void setVotoRecensioni(Double votoRecensioni) {
        this.votoRecensioni = votoRecensioni;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

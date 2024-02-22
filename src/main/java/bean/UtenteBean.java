package bean;


import java.math.BigDecimal;


//BEAN PER GESTIRE LE INFORMAZIONI RELATIVE ALL'UTENTE ALL'INTERNO DELL'APLICAZIONE
public class UtenteBean {

    private int idUtente;
    private String username;
    private String email;
    private String password;

    private double votoRecensione;

    private BigDecimal credito;

    private DatiUtenteBean datiUtente;

    public DatiUtenteBean getDatiUtente() {
        return datiUtente;
    }

    public void setDatiUtente(DatiUtenteBean datiUtente) {
        this.datiUtente = datiUtente;
    }


    private boolean isLogged;
    public UtenteBean() {
        this.isLogged = false;    }





    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }



    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getVotoRecensione() {
        return votoRecensione;
    }

    public void setVotoRecensione(double votoRecensione) {
        this.votoRecensione = votoRecensione;
    }

    public BigDecimal getCredito() {
        return credito ;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }
}


package com.example.demoproject;

public class UtenteBean {

    private int idUtente;//NON VOGLIO CAMBIARE IL DB ADESSO, DA RIMUOVERE E METTERE COME CHIAVE PRIMARIA DIRETTAMENTE L'EMAIL RICORDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    private String username;
    private String email;
    private String password;
    private String ruolo;

    public UtenteBean(){//DA USARE PER SESSIONE
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

    public String getRuolo(){
        return ruolo;
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


    public void setRuolo(String newRuolo){
        this.ruolo = newRuolo;
    }
}

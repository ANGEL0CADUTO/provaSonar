package dao;

import exceptions.CredenzialiSbagliateException;
import exceptions.CreditoInsufficienteException;
import exceptions.UtenteNonRegistratoException;
import model.Credenziali;
import model.UtenteModel;


import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Logger;

public class UtenteDAO implements UtenteDaoInterface {
    private static final Logger logger = Logger.getLogger(UtenteDAO.class.getName());
    private static final String CREDITO = "credito";


    //LOGIN, VERIFICA DELLE CREDENZIALI INSERITE
    public UtenteModel searchUser(Credenziali login) throws CredenzialiSbagliateException {

        String query = "SELECT * FROM mangaink.utente WHERE email = ?";

        int id = 0;
        String username = "";
        BigDecimal credito = new BigDecimal(0);
        double voto = 0;
        int informazioniUtenteID = 0;


        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, login.getEmail());

            ResultSet rs = st.executeQuery();
            if (rs != null && rs.next() && rs.getString("password").equals(login.getPassword())) {

                id = rs.getInt("idUtente");
                username = rs.getString("username");
                credito = rs.getBigDecimal(CREDITO);
                voto = rs.getDouble("votoRecensioni");
                informazioniUtenteID = rs.getInt("informazioniUtenteID");

            } else {
                logger.info("fallito");
                throw new CredenzialiSbagliateException();
            }
        } catch (SQLException e) {
            logger.severe("Errore nel tentativo di stabilire la connessione in searchUser: " + e.getMessage());
        }

        return new UtenteModel(id, login.getEmail(), username, voto, credito, informazioniUtenteID);
    }


    //REGISTRA, AGGIUNTA DEL NUOVO UTENTE UTILIZZANDO LE CREDENZIALI INSERITE
    public boolean addUser(UtenteModel model) throws UtenteNonRegistratoException {
        Connection conn = DBConnection.getIstance().connection();

        String query = "INSERT INTO mangaink.utente (email, username ,password ) VALUES (?, ?, ?)";
        boolean b = false;
        try (PreparedStatement st = conn.prepareStatement(query)) {


            if (model.getEmail().isEmpty() || model.getUsername().isEmpty() || model.getPassword().isEmpty()) {
                logger.warning("Uno o più campi sono vuoti. Inserimento utente fallito.");
                return false;
            }

            st.setString(1, model.getEmail());
            st.setString(2, model.getUsername());
            st.setString(3, model.getPassword());

            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {
                b = true;
                logger.info("Inserimento utente riuscito");
            } else {
                logger.info("Inserimento utente fallito");
                throw new UtenteNonRegistratoException();
            }
        } catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nell'addUser in utenteDao " + e.getMessage());
        }

        return b;
    }


    //AGGIORNAMENTO DELL'UTENTE ASSEGNANDOGLI L'ID DELLE INFORMAZIONI DI CONSEGNA
    public boolean informazioniUtente(UtenteModel model) {
        Boolean b = false;
        Connection conn = DBConnection.getIstance().connection();
        String query = "UPDATE mangaink.utente " +
                "SET informazioniUtenteID = (SELECT MAX(idInformazioniUtente) FROM mangaink.informazioniutente) " +
                "WHERE email = ?";

        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setString(1, model.getEmail());
            int righeScritte = st.executeUpdate();
            if (righeScritte > 0) {

                b = true;
            } else {
                logger.info("Accoppiamente Fallito");
            }
        } catch (SQLException e) {
            logger.severe("Errore in UtenteDAO in informazioneUtente " + e.getMessage());

        }

        return b;

    }

    //DEPOSITA CIFRA NEL CREDITO DELL'UTENTE
    public boolean userDeposit(UtenteModel utenteModel, String cifraString){
        Boolean b = false;
        Connection conn = DBConnection.getIstance().connection();
        String query = "UPDATE mangaink.utente SET credito= credito +  ? " +
                "WHERE idUtente =? ";

        try (PreparedStatement st = conn.prepareStatement(query)) {
            BigDecimal cifra = new BigDecimal(cifraString);
            st.setBigDecimal(1, cifra);
            st.setInt(2, utenteModel.getIdUtente());

            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {

                b = true;
            } else {
                logger.info("Deposito Credito Fallito");
            }

        } catch (SQLException e) {
            //  e.printStackTrace(); PER FARMI DIRE L'ERRORE COMPLETO
            logger.severe("Errore in UtenteDAO in userDeposit " + e.getMessage());
        }

        return b;


    }

    //PRELIEVO DAL CREDITO DELL'UTENTE DELLA CIFRA INSERITA
    public boolean userPreliev(UtenteModel model, String cifraString) {

        Boolean b = false;
        Connection conn = DBConnection.getIstance().connection();
        String query = "UPDATE mangaink.utente SET credito= credito - ?  \n" +
                "WHERE idUtente = ? ";


        try (PreparedStatement st = conn.prepareStatement(query)) {

            BigDecimal cifra = new BigDecimal(cifraString);
            st.setBigDecimal(1, cifra);//SONO LEGATI ALLA QUERY
            st.setInt(2, model.getIdUtente());

            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {
                b = true;
                logger.info("Prelievo Credito Riuscito");

            } else {
                logger.info("Prelievo Credito Fallito");

            }


        } catch (SQLException e) {
            //  e.printStackTrace(); PER FARMI DIRE L'ERRORE COMPLETO
            logger.severe("Errore in UtenteDAO in userPreliev " + e.getMessage());
        }

        return b;


    }

    //RICAVA IL VOTO COMPLESSIVO DELL'UTENTE (ricavata come media di tutte le recensioni ricevute)
    public double getVotoByUtenteID(int id) {
        String query = "SELECT votoRecensioni FROM utente WHERE idUtente = ?";

        double voto = 0;
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                voto = rs.getDouble(1);

            }
        } catch (SQLException e) {
            logger.severe("Errore in UtenteDAO in getVotoByUtenteID: " + e.getMessage());
        }

        return voto;
    }


    //METODO AUSILIARIO PER RICAVARSI ANCHE CREDITO IN PIU AL VOTO
    public UtenteModel getVotoAndCreditoByUtenteID(int id) {
        String query = "SELECT votoRecensioni,credito FROM utente WHERE idUtente = ?";


        Connection conn = DBConnection.getIstance().connection();
        UtenteModel model = null;

        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                model = new UtenteModel();
                model.setVotoRecensioni(rs.getDouble(1));
                model.setCredito(rs.getBigDecimal(CREDITO));

            }
        } catch (SQLException e) {
            logger.severe("Errore in UtenteDAO in getVotoByUtenteID: " + e.getMessage());
        }

        return model;
    }


    //AGGIORNA VOTO DELL'UTENTE DOPO UNA NUOVA RECENSIONE RICEVUTA
    public boolean aggiornaVotoByUtenteID(int id, double votoNuovo) {
        String query = "UPDATE utente SET votoRecensioni = ? WHERE idUtente = ?";
        Connection conn = DBConnection.getIstance().connection();
        boolean b = false;

        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setDouble(1, votoNuovo);
            st.setInt(2, id);

            int righeModificate = st.executeUpdate();  // Cambiato qui

            if (righeModificate > 0) {
                b = true;
            }

        } catch (SQLException e) {
            logger.severe("Errore in UtenteDAO in aggiornaVotoByUtenteID: " + e.getMessage());
        }
        return b;
    }

    public boolean checkCreditoSufficienteByUtenteID(int id, BigDecimal cifra) throws CreditoInsufficienteException {
        String query = "SELECT credito FROM utente WHERE idUtente = ?";

        Connection conn = DBConnection.getIstance().connection();


        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                BigDecimal creditoAttuale = rs.getBigDecimal(CREDITO);
                if (creditoAttuale.compareTo(cifra) < 0) {
                    throw new CreditoInsufficienteException();
                }
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Errore in UtenteDAO in checkCreditoSufficienteByUtenteID: " + e.getMessage());
        }
        return false;
    }


}



















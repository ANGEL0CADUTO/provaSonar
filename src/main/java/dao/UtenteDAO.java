package dao;

import bean.DatiUtenteBean;
import bean.UtenteBean;
import model.UtenteModel;


import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Logger;

public class UtenteDAO {
    private static final Logger logger = Logger.getLogger(UtenteDAO.class.getName());

    public boolean searchUser(UtenteBean bean) {
        boolean b = false;
        String query = "SELECT * FROM mangaink.utente WHERE email = ?";
        Connection conn;

        try {
            conn = DBConnection.getIstance().connection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, bean.getEmail()); // Aggiungi il parametro della email
            ResultSet rs = st.executeQuery();


            if (rs != null && rs.next() && rs.getString("password").equals(bean.getPassword())) {


                bean.setIdUtente(rs.getInt("idUtente"));
                bean.setUsername(rs.getString("username"));
                bean.setCredito(rs.getBigDecimal( "credito"));
                bean.setVotoRecensione(rs.getDouble("votoRecensioni"));

                int informazioniUtenteID = rs.getInt("informazioniUtenteID");
                if (!rs.wasNull()) {
                    DatiUtenteDao dao = new DatiUtenteDao();
                    DatiUtenteBean beanDati;

                    beanDati = dao.getDatiUserByInformazioniUtenteID(rs.getInt("informazioniUtenteID"));
                    beanDati.setIdInformazioniUtente(informazioniUtenteID);
                    bean.setDatiUtente(beanDati);
                }


                logger.info("ha funzionato");
                b = true;
            } else {
                logger.info("fallito");
            }

        } catch (SQLException e) {
            logger.severe("Errore nel tentativo di stabilire la connessione in searchUser: " + e.getMessage());
        }

        return b;
    }

    public boolean addUser(UtenteBean bean) {
        Connection conn = DBConnection.getIstance().connection();

        String query = "INSERT INTO mangaink.utente (email, username ,password ) VALUES (?, ?, ?)";
        boolean b = false;
        try (PreparedStatement st = conn.prepareStatement(query)) {

            if (bean.getEmail().isEmpty() || bean.getUsername().isEmpty() || bean.getPassword().isEmpty()) {
                logger.warning("Uno o più campi sono vuoti. Inserimento utente fallito.");
                return false;
            }

            st.setString(1, bean.getEmail());
            st.setString(2, bean.getUsername());
            st.setString(3, bean.getPassword());

            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {
                b = true;
                logger.info("Inserimento utente riuscito");
            } else {
                logger.info("Inserimento utente fallito");
            }
        } catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nell'addUser in utenteDao " + e.getMessage());
        }

        return b;
    }


    public boolean informazioniUtente(UtenteBean bean) {
        Boolean b = false;
        Connection conn = DBConnection.getIstance().connection();
        String query = "UPDATE mangaink.utente " +
                "SET informazioniUtenteID = (SELECT MAX(idInformazioniUtente) FROM mangaink.informazioniutente) " +
                "WHERE email = ?";

        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setString(1, bean.getEmail());
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


    public boolean userDeposit(UtenteModel utenteModel, String cifraString) { //DEPOSITA IL TUO CREDITO
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

    public boolean userPreliev(UtenteModel model, String cifraString) { //PRELEVA IL TUO CREDITO

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
                model.setCredito(rs.getBigDecimal("credito"));

            }
        } catch (SQLException e) {
            logger.severe("Errore in UtenteDAO in getVotoByUtenteID: " + e.getMessage());
        }

        return model;
    }

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

    public boolean checkCreditoSufficienteByUtenteID(int id, BigDecimal cifra) {
        String query = "SELECT credito FROM utente WHERE idUtente = ?";

        Connection conn = DBConnection.getIstance().connection();

        boolean b = false;

        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                b = true;
                if (rs.getBigDecimal("credito").compareTo(cifra) < 0) {

                    b = false;

                }
            }
        } catch (SQLException e) {
            logger.severe("Errore in UtenteDAO in checkCreditoSufficienteByUtenteID : " + e.getMessage());
        }
        return b;
    }


}



















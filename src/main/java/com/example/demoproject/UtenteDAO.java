package com.example.demoproject;
import javax.xml.xpath.XPathEvaluationResult;
import java.io.PipedReader;
import java.sql.*;
import java.util.logging.Logger;
public class UtenteDAO {
    private static final Logger logger = Logger.getLogger(UtenteDAO.class.getName());

    //Questa funzione non può ritornare semplicemente true o false, ma anche il bean O --FORSE-- NO LO DEVE POPOLARE!!!!
    public boolean searchUser(UtenteBean bean) {
        DBConnection connection = new DBConnection();
        boolean b = false;
        String query = "SELECT * FROM mangaink.utente WHERE email = ?"; //il ? verrà gestito in maniera sicura da st.setString
        //BISOGNA CREARE UN FILE DI CONFIGURAZIONE PER DISACCOPIARE LE INFO DI CONFIGURAZIONE DEL DB, AD ESEMPIO IL NOME DEL DB
        //NON DEVE ESSERE HARDCODED NELL'APPLICAZIONE
        Connection conn = connection.connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setString(1, bean.getEmail());

            try (ResultSet rs = st.executeQuery()) {

                if (rs != null && rs.next() && rs.getString("password").equals(bean.getPassword())) {
                    System.out.println(rs.getMetaData().getColumnCount()); //CARINO PERCHE RIDA IL NUMERO DI COLONNE CHE HA PRESO

                    System.out.println(rs.getString("password") + " " + rs.getBigDecimal("credito"));

                    bean.setIdUtente(rs.getInt("idUtente"));
                    bean.setUsername(rs.getString("username"));
                    bean.setCredito(rs.getBigDecimal("credito"));
                    bean.setVotoRecensione(rs.getDouble("votoRecensioni"));

                    logger.info("ha funzionato");
                    b = true;


                } else {
                    System.out.println(rs.getString("password") + rs.getString("email"));
                    logger.info("fallito");

                }
            }

        } catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nella searchUser in utenteDAO " + e.getMessage());

        } finally {
            connection.close(conn);  // Chiudi la connessione nel blocco finally
        }

        return b;


    }

    public boolean addUser(UtenteBean bean) {
        DBConnection connection = new DBConnection();
        boolean b = false;
        String query = "INSERT INTO mangaink.utente (email, username ,password ) VALUES (?, ?, ?)";
        Connection conn = connection.connection();


        try(PreparedStatement st = conn.prepareStatement(query)){

            if(bean.getEmail().isEmpty() || bean.getUsername().isEmpty()||bean.getPassword().isEmpty()) {
                logger.warning("Uno o più campi sono vuoti. Inserimento utente fallito.");
                return false;}

            st.setString(1,bean.getEmail());
            st.setString(2, bean.getUsername());
            st.setString(3, bean.getPassword());





            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {
                b = true;
                logger.info("Inserimento utente riuscito");
            } else {
                logger.info("Inserimento utente fallito");
            }


        }

        catch (SQLException e){
            logger.severe("E' stata lanciata la exception nell'addUser in utenteDao " + e.getMessage());
        }

        return true;
    }

   public boolean informazioniUtente(UtenteBean bean){
    DBConnection conn = new DBConnection();
    String query = "UPDATE mangaink.utene SET informazioniUtenteID=(SELECT LAST_INSERT_ID() FROM mangaink.informazioniUtente) WHERE utente.email = ?";

    }











}

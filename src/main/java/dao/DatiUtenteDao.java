package dao;

import bean.DatiUtenteBean;
import model.DatiUtente;

import java.sql.*;
import java.util.logging.Logger;

public class DatiUtenteDao {
    private static final Logger logger = Logger.getLogger(DatiUtenteDao.class.getName());


    //INSERISCE LE INFORMAZIONI DI CONSEGNA PER UN UTENTE
    public int addDatiUser(DatiUtenteBean bean1) {
        int generatedKey = -1;

        Connection conn = DBConnection.getIstance().connection();

        String query = "INSERT INTO mangaink.informazioniutente (indirizzo, civico, cap) VALUES (? , ?, ?)";

        try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            if(bean1.getIndirizzo().isEmpty() ||  bean1.getCivico().isEmpty()|| bean1.getCap().isEmpty()) {
                logger.warning("Uno o più campi sono vuoti. Inserimento dati utente fallito.");
                return generatedKey;}

            st.setString(1, bean1.getIndirizzo());
            st.setString(2, bean1.getCivico());
            st.setString(3, bean1.getCap());

            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {

                    generatedKey = generatedKeys.getInt(1);
                    bean1.setIdInformazioniUtente(generatedKey);

                } else {
                    logger.info("Inserimento dati dell'utente riuscito, ma impossibile ottenere la chiave generata.");
                }
                logger.info("Inserimento dati  dell'utente riuscito");}
            else {
                logger.info("Inserimento dati dell'utente fallito");}
        }

     catch(SQLException e){
        logger.severe("E' stata lanciata la exception nell'addDatiUser in DatiUtenteDao " + e.getMessage());}

     return generatedKey;
    }


    //RICAVA LE INFORMAZIONI DI UN UTENTE A PARTIRE DALL'IDUTENTE
    public DatiUtente getDatiUserByInformazioniUtenteID(int id) {

        Connection conn = DBConnection.getIstance().connection();
        String query = "SELECT indirizzo,civico,cap FROM informazioniutente WHERE idInformazioniUtente = ?";
        DatiUtente dati = null;


        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                dati = new DatiUtente();

                dati.setIndirizzo(rs.getString("indirizzo"));
                dati.setCivico(rs.getString("civico"));
                dati.setCap(rs.getString("cap"));
            }

        } catch (SQLException e) {
            logger.severe("errore in getDatiUserByInformazioniUtenteID in DatiUtenteDAO: " + e.getMessage());
        }
        return dati;
    }

    //MODIFICA I DATI DELL'UTENTE
    public boolean modificaDatiUser(DatiUtenteBean bean){
        boolean b = false;
        Connection conn = DBConnection.getIstance().connection();
        String query = "UPDATE informazioniutente SET indirizzo = ?, civico = ?, cap = ? WHERE idInformazioniUtente = ?";
        try(PreparedStatement st = conn.prepareStatement(query)){
            st.setString(1,bean.getIndirizzo());
            st.setString(2,bean.getCivico());
            st.setString(3,bean.getCap());
            st.setInt(4,bean.getIdInformazioniUtente());
            int rows = st.executeUpdate();
            if(rows>0){
                b=true;
            }

        }catch (SQLException e){
            logger.severe("errore in modificaDatiUser in DatiUtenteDAO : " + e.getMessage());
        }
        return b;

    }




}



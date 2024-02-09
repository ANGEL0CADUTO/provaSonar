package dao;

import bean.DatiUtenteBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatiUtenteDao {
    private static final Logger logger = Logger.getLogger(UtenteDAO.class.getName());

    public boolean addDatiUser(DatiUtenteBean bean1) {
        Boolean a = false;
        Connection conn = DBConnection.getIstance().connection();

        String query = "INSERT INTO mangaink.informazioniutente (indirizzo, civico, cap) VALUES (? , ?, ?)";

        try (PreparedStatement st = conn.prepareStatement(query)) {

            if(bean1.getIndirizzo().isEmpty() ||  bean1.getCivico() == 0|| bean1.getCap()==0) {
                logger.warning("Uno o più campi sono vuoti. Inserimento dati utente fallito.");
                return false;}

            st.setString(1, bean1.getIndirizzo());
            st.setInt(2, bean1.getCivico());
            st.setInt(3, bean1.getCap());




            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {
                a = true;
                logger.info("Inserimento dati  dell'utente riuscito");}
            else {
                logger.info("Inserimento dati dell'utente fallito");}

        }

     catch(SQLException e){
        logger.severe("E' stata lanciata la exception nell'addDatiUser in DatiUtenteDao " + e.getMessage());}

     return true;
    }




}



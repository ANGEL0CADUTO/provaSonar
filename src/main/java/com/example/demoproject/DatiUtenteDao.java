package com.example.demoproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatiUtenteDao {
    private static final Logger logger = Logger.getLogger(UtenteDAO.class.getName());
 public boolean addDatiUser(DatiUtenteBean bean1){
    Boolean a = false;
    DBConnection connection = new DBConnection(); //CREA CONNESIONE
     String query = "INSERT INTO mangaink.informazioniutente (indirizzo, civico, cap) VALUES (? , ?, ?)";
     Connection conn = connection.connection();

     try(PreparedStatement st = conn.prepareStatement(query)){

         st.setString(1,bean1.getIndirizzo());
         st.setInt(2, bean1.getCivico());
         st.setInt(3, bean1.getCap());


         //
         int righeScritte = st.executeUpdate();

         if (righeScritte > 0) {
             a = true;
             logger.info("Inserimento dati  dell'utente riuscito");
         } else {
             logger.info("Inserimento dati dell'utente fallito");
         }
     }
     catch (SQLException e){
         logger.severe("E' stata lanciata la exception nell'addDatiUser in DatiUtenteDao" + e.getMessage());
     }

     return true;

 }
}

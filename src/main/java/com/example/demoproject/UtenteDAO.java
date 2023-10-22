package com.example.demoproject;
import java.sql.*;
import java.util.logging.Logger;
public class UtenteDAO {
    private static final Logger logger = Logger.getLogger(UtenteDAO.class.getName());
    public boolean searchUser(String email,String password){
        DBConnection DB = new DBConnection();
        boolean b = false;
        String query = "SELECT password FROM utenti.utente WHERE email = ?"; //il ? verrà gestito in maniera sicura da st.setString

        try{
            Connection conn = DB.connection();

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,email);
            ResultSet rs = st.executeQuery("SELECT password FROM utenti.utente WHERE email ='" + email +"'");
            rs.next();
            if(rs.getString("password").equals(password)){
                logger.info("ha funzionato");
                b = true;
                DB.close(conn);
            }
            else{

                logger.info("fallito");
                DB.close(conn);
            }

        }
        catch(SQLException e){
            logger.severe("errore nella ricerca dell'user");

        }

        return b;


    }


}

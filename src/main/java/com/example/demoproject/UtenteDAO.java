package com.example.demoproject;
import java.sql.*;
import java.util.logging.Logger;
public class UtenteDAO {
    private static final Logger logger = Logger.getLogger(UtenteDAO.class.getName());

    //Questa funzione non può ritornare semplicemente true o false, ma anche il bean O --FORSE-- NO LO DEVE POPOLARE!!!!
    public boolean searchUser(String email, String password, UtenteBean bean){
        DBConnection connection = new DBConnection();
        boolean b = false;
        String query = "SELECT * FROM utenti.utente WHERE email = ?" ; //il ? verrà gestito in maniera sicura da st.setString

        try(Connection conn = connection.connection();
            PreparedStatement st = conn.prepareStatement(query)){

            st.setString(1,email);

            try(ResultSet rs = st.executeQuery()) {

                if (rs.next() && rs.getString("password").equals(password)) {
                    //System.out.println(rs.getMetaData().getColumnCount()); CARINO PERCHE RIDA IL NUMERO DI COLONNE CHE HA PRESO



                    bean.setIdUtente(rs.getInt("idutente"));
                    bean.setUsername(rs.getString("username"));
                    bean.setPassword(rs.getString("password"));
                    bean.setEmail(rs.getString("email"));
                    bean.setRuolo(rs.getString("ruolo"));
                    //System.out.println(bean.getIdUtente() + " " + bean.getUsername()+ " " + bean.getPassword()+ " " + bean.getEmail()+ " " + bean.getRuolo());
                    //System.out.println(bean.getUsername());

                    logger.info("ha funzionato");
                    b = true;
                    connection.close(conn);

                } else {
                    System.out.println(rs.getString("password") + rs.getString("email"));
                    logger.info("fallito");
                    connection.close(conn);
                }
            }

        }
        catch(SQLException e){
            logger.severe("E' stata lanciata la exception nell'utenteDAO(PARTE QUANDO HO SBAGLIATO CREDENZIALI ED IL RESULTSET" +
                    "è VUOTO");

        }

        return b;


    }


}

package dao;

import bean.DatiUtenteBean;
import bean.UtenteBean;

import java.sql.*;
import java.util.logging.Logger;

public class DatiUtenteDao {
    private static final Logger logger = Logger.getLogger(UtenteDAO.class.getName());

    public boolean addDatiUser(DatiUtenteBean bean1) {
        Boolean a = false;
        Connection conn = DBConnection.getIstance().connection();

        String query = "INSERT INTO mangaink.informazioniutente (indirizzo, civico, cap) VALUES (? , ?, ?)";

        try (PreparedStatement st = conn.prepareStatement(query)) {

            if(bean1.getIndirizzo().isEmpty() ||  bean1.getCivico().isEmpty()|| bean1.getCap().isEmpty()) {
                logger.warning("Uno o più campi sono vuoti. Inserimento dati utente fallito.");
                return false;}

            st.setString(1, bean1.getIndirizzo());
            st.setString(2, bean1.getCivico());
            st.setString(3, bean1.getCap());

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

    //DA CORREGGERE PERCHE USIAMO IL BEAN CHE IN RELTA DOVREBBE SPARIRE DALL'APPLICATIVO IN GIU
    public DatiUtenteBean getDatiUserByInformazioniUtenteID(int id) {

        Connection conn = DBConnection.getIstance().connection();
        String query = "SELECT indirizzo,civico,cap FROM informazioniutente WHERE idInformazioniUtente = ?";
        DatiUtenteBean bean = null;
        System.out.println(id);

        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                bean = new DatiUtenteBean();
                bean.setIndirizzo(rs.getString("indirizzo"));
                bean.setCivico(rs.getString("civico"));
                bean.setCap(rs.getString("cap"));
            }

        } catch (SQLException e) {
            logger.severe("errore in getDatiUserByInformazioniUtenteID in DatiUtenteDAO: " + e.getMessage());
        }
        return bean;
    }
    public boolean modificaDatiUser(DatiUtenteBean bean){
        boolean b = false;
        Connection conn = DBConnection.getIstance().connection();
        String query = "UPDATE informazioniUtente SET indirizzo = ?, civico = ?, cap = ? WHERE idInformazioniUtente = ?";

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



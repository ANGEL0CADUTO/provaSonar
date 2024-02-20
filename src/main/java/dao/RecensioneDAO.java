package dao;


import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RecensioneDAO {
    private static final Logger logger = Logger.getLogger(RecensioneDAO.class.getName());


    public List<Recensione> getRecensioniRicevuteByUtenteID(int id) {


        String query = "SELECT usernameRecensore, voto,testo,offertaID FROM recensione " +
                "WHERE statoRecensione = 1 AND recensitoID = ?;";


        ArrayList<Recensione> array = new ArrayList<>();

        Connection conn = DBConnection.getIstance().connection();


        try ( PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){

                Recensione recensione = new Recensione();
                recensione.setUsernameRecensore(rs.getString("usernameRecensore"));
                recensione.setVoto(rs.getDouble("voto"));
                recensione.setTesto(rs.getString("testo"));
                recensione.setOffertaID(rs.getInt("offertaID"));





                array.add(recensione);

            }


        }catch(SQLException e){
            logger.severe("Errore in RecensioneDAO in getRecensioniRicevuteByUtenteID :" + e.getMessage() );
        }
        return array;
    }


    public boolean inviaRecensione(Recensione recensione){

        String query = "INSERT INTO recensione(offertaID,recensitoID,voto,testo,usernameRecensore) VALUES (?,?,?,?,?)";

        Connection conn = DBConnection.getIstance().connection();

        try(PreparedStatement st = conn.prepareStatement(query)){
            st.setInt(1,recensione.getOffertaID());
            st.setInt(2,recensione.getRecensitoID());
            st.setDouble(3,recensione.getVoto());
            st.setString(4,recensione.getTesto());
            st.setString(5, recensione.getUsernameRecensore());
            st.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Errore nel RecensioneDAO in inviaRecensione :"+ e.getMessage());
        }
        return true;
    }

    public int getNumeroRecensioniByRecensitoID(int id) {
        String query = "SELECT count(*) FROM recensione WHERE recensitoID = ?";
        int risultato = 0;
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {

                    risultato = rs.getInt(1);

                }
            }

        } catch (SQLException e) {
            // Gestisci l'eccezione in modo appropriato
            logger.severe("Errore in RecensioneDAO: " + e.getMessage());
        }
        return risultato;
    }

    public boolean updateRecensito(int id){
        String query = "UPDATE offerta SET recensioneFatta = 1 WHERE idOfferta = ?";
        Connection conn = DBConnection.getIstance().connection();

        boolean b = false;

        try(PreparedStatement st = conn.prepareStatement(query)){
            st.setInt(1,id);
            int righe = st.executeUpdate();
            if(righe > 0){
                b = true;
            }
        } catch (SQLException e) {
            logger.severe("Errore in updateRecensito in RecensioneDAO : " + e.getMessage());
        }
        return b;

    }





}

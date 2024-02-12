package dao;

import model.OffertaModel;
import model.OffertaRicevuta;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class OffertaDAO {

    private static final Logger logger = Logger.getLogger(OffertaDAO.class.getName());

    public boolean insertOfferta(OffertaModel offerta) {
        boolean b = false;
        String query = "INSERT INTO offerta (annuncioID, utenteOfferenteID,usernameOfferente, offertaPrezzo, dataOfferta) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, offerta.getAnnuncioID());
            st.setInt(2, offerta.getUtenteOfferenteID());
            st.setString(3,offerta.getUsernameOfferente());
            st.setBigDecimal(4, (offerta.getOffertaPrezzo()).setScale(2, RoundingMode.HALF_UP));
            st.setTimestamp(5,Timestamp.valueOf(offerta.getDataOfferta()));


            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                b = true;
            }
        } catch (SQLException e) {
            logger.severe("Errore in insertOfferta in OFFERTADAO: " + e.getMessage());
        }
        return b;
    }


    public ArrayList<OffertaRicevuta> getOfferteRicevuteByAnnuncioID(int id){
        ArrayList<OffertaRicevuta> array = new ArrayList<>();
        String query = "SELECT idOfferta,usernameOfferente,offertaPrezzo, dataOfferta " +
                "FROM offerta " +
                "WHERE annuncioID = ? AND statoOfferta = 1 ;";
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {
            System.out.println(id);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();

            System.out.println("prima del while");

            while(rs.next()){
                System.out.println("siamo nel while");
                OffertaRicevuta offerta = new OffertaRicevuta();
                offerta.setIdOfferta(rs.getInt("idOfferta"));
                offerta.setAnnuncioID(id);
                offerta.setUsernameOfferente(rs.getString("usernameOfferente"));
                //offerta.setVotoRecensioni(rs.getDouble("votoRecensioni"));
                offerta.setOffertaPrezzo(rs.getBigDecimal("offertaPrezzo"));
                offerta.setDataOfferta(rs.getTimestamp("dataOfferta").toLocalDateTime());
                array.add(offerta);
                System.out.println("Ho riempito con :" + array.getLast().getUsernameOfferente());
            }
        } catch (SQLException e) {
            logger.severe("Errore in getOfferteRicevuteByAnnuncioID in OFFERTADAO: " + e.getMessage());
        }

        return array;
    }

    public boolean accettaOfferta(int idOfferta, int idAnnuncio){
        boolean b = false;
        String query = "UPDATE offerta SET statoOfferta = CASE WHEN idOfferta != ? THEN 2 WHEN idOfferta = ? THEN 3 END, dataVendita = NOW() WHERE annuncioID = ?";
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, idOfferta);
            st.setInt(2, idOfferta);
            st.setInt(3,idAnnuncio);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                b = true;
            }
        } catch (SQLException e) {
            logger.severe("Errore in accettaOfferta in OFFERTADAO: " + e.getMessage());
        }
        return b;

    }


    public OffertaRicevuta getDatiOffertaByOffertaID(int id){

        String query = "SELECT idOfferta, annuncioID,usernameOfferente,offertaPrezzo,dataVendita " +
                "FROM offerta WHERE idOfferta = ?;";
        Connection conn = DBConnection.getIstance().connection();
        OffertaRicevuta offertaRicevuta = null;
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();


            if(rs.next()){
                offertaRicevuta = new OffertaRicevuta();
                offertaRicevuta.setIdOfferta(rs.getInt("idOfferta"));
                offertaRicevuta.setUsernameOfferente(rs.getString("usernameOfferente"));
                offertaRicevuta.setAnnuncioID(rs.getInt("annuncioID"));
                offertaRicevuta.setOffertaPrezzo(rs.getBigDecimal("offertaPrezzo"));
                offertaRicevuta.setDataVendita(rs.getTimestamp("dataVendita").toLocalDateTime());
            }


        }catch(SQLException e){
            logger.severe("Errore in getDatiOffertaByOffertaID : " + e.getMessage());
        }

        return offertaRicevuta;
    }




}

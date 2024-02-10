package dao;

import model.OffertaModel;
import model.OffertaRicevuta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

public class OffertaDAO {

    private static final Logger logger = Logger.getLogger(OffertaDAO.class.getName());

    public boolean insertOfferta(OffertaModel offerta) {
        boolean b = false;
        String query = "INSERT INTO offerta (annuncioID, utenteOfferenteID, offertaPrezzo, dataOfferta, statoOfferta) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, offerta.getAnnuncioID());
            st.setInt(2, offerta.getUtenteOfferenteID());
            st.setBigDecimal(3, (offerta.getOffertaPrezzo()).setScale(2, RoundingMode.HALF_UP));//MICIDIALE, DA CORREGGERE

            st.setString(4, String.valueOf(offerta.getDataOfferta())); // Assicurati che il formato sia corretto
            st.setString(5, "1");
            // Assicurati di utilizzare il tipo di dati corretto e l'ordine corretto per gli altri parametri

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
        String query = "SELECT idOfferta,username, votoRecensioni, offertaPrezzo, dataOfferta\n" +
                "FROM offerta join utente on idUtente = utenteOfferenteID " +
                "WHERE annuncioID = ? AND statoOfferta = 1;";
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();


            while(rs.next()){
                OffertaRicevuta offerta = new OffertaRicevuta();
                offerta.setIdOfferta(rs.getInt("idOfferta"));
                offerta.setAnnuncioID(id);
                offerta.setUsernameOfferente(rs.getString("username"));
                offerta.setVotoRecensioni(rs.getDouble("votoRecensioni"));
                offerta.setOffertaPrezzo(rs.getBigDecimal("offertaPrezzo"));
                offerta.setDataOfferta(rs.getTimestamp("dataOfferta").toLocalDateTime());
                array.add(offerta);
            }
        } catch (SQLException e) {
            logger.severe("Errore in getOfferteRicevuteByAnnuncioID in OFFERTADAO: " + e.getMessage());
        }

        return array;
    }

    public boolean accettaOfferta(OffertaRicevuta offertaAccettata){
        boolean b = false;
        String query = "UPDATE offerta SET statoOfferta = CASE WHEN idOfferta !=? THEN 2 WHEN idOfferta = ? THEN 3 END WHERE annuncioID = ?;";
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, offertaAccettata.getIdOfferta());
            st.setInt(2, offertaAccettata.getIdOfferta());
            st.setInt(3,offertaAccettata.getAnnuncioID());



            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                b = true;
            }
        } catch (SQLException e) {
            logger.severe("Errore in accettaOfferta in OFFERTADAO: " + e.getMessage());
        }
        return b;

    }




}

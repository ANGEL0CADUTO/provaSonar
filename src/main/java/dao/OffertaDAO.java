package dao;

import model.OffertaModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
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


}

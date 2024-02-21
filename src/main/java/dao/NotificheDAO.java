package dao;

import model.NotificaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class NotificheDAO {

    private static final Logger logger = Logger.getLogger(NotificheDAO.class.getName());

    public NotificaModel readNotificaFromDatabase(int notificaId) {
        String query = "SELECT utente, volume, venditore, manga, prezzo_offerta FROM notifiche WHERE id = ?";
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, notificaId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                NotificaModel notificaModel = new NotificaModel();
                notificaModel.setUtente(resultSet.getString("utente"));
                notificaModel.setVolume(resultSet.getInt("volume"));
                notificaModel.setVenditore(resultSet.getString("venditore"));
                notificaModel.setManga(resultSet.getString("manga"));
                notificaModel.setPrezzoOfferta(resultSet.getInt("prezzo_offerta"));
                return notificaModel;
            }
        } catch (SQLException ex) {
            logger.severe("Errore in readNotificaFromDatabase" + ex.getMessage());
        }


        return null;
    }

    public void saveNotificaInDatabase(NotificaModel notifica, int idOfferta) {
        String query = "INSERT INTO notifiche (idOfferta,utente, volume, venditore, manga, prezzo_offerta) VALUES (?,?, ?, ?, ?, ?)";
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, idOfferta);
            statement.setString(2, notifica.getUtente());
            statement.setString(3, String.valueOf(notifica.getVolume()));
            statement.setString(4, notifica.getVenditore());
            statement.setString(5, notifica.getManga());
            statement.setString(6, String.valueOf(notifica.getPrezzoOfferta()));

            statement.executeUpdate();

        } catch (SQLException ex) {
            logger.severe("Errore in saveNotificaInDatabase: " + ex.getMessage());
        }
    }
}




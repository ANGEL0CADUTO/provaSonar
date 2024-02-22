package dao;

import model.NotificaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


//DAO MYSQL PER LE NOTIFICHE
public class NotificheDAO {

    private static final Logger logger = Logger.getLogger(NotificheDAO.class.getName());


    //LEGGE UNA NUOVA NOTIFICA
    public NotificaModel readNotificaFromDatabase() {
        String query = "SELECT utente, volume, venditore, manga, prezzo_offerta FROM notifiche WHERE idnotifiche = (select max(idnotifiche) from notifiche)";
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement statement = conn.prepareStatement(query)) {
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


    //SALVA NOTIFICA
    public boolean saveNotificaInDatabase(NotificaModel notifica) {
        String query = "INSERT INTO notifiche (utente, volume, venditore, manga, prezzo_offerta) VALUES (?, ?, ?, ?, ?)";
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, notifica.getUtente());
            statement.setString(2, String.valueOf(notifica.getVolume()));
            statement.setString(3, notifica.getVenditore());
            statement.setString(4, notifica.getManga());
            statement.setString(5, String.valueOf(notifica.getPrezzoOfferta()));

            int rows = statement.executeUpdate();
            if(rows>0){
                return true;
            }


        } catch (SQLException ex) {
            logger.severe("Errore in saveNotificaInDatabase: " + ex.getMessage());
        }
        return false;
    }
}




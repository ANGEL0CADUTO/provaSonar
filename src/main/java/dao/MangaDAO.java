package dao;

import bean.MangaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MangaDAO {

    private static final Logger logger = Logger.getLogger(MangaDAO.class.getName());

    public MangaBean getMangaByIDManga(int id){
        DBConnection connection = new DBConnection();
        MangaBean bean =  new MangaBean();
        String query = "SELECT * FROM mangaink.manga WHERE idManga = ?"; //BISOGNA CREARE UN FILE DI CONFIGURAZIONE
        // PER DISACCOPIARE LE INFO DI CONFIGURAZIONE DEL DB, AD ESEMPIO IL NOME DEL DB, NON DEVE ESSERE HARDCODED NELL'APPLICAZIONE

        Connection conn = connection.connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setString(1,String.valueOf(id));

            try (ResultSet rs = st.executeQuery()) {

                if (rs != null && rs.next()) {

                    bean.setNome(rs.getString("nome"));
                    bean.setAutore(rs.getString("autore"));
                    bean.setIdManga(rs.getInt("idManga"));
                    logger.info("ha funzionato");
                    return bean;


                } else {
                    System.out.println(rs.getString("nome") + rs.getString("autore"));
                    logger.info("fallito");

                }
            }

        } catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nella searchUser in mangaDAO" + e.getMessage());

        } finally {
            connection.close(conn);  // Chiudi la connessione nel blocco finally
        }

        return bean;
    }

}

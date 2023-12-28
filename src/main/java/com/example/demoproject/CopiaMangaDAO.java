package com.example.demoproject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CopiaMangaDAO {

    private static final Logger logger = Logger.getLogger(CopiaMangaDAO.class.getName());


    //PRENDE L'INTERA LISTA DI MANGA DI UNO SPECIFICO UTENTE//
    public CopiaMangaCollectionModel getCopieMangaListByUserID(UtenteModel model) {

        DBConnection connection = new DBConnection();
        boolean b = false;
        String query = "SELECT * from mangaink.copiamanga WHERE utenteID = ? AND statoCopiaManga = ?";

        CopiaMangaCollectionModel collezione = new CopiaMangaCollectionModel();

        Connection conn = connection.connection();

        try ( PreparedStatement st = conn.prepareStatement(query)) {
            // st.setString(1,bean.getIdUtente());
            st.setString(1, String.valueOf(model.getIdUtente()));
            st.setString(2,"1");

            try(ResultSet rs = st.executeQuery()){

                while(rs.next()){
                    CopiaMangaModel copiaManga = new CopiaMangaModel();
                    MangaDAO dao = new MangaDAO();

                    copiaManga.setIdCopiaManga(rs.getInt("idCopiaManga"));

                    copiaManga.setIdManga(rs.getInt("mangaID"));
                    copiaManga.setDataAcquisto(rs.getDate("dataAcquisto"));

                    copiaManga.setNome(dao.getMangaByIDManga(copiaManga.getIdManga()).getNome());

                    collezione.getListaManga().addLast(copiaManga);

                }

            }

        } catch (SQLException e) {
            logger.severe("E' stata lanciata eccezione in CopieMangaDAO" + " " + e.getMessage());

        }
        return collezione;
    }


}

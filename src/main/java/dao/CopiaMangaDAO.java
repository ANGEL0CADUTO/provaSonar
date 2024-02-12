package dao;
import model.CopiaMangaCollectionModel;
import model.CopiaMangaModel;
import model.UtenteModel;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

public class CopiaMangaDAO {

    private static final Logger logger = Logger.getLogger(CopiaMangaDAO.class.getName());


    //PRENDE L'INTERA LISTA DI MANGA DI UNO SPECIFICO UTENTE//
    public ArrayList<CopiaMangaModel> getCopieMangaListByUserID(UtenteModel model) {


        boolean b = false;
        String query = "SELECT * from copiamanga WHERE utenteID = ? AND statoCopiaManga = ?";

        ArrayList<CopiaMangaModel> collezione = new ArrayList<>();

        Connection conn = DBConnection.getIstance().connection();


        try ( PreparedStatement st = conn.prepareStatement(query)) {
            // st.setString(1,bean.getIdUtente());
            st.setString(1, String.valueOf(model.getIdUtente()));
            st.setString(2,"1");

            try(ResultSet rs = st.executeQuery()){

                while(rs.next()){
                    CopiaMangaModel copiaManga = new CopiaMangaModel();
                    MangaDAO dao = new MangaDAO();

                    copiaManga.setIdCopiaManga(rs.getInt("idCopiaManga"));
                    copiaManga.setTitolo(rs.getString("titolo"));
                    copiaManga.setDataAcquisto(rs.getDate("dataAcquisto"));
                    copiaManga.setVolume(rs.getInt("volume"));

                    collezione.add(copiaManga);

                }

            }

        } catch (SQLException e) {
            logger.severe("E' stata lanciata eccezione in CopieMangaDAO" + " " + e.getMessage());

        }
        return collezione;
    }
}

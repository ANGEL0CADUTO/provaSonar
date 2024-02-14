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

    public boolean aggiungiManga(CopiaMangaModel copia){
        String query = "INSERT INTO copiamanga(utenteID,titolo,volume,dataAcquisto) VALUES(?,?,?,?);";
        Connection conn = DBConnection.getIstance().connection();

        boolean b = false;

        try(PreparedStatement st = conn.prepareStatement(query)){
            st.setInt(1,copia.getIdUtente());
            st.setString(2,copia.getTitolo());
            st.setInt(3,copia.getVolume());
            st.setTimestamp(4,Timestamp.valueOf(copia.getDataAcquisto()));

            int righe = st.executeUpdate();

            if(righe>0){
                b = true;
            }

        } catch (SQLException e) {
            logger.severe("Errore in CopiaMangaDAO in aggiungiManga : " + e.getMessage());
        }
        return b;
    }


    //PRENDE L'INTERA LISTA DI MANGA DI UNO SPECIFICO UTENTE//
    public ArrayList<CopiaMangaModel> getCopieMangaListByUserID(UtenteModel model) {


        boolean b = false;
        String query = "SELECT * FROM copiamanga WHERE utenteID = ? AND (statoCopiaManga = 1 OR statoCopiaManga = 2);";


        ArrayList<CopiaMangaModel> collezione = new ArrayList<>();

        Connection conn = DBConnection.getIstance().connection();


        try ( PreparedStatement st = conn.prepareStatement(query)) {
            // st.setString(1,bean.getIdUtente());
            st.setString(1, String.valueOf(model.getIdUtente()));


            try(ResultSet rs = st.executeQuery()){

                while(rs.next()){
                    CopiaMangaModel copiaManga = new CopiaMangaModel();

                    copiaManga.setIdCopiaManga(rs.getInt("idCopiaManga"));
                    copiaManga.setTitolo(rs.getString("titolo"));
                    copiaManga.setDataAcquisto(rs.getTimestamp("dataAcquisto").toLocalDateTime());
                    copiaManga.setVolume(rs.getInt("volume"));
                    copiaManga.setStatoCopiaManga(rs.getInt("statoCopiaManga"));


                    collezione.add(copiaManga);

                }

            }

        } catch (SQLException e) {
            logger.severe("E' stata lanciata eccezione in CopieMangaDAO" + " " + e.getMessage());

        }
        return collezione;
    }
}

package dao;


import model.AnnunciModel;
import model.AnnuncioModel;
import model.CopiaMangaModel;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;



public class AnnuncioDAO {
    private static final Logger logger = Logger.getLogger(AnnuncioDAO.class.getName());

    public boolean isAnnuncioPresente(CopiaMangaModel copiaMangaModel1){//GLI ARRIVA  ID 0

        boolean b = false;
        String query = "SELECT * FROM mangaink.annuncio WHERE copiaMangaID = ?";
        Connection conn = DBConnection.getIstance().connection();


        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, String.valueOf(copiaMangaModel1.getIdCopiaManga()));



            try (ResultSet rs = st.executeQuery()) {
                if (rs != null && rs.next()) {
                    logger.info("Annuncio già presente");
                    b=true;
                }else {logger.info("Annuncio inesistente");
                }


            }
        }catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nel isAnnuncioPresente in AnnuncioDao " + e.getMessage());}

        return b;
    }


    public AnnunciModel getAnnunci(){

        String query = "SELECT utente.username, manga.nome, annuncio.prezzoDiVendita,annuncio.idAnnuncio " +
                "FROM annuncio " +
                "JOIN copiamanga ON copiamanga.idCopiaManga = annuncio.copiaMangaID " +
                "JOIN manga ON manga.idManga = copiamanga.mangaID " +
                "JOIN utente ON copiamanga.utenteID = utente.idUtente;";


        AnnunciModel annuncio = new AnnunciModel();

        Connection conn = DBConnection.getIstance().connection();


        try ( PreparedStatement st = conn.prepareStatement(query)) {


            try(ResultSet rs = st.executeQuery()){

                while(rs.next()){

                    AnnuncioModel annuncioModel = new AnnuncioModel();
                   annuncioModel.setNomeUtente( rs.getString("username"));
                   annuncioModel.setNomeManga(rs.getString("nome"));
                    annuncioModel.setIdAnnuncio(rs.getInt("idAnnuncio"));
                    annuncioModel.setPrezzo(BigDecimal.valueOf(rs.getInt("prezzoDiVendita")));

                    annuncio.getListaDiAnnunci().add(annuncioModel);

                }

            }

        } catch (SQLException e) {
            logger.severe("E' stata lanciata eccezione in getAnnunci in AnnuncioDao" + " " + e.getMessage());

        }
      //  System.out.println("BECCA STO ANNUNCIO " +  annuncio);
        return annuncio ;
    }

    public boolean addAnnuncio(CopiaMangaModel copiaMangaModel, BigDecimal prezzo, String dataFormattata) {//DA MIGLIORARE

        boolean b = false;
        String query = "INSERT INTO mangaink.annuncio (copiaMangaID, prezzoDiVendita ,statoAnnuncio,dataAnnuncio ) VALUES (?, ?, ?, ?)";
        Connection conn = DBConnection.getIstance().connection();



        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setString(1,String.valueOf(copiaMangaModel.getIdCopiaManga()));
            st.setString(2,String.valueOf(prezzo));//
            st.setString(3, "1");
            st.setString(4,String.valueOf(dataFormattata));

            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {
                b = true;
                logger.info("Inserimento Annuncio riuscito");
            } else {
                logger.info("Inserimento Annuncio fallito");
            }


        }

        catch (SQLException e){
            logger.severe("E' stata lanciata la exception nell'addAnnuncio in AnnuncioDao " + e.getMessage());
        }

        return b;
        }


    public ArrayList<AnnuncioModel> getMyAnnunci(int id){


        String query = "SELECT manga.nome, annuncio.prezzoDiVendita, annuncio.dataAnnuncio, annuncio.idAnnuncio " +
                "FROM annuncio " +
                "JOIN copiamanga ON copiamanga.idCopiaManga = annuncio.copiaMangaID " +
                "JOIN manga ON manga.idManga = copiamanga.mangaID " +
                "WHERE annuncio.statoAnnuncio = '1' AND annuncio.utenteVenditoreID = ?; ";

        ArrayList<AnnuncioModel> array = new ArrayList<>();
        Connection conn = DBConnection.getIstance().connection();

        try ( PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();


            while(rs.next()){

                AnnuncioModel annuncioModel = new AnnuncioModel();

                annuncioModel.setNomeManga(rs.getString("nome"));
                annuncioModel.setIdAnnuncio(rs.getInt("idAnnuncio"));
                annuncioModel.setDataAnnuncio(rs.getTimestamp("dataAnnuncio").toLocalDateTime());
                annuncioModel.setPrezzo(BigDecimal.valueOf(rs.getInt("prezzoDiVendita")));

                array.add(annuncioModel);
            }

        }catch(SQLException e){
            logger.severe("errore in AnnuncioDAO nella getMyAnnunci : " + e.getMessage());
        }
        for(AnnuncioModel a : array){
            System.out.println(a.getIdAnnuncio()+ a.getNomeManga() + a.getDataAnnuncio() + a.getPrezzo());
        }
        return array ;
    }

}











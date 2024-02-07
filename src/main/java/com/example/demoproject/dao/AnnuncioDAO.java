package com.example.demoproject.dao;


import com.example.demoproject.model.AnnunciModel;
import com.example.demoproject.model.CopiaMangaCollectionModel;
import com.example.demoproject.model.CopiaMangaModel;
import com.example.demoproject.model.UtenteModel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

public class AnnuncioDAO {
    private static final Logger logger = Logger.getLogger(AnnuncioDAO.class.getName());

    public boolean isAnnuncioPresente(CopiaMangaModel copiaMangaModel1){//GLI ARRIVA  ID 0
        DBConnection connection = new DBConnection();
        boolean b = false;
        String query = "SELECT * FROM mangaink.annuncio WHERE copiaMangaID = ?";
        Connection conn = connection.connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, String.valueOf(copiaMangaModel1.getIdCopiaManga()));



            try (ResultSet rs = st.executeQuery()) {
                if (rs != null && rs.next()) {
                    logger.info("Annuncio già presente");
                    b=true;
                }else {logger.info("Annuncio inesistente");}


            }
        }catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nel isAnnuncioPresente in AnnuncioDao " + e.getMessage());}

        return b;
    }


    public AnnunciModel getAnnunci(){
        DBConnection connection = new DBConnection();

        String query = "SELECT utente.username, manga.nome, annuncio.prezzoDiVendita " +
                "FROM annuncio " +
                "JOIN copiamanga ON copiamanga.idCopiaManga = annuncio.copiaMangaID " +
                "JOIN manga ON manga.idManga = copiamanga.mangaID " +
                "JOIN utente ON copiamanga.utenteID = utente.idUtente;";


        AnnunciModel annuncio = new AnnunciModel();

        Connection conn = connection.connection();

        try ( PreparedStatement st = conn.prepareStatement(query)) {


            try(ResultSet rs = st.executeQuery()){

                while(rs.next()){
                    Object[] annuncioArray = new Object[3];
                    annuncioArray[0] = rs.getString("username");
                    annuncioArray[1] = rs.getString("nome");
                    annuncioArray[2] = rs.getInt("prezzoDiVendita");
                    annuncio.getListaDiAnnunci().add(annuncioArray);

                }

            }

        } catch (SQLException e) {
            logger.severe("E' stata lanciata eccezione in getAnnunci in AnnuncioDao" + " " + e.getMessage());

        }
      //  System.out.println("BECCA STO ANNUNCIO " +  annuncio);
        return annuncio ;
    }

    public boolean addAnnuncio(CopiaMangaModel copiaMangaModel, BigDecimal prezzo, String dataFormattata) {//DA MIGLIORARE
        DBConnection connection = new DBConnection();
        boolean b = false;
        String query = "INSERT INTO mangaink.annuncio (copiaMangaID, prezzoDiVendita ,statoAnnuncio,dataAnnuncio ) VALUES (?, ?, ?, ?)";
        Connection conn = connection.connection();



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
    }












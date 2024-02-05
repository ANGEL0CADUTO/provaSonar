package com.example.demoproject.dao;

import com.example.demoproject.model.CopiaMangaModel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AnnuncioDAO {
    private static final Logger logger = Logger.getLogger(AnnuncioDAO.class.getName());
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













package com.example.demoproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

public class AnnuncioDAO {
    private static final Logger logger = Logger.getLogger(AnnuncioDAO.class.getName());
    public boolean addAnnuncio(UtenteBean utenteBean,int prezzo, String dataFormattata) {//DA MIGLIORARE
        DBConnection connection = new DBConnection();
        boolean b = false;
        String query = "INSERT INTO mangaink.annuncio (copiaMangaID, prezzoDiVendita ,statoAnnuncio,dataAnnuncio ) VALUES (?, ?, ?, ?)";
        Connection conn = connection.connection();

        CopiaMangaModel mangaModel = new CopiaMangaModel();
        CopiaMangaDAO dao =new CopiaMangaDAO();
        UtenteModel utenteModel = new UtenteModel();
        utenteModel.setIdUtente(utenteBean.getIdUtente());
        mangaModel.setIdCopiaManga(1);
        dao.getCopieMangaListByUserID(utenteModel);

        try (PreparedStatement st = conn.prepareStatement(query)) {


            st.setString(1,String.valueOf(mangaModel.getIdCopiaManga()));
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













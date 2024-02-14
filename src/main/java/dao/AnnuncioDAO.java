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

    public boolean isAnnuncioPresente(CopiaMangaModel copiaMangaModel1) {//GLI ARRIVA  ID 0

        boolean b = false;
        String query = "SELECT * FROM mangaink.annuncio WHERE copiaMangaID = ?";
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, String.valueOf(copiaMangaModel1.getIdCopiaManga()));

            try (ResultSet rs = st.executeQuery()) {
                if (rs != null && rs.next()) {
                    logger.info("Annuncio già presente");
                    b = true;
                } else {
                    logger.info("Annuncio inesistente");
                }
            }
        } catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nel isAnnuncioPresente in AnnuncioDao " + e.getMessage());
        }

        return b;
    }


    public ArrayList<AnnuncioModel> getAnnunci(int id,String name) {

        String query = "SELECT usernameVenditore, titoloManga, prezzoDiVendita,volume, idAnnuncio,dataAnnuncio FROM annuncio " +
                "WHERE statoAnnuncio = 1 AND utenteVenditoreID != ? AND titoloManga  LIKE '%' ? '%' ";
        ArrayList<AnnuncioModel> array = new ArrayList<>();
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1,id);
            st.setString(2,name);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    AnnuncioModel annuncioModel = new AnnuncioModel();
                    annuncioModel.setNomeUtente(rs.getString("usernameVenditore"));
                    annuncioModel.setNomeManga(rs.getString("titoloManga"));
                    annuncioModel.setIdAnnuncio(rs.getInt("idAnnuncio"));
                    annuncioModel.setVolume(rs.getInt("volume"));
                    annuncioModel.setPrezzo(BigDecimal.valueOf(rs.getInt("prezzoDiVendita")));

                    array.add(annuncioModel);
                }
            }
        } catch (SQLException e) {
            logger.severe("E' stata lanciata eccezione in getAnnunci in AnnuncioDao" + " " + e.getMessage());
        }
        //  System.out.println("BECCA STO ANNUNCIO " +  annuncio);
        return array;
    }

    public ArrayList<AnnuncioModel> getAnnunciByName(int id,String name) {

        String query = "SELECT usernameVenditore,titoloManga, prezzoDiVendita,volume, idAnnuncio,dataAnnuncio FROM annuncio " +
                "WHERE statoAnnuncio = 1  AND titoloManga  LIKE '%' ? '%' ";
        ArrayList<AnnuncioModel> array = new ArrayList<>();
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1,id);
            st.setString(2,name);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    AnnuncioModel annuncioModel = new AnnuncioModel();
                    annuncioModel.setNomeUtente(rs.getString("usernameVenditore"));
                    annuncioModel.setNomeManga(rs.getString("titoloManga"));
                    annuncioModel.setVolume(rs.getInt("volume"));
                    annuncioModel.setIdAnnuncio(rs.getInt("idAnnuncio"));
                    annuncioModel.setPrezzo(BigDecimal.valueOf(rs.getInt("prezzoDiVendita")));

                    array.add(annuncioModel);
                }
            }
        } catch (SQLException e) {
            logger.severe("E' stata lanciata eccezione in getAnnunciByName in AnnuncioDao" + " " + e.getMessage());
        }
        //  System.out.println("BECCA STO ANNUNCIO " +  annuncio);
        return array;
    }





    public boolean addAnnuncio(CopiaMangaModel copiaMangaModel, BigDecimal prezzo, String dataFormattata, String username) {//DA MIGLIORARE

        boolean b = false;
        String query = "INSERT INTO mangaink.annuncio (utenteVenditoreID,usernameVenditore,copiaMangaID, " +
                "titoloManga,volume,prezzoDiVendita ,dataAnnuncio ) " +
                "VALUES (?, ?, ?, ?, ? , ? ,?)";
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, copiaMangaModel.getIdUtente());
            st.setString(2, username);
            st.setInt(3, copiaMangaModel.getIdCopiaManga());
            st.setString(4, copiaMangaModel.getTitolo());
            st.setInt(5, copiaMangaModel.getVolume());
            st.setBigDecimal(6, prezzo);
            st.setString(7, dataFormattata);

            int righeScritte = st.executeUpdate();
            if (righeScritte > 0) {
                b = true;
                logger.info("Inserimento Annuncio riuscito");
            } else {
                logger.info("Inserimento Annuncio fallito");
            }
        } catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nell'addAnnuncio in AnnuncioDao " + e.getMessage());
        }

        return b;
    }


    public ArrayList<AnnuncioModel> getMyAnnunci(int id) {
        String query = "SELECT titoloManga, prezzoDiVendita, dataAnnuncio, idAnnuncio " +
                "FROM annuncio " +
                "WHERE annuncio.statoAnnuncio = '1' AND annuncio.utenteVenditoreID = ?; ";

        ArrayList<AnnuncioModel> array = new ArrayList<>();
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                AnnuncioModel annuncioModel = new AnnuncioModel();
                annuncioModel.setNomeManga(rs.getString("titoloManga"));
                annuncioModel.setIdAnnuncio(rs.getInt("idAnnuncio"));
                annuncioModel.setDataAnnuncio(rs.getTimestamp("dataAnnuncio").toLocalDateTime());
                annuncioModel.setPrezzo(BigDecimal.valueOf(rs.getInt("prezzoDiVendita")));

                array.add(annuncioModel);
            }
        } catch (SQLException e) {
            logger.severe("errore in AnnuncioDAO nella getMyAnnunci : " + e.getMessage());
        }
        for (AnnuncioModel a : array) {
            System.out.println(a.getIdAnnuncio() + a.getNomeManga() + a.getDataAnnuncio() + a.getPrezzo());
        }
        return array;
    }

    public AnnuncioModel getDatiAnnuncioByAnnuncioID(int annuncioID) {
        String query = "SELECT titoloManga, utenteVenditoreID, volume, prezzoDiVendita,dataAnnuncio FROM " +
                "annuncio WHERE idAnnuncio = ?;";
        Connection conn = DBConnection.getIstance().connection();
        AnnuncioModel annuncio = null;

        try (PreparedStatement st = conn.prepareStatement(query)) {
            System.out.println("il valore dell'annuncio che gli abbiamo passato è :"+ annuncioID);
            st.setInt(1, annuncioID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                System.out.println("è arrivato in annunciodao getDatiAnnuncioByAnnuncioID : " +annuncioID);
                annuncio = new AnnuncioModel();
                annuncio.setNomeManga(rs.getString("titoloManga"));
                annuncio.setUtenteVenditoreID(rs.getInt("utenteVenditoreID"));
                annuncio.setVolume(rs.getInt("volume"));

                annuncio.setPrezzo(rs.getBigDecimal("prezzoDiVendita"));
                annuncio.setDataAnnuncio(rs.getTimestamp("dataAnnuncio").toLocalDateTime());

            }
        } catch (SQLException e) {
            logger.severe("Errore nel AnnuncioDAO in getDatiAnnuncioByAnnuncioID : " + e.getMessage());
        }
        return annuncio;
    }

    public boolean setStatoAccettatoByAnnuncioID(int idAnnuncio) {
        boolean b = false;
        String query = "UPDATE annuncio SET statoAnnuncio = 2 WHERE idAnnuncio = ?";
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, idAnnuncio);
            int righe = st.executeUpdate();
            if (righe > 0) {
                b = true;
            }
        } catch (SQLException e) {
            logger.severe("Errore nel AnnuncioDAO in setStatoAccettatoByAnnuncioID :" + e.getMessage());
        }

        return b;
    }


}











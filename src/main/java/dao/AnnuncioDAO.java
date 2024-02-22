package dao;



import exceptions.AnnuncioNonInseritoException;
import model.AnnuncioModel;
import model.CopiaMangaModel;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class AnnuncioDAO {
    private static final Logger logger = Logger.getLogger(AnnuncioDAO.class.getName());
    private static final String USERNAME_VENDITORE = "usernameVenditore";
    private static final String TITOLO_MANGA = "titoloManga";
    private static final String IDANNUNCIO = "idAnnuncio";
    private static final String PREZZO_DI_VENDITA= "prezzoDiVendita";
    private static final String VOLUME = "volume";


    //CONTROLLA NEL DATABASE SE è PRESENTE UN ANNUNCIO CON L'ID CHE GLI PASSIAMO
    public boolean isAnnuncioPresente(CopiaMangaModel copiaMangaModel1) {

        boolean b = false;
        String query = "SELECT * FROM mangaink.annuncio WHERE copiaMangaID = ? AND statoAnnuncio = 1";
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


    //PRENDE TUTTI GLI ANNUNCI NON PUBBLICATI DALL'UTENTE CHE LI STA VISUALIZZANDO, FILTRANDO PER IL VALORE DI RICERCA INSERITO
    public List<AnnuncioModel> getAnnunci(int id,String name) {

        String query = "SELECT usernameVenditore, utenteVenditoreID, copiaMangaID, titoloManga, prezzoDiVendita,volume, idAnnuncio,dataAnnuncio FROM annuncio " +
                "WHERE statoAnnuncio = 1 AND utenteVenditoreID != ? AND titoloManga  LIKE '%' ? '%' ";
        ArrayList<AnnuncioModel> array = new ArrayList<>();
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1,id);
            st.setString(2,name);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    AnnuncioModel annuncioModel = new AnnuncioModel();
                    annuncioModel.setNomeUtente(rs.getString(USERNAME_VENDITORE));
                    annuncioModel.setUtenteVenditoreID(rs.getInt("utenteVenditoreID"));
                    annuncioModel.setNomeManga(rs.getString(TITOLO_MANGA));
                    annuncioModel.setIdAnnuncio(rs.getInt(IDANNUNCIO));
                    annuncioModel.setVolume(rs.getInt(VOLUME));
                    annuncioModel.setPrezzo(BigDecimal.valueOf(rs.getInt(PREZZO_DI_VENDITA)));
                    annuncioModel.setCopiaMangaID(rs.getInt("copiaMangaID"));
                    array.add(annuncioModel);


                }
            }
        } catch (SQLException e) {
            logger.severe("E' stata lanciata eccezione in getAnnunci in AnnuncioDao" + " " + e.getMessage());
        }

        return array;
    }


    //AGGIUNGE UN ANNUNCIO
    public boolean addAnnuncio(CopiaMangaModel copiaMangaModel, BigDecimal prezzo, String dataFormattata, String username) throws AnnuncioNonInseritoException {//DA MIGLIORARE

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
                throw new AnnuncioNonInseritoException();

            }
        } catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nell'addAnnuncio in AnnuncioDao " + e.getMessage());
        }

        return b;
    }

    //PRENDE GLI ANNUNCI DI UN UTENTE
    public List<AnnuncioModel> getMyAnnunci(int id) {
        String query = "SELECT titoloManga, volume, prezzoDiVendita, dataAnnuncio, idAnnuncio " +
                "FROM annuncio " +
                "WHERE annuncio.statoAnnuncio = '1' AND annuncio.utenteVenditoreID = ?; ";

        ArrayList<AnnuncioModel> array = new ArrayList<>();
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                AnnuncioModel annuncioModel = new AnnuncioModel();
                annuncioModel.setNomeManga(rs.getString(TITOLO_MANGA));
                annuncioModel.setVolume(rs.getInt(VOLUME));
                annuncioModel.setIdAnnuncio(rs.getInt(IDANNUNCIO));
                annuncioModel.setDataAnnuncio(rs.getTimestamp("dataAnnuncio").toLocalDateTime());
                annuncioModel.setPrezzo(BigDecimal.valueOf(rs.getInt(PREZZO_DI_VENDITA)));

                array.add(annuncioModel);
            }
        } catch (SQLException e) {
            logger.severe("errore in AnnuncioDAO nella getMyAnnunci : " + e.getMessage());
        }

        return array;
    }

    //PRENDE GLI ANNUNCI VECCHI, CIOE' QUELLI CHE SONO TERMINATI PER UNA VENDITA
    public List<AnnuncioModel> getMyAnnunciVendutiByUtenteID(int id) {
        String query = "SELECT utenteVenditoreID, titoloManga, volume, idAnnuncio " +
                "FROM annuncio " +
                "WHERE statoAnnuncio = 2 AND utenteVenditoreID = ?; ";

        ArrayList<AnnuncioModel> array = new ArrayList<>();
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                AnnuncioModel annuncioModel = new AnnuncioModel();
                annuncioModel.setNomeManga(rs.getString(TITOLO_MANGA));
                annuncioModel.setIdAnnuncio(rs.getInt(IDANNUNCIO));
                annuncioModel.setVolume(rs.getInt(VOLUME));

                array.add(annuncioModel);
            }
        } catch (SQLException e) {
            logger.severe("errore in AnnuncioDAO nella getMyAnnunciVendutiByUtenteID : " + e.getMessage());
        }

        return array;
    }



    //RECUPERA INFORMAZIONI PER UN ANNUNCIO
    public AnnuncioModel getDatiAnnuncioByAnnuncioID(int annuncioID){
            String query = "SELECT titoloManga, utenteVenditoreID, usernameVenditore, volume, prezzoDiVendita,dataAnnuncio,copiaMangaID FROM " +
                    "annuncio WHERE idAnnuncio = ?;";
            Connection conn = DBConnection.getIstance().connection();
            AnnuncioModel annuncio = null;

            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setInt(1, annuncioID);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    annuncio = new AnnuncioModel();
                    annuncio.setIdAnnuncio(annuncioID);
                    annuncio.setCopiaMangaID(rs.getInt("copiaMangaID"));
                    annuncio.setNomeManga(rs.getString(TITOLO_MANGA));
                    annuncio.setUtenteVenditoreID(rs.getInt("utenteVenditoreID"));
                    annuncio.setVolume(rs.getInt(VOLUME));
                    annuncio.setNomeUtente(rs.getString(USERNAME_VENDITORE));
                    annuncio.setPrezzo(rs.getBigDecimal(PREZZO_DI_VENDITA));
                    annuncio.setDataAnnuncio(rs.getTimestamp("dataAnnuncio").toLocalDateTime());

                }
            } catch (SQLException e) {
                logger.severe("Errore nel AnnuncioDAO in getDatiAnnuncioByAnnuncioID : " + e.getMessage());
            }
            return annuncio;

        }




    //MODIFICA LO STATO DI UN ANNUNCIO PERCHE SIA CONTRASSEGNATO COME ACCETTATO
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











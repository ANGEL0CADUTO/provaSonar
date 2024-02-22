package dao;

import model.OffertaModel;
import model.OffertaRicevuta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OffertaDAO {

    private static final Logger logger = Logger.getLogger(OffertaDAO.class.getName());
    private static final String USERNAME_OFFERENTE = "usernameOfferente";
    private static final String ANNUNCIOID = "annuncioID";
    private static final String DATA_VENDITA = "dataVendita";
    private static final String ID_OFFERTA = "idOfferta";
    private static final String OFFERTA_PREZZO = "offertaPrezzo";


    //INSERISCE UNA NUOVA OFFERTA PER UN ANNUNCIO
    public boolean insertOfferta(OffertaModel offerta) {
        boolean b = false;
        String query = "INSERT INTO offerta (annuncioID, copiaMangaID, utenteOfferenteID,usernameOfferente, offertaPrezzo, dataOfferta) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, offerta.getAnnuncioID());
            st.setInt(2,offerta.getCopiaMangaID());
            st.setInt(3, offerta.getUtenteOfferenteID());
            st.setString(4,offerta.getUsernameOfferente());
            st.setBigDecimal(5, (offerta.getOffertaPrezzo()).setScale(2, RoundingMode.HALF_UP));
            st.setTimestamp(6,Timestamp.valueOf(offerta.getDataOfferta()));


            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                b = true;
            }
        } catch (SQLException e) {
            logger.severe("Errore in insertOfferta in OFFERTADAO: " + e.getMessage());
        }
        return b;
    }


    //RICAVA TUTTE LE OFFERTE RICEVUTE PER UN ANNUNCIO
    public List<OffertaRicevuta> getOfferteRicevuteByAnnuncioID(int id){
        ArrayList<OffertaRicevuta> array = new ArrayList<>();
        String query = "SELECT idOfferta,utenteOfferenteID,usernameOfferente,offertaPrezzo, dataOfferta,copiaMangaID " +
                "FROM offerta " +
                "WHERE annuncioID = ? AND statoOfferta = 1 ;";
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1,id);
            ResultSet rs = st.executeQuery();


            while(rs.next()){

                OffertaRicevuta offerta = new OffertaRicevuta();
                offerta.setIdOfferta(rs.getInt(ID_OFFERTA));
                offerta.setAnnuncioID(id);
                offerta.setUtenteOfferenteID(rs.getInt("utenteOfferenteID"));
                offerta.setUsernameOfferente(rs.getString(USERNAME_OFFERENTE));
                offerta.setCopiaMangaID(rs.getInt("copiaMangaID"));
                offerta.setOffertaPrezzo(rs.getBigDecimal(OFFERTA_PREZZO));
                offerta.setDataOfferta(rs.getTimestamp("dataOfferta").toLocalDateTime());
                array.add(offerta);

            }
        } catch (SQLException e) {
            logger.severe("Errore in getOfferteRicevuteByAnnuncioID in OFFERTADAO: " + e.getMessage());
        }

        return array;
    }


    //RICAVA IL NUMERO TOTALE DI OFFERTE RICEVUTE PER UN ANNUNCIO
    public int getNumeroOfferteRicevuteByAnnuncioID(int idAnnuncio){
        String query = "SELECT COUNT(*) from offerta WHERE annuncioID = ? AND statoOfferta = 1";
        Connection conn = DBConnection.getIstance().connection();
        int numero = 0;
        try(PreparedStatement st = conn.prepareStatement(query)){

            st.setInt(1,idAnnuncio);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                numero = rs.getInt(1);
            }


        }catch (SQLException e){
            logger.severe("Errore in getNumeroOfferteRicevuteByAnnuncioID in OFFERTADAO: "+ e.getMessage());
        }
        return numero;
    }


    //ACCETTA UNA OFFERTA SPECIFICA UTILIZZANDONE L ID
    public boolean accettaOfferta(OffertaModel offerta){
        boolean b = false;
        String query = "UPDATE offerta SET statoOfferta = CASE WHEN idOfferta != ? THEN 2 WHEN idOfferta = ? THEN 3 END, dataVendita = NOW() WHERE annuncioID = ?";
        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, offerta.getIdOfferta());
            st.setInt(2, offerta.getIdOfferta());
            st.setInt(3,offerta.getAnnuncioID());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                b = true;
            }
        } catch (SQLException e) {
            logger.severe("Errore in accettaOfferta in OFFERTADAO: " + e.getMessage());
        }
        return b;

    }

    //RICAVA INFORMAZIONI PER UNA OFFERTA ACCETTATA
   public OffertaModel getDatiOffertaAccettataByAnnuncioID(int idAnnuncio){

       String query = "SELECT idOfferta, annuncioID,usernameOfferente,offertaPrezzo,dataVendita,copiaMangaID" +
               " FROM offerta WHERE annuncioID =? AND statoOfferta=3";
       Connection conn = DBConnection.getIstance().connection();
       OffertaModel offertaModel = null;
        try(PreparedStatement st = conn.prepareStatement(query)){

            st.setInt(1,idAnnuncio);

            ResultSet rs = st.executeQuery();
            if(rs.next()){

                offertaModel= new OffertaModel();
                offertaModel.setIdOfferta(rs.getInt(ID_OFFERTA));
                offertaModel.setAnnuncioID(rs.getInt(ANNUNCIOID));
                offertaModel.setUsernameOfferente(rs.getString(USERNAME_OFFERENTE));
                offertaModel.setOffertaPrezzo(rs.getBigDecimal(OFFERTA_PREZZO));
                offertaModel.setDataOfferta(rs.getTimestamp(DATA_VENDITA).toLocalDateTime());

                offertaModel.setCopiaMangaID(rs.getInt("copiaMangaID"));
            }

        } catch (SQLException e) {
            logger.severe("ERRORE IN OFFERTADAO IN getDatiOffertaAccettataByAnnuncioID " + e.getMessage() );}



        return offertaModel;
   }



    //RICAVA INFORMAZIONI PER UNA OFFERTA SPECIFICATA DAL SUO ID
    public OffertaRicevuta getDatiOffertaByOffertaID(int id){

        String query = "SELECT idOfferta, annuncioID,usernameOfferente,offertaPrezzo,dataVendita " +
                "FROM offerta WHERE idOfferta = ?;";
        Connection conn = DBConnection.getIstance().connection();
        OffertaRicevuta offertaRicevuta = null;
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();


            if(rs.next()){
                offertaRicevuta = new OffertaRicevuta();
                offertaRicevuta.setIdOfferta(rs.getInt(ID_OFFERTA));
                offertaRicevuta.setUsernameOfferente(rs.getString(USERNAME_OFFERENTE));
                offertaRicevuta.setAnnuncioID(rs.getInt(ANNUNCIOID));
                offertaRicevuta.setOffertaPrezzo(rs.getBigDecimal(OFFERTA_PREZZO));
                offertaRicevuta.setDataVendita(rs.getTimestamp(DATA_VENDITA).toLocalDateTime());
            }


        }catch(SQLException e){
            logger.severe("Errore in getDatiOffertaByOffertaID : " + e.getMessage());
        }

        return offertaRicevuta;
    }


    //RICAVA INFORMAZIONI OFFERTA ACCETTATA DALL'IDUTENTE
    public List<OffertaModel> getDatiOffertaAccettataByUtenteID(int id){

        String query = "SELECT idOfferta, annuncioID, offertaPrezzo, dataVendita, recensioneFatta " +
        "FROM offerta " +
        "WHERE utenteOfferenteID = ? AND statoOfferta = 3";

        Connection  conn = DBConnection.getIstance().connection();
        List<OffertaModel> array = new ArrayList<>();

        try(PreparedStatement st = (conn.prepareStatement(query))){
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                OffertaModel offerta = new OffertaModel();
                offerta.setIdOfferta(rs.getInt(ID_OFFERTA));
                offerta.setAnnuncioID(rs.getInt(ANNUNCIOID));

                offerta.setOffertaPrezzo(rs.getBigDecimal(OFFERTA_PREZZO));
                offerta.setDataOfferta(rs.getTimestamp(DATA_VENDITA).toLocalDateTime());
                offerta.setRecensito(rs.getInt("recensioneFatta"));


                array.add(offerta);

            }
        } catch (SQLException e) {
            logger.severe("Errore nel OffertaDAO in in getAcquistiByUtenteID " + e.getMessage());
        }
        return array;
    }

    //RICAVA IL QUANTITATIVO DI DENARO CHE L'UTENTE HA ATTUALMENTE IN OFFERTE ANCORA IN CORSO
    public BigDecimal getPendingMoneyUtenteByUtenteID(int id) {
        String query = "SELECT SUM(offertaPrezzo) FROM offerta WHERE utenteOfferenteID = ? AND statoOfferta = 1;";
        Connection conn = DBConnection.getIstance().connection();

        BigDecimal cifra = new BigDecimal("0");

        try(PreparedStatement st = conn.prepareStatement(query)){
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                cifra = rs.getBigDecimal(1);
            }

        } catch (SQLException e) {
            logger.severe("Errore in OffertaDAO in getPendingMoneyUtenteByUtenteID: " + e.getMessage());
        }
        return cifra;
    }
}

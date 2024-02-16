package dao;

import model.OffertaModel;
import model.OffertaRicevuta;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class OffertaDAO {

    private static final Logger logger = Logger.getLogger(OffertaDAO.class.getName());

    public boolean insertOfferta(OffertaModel offerta) {
        boolean b = false;
        String query = "INSERT INTO offerta (annuncioID, copiaMangaID, utenteOfferenteID,usernameOfferente, offertaPrezzo, dataOfferta) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = DBConnection.getIstance().connection();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            System.out.println("QUI: " + offerta.getAnnuncioID());
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


    public ArrayList<OffertaRicevuta> getOfferteRicevuteByAnnuncioID(int id){
        ArrayList<OffertaRicevuta> array = new ArrayList<>();
        String query = "SELECT idOfferta,utenteOfferenteID,usernameOfferente,offertaPrezzo, dataOfferta " +
                "FROM offerta " +
                "WHERE annuncioID = ? AND statoOfferta = 1 ;";
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement st = conn.prepareStatement(query)) {
            System.out.println(id);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();

            System.out.println("prima del while");

            while(rs.next()){
                System.out.println("siamo nel while");
                OffertaRicevuta offerta = new OffertaRicevuta();
                offerta.setIdOfferta(rs.getInt("idOfferta"));
                offerta.setAnnuncioID(id);
                offerta.setUtenteOfferenteID(rs.getInt("utenteOfferenteID"));
                offerta.setUsernameOfferente(rs.getString("usernameOfferente"));
                //offerta.setVotoRecensioni(rs.getDouble("votoRecensioni"));
                offerta.setOffertaPrezzo(rs.getBigDecimal("offertaPrezzo"));
                offerta.setDataOfferta(rs.getTimestamp("dataOfferta").toLocalDateTime());
                array.add(offerta);
                System.out.println("Ho riempito con :" + array.getLast().getUsernameOfferente());
            }
        } catch (SQLException e) {
            logger.severe("Errore in getOfferteRicevuteByAnnuncioID in OFFERTADAO: " + e.getMessage());
        }

        return array;
    }

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

//select idOfferta, annuncioID,usernameOfferente,offertaPrezzo,dataVendita,copiaMangaID FROM offerta WHERE idUtente =? AND statoOfferta=3

   public OffertaModel getDatiOffertaAccettataByAnnuncioID(int idAnnuncio){

       String query = "SELECT idOfferta, annuncioID,usernameOfferente,offertaPrezzo,dataVendita,copiaMangaID" +
               " FROM offerta WHERE annuncioID =? AND statoOfferta=3";
       Connection conn = DBConnection.getIstance().connection();
       OffertaModel offertaModel = null;
        try(PreparedStatement st = conn.prepareStatement(query)){

            st.setInt(1,idAnnuncio);

            ResultSet rs = st.executeQuery();
            if(rs.next()){
                System.out.println("PORCONE IN OFFERTADAO getDatiOffertaAccettataByAnnuncioID");
                offertaModel= new OffertaModel();
                offertaModel.setIdOfferta(rs.getInt("idOfferta"));
                offertaModel.setAnnuncioID(rs.getInt("annuncioID"));
                offertaModel.setUsernameOfferente(rs.getString("usernameOfferente"));
                offertaModel.setOffertaPrezzo(rs.getBigDecimal("offertaPrezzo"));
                offertaModel.setDataOfferta(rs.getTimestamp("dataVendita").toLocalDateTime());
                offertaModel.setCopiaMangaID(rs.getInt("copiaMangaID"));
            }

        } catch (SQLException e) {
            logger.severe("ERRORE IN OFFERTADAO IN getMangaVendutiByUtenteID " + e.getMessage() );}



        return offertaModel;
   }




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
                offertaRicevuta.setIdOfferta(rs.getInt("idOfferta"));
                offertaRicevuta.setUsernameOfferente(rs.getString("usernameOfferente"));
                offertaRicevuta.setAnnuncioID(rs.getInt("annuncioID"));
                offertaRicevuta.setOffertaPrezzo(rs.getBigDecimal("offertaPrezzo"));
                offertaRicevuta.setDataVendita(rs.getTimestamp("dataVendita").toLocalDateTime());
            }


        }catch(SQLException e){
            logger.severe("Errore in getDatiOffertaByOffertaID : " + e.getMessage());
        }

        return offertaRicevuta;
    }



    public ArrayList<OffertaModel> getDatiOffertaAccettataByUtenteID(int id){

        String query = "SELECT idOfferta, annuncioID, offertaPrezzo, dataVendita, recensioneFatta " +
        "FROM offerta " +
        "WHERE utenteOfferenteID = ? AND statoOfferta = 3";

        Connection  conn = DBConnection.getIstance().connection();
        ArrayList<OffertaModel> array = new ArrayList<>();

        try(PreparedStatement st = (conn.prepareStatement(query))){
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                OffertaModel offerta = new OffertaModel();
                offerta.setIdOfferta(rs.getInt("idOfferta"));
                offerta.setAnnuncioID(rs.getInt("annuncioID"));

                offerta.setOffertaPrezzo(rs.getBigDecimal("offertaPrezzo"));
                offerta.setDataOfferta(rs.getTimestamp("dataVendita").toLocalDateTime());
                offerta.setRecensito(rs.getInt("recensioneFatta"));


                array.add(offerta);

            }
        } catch (SQLException e) {
            logger.severe("Errore nel OffertaDAO in in getAcquistiByUtenteID " + e.getMessage());
        }
        return array;
    }




}

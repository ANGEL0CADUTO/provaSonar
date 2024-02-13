package dao;

import controllerapplicativo.AnnuncioControllerApplicativo;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class RecensioneDAO {
    private static final Logger logger = Logger.getLogger(RecensioneDAO.class.getName());
    public ArrayList<Recensione> getCopieMangaListByUserID(UtenteModel model) {
        ArrayList<Recensione> array = new ArrayList<>();
        return array;
    }

    public ArrayList<Recensione> getRecensioniRicevuteByUtenteID(int id) {

        System.out.println("mi trovo all'inizio di getRecensioniRicevuteByUtenteID");
        String query = "SELECT usernameRecensore, voto,testo,offertaID FROM recensione " +
                "WHERE statoRecensione = 1 AND recensitoID = ?;";


        ArrayList<Recensione> array = new ArrayList<>();

        Connection conn = DBConnection.getIstance().connection();


        try ( PreparedStatement st = conn.prepareStatement(query)) {
            System.out.println("mi trovo al try di getRecensioniRicevuteByUtenteID");
            st.setInt(1,1);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                System.out.println("mi trovo nel while di getRecensioniRicevuteByUtenteID");
                Recensione recensione = new Recensione();
                recensione.setUsernameRecensore(rs.getString("usernameRecensore"));
                recensione.setVoto(rs.getDouble("voto"));
                recensione.setTesto(rs.getString("testo"));

                int idOfferta = rs.getInt("offertaID");
                OffertaDAO dao2 = new OffertaDAO();
                OffertaRicevuta offerta = dao2.getDatiOffertaByOffertaID(idOfferta);
                recensione.setPrezzoFinale(offerta.getOffertaPrezzo());
                recensione.setDataAcquisto(offerta.getDataVendita());


                //CORREGGERE

                AnnuncioDAO dao3 = new AnnuncioDAO();
                AnnuncioModel annuncio = dao3.getDatiAnnuncioByAnnuncioID(offerta.getAnnuncioID());
                recensione.setTitoloRecensito(annuncio.getNomeManga());
                recensione.setPrezzoIniziale(annuncio.getPrezzo());
                recensione.setVolumeRecensito(annuncio.getVolume());

                array.add(recensione);

            }


        }catch(SQLException e){
            logger.severe("Errore in RecensioneDAO in getRecensioniRicevuteByUtenteID :" + e.getMessage() );
        }
        return array;
    }
}

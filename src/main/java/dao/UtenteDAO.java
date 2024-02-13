package dao;
import bean.DatiUtenteBean;
import bean.UtenteBean;
import model.UtenteModel;


import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Logger;
public class UtenteDAO {
    private static final Logger logger = Logger.getLogger(UtenteDAO.class.getName());

    public boolean searchUser(UtenteBean bean) {
        boolean b = false;
        String query = "SELECT * FROM mangaink.utente WHERE email = ?";
        Connection conn;

        try {
            conn = DBConnection.getIstance().connection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, bean.getEmail()); // Aggiungi il parametro della email
            ResultSet rs =st.executeQuery();


            if (rs != null && rs.next() && rs.getString("password").equals(bean.getPassword())) {
                System.out.println(rs.getMetaData().getColumnCount()); // Numero di colonne

                System.out.println(rs.getString("password") + " " + rs.getBigDecimal("credito"));

                bean.setIdUtente(rs.getInt("idUtente"));
                bean.setUsername(rs.getString("username"));
                bean.setCredito(rs.getBigDecimal("credito"));
                bean.setVotoRecensione(rs.getDouble("votoRecensioni"));

                int informazioniUtenteID = rs.getInt("informazioniUtenteID");
                if(!rs.wasNull()) {
                    DatiUtenteDao dao = new DatiUtenteDao();
                    DatiUtenteBean beanDati;

                    beanDati = dao.getDatiUserByInformazioniUtenteID(rs.getInt("informazioniUtenteID"));
                    beanDati.setIdInformazioniUtente(informazioniUtenteID);
                    bean.setDatiUtente(beanDati);
                }


                logger.info("ha funzionato");
                b = true;
            } else {
                logger.info("fallito");
            }

        } catch (SQLException e) {
            logger.severe("Errore nel tentativo di stabilire la connessione in searchUser: " + e.getMessage());
        }

        return b;
    }


    //Questa funzione non può ritornare semplicemente true o false, ma anche il bean O --FORSE-- NO LO DEVE POPOLARE!!!!
    /*public boolean searchUser(UtenteBean bean) {
        boolean b = false;
        String query = "SELECT * FROM mangaink.utente WHERE email = ?";
        Connection conn = DBConnection.getIstance().connection();

        try (PreparedStatement st = conn.prepareStatement(query);
             ResultSet rs = st.executeQuery()) {

            st.setString(1, bean.getEmail());

            if (rs != null && rs.next() && rs.getString("password").equals(bean.getPassword())) {
                System.out.println(rs.getMetaData().getColumnCount()); // Numero di colonne

                System.out.println(rs.getString("password") + " " + rs.getBigDecimal("credito"));

                bean.setIdUtente(rs.getInt("idUtente"));
                bean.setUsername(rs.getString("username"));
                bean.setCredito(rs.getBigDecimal("credito"));
                bean.setVotoRecensione(rs.getDouble("votoRecensioni"));

                logger.info("ha funzionato");
                b = true;
            } else {
                logger.info("fallito");
            }

        } catch (SQLException e) {
            logger.severe("E' stata lanciata la exception nella searchUser in utenteDAO " + e.getMessage());
        }

        return b;
    }

     */




    public boolean addUser(UtenteBean bean) {
        Connection conn = DBConnection.getIstance().connection();
        boolean b = false;
        String query = "INSERT INTO mangaink.utente (email, username ,password ) VALUES (?, ?, ?)";
        try(PreparedStatement st = conn.prepareStatement(query)){

            if(bean.getEmail().isEmpty() || bean.getUsername().isEmpty()||bean.getPassword().isEmpty()) {
                logger.warning("Uno o più campi sono vuoti. Inserimento utente fallito.");
                return false;}

            st.setString(1,bean.getEmail());
            st.setString(2, bean.getUsername());
            st.setString(3, bean.getPassword());

            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {
                b = true;
                logger.info("Inserimento utente riuscito");
            } else {
                logger.info("Inserimento utente fallito");
            }
        }
        catch (SQLException e){
            logger.severe("E' stata lanciata la exception nell'addUser in utenteDao " + e.getMessage());
        }

        return true;
    }


   public boolean informazioniUtente(UtenteBean bean ){
        Boolean b= false;
        Connection conn = DBConnection.getIstance().connection();
        String query = "UPDATE mangaink.utente " +
            "SET informazioniUtenteID = (SELECT MAX(idInformazioniUtente) FROM mangaink.informazioniutente) " +
            "WHERE email = ?";


        try(PreparedStatement st = conn.prepareStatement(query)){

            st.setString(1,bean.getEmail());

           // st.setString(1,"Ade@gmail.com");


            int righeScritte = st.executeUpdate();
            System.out.println(righeScritte);


            if (righeScritte > 0) {
                b = true;
                logger.info("Accoppiamente Riuscito");

            } else {
                logger.info("Accoppiamente Fallito");
                System.out.println(righeScritte);

            }


        } catch (SQLException e) {
          //  e.printStackTrace(); PER FARMI DIRE L'ERRORE COMPLETO
            throw new RuntimeException(e);}

         return b ;

    }


    public boolean userDeposit(UtenteModel utenteModel,String cifraString){ //DEPOSITA IL TUO CREDITO
        Boolean b= false;
        Connection conn = DBConnection.getIstance().connection();
        String query = "UPDATE mangaink.utente SET credito= credito +  ? " +
                "WHERE idUtente =? ";

        try(PreparedStatement st = conn.prepareStatement(query)){
            BigDecimal cifra = new BigDecimal(cifraString);
            st.setBigDecimal(1,cifra);
            st.setInt(2,utenteModel.getIdUtente());


            int righeScritte = st.executeUpdate();
            System.out.println(righeScritte);


            if (righeScritte > 0) {
                b = true;
                logger.info("Deposito Credito Riuscito");

            } else {
                logger.info("Deposito Credito Fallito");
                System.out.println(righeScritte);

            }


        } catch (SQLException e) {
            //  e.printStackTrace(); PER FARMI DIRE L'ERRORE COMPLETO
            throw new RuntimeException(e);}

        return b;




    }

    public boolean userPreliev(UtenteModel model,String cifraString){ //PRELEVAA IL TUO CREDITO
        //DepositaEPrelevaGrafico pr = new DepositaEPrelevaGrafico();
        Boolean b= false;
        Connection conn = DBConnection.getIstance().connection();
        String query = "UPDATE mangaink.utente SET credito= credito - ?  \n" +
                "WHERE idUtente = ? ";


        try(PreparedStatement st = conn.prepareStatement(query)){

            BigDecimal cifra = new BigDecimal(cifraString);
            st.setBigDecimal(1,cifra);//SONO LEGATI ALLA QUERY
            st.setInt(2,model.getIdUtente());

            int righeScritte = st.executeUpdate();

            if (righeScritte > 0) {
                b = true;
                logger.info("Prelievo Credito Riuscito");

            } else {
                logger.info("Prelievo Credito Fallito");

            }


        } catch (SQLException e) {
            //  e.printStackTrace(); PER FARMI DIRE L'ERRORE COMPLETO
            throw new RuntimeException(e);}

        return b;




    }

}

















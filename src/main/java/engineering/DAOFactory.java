package engineering;

import dao.*;
import dao.memory.DatiUtenteDAOMemory;
import dao.memory.UtenteDAOMemory;
//import dao.memory.*; // Qui metteremo le versioni "In Memory"
import java.io.InputStream;
import java.util.Properties;

public class DAOFactory {

    private static DAOFactory instance = null;
    private String persistenza;

    private DAOFactory() {
        try (InputStream config = getClass().getClassLoader().getResourceAsStream("connection.properties")) {
            Properties properties = new Properties();
            properties.load(config);
            // Leggiamo se siamo in "DEMO" o "DBMS"
            this.persistenza = properties.getProperty("PERSISTENZA");
        } catch (Exception e) {
            this.persistenza = "DEMO"; // Fallback di sicurezza
        }
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    // Metodo per ottenere il DAO delle Notifiche
//    public NotificheDAO getNotificheDAO() {
//        if ("DEMO".equalsIgnoreCase(persistenza)) {
//            return new NotificheDAOMemory();
//        } else {
//            return new NotificheDAO();
//        }
//    }

    // Metodo per ottenere il DAO degli Annunci(POLIMORFIMO )
//    public AnnuncioDAO getAnnuncioDAO() {
//        if ("DEMO".equalsIgnoreCase(persistenza)) {
//            return new AnnuncioDAOMemory();
//        } else {
//            return new AnnuncioDAO();
//        }
//    }

    public DatiUtenteDaoInterface getDatiUtenteDAO() {
        if ("DEMO".equalsIgnoreCase(persistenza)) {
            return new DatiUtenteDAOMemory();//deve essere in memory
        } else {
            return new DatiUtenteDao();
        }
    }

public UtenteDaoInterface getUtenteDAO() {
        if ("DEMO".equalsIgnoreCase(persistenza)) {
            return new UtenteDAOMemory();
        } else {
            return new UtenteDAO();
        }
    }











}





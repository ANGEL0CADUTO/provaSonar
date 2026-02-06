package dao;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;



//CLASSE SINGLETON PER LA CONNESSIONE
public class DBConnection {
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());


    private static DBConnection instance = null;
    private Connection conn = null;

    private String jdbcUrl;
    private String username;
    private String password;


    public static synchronized DBConnection getIstance(){
        if(instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }


    public synchronized Connection connection(){

        if(this.conn == null){
            getInfo();

            try {
                this.conn = DriverManager.getConnection(jdbcUrl,username,password);
            } catch (SQLException e) {
                logger.severe("Errore in DBConnection nella connection() : " + e.getMessage());
            }
        }

        return this.conn;
    }

    private void getInfo(){
        try(InputStream config = getClass().getClassLoader().getResourceAsStream("connection.properties")){

            if (config == null) {
                logger.severe("Errore: Il file connection.properties non è stato trovato nel classpath!");
                return;
            }

            Properties properties = new Properties();
            properties.load(config);

            jdbcUrl = properties.getProperty("CONNECTION_URL");
            username = properties.getProperty("LOGIN_USER");
            password = properties.getProperty("LOGIN_PASSWORD");


        } catch (IOException e) {
            logger.severe("Errore nella getInfo  " + e.getMessage());
        }
    }

}

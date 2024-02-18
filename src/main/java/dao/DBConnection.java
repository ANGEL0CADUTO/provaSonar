package dao;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;


public class DBConnection {
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    private static final String PATH = "src/main/resources/connection.properties";

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
        try(FileInputStream config = new FileInputStream(PATH)){

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

package com.example.demoproject;

import java.sql.Connection; //ho aggiunto nei moduli x farlo funzionare

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DBConnection {
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());
    public Connection connection(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/utenti";
        String username = "root";
        String password = System.getenv("MySQL_Password");
        Connection conn = null;

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(jdbcUrl,username,password);

    }
    catch (ClassNotFoundException | SQLException e){
        logger.severe("errore nel tentativo di stabilire la connessione col db");

    }

        return conn;
    }


    public void close(Connection conn){

        try {
            conn.close();
        }
        catch (SQLException e){
            logger.severe("Errore nella chiusura della connessione");

        }
    }


}

package com.example.demoproject.dao;

import java.sql.Connection; //ho aggiunto nei moduli x farlo funzionare

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DBConnection {
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());//BUONA PRATICA USARE IL LOGGER(lo dice sonarcloud)
    public Connection connection(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/mangaink";
        String username = "root";
        String password = "root";//BUONA PRATICA NON MOSTRARE LA PASSWORD DB IN CHIARO(dichiaro variabile d'ambiente WINDOWS)
        Connection conn = null;

    try{
       // Class.forName("com.mysql.cj.jdbc.Driver"); QUESTO SERVIVA A CARICARE IL DRIVER, SEMBRA CHE CON LE VERSIONI JDBC RECENTI VENGA CREATO DIRETTAMENTE NELLA PROX LINEA DI CODICE
        conn = DriverManager.getConnection(jdbcUrl,username,password);

    }
    catch (SQLException e){
        logger.severe("errore nel tentativo di stabilire la connessione col db");

    }

        return conn;
    }


    public void close(Connection conn){//CHIUSURA CONNESSIONE

        try {
            conn.close();
        }
        catch (SQLException e){
            logger.severe("Errore nella chiusura della connessione");

        }
    }


}

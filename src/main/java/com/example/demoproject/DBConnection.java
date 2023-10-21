package com.example.demoproject;

import java.sql.Connection; //ho aggiunto nei moduli x farlo funzionare
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

    public Connection connection(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/utenti";
        String username = "root";
        String password = "root";
        Connection conn = null;

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(jdbcUrl,username,password);

    }
    catch (ClassNotFoundException | SQLException e){
        System.err.println("errore nel tentativo di stabilire la connessione col db");

    }

        return conn;
    }


    public void close(Connection conn){

        try {
            conn.close();
        }
        catch (SQLException e){
            System.err.println("Errore nella chiusura della connessione");
            e.printStackTrace();
        }
    }


}

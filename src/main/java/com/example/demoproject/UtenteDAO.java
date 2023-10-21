package com.example.demoproject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtenteDAO {

    public boolean searchUser(String email,String password){
        DBConnection DB = new DBConnection();
        boolean b = false;
        try{
            Connection conn = DB.connection();
            String query = "SELECT password FROM utenti.utente WHERE email =";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT password FROM utenti.utente WHERE email ='" + email +"'");
            rs.next();
            if(rs.getString("password").equals(password)){
                System.out.println("ha funzionato");
                b = true;
            }
            else{
                System.out.println("fallito");
            }

        }
        catch(SQLException e){
            System.err.print("errore nella ricerca dell'user");

        }

        return b;


    }


}

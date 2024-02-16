package controllerapplicativo;

import dao.AnnuncioDAO;
import dao.UtenteDAO;
import model.AnnunciModel;
import model.AnnuncioModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CompraMangaControllerApplicativo {
    public ArrayList<AnnuncioModel> showAnnunce(int id,String name) {
     AnnuncioDAO dao = new AnnuncioDAO();
     ArrayList<AnnuncioModel> array = dao.getAnnunci(id,name);
     UtenteDAO dao2 = new UtenteDAO();

     for(AnnuncioModel a : array){
         a.setVotoUtente(dao2.getVotoByUtenteID(a.getUtenteVenditoreID()));
         System.out.println("mi trovo dentro l'array e il voto è " + a.getVotoUtente() +"per l'utente con id " + a.getUtenteVenditoreID());
     }
     return array;

    }
}

package controllerapplicativo;

import dao.RecensioneDAO;
import model.Recensione;

import java.util.ArrayList;

public class RecensioniApplicativo {

    public ArrayList<Recensione> getMyRecensioniRicevute(int id){
        RecensioneDAO dao = new RecensioneDAO();




        return dao.getRecensioniRicevuteByUtenteID(id);
    }
}

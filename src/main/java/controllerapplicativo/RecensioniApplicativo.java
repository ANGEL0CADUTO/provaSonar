package controllerapplicativo;

import dao.RecensioneDAO;
import model.Recensione;

import java.util.List;

public class RecensioniApplicativo {

    public List<Recensione> getMyRecensioniRicevute(int id){
        RecensioneDAO dao = new RecensioneDAO();




        return dao.getRecensioniRicevuteByUtenteID(id);
    }
}

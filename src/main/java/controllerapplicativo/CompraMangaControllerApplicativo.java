package controllerapplicativo;

import dao.AnnuncioDAO;
import model.AnnunciModel;
import model.AnnuncioModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CompraMangaControllerApplicativo {
    public ArrayList<AnnuncioModel> showAnnunce(int id,String name) {
     AnnuncioDAO dao = new AnnuncioDAO();
     return dao.getAnnunci(id,name);
    }
}

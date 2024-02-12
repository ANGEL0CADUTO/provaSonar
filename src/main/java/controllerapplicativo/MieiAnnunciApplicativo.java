package controllerapplicativo;

import dao.AnnuncioDAO;
import dao.OffertaDAO;
import model.AnnuncioModel;
import view.MieiAnnunciGrafico;

import java.util.ArrayList;

public class MieiAnnunciApplicativo {

    public ArrayList<AnnuncioModel> getMyAnnunci(int id){
        AnnuncioDAO dao = new AnnuncioDAO();
        return dao.getMyAnnunci(id);

    }

}

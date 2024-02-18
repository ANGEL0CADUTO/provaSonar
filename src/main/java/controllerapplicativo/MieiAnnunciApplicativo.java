package controllerapplicativo;

import dao.AnnuncioDAO;

import model.AnnuncioModel;



import java.util.List;

public class MieiAnnunciApplicativo {

    public List<AnnuncioModel> getMyAnnunci(int id){
        AnnuncioDAO dao = new AnnuncioDAO();
        return dao.getMyAnnunci(id);

    }

}

package controllerapplicativo;

import dao.AnnuncioDAO;
import model.AnnunciModel;

public class CompraMangaControllerApplicativo {
    public AnnunciModel showAnnunce() {
     AnnuncioDAO dao = new AnnuncioDAO();
     return dao.getAnnunci();
    }
}

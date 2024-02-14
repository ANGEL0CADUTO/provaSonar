package controllerapplicativo;

import bean.CopiaMangaBean;
import model.CopiaMangaCollectionModel;
import dao.CopiaMangaDAO;
import bean.UtenteBean;
import model.CopiaMangaModel;
import model.UtenteModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class LibreriaUtenteControllerApplicativo {



    public ArrayList<CopiaMangaModel> showUserManga(UtenteBean bean){

        CopiaMangaDAO dao = new CopiaMangaDAO();

        UtenteModel utente = new UtenteModel();

        utente.setIdUtente(bean.getIdUtente());
        utente.setEmail(bean.getEmail());
        utente.setPassword(bean.getPassword());
        utente.setUsername(bean.getPassword());
        utente.setVotoRecensioni(bean.getVotoRecensione());
        utente.setCredito(bean.getCredito());

        return dao.getCopieMangaListByUserID(utente);

    }
    public int aggiungiManga(CopiaMangaBean copiaBean){
        CopiaMangaModel copiaModel = new CopiaMangaModel();

        copiaModel.setIdUtente(copiaBean.getIdUtente());
        copiaModel.setTitolo(copiaBean.getTitolo());
        copiaModel.setVolume(copiaBean.getVolume());
        copiaModel.setDataAcquisto(copiaBean.getDataAcquisto());

        CopiaMangaDAO dao = new CopiaMangaDAO();

        return dao.aggiungiManga(copiaModel);

    }
}

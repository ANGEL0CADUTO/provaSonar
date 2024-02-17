package Pattern;

import bean.OffertaBean;
import dao.AnnuncioDAO;
import dao.CopiaMangaDAO;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.OffertaModel;
import model.UtenteModel;

public class OffertaFacade {
    private OffertaDAO offertaDAO;
    private UtenteDAO utenteDAO;
    private AnnuncioDAO annuncioDAO;

    private CopiaMangaDAO copiaMangaDAO;

    public OffertaFacade() {
        this.offertaDAO = new OffertaDAO();
        this.utenteDAO = new UtenteDAO();
        this.annuncioDAO = new AnnuncioDAO();
        this.copiaMangaDAO = new CopiaMangaDAO();
    }

    public boolean accettaOffertaByOffertaID(OffertaModel offerta, int idUtenteVenditore) {


        if (copiaMangaDAO.setStatoVendutoByCopiaMangaID(offerta.getCopiaMangaID())&& userPreliev(offerta.getUtenteOfferenteID(), offerta.getOffertaPrezzo().toString()) &&
                offertaDAO.accettaOfferta(offerta) &&
                userDeposit(idUtenteVenditore, offerta.getOffertaPrezzo().toString())) {
            return annuncioDAO.setStatoAccettatoByAnnuncioID(offerta.getAnnuncioID());
        }
        return false;
    }

    private boolean userDeposit(int idUtente, String amount) {
        UtenteModel utenteModel = new UtenteModel();
        utenteModel.setIdUtente(idUtente);
        return utenteDAO.userDeposit(utenteModel, amount);
    }

    private boolean userPreliev(int idUtente, String amount) {
        UtenteModel utenteModel = new UtenteModel();
        utenteModel.setIdUtente(idUtente);
        return utenteDAO.userPreliev(utenteModel, amount);
    }
}

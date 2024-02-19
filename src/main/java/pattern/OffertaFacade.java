package pattern;

import dao.AnnuncioDAO;
import dao.CopiaMangaDAO;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.CopiaMangaModel;
import model.OffertaModel;
import model.UtenteModel;

public class OffertaFacade {
    private final OffertaDAO offertaDAO;
    private final UtenteDAO utenteDAO;
    private final AnnuncioDAO annuncioDAO;
    private final CopiaMangaDAO copiaMangaDAO;

    public OffertaFacade() {
        this.offertaDAO = new OffertaDAO();
        this.utenteDAO = new UtenteDAO();
        this.annuncioDAO = new AnnuncioDAO();
        this.copiaMangaDAO = new CopiaMangaDAO();
    }

    public boolean accettaOffertaByOffertaID(OffertaModel offerta, CopiaMangaModel copia) {
        if (copiaMangaDAO.setStatoVendutoByCopiaMangaID(copia.getIdCopiaManga()) &&
                userPreliev(offerta.getUtenteOfferenteID(), offerta.getOffertaPrezzo().toString()) &&
                offertaDAO.accettaOfferta(offerta) &&
                userDeposit(offerta.getUtenteVenditoreID(), offerta.getOffertaPrezzo().toString())) {


            copiaMangaDAO.aggiungiManga(copia);
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

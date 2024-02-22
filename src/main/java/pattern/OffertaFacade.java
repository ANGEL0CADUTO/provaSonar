package pattern;

import dao.AnnuncioDAO;
import dao.CopiaMangaDAO;
import dao.OffertaDAO;
import dao.UtenteDAO;
import model.CopiaMangaModel;
import model.OffertaModel;
import model.UtenteModel;


// FACADE PATTERN PER GESTIRE L'ACCETTAZIONE DI UN OFFERTA PER IL PROPRIO ANNUNCIOD DI VENDITA
public class OffertaFacade {
    private final OffertaDAO offertaDAO;
    private final UtenteDAO utenteDAO;
    private final AnnuncioDAO annuncioDAO;
    private final CopiaMangaDAO copiaMangaDAO;

    //Inizializza gli oggetti DAO necessari per interagire con il database.
    public OffertaFacade() {
        this.offertaDAO = new OffertaDAO();
        this.utenteDAO = new UtenteDAO();
        this.annuncioDAO = new AnnuncioDAO();
        this.copiaMangaDAO = new CopiaMangaDAO();
    }

    //Accetta un'offerta in base all'ID dell'offerta e alla copia del manga associata.
    public boolean accettaOffertaByOffertaID(OffertaModel offerta, CopiaMangaModel copia) {

        //Effettua diverse operazioni, come aggiornare lo stato della copia del manga,
        // gestire il prelievo e il deposito di denaro dagli utenti coinvolti,
        // accettare l'offerta tramite l'offertaDAO, e infine aggiornare lo stato dell'annuncio ad "Accettato".
        if (copiaMangaDAO.setStatoVendutoByCopiaMangaID(copia.getIdCopiaManga()) &&
                userPreliev(offerta.getUtenteOfferenteID(), offerta.getOffertaPrezzo().toString()) &&
                offertaDAO.accettaOfferta(offerta) &&
                userDeposit(offerta.getUtenteVenditoreID(), offerta.getOffertaPrezzo().toString())) {


            copiaMangaDAO.aggiungiManga(copia);
            return annuncioDAO.setStatoAccettatoByAnnuncioID(offerta.getAnnuncioID());
        }
        return false;
    }


    //Gestisce il deposito
    private boolean userDeposit(int idUtente, String amount) {

        UtenteModel utenteModel = new UtenteModel();
        utenteModel.setIdUtente(idUtente);
        return utenteDAO.userDeposit(utenteModel, amount);

    }
    //Gestisce il prelievo
    private boolean userPreliev(int idUtente, String amount) {
        UtenteModel utenteModel = new UtenteModel();
        utenteModel.setIdUtente(idUtente);
        return utenteDAO.userPreliev(utenteModel, amount);
    }

}

package controllerapplicativo;

import dao.NotificheDAO;
import model.NotificaModel;

public class NotificheControllerApplicativo {


    // Modifica il tipo di ritorno da String a NotificheModel
    public NotificaModel getNotifica() {
        NotificheDAO dao = new NotificheDAO();
        return dao.readNotificaFromDatabase();


    }
}

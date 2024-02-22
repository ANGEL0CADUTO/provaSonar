package controllerapplicativo;

import dao.NotificheDAO;
import model.NotificaModel;

public class NotificheControllerApplicativo {


    public NotificaModel getNotifica() {
        NotificheDAO dao = new NotificheDAO();
        return dao.readNotificaFromDatabase();


    }
}

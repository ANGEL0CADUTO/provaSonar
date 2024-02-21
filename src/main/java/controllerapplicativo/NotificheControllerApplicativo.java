package controllerapplicativo;

import dao.json.NotificheJSONDAO;
import model.NotificaModel;

public class NotificheControllerApplicativo {

    private NotificheJSONDAO dao = new NotificheJSONDAO();

    // Modifica il tipo di ritorno da String a NotificheModel
    public NotificaModel getNotifica() {
        return dao.readNotificaFromFile("NotificaFile.json");
    }
}

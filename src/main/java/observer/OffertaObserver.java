package observer;

import model.OffertaModel;

public interface OffertaObserver {
    //RAPPRESENTA GLI OSSERVATORI (CONTRATTO CuHE GLI OSSERVATORI DEVONO SEGUIRE)
    // CIOE' QUALI METODI DEVONO ESEGUIRE PER ESSERE PER RICEVERE LE NOTIFICHE DAL SOGGETTO(UTENTI MODE
    void update();


}


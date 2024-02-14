package Observer;

import bean.OffertaBean;

import java.util.ArrayList;
import java.util.List;

public class OffertaSubject {//AGGIUNGE, RIMUOVE E NOTIFICA GLI OSSERVATORI

    private List<OffertaObserver> observers;

    public OffertaSubject() {
        observers = new ArrayList<>();
    }

    public void aggiungiObserver(OffertaObserver observer) {
        observers.add(observer);
    }

    public void rimuoviObserver(OffertaObserver observer) {
        observers.remove(observer);
    }

    public void notificaObservers(OffertaBean offerta) {
        for (OffertaObserver observer : observers) {
            System.out.println("non so che metterci");
        }
    }

}

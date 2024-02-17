package Observer;

import bean.OffertaBean;

import java.util.ArrayList;
import java.util.List;

public class OffertaSubject {//AGGIUNGE, RIMUOVE E NOTIFICA GLI OSSERVATORI

    private List<OffertaObserver> observersArrayList;

    public OffertaSubject() {
        observersArrayList = new ArrayList<>();
    }

    public void aggiungiObserver(OffertaObserver observer) {
        observersArrayList.add(observer);
    }

    public void rimuoviObserver(OffertaObserver observer) {
        observersArrayList.remove(observer);
    }

    public void notificaObservers() {
        for (OffertaObserver observer : observersArrayList) {
            System.out.println("Stai aggiornando gli Observer");
            observer.update();
        }
    }

}

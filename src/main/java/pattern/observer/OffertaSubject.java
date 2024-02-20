package pattern.observer;
import java.util.ArrayList;
import java.util.List;
public class OffertaSubject {

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
            observer.update();
        }
    }



}

package pattern.observer;
import java.util.ArrayList;
import java.util.List;
public abstract class OffertaSubject {

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

    protected void notificaObservers() {
        for (OffertaObserver observer : observersArrayList) {
            observer.update();
        }
    }



}

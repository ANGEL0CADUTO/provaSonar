package model;

import pattern.observer.OffertaSubject;


import java.util.ArrayList;
import java.util.List;

public class OfferteModel extends OffertaSubject {
    //CONCRETE SUBJECT
    /*mantiene lo stato del soggetto osservato e notifica gli observer in caso di un cambio di stato
 Invoca le operazioni di notifica ereditate dal Subject, quando devono essere informati i ConcreteObserver.*/
    private static OfferteModel offerteList = null;//per mantenere lo stato
    private List<OffertaModel> offertaList = new ArrayList<>();


    public void notificaCambiamentiAObservers() {
        super.notificaObservers();
    }

    public static OfferteModel getInstance() { //Pattern Singleton
        if (offerteList == null) {
            offerteList = new OfferteModel();
        }
        return offerteList;
    }

    public void setState(List<OffertaModel> offertaList) {
        this.offertaList = offertaList;
        //in questo caso non viene fatto notifyObservers() volontariamente
    }

    public List<OffertaModel> getState() {
        return offertaList;
    }


}

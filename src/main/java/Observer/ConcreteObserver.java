package Observer;

import model.AnnuncioModel;
import model.OffertaModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ConcreteObserver implements OffertaObserver{
   /* implementa l’interfaccia dell’Observer definendo
    il comportamento in caso di cambio di stato del soggetto osservato*/


    /*questa implementazione può essere considerata una forma di persistenza su file system,
    poiché i dati vengono salvati in un file e possono essere letti in un secondo momento,
     consentendo di conservare le informazioni anche dopo che il programma è stato eseguito.*/




    private OffertaModel offertaModel;

    private  String nomeManga;
    private  int volume;
    private String nomeVenditore;

    public ConcreteObserver(OffertaModel offertaModel,String nomeVenditore,String nomeManga, int volume){
        this.offertaModel = offertaModel;
        offertaModel.aggiungiObserver(this);//Significa che ConcreteObserver sta registrando se stesso per ricevere aggiornamenti
        this.nomeVenditore= nomeVenditore;
        this.nomeManga = nomeManga;
        this.volume=volume;

    }

    @Override
    public void update() {
        String notifica = creaNotifica();
        saveToFile(notifica, "NotificaFile.txt");
    }

    private String creaNotifica() {
        StringBuilder notificaBuilder = new StringBuilder();
        notificaBuilder.append("L'utente:    ").append(offertaModel.getUsernameOfferente()).append("\n\n");
        notificaBuilder.append("Ha fatto un offerta per il manga:   ").append(nomeManga).append("\n\n");
        notificaBuilder.append("Volume:   ").append(volume).append("\n\n");
        notificaBuilder.append("l'offerta è di euro:").append(offertaModel.getOffertaPrezzo()).append("\n\n");
        notificaBuilder.append("l'offerta è stata fatta a te utente:").append(nomeVenditore).append("\n\n");
        return notificaBuilder.toString();
    }

    private void saveToFile(String notifica, String filePath) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            printWriter.print(notifica);
        } catch (IOException e) {
            System.out.println("NON SEI RIUSCITO A NOTIFICARE");
            e.printStackTrace();
        }
    }

}


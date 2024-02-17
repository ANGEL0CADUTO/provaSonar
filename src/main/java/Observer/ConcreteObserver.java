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
    private OffertaModel offertaModel;
    private String nomeVenditore;

    public ConcreteObserver(OffertaModel offertaModel,String nomeVenditore){
        this.offertaModel = offertaModel;
        offertaModel.aggiungiObserver(this);//Significa che ConcreteObserver sta registrando se stesso per ricevere aggiornamenti
        this.nomeVenditore= nomeVenditore;

    }

    @Override
    public void update() {

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("NotificaFile.txt")))) {

            printWriter.print("Nome offerente:    ");
            printWriter.println(offertaModel.getUsernameOfferente());
            printWriter.println("");

            printWriter.print("Ha fatto un offerta per il manga:   ");
            printWriter.println("");//dovrei passarmi anche nomeManga in AnnuncioModel

            printWriter.println("l'offerta è di :");
            printWriter.println(offertaModel.getOffertaPrezzo());
            printWriter.println("");

            printWriter.println("l'offerta è stata fatta all'utente :");
            printWriter.println(nomeVenditore);
            printWriter.println("");




        } catch (IOException e) {
            System.out.println("NON SEI RIUSCITO A NOTIFICARE");
        }
    }

}


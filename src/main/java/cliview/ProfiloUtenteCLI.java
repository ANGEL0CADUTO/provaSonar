package cliview;

import bean.DatiUtenteBean;
import bean.UtenteBean;
import controllerapplicativo.ProfiloUtenteApplicativo;

import java.text.DecimalFormat;
import java.util.Scanner;

public class ProfiloUtenteCLI {

    private UtenteBean utenteBean;

    public ProfiloUtenteCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);
        int choice;


        do {

            System.out.println("*************************************");
            System.out.println("Ci troviamo in Homepage/PROFILO UTENTE:");
            System.out.println("0. Torna indietro");
            System.out.println("1. Visualizza Dati");
            System.out.println("2. Visualizza Recensioni");
            System.out.println("3. Modifica dati consegna");


            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    visualizzaDati();
                    break;
                case 2:
                    visualizzaRecensioni();
                    break;
                case 3:
                    modificaDatiConsegna();
                    break;
                case 0:
                    System.out.println("Tornando indietro.");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }

        } while (choice != 0);
    }

    public void visualizzaDati() {
        ProfiloUtenteApplicativo controllerApp = new ProfiloUtenteApplicativo();
        UtenteBean bean2 = controllerApp.updateVotoAndCreditoByUtenteID(utenteBean.getIdUtente());
        utenteBean.setVotoRecensione(bean2.getVotoRecensione());
        utenteBean.setCredito(bean2.getCredito());

        System.out.println("Email: " + utenteBean.getEmail());
        System.out.println("Username: " + utenteBean.getUsername());
        System.out.println("Credito: " + utenteBean.getCredito());

        double votoRecensione = utenteBean.getVotoRecensione();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String votoArrotondatoString = decimalFormat.format(votoRecensione);
        System.out.println("Voto Recensione: " + votoArrotondatoString);

        if (utenteBean.getDatiUtente() != null) {
            System.out.println("Indirizzo: " + utenteBean.getDatiUtente().getIndirizzo());
            System.out.println("Civico: " + utenteBean.getDatiUtente().getCivico());
            System.out.println("CAP: " + utenteBean.getDatiUtente().getCap());
        }

        System.out.println("---------------");
    }

    public void visualizzaRecensioni() {
        RecensioneCLI recensioneCLI = new RecensioneCLI(utenteBean);
        recensioneCLI.initialize();
    }

    public void modificaDatiConsegna() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nuovo indirizzo: ");
        String nuovoIndirizzo = scanner.nextLine();

        System.out.print("Nuovo civico: ");
        String nuovoCivico = scanner.nextLine();



        System.out.print("Nuovo CAP: ");
        String nuovoCap = scanner.nextLine();

        if (!nuovoIndirizzo.isEmpty() && !nuovoCivico.isEmpty() && !nuovoCap.isEmpty()) {
            DatiUtenteBean bean = new DatiUtenteBean();
            bean.setIndirizzo(nuovoIndirizzo);
            bean.setCivico(nuovoCivico);
            bean.setCap(nuovoCap);

            ProfiloUtenteApplicativo pu = new ProfiloUtenteApplicativo();

            if (utenteBean.getDatiUtente() != null) {
                bean.setIdInformazioniUtente(utenteBean.getDatiUtente().getIdInformazioniUtente());
                boolean successo = pu.modificaDati(bean);

                if (successo) {
                    utenteBean.getDatiUtente().setIndirizzo(nuovoIndirizzo);
                    utenteBean.getDatiUtente().setCivico(nuovoCivico);
                    utenteBean.getDatiUtente().setCap(nuovoCap);

                    System.out.println("Modifica dati avvenuta con successo.");
                } else {
                    System.out.println("Errore durante la modifica dei dati.");
                }
            } else {
                int idNuoviDati = pu.insertDatiUtente(bean);
                utenteBean.setDatiUtente(bean);
                utenteBean.getDatiUtente().setIdInformazioniUtente(idNuoviDati);
                pu.updateInformazioniUtenteID(utenteBean);

                if (idNuoviDati != -1) {
                    System.out.println("Inserimento dati avvenuto con successo.");
                } else {
                    System.out.println("Errore durante l'inserimento dei dati.");
                }
            }
        } else {
            System.out.println("I campi erano vuoti. Nessuna modifica effettuata.");
        }


    }


}

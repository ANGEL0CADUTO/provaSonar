package cliview;

import bean.DatiUtenteBean;
import bean.UtenteBean;
import controllerapplicativo.ProfiloUtenteApplicativo;
import utils.CLIPrinter;

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
            CLIPrinter.println("*************************************");
            CLIPrinter.println("Ci troviamo in Homepage/PROFILO UTENTE:");
            CLIPrinter.println("0. Torna indietro");
            CLIPrinter.println("1. Visualizza Dati");
            CLIPrinter.println("2. Visualizza Recensioni");
            CLIPrinter.println("3. Modifica dati consegna");

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
                    CLIPrinter.println("Tornando indietro.");
                    break;
                default:
                    CLIPrinter.println("Scelta non valida. Riprova.");
                    break;
            }

        } while (choice != 0);
    }

    public void visualizzaDati() {
        ProfiloUtenteApplicativo controllerApp = new ProfiloUtenteApplicativo();
        UtenteBean bean2 = controllerApp.updateVotoAndCreditoByUtenteID(utenteBean.getIdUtente());
        utenteBean.setVotoRecensione(bean2.getVotoRecensione());
        utenteBean.setCredito(bean2.getCredito());

        CLIPrinter.println("Email: " + utenteBean.getEmail());
        CLIPrinter.println("Username: " + utenteBean.getUsername());
        CLIPrinter.println("Credito: " + utenteBean.getCredito());

        double votoRecensione = utenteBean.getVotoRecensione();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String votoArrotondatoString = decimalFormat.format(votoRecensione);
        CLIPrinter.println("Voto Recensione: " + votoArrotondatoString);

        if (utenteBean.getDatiUtente() != null) {
            CLIPrinter.println("Indirizzo: " + utenteBean.getDatiUtente().getIndirizzo());
            CLIPrinter.println("Civico: " + utenteBean.getDatiUtente().getCivico());
            CLIPrinter.println("CAP: " + utenteBean.getDatiUtente().getCap());
        }

        CLIPrinter.println("---------------");
    }

    public void visualizzaRecensioni() {
        RecensioneCLI recensioneCLI = new RecensioneCLI(utenteBean);
        recensioneCLI.initialize();
    }

    public void modificaDatiConsegna() {
        Scanner scanner = new Scanner(System.in);

        CLIPrinter.print("Nuovo indirizzo: ");
        String nuovoIndirizzo = scanner.nextLine();

        CLIPrinter.print("Nuovo civico: ");
        String nuovoCivico = scanner.nextLine();

        CLIPrinter.print("Nuovo CAP: ");
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

                    CLIPrinter.println("Modifica dati avvenuta con successo.");
                } else {
                    CLIPrinter.println("Errore durante la modifica dei dati.");
                }
            } else {
                int idNuoviDati = pu.insertDatiUtente(bean);
                utenteBean.setDatiUtente(bean);
                utenteBean.getDatiUtente().setIdInformazioniUtente(idNuoviDati);
                pu.updateInformazioniUtenteID(utenteBean);

                if (idNuoviDati != -1) {
                    CLIPrinter.println("Inserimento dati avvenuto con successo.");
                } else {
                    CLIPrinter.println("Errore durante l'inserimento dei dati.");
                }
            }
        } else {
            CLIPrinter.println("I campi erano vuoti. Nessuna modifica effettuata.");
        }
    }
}

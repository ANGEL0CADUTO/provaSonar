package cliview;

import bean.UtenteBean;
import controllerapplicativo.DepositaEPrelevaApplicativo;
import exceptions.CreditoInsufficienteException;
import utils.CLIPrinter;

import java.math.BigDecimal;
import java.util.Scanner;

public class ContoCLI {
    private UtenteBean utenteBean;

    public ContoCLI(UtenteBean utente) {
        this.utenteBean = utente;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            CLIPrinter.println("*************************************");
            CLIPrinter.println("Ci troviamo in HomePage/CONTO:");
            CLIPrinter.println("0. Torna indietro");
            CLIPrinter.println("1. Deposita");
            CLIPrinter.println("2. Preleva");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    deposita();
                    break;
                case 2:
                    preleva();
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

    public void deposita() {
        CLIPrinter.println("Inserisci la cifra da depositare: ");
        Scanner scanner = new Scanner(System.in);
        String cifraString = scanner.nextLine();

        try {
            BigDecimal cifra = new BigDecimal(cifraString);

            if (cifra.compareTo(BigDecimal.ZERO) <= 0) {
                CLIPrinter.println("La cifra da depositare deve essere maggiore di zero.");
                return;
            }

            DepositaEPrelevaApplicativo dp = new DepositaEPrelevaApplicativo();
            boolean esitoDeposito = dp.deposita(utenteBean, cifra.toString());

            if (esitoDeposito) {
                utenteBean.setCredito(utenteBean.getCredito().add(cifra));
                CLIPrinter.println("Deposito andato a buon fine. Nuovo credito: " + utenteBean.getCredito());
            } else {
                CLIPrinter.println("Errore durante il deposito.");
            }
        } catch (NumberFormatException e) {
            CLIPrinter.println("Inserire una cifra valida.");
        }
    }

    public void preleva() {
        CLIPrinter.println("Inserisci la cifra da prelevare: ");
        Scanner scanner = new Scanner(System.in);
        String cifraString = scanner.nextLine();

        try {
            BigDecimal cifra = new BigDecimal(cifraString);

            if (cifra.compareTo(BigDecimal.ZERO) <= 0) {
                CLIPrinter.println("La cifra da prelevare deve essere maggiore di zero.");
                return;
            }

            DepositaEPrelevaApplicativo pr = new DepositaEPrelevaApplicativo();
            boolean esitoPrelievo = false;
            try {
                esitoPrelievo = pr.preleva(utenteBean, cifra.toString());
            } catch (CreditoInsufficienteException e) {
                CLIPrinter.println("Errore di exception personalizzata : " + e.getMessage());
            }

            if (esitoPrelievo) {
                utenteBean.setCredito(utenteBean.getCredito().subtract(cifra));
                CLIPrinter.println("Prelievo andato a buon fine. Nuovo credito: " + utenteBean.getCredito());
            } else {
                CLIPrinter.println("Credito insufficiente o errore durante il prelievo.");
            }
        } catch (NumberFormatException e) {
            CLIPrinter.println("Inserire una cifra valida.");
        }
    }
}

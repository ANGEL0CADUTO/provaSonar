package view2;

import bean.UtenteBean;
import controllerapplicativo.DepositaEPrelevaApplicativo;

import java.math.BigDecimal;
import java.util.Scanner;

public class ContoCLI {
    private UtenteBean utenteBean;

    public ContoCLI(UtenteBean utente) {
        this.utenteBean = utente;

    }

    public void initialize(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("*************************************");
            System.out.println("Ci troviamo in HomePage/CONTO:");
            System.out.println("0. Torna indietro");
            System.out.println("1. Deposita");
            System.out.println("2. Preleva");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    deposita();
                    break;
                case 2:
                    preleva();
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

    public void deposita(){
        System.out.println("Inserisci la cifra da depositare: ");
        Scanner scanner = new Scanner(System.in);
        String cifraString = scanner.nextLine();

        try {
            BigDecimal cifra = new BigDecimal(cifraString);

            if (cifra.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("La cifra da depositare deve essere maggiore di zero.");
                return;
            }

            DepositaEPrelevaApplicativo dp = new DepositaEPrelevaApplicativo();
            boolean esitoDeposito = dp.deposita(utenteBean, cifra.toString());

            if (esitoDeposito) {
                utenteBean.setCredito(utenteBean.getCredito().add(cifra));
                System.out.println("Deposito andato a buon fine. Nuovo credito: " + utenteBean.getCredito());
            } else {
                System.out.println("Errore durante il deposito.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Inserire una cifra valida.");
        }

    }
    public void preleva(){
        System.out.println("Inserisci la cifra da prelevare: ");
        Scanner scanner = new Scanner(System.in);
        String cifraString = scanner.nextLine();

        try {
            BigDecimal cifra = new BigDecimal(cifraString);

            if (cifra.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("La cifra da prelevare deve essere maggiore di zero.");
                return;
            }

            DepositaEPrelevaApplicativo pr = new DepositaEPrelevaApplicativo();
            boolean esitoPrelievo = pr.preleva(utenteBean, cifra.toString());

            if (esitoPrelievo) {
                utenteBean.setCredito(utenteBean.getCredito().subtract(cifra));
                System.out.println("Prelievo andato a buon fine. Nuovo credito: " + utenteBean.getCredito());
            } else {
                System.out.println("Credito insufficiente o errore durante il prelievo.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Inserire una cifra valida.");
        }
    }
}


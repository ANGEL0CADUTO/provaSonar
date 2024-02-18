package view2;

import bean.UtenteBean;

import java.util.Scanner;



public class HomePageCLI {
    private UtenteBean utenteBean;

    public HomePageCLI(UtenteBean bean) {
        this.utenteBean = bean;
    }

    public void setUtenteBean(UtenteBean bean){
        this.utenteBean = bean;
    }



    public void initialize()  {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            if (!utenteBean.isLogged()) {
                System.out.println("*************************************");
                System.out.println("Ci troviamo in HOMEPAGE:");
                System.out.println("1. Login");
                System.out.println("2. Registrazione");
            } else {
                System.out.println("*************************************");
                System.out.println("Ci troviamo in HOMEPAGE:");
                System.out.println("0. Quit");
                System.out.println("1. Profilo");
                System.out.println("2. Libreria");
                System.out.println("3. Miei Annunci");
                System.out.println("4. Conto");
                System.out.println("5. Compra");
                System.out.println("6. Logout");
            }

            choice = scanner.nextInt();
            if (!utenteBean.isLogged()) {
                switch (choice) {
                    case 1:
                        login(utenteBean);
                        break;
                    case 2:
                        registrazione();
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }
            } else {
                switch (choice) {
                    case 1:
                        goToProfiloUtente(utenteBean);
                        break;
                    case 2:
                        goToLibreria(utenteBean);
                        break;
                    case 3:
                        goToMieiAnnunci(utenteBean);
                        break;
                    case 4:
                        goToConto(utenteBean);
                        break;
                    case 5:
                        goToCompra(utenteBean);
                        break;
                    case 6:
                        logout(utenteBean);
                        break;
                    case 0:
                        System.out.println("Uscita dal programma.");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }
            }

        }while (choice != 0) ;

    }


    public void login(UtenteBean bean) {
        LoginCLI loginCLI = new LoginCLI(utenteBean);
        loginCLI.initialize();
    }

    public void registrazione(){
        RegistrazioneCLI registrazioneCLI = new RegistrazioneCLI();
        registrazioneCLI.initialize();
    }

    public void goToMieiAnnunci(UtenteBean utenteBean) {
        MieiAnnunciCLI mieiAnnunciCLI = new MieiAnnunciCLI(utenteBean);
        System.out.println("Visualizzazione dei tuoi annunci...");
        mieiAnnunciCLI.initialize();

    }
    public void goToProfiloUtente(UtenteBean utenteBean) {
        ProfiloUtenteCLI profiloUtenteCLI = new ProfiloUtenteCLI(utenteBean);
        profiloUtenteCLI.initialize();
    }

    public void goToLibreria(UtenteBean utenteBean) {
        LibreriaCLI libreriaCLI = new LibreriaCLI(utenteBean);
        libreriaCLI.initialize();
    }

    public void goToConto(UtenteBean utenteBean) {
        ContoCLI contoCLI = new ContoCLI(utenteBean);
        contoCLI.initialize();
    }

    public void goToCompra(UtenteBean utenteBean) {
        CompraCLI compraCLI = new CompraCLI(utenteBean);
        compraCLI.initialize();
    }


    public void logout(UtenteBean utenteBean) {
        utenteBean.setLogged(false);
    }


}



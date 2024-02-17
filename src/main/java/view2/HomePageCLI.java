package view2;

import bean.DatiUtenteBean;
import bean.UtenteBean;
import controllerapplicativo.LoginApplicativo;
import controllerapplicativo.RegistraApplicativo;

import java.io.IOException;
import java.util.Scanner;

public class HomePageCLI {
    private UtenteBean utenteBean;


    public void login() {
        UtenteBean bean = new UtenteBean();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il tuo username: ");
        String email = scanner.nextLine();

        bean.setEmail(email);
        System.out.println("Inserisci la tua password: ");
        String password = scanner.nextLine();
        bean.setPassword(password);

        LoginApplicativo controllerApp = new LoginApplicativo();
        if (controllerApp.login(bean)) {
            System.out.println("Login effettuato con successo per l'utente: " + bean.getEmail());
            this.utenteBean = bean;
        } else {
            System.out.println("Login fallito. Controlla le credenziali e riprova.");
        }
    }





    public void goToMieiAnnunci(UtenteBean utenteBean) {
        System.out.println("Visualizzazione dei tuoi annunci...");
        // Aggiungere ulteriori operazioni se necessario
    }
    public void goToProfiloUtente(UtenteBean utenteBean) {
        // Implementa le operazioni necessarie per navigare al profilo utente
    }

    public void goToLibreria(UtenteBean utenteBean) {
        // Implementa le operazioni necessarie per navigare alla libreria
    }

    public void goToConto(UtenteBean utenteBean) {
        // Implementa le operazioni necessarie per navigare al conto
    }

    public void goToCompra(UtenteBean utenteBean) {
        // Implementa le operazioni necessarie per navigare all'acquisto
    }

    // Aggiungi eventuali altre funzioni mancanti in base alle tue esigenze

    public void logout(UtenteBean utenteBean) {
        // Implementa le operazioni necessarie per il logout
    }


    public void initialize()  {
        UtenteBean utenteBean = new UtenteBean();


        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            if (!utenteBean.isLogged()) {
                System.out.println("Scegli un'opzione:");
                System.out.println("1. Login");
                System.out.println("2. Registrazione");
            } else {
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
                        login();
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



    public void registrazione() {
        Scanner scanner = new Scanner(System.in);
        UtenteBean bean = new UtenteBean();

        // Validazione email
        String email;
        do {
            System.out.println("Inserisci la tua email: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email non può essere vuota. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per l'email
                bean.setEmail(email);
            }
        } while (email.isEmpty());

        // Validazione username
        String username;
        do {
            System.out.println("Inserisci il tuo username: ");
            username = scanner.nextLine().trim();
            if (username.isEmpty()) {
                System.out.println("Username non può essere vuoto. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per lo username
                bean.setUsername(username);
            }
        } while (username.isEmpty());

        // Validazione password
        String password;
        do {
            System.out.println("Inserisci la tua password: ");
            password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Password non può essere vuota. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per la password
                bean.setPassword(password);
            }
        } while (password.isEmpty());

        DatiUtenteBean datiUtente = new DatiUtenteBean();

        // Validazione indirizzo
        String indirizzo;
        do {
            System.out.println("Inserisci il tuo indirizzo: ");
            indirizzo = scanner.nextLine().trim();
            if (indirizzo.isEmpty()) {
                System.out.println("Indirizzo non può essere vuoto. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per l'indirizzo
                datiUtente.setIndirizzo(indirizzo);
            }
        } while (indirizzo.isEmpty());

        // Validazione civico
        String civico;
        do {
            System.out.println("Inserisci il tuo civico: ");
            civico = scanner.nextLine().trim();
            if (civico.isEmpty()) {
                System.out.println("Civico non può essere vuoto. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per il civico
                datiUtente.setCivico(civico);
            }
        } while (civico.isEmpty());

        // Validazione CAP
        String cap;
        do {
            System.out.println("Inserisci il tuo CAP: ");
            cap = scanner.nextLine().trim();
            if (cap.isEmpty()) {
                System.out.println("CAP non può essere vuoto. Riprova.");
            } else {
                // Aggiungi eventuali altri controlli di formato per il CAP
                datiUtente.setCap(cap);
            }
        } while (cap.isEmpty());

        // Altre operazioni o controlli possono essere aggiunti in base alle esigenze

        // Ad esempio, puoi chiamare il metodo di registrazione dell'applicazione
        RegistraApplicativo controllerApp = new RegistraApplicativo();
        if (controllerApp.registra(bean) && controllerApp.registraDati(datiUtente) != -1) {
            System.out.println("Registrazione effettuata con successo per l'utente: " + bean.getEmail());
        } else {
            System.out.println("Registrazione fallita. Controlla i dati inseriti e riprova.");
        }
    }
}



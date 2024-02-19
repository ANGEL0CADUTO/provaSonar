package cliview;

import bean.UtenteBean;
import controllerapplicativo.LoginApplicativo;

import java.util.Scanner;

public class LoginCLI {
    private UtenteBean utenteBean;
    private HomePageCLI homePageCLI;
    public LoginCLI(UtenteBean utente) {
        this.utenteBean = utente;

    }

    public void initialize(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci l'email: ");
        String email = scanner.nextLine();

        utenteBean.setEmail(email);
        System.out.println("Inserisci la tua password: ");
        String password = scanner.nextLine();
        utenteBean.setPassword(password);

        LoginApplicativo controllerApp = new LoginApplicativo();
        if (controllerApp.login(utenteBean)) {
            System.out.println("Login effettuato con successo per l'utente: " + utenteBean.getEmail());
            utenteBean.setLogged(true);


        } else {
            System.out.println("Login fallito. Controlla le credenziali e riprova.");
        }
    }


}

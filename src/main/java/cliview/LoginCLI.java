package cliview;

import bean.CredenzialiBean;
import bean.UtenteBean;
import controllerapplicativo.LoginApplicativo;
import exceptions.CredenzialiSbagliateException;
import utils.CLIPrinter;

import java.util.Scanner;

public class LoginCLI {

    private HomePageCLI homePageCLI;

    public LoginCLI(HomePageCLI homePageCLI) {
        this.homePageCLI = homePageCLI;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);
        CredenzialiBean bean = new CredenzialiBean();

        CLIPrinter.println("Inserisci l'email: ");
        String email = scanner.nextLine();

        bean.setEmail(email);
        CLIPrinter.println("Inserisci la tua password: ");
        String password = scanner.nextLine();
        bean.setPassword(password);

        LoginApplicativo controllerApp = new LoginApplicativo();
        try {
            UtenteBean nuovoUtenteBean = controllerApp.login(bean);
            homePageCLI.setUtenteBean(nuovoUtenteBean);
        } catch (CredenzialiSbagliateException e) {
            CLIPrinter.println("Abbiamo ricevuto una exception personalizzata : " + e.getMessage());
        }
    }
}

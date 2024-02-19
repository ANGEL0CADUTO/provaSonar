package cliview;

import bean.UtenteBean;

public class StartCLI {

    public static void main(String[] args) {
        System.out.println("Benvenuto! Seleziona un'opzione:");

        UtenteBean utenteBean = new UtenteBean();
        HomePageCLI homePageCLI = new HomePageCLI(utenteBean);
        homePageCLI.initialize();

        System.out.println("ARRIVEDERCI!");
    }
}

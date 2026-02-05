package cliview;

import bean.UtenteBean;


public class StartCLI {//ola

    public static void main(String[] args) {



        UtenteBean utenteBean = new UtenteBean();
        HomePageCLI homePageCLI = new HomePageCLI(utenteBean);
        homePageCLI.initialize();

    }
}

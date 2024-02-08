package controllerapplicativo;

import bean.UtenteBean;
import dao.UtenteDAO;

public class LoginApplicativo {



    public boolean login(UtenteBean bean){
        UtenteDAO ricercaUtente = new UtenteDAO();

        return ricercaUtente.searchUser(bean);


    }


}

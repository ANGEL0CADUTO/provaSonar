package exceptions;

import java.io.Serial;

//EXCEPTION CHE VIENE SOLLEVATA NEL MOMENTO IN CUI VENGONO INSERITE CREDENZIALI ERRATE AL LOGIN
public class CredenzialiSbagliateException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public CredenzialiSbagliateException() {
        super("Credenziali sbagliate!");
    }
}
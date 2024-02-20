package exceptions;

import java.io.Serial;

public class CredenzialiSbagliateException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public CredenzialiSbagliateException() {
        super("Credenziali sbagliate!");
    }
}
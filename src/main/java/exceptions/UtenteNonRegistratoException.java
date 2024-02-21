package exceptions;

import java.io.Serial;

public class UtenteNonRegistratoException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public UtenteNonRegistratoException() {
        super("Inserimento utente fallito");
    }
}

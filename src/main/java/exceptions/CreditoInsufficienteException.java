package exceptions;

import java.io.Serial;

public class CreditoInsufficienteException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public CreditoInsufficienteException() {
        super("Credito insufficiente!");
    }
}

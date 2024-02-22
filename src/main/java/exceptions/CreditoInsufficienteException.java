package exceptions;

import java.io.Serial;

//EXCEPTION CHE VIENE SOLLEVATA AL MOMENTO CHE SI PROVA A DEPOSITARE UNA CIFRA SUPERIORE AL CREDITO DELL'UTENTE
public class CreditoInsufficienteException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public CreditoInsufficienteException() {
        super("Credito insufficiente!");
    }
}

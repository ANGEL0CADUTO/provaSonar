package exceptions;

import java.io.Serial;


//EXCEPTION CHE VIENE SOLLEVATA NEL MOMENTO CHE NON SI RIESCE AD INSERIRE UN NUOVO UTENTE AL MOMENTO DELLA REGISTRAZIONE
public class UtenteNonRegistratoException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public UtenteNonRegistratoException() {
        super("Inserimento utente fallito");
    }
}

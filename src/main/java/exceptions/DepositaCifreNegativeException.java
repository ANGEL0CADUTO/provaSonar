package exceptions;

import java.io.Serial;

public class DepositaCifreNegativeException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public DepositaCifreNegativeException(){super("Non puoi depositare cifre negative");}

}

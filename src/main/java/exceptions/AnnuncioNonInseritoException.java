package exceptions;


import java.io.Serial;

public class AnnuncioNonInseritoException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public AnnuncioNonInseritoException(){super("L'annuncio non è stato inserito!");}
}

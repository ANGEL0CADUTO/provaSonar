package exceptions;


import java.io.Serial;

//EXCEPTION CHE VIENE SOLLEVATA NEL MOMENTO IN CUI UN ANNUNCIO NON RIESCE AD ESSERE INSERITO
public class AnnuncioNonInseritoException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public AnnuncioNonInseritoException(){super("L'annuncio non è stato inserito!");}
}

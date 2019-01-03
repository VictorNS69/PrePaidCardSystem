package es.upm.pproject.tdd.exceptions;

public class ExpiredCardException extends Exception{
    private static final String MSG = "Card has expired.";
    public ExpiredCardException(){
        super(MSG);
    }
}
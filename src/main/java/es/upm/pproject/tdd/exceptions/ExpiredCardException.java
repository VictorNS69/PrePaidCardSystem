package es.upm.pproject.tdd.exceptions;

public class ExpiredCardException extends Exception{
    private static final String MSG = "Error: Card has expired.";
    public ExpiredCardException(){
        super(MSG);
    }
}
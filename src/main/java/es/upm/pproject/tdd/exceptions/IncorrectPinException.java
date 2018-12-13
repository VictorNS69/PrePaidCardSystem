package es.upm.pproject.tdd.exceptions;

public class IncorrectPinException extends Exception{
    private static final String MSG = "Error: Incorrect PIN. Try again.";
    public IncorrectPinException(){
        super(MSG);
    }
}
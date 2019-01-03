package es.upm.pproject.tdd.exceptions;

public class InvalidAmountException extends Exception{
    private static final String MSG = "The amount is not a valid number.";
    public InvalidAmountException() {
        super(MSG);
    }
}
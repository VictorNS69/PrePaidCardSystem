package es.upm.pproject.tdd.exceptions;

public class InvalidMovementException extends Exception{
    private static final String MSG = "This movement is not valid.";
    public InvalidMovementException() {
        super(MSG);
    }
}
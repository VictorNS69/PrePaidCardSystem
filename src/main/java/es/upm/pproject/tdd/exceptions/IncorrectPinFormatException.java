package es.upm.pproject.tdd.exceptions;

public class IncorrectPinFormatException extends Exception{
	private static final String MSG = "Error: Incorrect PIN format. PIN must have 4 digits.";
    public IncorrectPinFormatException(){
        super(MSG);
    }
}

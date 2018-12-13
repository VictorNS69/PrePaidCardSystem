package es.upm.pproject.tdd.exceptions;

public class AlreadyRegisteredException extends Exception{
	private static final String MSG = "Error: Card is alrready registered in the system.";
	public AlreadyRegisteredException() {
		super(MSG);
	}
}
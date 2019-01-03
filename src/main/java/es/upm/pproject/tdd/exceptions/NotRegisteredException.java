package es.upm.pproject.tdd.exceptions;

public class NotRegisteredException extends Exception{
    private static final String MSG = "Card is not registered in the system.";
    public NotRegisteredException(){
        super(MSG);
    }
}

package es.upm.pproject.tdd.exceptions;

public class NotEnoughMoneyException extends Exception{
    private static final String MSG = "Card has not enough money.";
    public NotEnoughMoneyException(){
        super(MSG);
    }
}
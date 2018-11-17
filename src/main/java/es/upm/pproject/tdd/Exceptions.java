package es.upm.pproject.tdd;

class IncorrectPINException extends Exception{
    private static final String MSG = "Error: Incorrect PIN.";
    public IncorrectPINException(){
        super(MSG);
    }
}

class NotRegisteredException extends Exception{
    private static final String MSG = "Error: Crd is not registered in the system.";
    public NotRegisteredException(){
        super(MSG);
    }
}

class NotEnoughMoneyException extends Exception{
    private static final String MSG = "Error: Card has not enough money.";
    public NotEnoughMoneyException(){
        super(MSG);
    }
}

class ExpiredCardException extends Exception{
    private static final String MSG = "Error: Card has expired.";
    public ExpiredCardException(){
        super(MSG);
    }
}

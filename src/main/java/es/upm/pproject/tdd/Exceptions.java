package es.upm.pproject.tdd;

class IncorrectPINException extends Exception{
    public IncorrectPINException(String msg){
        super(msg);
    }
}

class NotRegisteredException extends Exception{
    public NotRegisteredException(String msg){
        super(msg);
    }
}

class NotEnoughMoneyException extends Exception{
    public NotEnoughMoneyException(String msg){
        super(msg);
    }
}

class ExpiredCardException extends Exception{
    public ExpiredCardException(String msg){
        super(msg);
    }
}
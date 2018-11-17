package es.upm.pproject.tdd;

public interface Interface{

    void buyCard(String name, String surname, String PIN, int amount);
    void pay(int number, int amount, String PIN) throws NotRegisteredException, IncorrectPINException, NotEnoughMoneyException, ExpiredCardException;
    void chargeMoney(int number, int amount, String PIN) throws NotRegisteredException, IncorrectPINException, ExpiredCardException;
    void changePIN(String oldPIN, String newPIN) throws NotRegisteredException, IncorrectPINException;
    void consultBalance(int number, String PIN) throws NotRegisteredException, IncorrectPINException;
    void consultMovements(int number, String PIN) throws NotRegisteredException, IncorrectPINException;

}

package es.upm.pproject.tdd.backend;

public interface Interface{

	/**The credit card number (12-digit) will be randomly generated and
	 * the expiration date will be one year after the buying date. 
	 * @param name
	 * @param surname
	 * @param pin
	 * @param amount
	 * @throws AlreadyRegisteredException
	 */
	void buyCard(String name, String surname, String pin, int amount) throws AlreadyRegisteredException;

	/**The user can pay an amount of money by introducing the credit card number, the amount to pay
	 * and the PIN. The system must check whether the user have enough money in the card and, if so,
	 * decrement from the balance the ammount introduced. The pin number must be checked before
	 * accepting the payment.
	 * @param number
	 * @param amount
	 * @param pin
	 * @throws NotRegisteredException
	 * @throws IncorrectPINException
	 * @throws NotEnoughMoneyException
	 * @throws ExpiredCardException
	 */
	void pay(int number, int amount, String pin) throws NotRegisteredException, IncorrectPINException,
			NotEnoughMoneyException, ExpiredCardException;

	/**The user can charge some amount of money in a card. The form will ask the user for the credit
	 * card number, the ammount of money and the PIN. The PIN must be checked before accepting the
	 * payment.
	 * @param number
	 * @param amount
	 * @param pin
	 * @throws NotRegisteredException
	 * @throws IncorrectPINException
	 * @throws ExpiredCardException
	 */
	void chargeMoney(int number, int amount, String pin) throws NotRegisteredException, IncorrectPINException, 
			ExpiredCardException;

	/**The user can change the PIN by introducing the old PIN and the new one. If the old PIN is
	 * incorrect the system must show an error.
	 * @param oldPin
	 * @param newPin
	 * @throws NotRegisteredException
	 * @throws IncorrectPINException
	 */
	void changePIN(String oldPin, String newPin) throws NotRegisteredException, IncorrectPINException;

	/**The user can check the balance of a card by introducing the card number and its PIN
	 * @param number
	 * @param pin
	 * @throws NotRegisteredException
	 * @throws IncorrectPINException
	 */
	void consultBalance(int number, String pin) throws NotRegisteredException, IncorrectPINException;

	/** The user can consult the movements done with a card in the current session by introducing the
	 * card number and the PIN. The movements must be shown in decreasing order, that is, the last
	 * movement must be shown the first one.
	 * @param number
	 * @param pin
	 * @throws NotRegisteredException
	 * @throws IncorrectPINException
	 */
	void consultMovements(int number, String pin) throws NotRegisteredException, IncorrectPINException;

}

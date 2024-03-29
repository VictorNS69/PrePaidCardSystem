package es.upm.pproject.tdd.backend;

import java.util.List;

import es.upm.pproject.tdd.exceptions.*;

public interface PrePaidInterface{

	/**The credit card number (12-digit) will be randomly generated and
	 * the expiration date will be one year after the buying date. 
	 * @param name
	 * @param surname
	 * @param pin
	 * @param amount
	 * @return 
	 * @throws AlreadyRegisteredException
	 * @throws ExpiredCardException 
	 * @throws IncorrectPinException 
	 * @throws IncorrectPinFormatException 
	 * @throws InvalidAmountException 
	 */
	long buyCard(String name, String surname, String pin, double amount) throws AlreadyRegisteredException,
	IncorrectPinFormatException, IncorrectPinException, ExpiredCardException, InvalidAmountException;

	/**The user can pay an amount of money by introducing the credit card number, the amount to pay
	 * and the PIN. The system must check whether the user have enough money in the card and, if so,
	 * decrement from the balance the amount introduced. The pin number must be checked before
	 * accepting the payment.
	 * @param number
	 * @param amount
	 * @param pin
	 * @throws NotRegisteredException
	 * @throws IncorrectPinException
	 * @throws NotEnoughMoneyException
	 * @throws ExpiredCardException
	 * @throws IncorrectPinFormatException 
	 * @throws InvalidAmountException 
	 * @throws InvalidMovementException 
	 */
	void pay(long number, double amount, String pin) throws NotRegisteredException, IncorrectPinException,
			NotEnoughMoneyException, IncorrectPinFormatException, InvalidAmountException,
			InvalidMovementException;

	/**The user can charge some amount of money in a card. The form will ask the user for the credit
	 * card number, the amount of money and the PIN. The PIN must be checked before accepting the
	 * payment.
	 * @param number
	 * @param amount
	 * @param pin
	 * @throws NotRegisteredException
	 * @throws IncorrectPinException
	 * @throws ExpiredCardException
	 * @throws IncorrectPinFormatException 
	 * @throws InvalidAmountException 
	 * @throws InvalidMovementException 
	 */
	void chargeMoney(long number, double amount, String pin) throws NotRegisteredException, 
	IncorrectPinException, IncorrectPinFormatException, InvalidAmountException,
	InvalidMovementException;

	/**The user can check the balance of a card by introducing the card number and its PIN
	 * @param number
	 * @param pin
	 * @throws NotRegisteredException
	 * @throws IncorrectPinException
	 * @throws ExpiredCardException
	 * @throws IncorrectPinFormatException 
	 */
	double consultBalance(long number, String pin) throws NotRegisteredException, 
	IncorrectPinException, IncorrectPinFormatException;

	/** The user can consult the movements done with a card in the current session by introducing the
	 * card number and the PIN. The movements must be shown in decreasing order, that is, the last
	 * movement must be shown the first one.
	 * @param number
	 * @param pin
	 * @return the list of movements
	 * @throws NotRegisteredException
	 * @throws IncorrectPinException
	 * @throws IncorrectPinFormatException 
	 * @throws ExpiredCardException 
	 */
	List <Movement> consultMovements(long number, String pin) throws NotRegisteredException, IncorrectPinException,
	IncorrectPinFormatException;

	/** The user can change the PIN by introducing the old PIN and the new one. If the old PIN is
	 * incorrect the system must show an error.
	 * @param number
	 * @param oldPin
	 * @param newPin
	 * @return Card
	 * @throws NotRegisteredException
	 * @throws IncorrectPinException
	 * @throws ExpiredCardException
	 * @throws IncorrectPinFormatException
	 */
	Card changePIN(long number, String oldPin, String newPin) throws NotRegisteredException, 
	IncorrectPinException, IncorrectPinFormatException, ExpiredCardException;

}

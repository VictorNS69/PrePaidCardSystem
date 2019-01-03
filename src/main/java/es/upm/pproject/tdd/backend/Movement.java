package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.*;

import es.upm.pproject.tdd.exceptions.InvalidAmountException;
import es.upm.pproject.tdd.exceptions.InvalidMovementException;

public class Movement {
	private Calendar date;
	private double amount;
	private char sign;
	
	/** Create a movement using a sign of the operation
	 * and the amount to use.
	 * Sign:
	 *	 * 0 == - (pay)
	 *	 * 1 == + (charge)
	 * Note:
	 * We can use the sign as '-0' or '+0',
	 * but try to use only '0'.
	 * The amount must be a positive value. 
	 * @param sign
	 * @param amount
	 * @throws InvalidMovementException
	 * @throws InvalidAmountException
	 */
	public Movement (int sign, double amount) throws InvalidMovementException, 
	InvalidAmountException {
		if (sign != 0 && sign!=1)
			throw new InvalidMovementException();
		
		this.date = Calendar.getInstance();
		if (amount <= 0)
			throw new InvalidAmountException();
		
		this.amount = amount;
		if (sign == 1)
			this.sign = '+';
		else
			this.sign = '-';
	}
	
	/** Prints in a pretty way the movement.
	 */
	@Override
	public String toString() {
		Date today = this.date.getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' HH:mm:ss");
		return ft.format(today)+"   "+ this.sign + this.amount +"€";
	}
}

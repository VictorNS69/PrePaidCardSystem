package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import es.upm.pproject.tdd.exceptions.*;

public class Card {
	private long number;
	private String name,  surname, pin;
	private float balance = 0, amount;
	private String expirationDate;
	private Calendar exD;

	/** Constructor. Create a new card.
	 *  number and calendar parameters can be null.
	 *  pin must have a hashed number.
	 * @param number
	 * @param name
	 * @param surname
	 * @param pin
	 * @param amount
	 * @param calendar
	 * @throws IncorrectPinException
	 * @throws ExpiredCardException
	 */
	public Card (Long number, String name, String surname, String pin, float amount, String calendar) 
			throws IncorrectPinException, ExpiredCardException{
		if (number == null)
			this.number = this.generateNumberCard(); 
		
		else
			this.number = number;
		
		this.name = name;
		this.surname = surname;
		
		if (pin == null)
			throw new IncorrectPinException();
	
		else 
			this.pin = pin;
		
		this.balance = this.balance + amount;
		this.amount = amount;
		if (calendar == null) {
			this. exD = this.setExpirationDate(Calendar.getInstance());
			this.expirationDate = this.dateFormat(this.exD);
		}
		else {
			this. exD = Calendar.getInstance();
			String auxDate =this.dateFormat(this.exD);
			if (this.compareDate(calendar, auxDate)) 
				throw new ExpiredCardException();
			
			else
				this.expirationDate = calendar;
		}
	}
	
	/** Return the number of the card.
	 * @return
	 */
	public long getNumber() {
		return this.number;
	}

	/** Return the name of the card.
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/** Return the surname of the card.
	 * @return
	 */
	public String getSurname() {
		return this.surname;
	}

	/** Return the hashed pin of the card.
	 * @return
	 */
	public String getPin() {
		return this.pin;
	}

	/** Return the balance of the card.
	 * @return
	 */
	public float getBalance () {
		return this.balance;
	}

	/** Return the amount of the card.
	 * @return
	 */
	public float getAmount () {
		return this.amount;
	}

	/** Return the date of the card in a pretty way.
	 * @return
	 */
	public String getPrettyExpirationDate() {
		return this.expirationDate;
	}
	
	/** Return the date of the card like a calendar type.
	 * @return
	 */
	private Calendar getExpirationDate() {
		return this.exD;
	}

	/** Sets the balance adding the value of the parameter.
	 * @param value
	 */
	public void setBalance(float value) {
		this.balance = value;
	}

	/** Returns the Card in a pretty format.
	 * @return
	 */
	@Override
	public String toString() {
		return this.number +" "+ this.name +" "+ this.surname+" "+ this.pin +" "+ this.balance +" "+ this.expirationDate;
	}
	
	/** Generates a random number of 12 digits.
	 * @return
	 */
	private long generateNumberCard() {
		return (long)(Math.random()*Math.pow(10, 12)+ 000000000000L);
	}

	/**Sets the expiration date. (+1 year)
	 * @param date
	 * @return
	 */
	private Calendar setExpirationDate(Calendar date) {
		int year = date.get(Calendar.YEAR)+1;
		date.set(Calendar.YEAR, year);
		return date;
	}

	/**Sets the format of the date for a pretty date format.
	 * @param calendar
	 * @return
	 */
	private String dateFormat(Calendar calendar) {
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}
	
	/* Compare if date1 is <= date2
	 */
	private boolean compareDate(String date1, String date2) {
		String[] d1 = date1.split("-");
		String[] d2 = date2.split("-");
		if (d1[2].compareTo(d2[2]) == 0) {
			if (d1[1].compareTo(d2[1]) == 0) {
				if (d1[0].compareTo(d2[0]) <= 0) 
					return true;
			}
			else if (d1[1].compareTo(d2[1]) < 0) 
				return true;
		}
		else if (d1[2].compareTo(d2[2]) < 0) 
			return true;
		
		return false;
	}
}
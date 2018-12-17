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
	

	public long getNumber() {
		return this.number;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getPin() {
		return this.pin;
	}

	public float getBalance () {
		return this.balance;
	}

	public float getAmount () {
		return this.amount;
	}

	public String getPrettyExpirationDate() {
		return this.expirationDate;
	}
	
	private Calendar getExpirationDate() {
		return this.exD;
	}

	public void setBalance(float value) {
		this.balance = value;
	}

	public String toString() {
		return this.number +" "+ this.name +" "+ this.surname+" "+ this.pin +" "+ this.balance +" "+ this.expirationDate;
	}
	
	private long generateNumberCard() {
		return (long)(Math.random()*Math.pow(10, 12)+ 000000000000L);
	}

	private Calendar setExpirationDate(Calendar date) {
		int year = date.get(Calendar.YEAR)+1;
		date.set(Calendar.YEAR, year);
		return date;
	}

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

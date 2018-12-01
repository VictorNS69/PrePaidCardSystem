package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Card {
	private long number;
	private String name,  surname, pin;
	private float balance = 0, amount;
	private String expirationDate;
	private Calendar exD;

	public Card (Long number, String name, String surname, String pin, float amount, String calendar) 
			throws IncorrectPinFormatException{
		if (number == null)
			this.number = this.generateNumberCard();
		
		else
			this.number = number;
		
		this.name = name;
		this.surname = surname;
		
		if (pin.length() == 4) {
			HashPin hp = new HashPin(pin);
			this.pin = hp.getHashPin();
		}
		else 
			this.pin = pin;
		
		this.balance = this.balance + amount;
		this.amount = amount;
		if (calendar == null) {
			this. exD = this.setExpirationDate(Calendar.getInstance());
			this.expirationDate = this.dateFormat(this.exD);
		}
		else
			this.expirationDate = calendar;
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
		this.balance = this.balance + value;
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
}

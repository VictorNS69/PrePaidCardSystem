package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Card {
	private long number;
	private String name,  surname, pin;
	private float balance = 0, amount;
	private Calendar expirationDate;

	public Card (String name, String surname, String pin, float amount) {
		this.number = this.generateNumberCard();
		this.name = name;
		this.surname = surname;
		HashPin hp = new HashPin(pin);
		this.pin = hp.getHashPin();
		this.balance = this.balance + amount;
		this.amount = amount;
		this.expirationDate = this.setExpirationDate(Calendar.getInstance());
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
		return this.dateFormat(this.expirationDate);
	}
	
	public Calendar getExpirationDate() {
		return this.expirationDate;
	}

	public void setBalance(float value) {
		this.balance = this.balance + value;
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
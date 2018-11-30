package es.upm.pproject.tdd.backend;

import java.math.BigInteger;
import java.security.MessageDigest;
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
		this.pin = this.hashPin(pin);
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

	public String getExpirationDate() {
		return this.dateFormat(this.expirationDate);
	}

	public void setBalance(float value) {
		this.balance = this.balance + value;
	}

	private long generateNumberCard() {
		return (long)(Math.random()*Math.pow(10, 12)+ 000000000000L);
	}

	private String hashPin(String number) {
		try { 
			// Static getInstance method is called with hashing SHA 
			MessageDigest md = MessageDigest.getInstance("SHA-256"); 

			// digest() method called 
			// to calculate message digest of an input 
			// and return array of byte 
			byte[] messageDigest = md.digest(number.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16); 

			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 

			return hashtext; 
		} 
		catch (Exception e) { 
			e.printStackTrace();
			return null; 
		} 
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

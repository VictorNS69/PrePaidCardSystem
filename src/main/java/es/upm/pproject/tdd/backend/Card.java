package es.upm.pproject.tdd.backend;

import java.util.Calendar;

public class Card {
	private int number;
	private String name;
	private String surname;
	private String pin;
	private float balance;
	private float amount;
	private Calendar expirationDate;
	
	public Card (int number, String name, String surname, String pin, float balance, float amount, Calendar expirationDate) {
		this.number = number;
		this.name = name;
		this.surname = surname;
		this.pin = pin;
		this.balance = balance;
		this.amount = amount;
		this.expirationDate = expirationDate;
	}
	
	public int getNumber() {
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
	
	public Calendar getExpirationDate() {
		return this.expirationDate;
	}
}

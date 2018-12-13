package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import es.upm.pproject.tdd.exceptions.*;


public class Manager implements Interface{
	private Map <Long, Card> cards = new HashMap <>();
	private String date;

	public Manager (List <Card> list) {
		for (Card c : list) {
			this.cards.put(c.getNumber(), c);
		} 
		this.date = this.dateFormat(Calendar.getInstance());
	}
	
	private String dateFormat(Calendar calendar) {
		Date cal = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(cal);
	}
	
	public Card getCard(Long k) {
		return cards.get(k);
	}
	
	public String getDate() {
		return this.date;
	}
	
	@Override
	public long buyCard(String name, String surname, String pin, float amount) throws 
	AlreadyRegisteredException, IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException {
		Card newCard = new Card(null, name, surname, pin, amount, null);
		if (!this.cards.containsKey(newCard.getNumber())) {
			this.cards.put(newCard.getNumber(), newCard);
			return newCard.getNumber();
		}
		
		else 
			return this.buyCard(name, surname, pin, amount);
	}

	@Override
	public void pay(long number, float amount, String pin) throws NotRegisteredException,
	IncorrectPinException, NotEnoughMoneyException, ExpiredCardException {
		if (this.getCard(number) == null) 
			throw new NotRegisteredException();
		
		if (this.getCard(number).getPin() != pin)
			throw new IncorrectPinException();
		
		if (this.getCard(number).getPrettyExpirationDate().compareTo(this.date) <= 0 )
			throw new ExpiredCardException();
		
		if (this.getCard(number).getBalance() < amount)
			throw new NotEnoughMoneyException();
		
		else
			this.getCard(number).setBalance(-1*amount);
	}

	@Override
	public void chargeMoney(long number, float amount, String pin)
			throws NotRegisteredException, IncorrectPinException, ExpiredCardException {
		if (this.getCard(number) == null) 
			throw new NotRegisteredException();
		
		if (this.getCard(number).getPin() != pin)
			throw new IncorrectPinException();
		
		if (this.getCard(number).getPrettyExpirationDate().compareTo(this.date) <= 0 )
			throw new ExpiredCardException();
		
		this.getCard(number).setBalance(amount);
	}

	@Override
	public void changePIN(String oldPin, String newPin) throws NotRegisteredException, 
	IncorrectPinException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float consultBalance(long number, String pin) throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException {
		if (this.getCard(number) == null) 
			throw new NotRegisteredException();

		if (this.getCard(number).getPin() != pin)
			throw new IncorrectPinException();

		if (this.getCard(number).getPrettyExpirationDate().compareTo(this.date) <= 0 )
			throw new ExpiredCardException();
		
		return this.getCard(number).getBalance();
	}

	@Override
	public void consultMovements(long number, String pin) throws NotRegisteredException,
	IncorrectPinException {
		// TODO Auto-generated method stub
		
	}
	
}

package es.upm.pproject.tdd.backend;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Manager implements Interface{
	private Map <Long, Card> cards = new HashMap <>();
	private Calendar calendar = Calendar.getInstance();

	public Manager (List <Card> cards) {
		for (Card c : cards) {
			this.cards.put(c.getNumber(), c);
		}
	}
	
	public Card getCard(Long K) {
		return cards.get(K);
	}
	
	@Override
	public void buyCard(String name, String surname, String pin, float amount) throws AlreadyRegisteredException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pay(long number, float amount, String pin)
			throws NotRegisteredException, IncorrectPinException, NotEnoughMoneyException, ExpiredCardException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chargeMoney(long number, float amount, String pin)
			throws NotRegisteredException, IncorrectPinException, ExpiredCardException {
		Card card = this.cards.get(number);
		if (card == null) 
			throw new NotRegisteredException();
		
		if (card.getPin() != pin)
			throw new IncorrectPinException();
		
		if (card.getExpirationDate().compareTo(this.calendar)<0)
			throw new ExpiredCardException();
		
		card.setBalance(amount);
	}

	@Override
	public void changePIN(String oldPin, String newPin) throws NotRegisteredException, IncorrectPinException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consultBalance(long number, String pin) throws NotRegisteredException, IncorrectPinException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consultMovements(long number, String pin) throws NotRegisteredException, IncorrectPinException {
		// TODO Auto-generated method stub
		
	}
	
}

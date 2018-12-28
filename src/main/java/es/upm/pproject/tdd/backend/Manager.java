package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import es.upm.pproject.tdd.exceptions.*;

public class Manager implements PrePaidInterface{
	private Map <Long, Card> cards = new HashMap <>();
	private String date;

	/** Constructor. Creates a Map with the objects of the
	 * list. This list must have only cards. Also sets the
	 * date at the moment.
	 * @param list
	 */
	public Manager (List <Card> list) {
		for (Card c : list) {
			this.cards.put(c.getNumber(), c);
		} 
		this.date = this.dateFormat(Calendar.getInstance());
	}
	
	/** Sets the format of the date to have it in a 
	 * pretty way.
	 * @param calendar
	 * @return
	 */
	private String dateFormat(Calendar calendar) {
		Date cal = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(cal);
	}
	
	/** Returns the card with specific key (card number).
	 * Or null if it doesn't exist.
	 * @param k
	 * @return
	 */
	public Card getCard(Long k) {
		return this.cards.get(k);
	}
	
	/**Returns the Map of cards.
	 * @return
	 */
	public Map <Long, Card> getMap() {
		return this.cards;
	}
	
	/** Returns the today's date in a pretty format. 
	 * @return
	 */
	public String getDate() {
		return this.date;
	}
	
	@Override
	public long buyCard(String name, String surname, String pin, double amount) throws 
	AlreadyRegisteredException, IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException {
		if (pin == null)
			throw new IncorrectPinException();
		
		if (this.cards.size() >= Math.pow(10, 12)-1)
			throw new AlreadyRegisteredException();
		
		String hsPin = new HashPin(pin).getHashPin();	
		Card newCard = new Card(null, name, surname, hsPin, amount, null);
		if (!this.cards.containsKey(newCard.getNumber())) {
			this.cards.put(newCard.getNumber(), newCard);
			return newCard.getNumber();
		}
		else 
			return this.buyCard(name, surname, pin, amount);
	}

	@Override
	public void pay(long number, double amount, String pin) throws NotRegisteredException,
	IncorrectPinException, NotEnoughMoneyException, ExpiredCardException, IncorrectPinFormatException {
		if (pin == null)
			throw new IncorrectPinException();
		
		String hsPin = new HashPin(pin).getHashPin();
		
		if (this.getCard(number) == null) 
			throw new NotRegisteredException();
		
		if (!this.getCard(number).getPin().equals(hsPin))
			throw new IncorrectPinException();
		
		if (this.getCard(number).getPrettyExpirationDate().compareTo(this.date) <= 0 )
			throw new ExpiredCardException();
		
		if (this.getCard(number).getBalance() < amount)
			throw new NotEnoughMoneyException();
		
		else
			this.getCard(number).setBalance(this.getCard(number).getAmount()-amount);
	}

	@Override
	public void chargeMoney(long number, double amount, String pin)
			throws NotRegisteredException, IncorrectPinException, ExpiredCardException, 
			IncorrectPinFormatException {
		if (pin == null)
			throw new IncorrectPinException();
		
		String hsPin = new HashPin(pin).getHashPin();
		
		if (this.getCard(number) == null) 
			throw new NotRegisteredException();
		
		if (!this.getCard(number).getPin().equals(hsPin))
			throw new IncorrectPinException();
		
		if (this.getCard(number).getPrettyExpirationDate().compareTo(this.date) <= 0 )
			throw new ExpiredCardException();
		
		this.getCard(number).setBalance(this.getCard(number).getAmount()+amount);
	}

	@Override
	public Card changePIN(long number, String oldPin, String newPin) throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
		if (oldPin == null || newPin == null)
			throw new IncorrectPinException();
		
		String hsOldPin = new HashPin(oldPin).getHashPin();
		String hsNewPin = new HashPin(newPin).getHashPin();
		
		if (this.getCard(number) == null) 
			throw new NotRegisteredException();
		
		if (!this.getCard(number).getPin().equals(hsOldPin))
			throw new IncorrectPinException();
		
		if (this.getCard(number).getPrettyExpirationDate().compareTo(this.date) <= 0 )
			throw new ExpiredCardException();
		
		Card newCard = new Card(this.getCard(number).getNumber(), this.getCard(number).getName(), 
				this.getCard(number).getSurname(), hsNewPin, this.getCard(number).getBalance(),
				this.getCard(number).getPrettyExpirationDate());
		this.cards.replace(number, newCard);
		return this.cards.get(number);
	}

	@Override
	public double consultBalance(long number, String pin) throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
		if (pin == null)
			throw new IncorrectPinException();
		
		String hsPin = new HashPin(pin).getHashPin();
		
		if (this.getCard(number) == null) 
			throw new NotRegisteredException();

		if (!this.getCard(number).getPin().equals(hsPin))
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

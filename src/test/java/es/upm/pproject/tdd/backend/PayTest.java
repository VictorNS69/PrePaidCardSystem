package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import es.upm.pproject.tdd.exceptions.*;

public class PayTest {
	private Manager manager;
	private List <Card> cardsList = new ArrayList<Card>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException, ExpiredCardException{
		Card card = new Card(null, "Victor", "Nieves", "1234", 100, null);
		this.cardsList.add(card);
		this.manager = new Manager(this.cardsList);
		this.card = this.manager.getCard(card.getNumber()); 
	}
	
	@Test
	public void payOk_1() throws NotRegisteredException, IncorrectPinException, 
	NotEnoughMoneyException, ExpiredCardException {
		this.manager.pay(this.card.getNumber(), 10, this.card.getPin());
		assertEquals(90, this.card.getBalance());
	}
	
	@Test
	public void payOk_2() throws NotRegisteredException, IncorrectPinException, 
	NotEnoughMoneyException, ExpiredCardException {
		this.manager.pay(this.card.getNumber(), 100, this.card.getPin());
		assertEquals(0, this.card.getBalance());
	}
	
	@Test
	public void payNotEnoughMoney() throws NotRegisteredException, IncorrectPinException, 
	NotEnoughMoneyException, ExpiredCardException {
		assertThrows(NotEnoughMoneyException.class, ()->{
			this.manager.pay(this.card.getNumber(),1000, this.card.getPin());
		});
	}
	
	 @Test
	    public void testPayNullCard() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException {
	    	assertThrows(NotRegisteredException.class, ()->{
				this.manager.pay(12, 10, this.card.getPin());
			});
	    }

	    @Test
	    public void testPayIncorrectPin() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException {
	    	assertThrows(IncorrectPinException.class, ()->{
				this.manager.pay(this.card.getNumber(), 10, "ae22");
			});
	    }

	    @Test
	    public void testPayExpiredDate() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
	    	assertThrows(ExpiredCardException.class, ()->{
	    		Card card = new Card(null,"Daniel", "Morgera", "1234", 0, "01-01-1990");
	    		this.cardsList.add(card);
	    		this.init();
				this.manager.pay(card.getNumber(), 10, card.getPin());
			});
	    }
}

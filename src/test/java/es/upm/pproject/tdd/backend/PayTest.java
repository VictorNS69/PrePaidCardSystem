package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import es.upm.pproject.tdd.exceptions.*;

public class PayTest {
	private CardOperations manager;
	private Map <Long, Card> map = new HashMap<>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException{
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null, "Victor", "Nieves",pin, 100, null);
		this.map.put(card.getNumber(), card);
		this.manager = new CardOperations(this.map);
		this.card = this.manager.getCard(card.getNumber()); 
	}
	
	@Test
	public void payOk_1() throws NotRegisteredException, IncorrectPinException, 
	NotEnoughMoneyException, ExpiredCardException, IncorrectPinFormatException, 
	InvalidAmountException {
		this.manager.pay(this.card.getNumber(), 10, "1234");
		assertEquals(90, this.card.getBalance());
	}
	
	@Test
	public void payOk_2() throws NotRegisteredException, IncorrectPinException, 
	NotEnoughMoneyException, ExpiredCardException, IncorrectPinFormatException, InvalidAmountException {
		this.manager.pay(this.card.getNumber(), 100, "1234");
		assertEquals(0, this.card.getBalance());
	}
	
	@Test
	public void payNotEnoughMoney() throws NotRegisteredException, IncorrectPinException, 
	NotEnoughMoneyException, ExpiredCardException {
		assertThrows(NotEnoughMoneyException.class, ()->{
			this.manager.pay(this.card.getNumber(),1000, "1234");
		});
	}
	
	 @Test
	    public void testPayNullCard() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException {
	    	assertThrows(NotRegisteredException.class, ()->{
				this.manager.pay(12, 10, "1234");
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
	    		String pin = new HashPin("1234").getHashPin();
	    		Card card = new Card(null,"Daniel", "Morgera", pin, 0, "01-01-1990");
	    		this.map.put(card.getNumber(), card);
	    		this.init();
				this.manager.pay(card.getNumber(), 10,"1234");
			});
	    }
}

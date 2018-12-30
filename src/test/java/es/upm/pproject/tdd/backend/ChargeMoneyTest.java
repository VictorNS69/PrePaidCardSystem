package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import es.upm.pproject.tdd.exceptions.*;

public class ChargeMoneyTest {
	private CardOperations manager;
	private Map <Long, Card> map = new HashMap<>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException{
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null, "Victor", "Nieves", pin, 0, null);
		this.map.put(card.getNumber(), card);
		this.manager = new CardOperations(this.map);
		this.card = this.manager.getCard(card.getNumber()); 
	}
	
    @Test
    public void chargeMoneyOk_1() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException, 
    		InvalidAmountException {
        this.manager.chargeMoney(this.card.getNumber(), 10, "1234");
        assertEquals(10, this.card.getBalance());
    }

    @Test
    public void chargeMoneyOk_2() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException,
    		InvalidAmountException{
		String pin = new HashPin("1234").getHashPin();
    	Card card = new Card(null,"Daniel", "Morgera", pin, 0, null);
		this.map.put(card.getNumber(), card);
		this.init();
		this.card = this.manager.getCard(card.getNumber());
        this.manager.chargeMoney(this.card.getNumber(), 20, "1234");
        assertEquals(20, this.card.getBalance());
    }

    @Test
    public void chargeMoneyNullCard() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException {
    	assertThrows(NotRegisteredException.class, ()->{
			this.manager.chargeMoney(12, 10, "1234");
		});
    }

    @Test
    public void chargeMoneyIncorrectPin() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException {
    	assertThrows(IncorrectPinException.class, ()->{
			this.manager.chargeMoney(this.card.getNumber(), 10, "ae22");
		});
    }

    @Test
    public void chargeMoneyExpiredDate() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
    	assertThrows(ExpiredCardException.class, ()->{
    		String pin = new HashPin("1234").getHashPin();
    		Card card = new Card(null,"Daniel", "Morgera", pin, 0, "01-01-1990");
    		this.map.put(card.getNumber(), card);
    		this.init();
			this.manager.chargeMoney(card.getNumber(), 10, card.getPin());
		});
    }
    
	@Test
	public void chargeMoneyCardNullPin() {
		assertThrows(IncorrectPinException.class, ()->{
			this.manager.chargeMoney(this.card.getNumber(), 10, null);
		});
	}
	
	@Test 
	public void chargeMoneyInvalidAmount() {
		assertThrows(InvalidAmountException.class, ()->{
			this.manager.chargeMoney(this.card.getNumber(), -10, "1234");
		});
	}
}

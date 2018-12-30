package es.upm.pproject.tdd.backend;

import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;

public class BuyCardTest {
	private CardOperations manager;
	private Map <Long, Card> map = new HashMap<>();
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException,
	ExpiredCardException{
		Card card = new Card(null, "Victor", "Nieves", "1234", 100, null);
		this.map.put(card.getNumber(), card);
		manager = new CardOperations(this.map);
	}
	 
	@Test
	public void buyCardOk() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException, AlreadyRegisteredException, InvalidAmountException {
		long number = this.manager.buyCard("Victor", "Nieves", "1234", 120);
		assertNotNull(this.manager.getCard(number));
		assertEquals(2, this.manager.getMap().size());
	}
	
	
	@Test
	public void buyCardWrongPin_1() {
		assertThrows(IncorrectPinFormatException.class, ()->{
    		this.manager.buyCard("Daniel", "Morgera", "0", 0);
		});
	}
	
	@Test
	public void buyCardWrongPin_2() {
		assertThrows(IncorrectPinFormatException.class, ()->{
    		this.manager.buyCard("Daniel", "Morgera", "1538458965", 0);
		});
	}
	
	@Test
	public void buyCardNullPin() {
		assertThrows(IncorrectPinException.class, ()->{
    		this.manager.buyCard("Daniel", "Morgera", null, 0);
		});
	}
	
	@Test 
	public void buyCardInvalidAmount() {
		assertThrows(InvalidAmountException.class, ()->{
    		this.manager.buyCard("Daniel", "Morgera", "1234", -10);
		});
	}
}

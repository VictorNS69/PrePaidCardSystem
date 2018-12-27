package es.upm.pproject.tdd.backend;

import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;

public class BuyCardTest {
	private Manager manager;
	private List <Card> cardsList = new ArrayList<Card>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException,
	ExpiredCardException{
		Card card = new Card(null, "Victor", "Nieves", "1234", 100, null);
		this.cardsList.add(card);
		this.manager = new Manager(this.cardsList);
		this.card = this.manager.getCard(card.getNumber()); 
	}
	 
	@Test
	public void buyCardOk() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException, AlreadyRegisteredException {
		long number = this.manager.buyCard("Victor", "Nieves", "1234", 120);
		this.cardsList.add(card);
		assertNotNull(this.manager.getCard(number));
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
}

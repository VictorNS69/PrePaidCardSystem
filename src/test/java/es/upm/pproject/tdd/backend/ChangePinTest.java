package es.upm.pproject.tdd.backend;

import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;

public class ChangePinTest {
	private CardOperations manager;
	private Card card;
	private Map <Long, Card> map = new HashMap<>();
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException,
	ExpiredCardException{
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null, "Victor", "Nieves", pin, 100, null);
		this.map.put(card.getNumber(), card);
		this.manager = new CardOperations(this.map);
		this.card = this.manager.getCard(card.getNumber()); 
	}
	
	@Test
	public void changePinOk_1() throws IncorrectPinFormatException, 
	NotRegisteredException, IncorrectPinException, ExpiredCardException {
		String expectedPin = new HashPin("0001").getHashPin();
		Card card = this.manager.changePIN(this.card.getNumber(), "1234", "0001");
		assertEquals(expectedPin, card.getPin());
	}
	
	@Test
	public void changePinSamePin() throws IncorrectPinFormatException, 
	NotRegisteredException, IncorrectPinException, ExpiredCardException {
		String expectedPin = new HashPin("1234").getHashPin();
		Card card = this.manager.changePIN(this.card.getNumber(), "1234", "1234");
		assertEquals(expectedPin, card.getPin());
	}
	
	@Test
	public void changePinNullOldPin() throws IncorrectPinFormatException, 
	NotRegisteredException, IncorrectPinException, ExpiredCardException {
		assertThrows(IncorrectPinException.class, ()->{
    		this.manager.changePIN(this.card.getNumber(), null, "0001");
		});
	}
	
	@Test
	public void changePinNullNewPin() throws IncorrectPinFormatException, 
	NotRegisteredException, IncorrectPinException, ExpiredCardException {
		assertThrows(IncorrectPinException.class, ()->{
    		this.manager.changePIN(this.card.getNumber(), "1234", null);
		});
	}
	
	@Test
	public void changePinWrongPin_1() {
		assertThrows(IncorrectPinFormatException.class, ()->{
			this.manager.changePIN(this.card.getNumber(), "0", "0001");
		});
	}
	
	@Test
	public void changePinWrongPin_2() {
		assertThrows(IncorrectPinFormatException.class, ()->{
			this.manager.changePIN(this.card.getNumber(), "123456732", "0001");
		});
	}
}

package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.*;
import es.upm.pproject.tdd.exceptions.*;

public class ConsultMovementsTest {
	private CardOperations manager;
	private Map <Long, Card> map = new HashMap<>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException{
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null, "Victor", "Nieves", pin, 100, null);
		this.map.put(card.getNumber(), card);
		this.manager = new CardOperations(this.map);
		this.card = this.manager.getCard(card.getNumber()); 
	}
	
	public String expected(int sign, double amount) {
		if (sign == 1)
			sign = '+';
		else
			sign = '-';
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' HH:mm:ss");
		return ft.format(today)+"   "+ (char)sign + amount +"€";
	}
	
	@Test
	public void consultMovementsOk_1() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException, 
	InvalidAmountException, InvalidMovementException {
		this.manager.chargeMoney(this.card.getNumber(), 10, "1234");
		ArrayList<Movement> list = (ArrayList<Movement>) this.manager.consultMovements(this.card.getNumber(), "1234");
		assertEquals(expected(1,10), list.get(0).toString());
	}
	
	@Test
	public void consultMovementsOk_2() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException, 
	InvalidAmountException, InvalidMovementException {
		this.manager.chargeMoney(this.card.getNumber(), 10, "1234");
		this.manager.chargeMoney(this.card.getNumber(), 11, "1234");
		ArrayList<Movement> list = (ArrayList<Movement>) this.manager.consultMovements(this.card.getNumber(), "1234");
		assertEquals(expected(1,10), list.get(0).toString());
		assertEquals(expected(1,11), list.get(1).toString());
	}
	
	@Test
	public void consultMovementsOk_3() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException, 
	InvalidAmountException, InvalidMovementException, NotEnoughMoneyException {
		this.manager.pay(this.card.getNumber(), 10, "1234");
		ArrayList<Movement> list = (ArrayList<Movement>) this.manager.consultMovements(this.card.getNumber(), "1234");
		assertEquals(expected(0,10), list.get(0).toString());
	}
	
	@Test
	public void consultMovementsOk_4() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException, 
	InvalidAmountException, InvalidMovementException, NotEnoughMoneyException {
		this.manager.pay(this.card.getNumber(), 10, "1234");
		this.manager.pay(this.card.getNumber(), 11, "1234");
		ArrayList<Movement> list = (ArrayList<Movement>) this.manager.consultMovements(this.card.getNumber(), "1234");
		assertEquals(expected(0,10), list.get(0).toString());
		assertEquals(expected(0,11), list.get(1).toString());
	}
	
	@Test
	public void consultMoventsNotRegistered() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null,"Daniel", "Morgera", pin, 0, null);
		assertThrows(NotRegisteredException.class, ()->{
			this.manager.consultMovements(card.getNumber(), "1234");
		});
	}

	@Test
	public void consultMovementNullPin() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException {
		assertThrows(IncorrectPinException.class, ()->{
			this.manager.consultMovements(this.card.getNumber(), null);
		});
	}

	@Test
	public void consultMovementIncorrectPin() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
		assertThrows(IncorrectPinException.class, ()->{
			this.manager.consultMovements(this.card.getNumber(), "1235");
		});
	}
}

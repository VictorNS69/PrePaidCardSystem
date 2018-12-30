package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;

public class CardOperationsTest {
	private CardOperations manager;
	private Card card;
	private Map<Long, Card> map = new HashMap<>();
	
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException,
	ExpiredCardException{
		Card card = new Card(null, "Victor", "Nieves", "1234", 100, null);
		this.map.put(card.getNumber(), card);
		this.manager = new CardOperations(this.map);
		this.card = this.manager.getCard(card.getNumber()); 
	}
	
	private String dateFormat(Calendar calendar) {
		Date cal = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(cal);
	}
	
	@Test
	public void managerGetDate() {
		assertEquals(dateFormat(Calendar.getInstance()), this.manager.getDate());
	}
	
	@Test
	public void managerGetCard() {
		assertEquals(this.card, this.manager.getCard(this.card.getNumber()));
	}
	
	@Test
	public void managerGetMap() {
		assertEquals(this.map.toString(), this.manager.getMap().toString());
	}
	
	@Test
	public void managerNull() {
		this.manager = new CardOperations(null);
		assertEquals(0, this.manager.getMap().size());
	}
	
	@Test
	public void managerVoid() {
		this.manager = new CardOperations(new HashMap<>());
		assertEquals(0, this.manager.getMap().size());
	}
}

package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;

public class CardOperationsGettersTest {
	private CardOperations manager;
	private List <Card> cardsList = new ArrayList<Card>();
	private Card card;
	private Map<Long, Card> map = new HashMap<>();
	
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException,
	ExpiredCardException{
		Card card = new Card(null, "Victor", "Nieves", "1234", 100, null);
		this.cardsList.add(card);
		this.manager = new CardOperations(this.cardsList);
		this.map.put(card.getNumber(), card);
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
}

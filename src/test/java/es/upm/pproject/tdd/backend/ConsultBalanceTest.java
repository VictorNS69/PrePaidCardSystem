package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ConsultBalanceTest {
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
    public void testConsultBalanceOk_1() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException {
        assertEquals(100, this.manager.consultBalance(this.card.getNumber(), this.card.getPin()));
    }
	
	@Test
    public void testConsultBalanceOk_2() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException {
        this.manager.chargeMoney(this.card.getNumber(), 10, this.card.getPin());
        assertEquals(110, this.manager.consultBalance(this.card.getNumber(), this.card.getPin()));
    }
	
	@Test
    public void testConsultBalanceOk_3() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
		Card card = new Card(null,"Daniel", "Morgera", "1234", 0, null);
		this.cardsList.add(card);
		this.init();
		assertEquals(0, this.manager.consultBalance(card.getNumber(), card.getPin()));
    }
	
	 @Test
	    public void testConsultBalanceNullCard() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException {
	    	assertThrows(NotRegisteredException.class, ()->{
				this.manager.consultBalance(1, this.card.getPin());
			});
	    }

	    @Test
	    public void testConsultBalanceIncorrectPin() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException {
	    	assertThrows(IncorrectPinException.class, ()->{
	    		this.manager.consultBalance(this.card.getNumber(), "1aa235");
			});
	    }

	    @Test
	    public void testConsultBalanceExpiredDate() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
	    	assertThrows(ExpiredCardException.class, ()->{
	    		Card card = new Card(null,"Daniel", "Morgera", "1234", 0, "01-01-1990");
	    		this.cardsList.add(card);
	    		this.init();
				this.manager.consultBalance(card.getNumber(), card.getPin());
			});
	    }
}

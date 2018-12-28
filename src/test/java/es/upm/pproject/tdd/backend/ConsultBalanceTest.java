package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import es.upm.pproject.tdd.exceptions.*;

public class ConsultBalanceTest {
	private Manager manager;
	private List <Card> cardsList = new ArrayList<Card>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException, ExpiredCardException{
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null, "Victor", "Nieves", pin, 100, null);
		this.cardsList.add(card);
		this.manager = new Manager(this.cardsList);
		this.card = this.manager.getCard(card.getNumber()); 
	}
	
	@Test
    public void testConsultBalanceOk_1() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
        assertEquals(100, this.manager.consultBalance(this.card.getNumber(), "1234"));
    }
	
	@Test
    public void testConsultBalanceOk_2() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException, 
    		InvalidAmountException {
        this.manager.chargeMoney(this.card.getNumber(), 10, "1234");
        assertEquals(110, this.manager.consultBalance(this.card.getNumber(), "1234"));
    }
	
	@Test
    public void testConsultBalanceOk_3() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null,"Daniel", "Morgera", pin, 0, null);
		this.cardsList.add(card);
		this.init();
		assertEquals(0, this.manager.consultBalance(card.getNumber(),"1234"));
    }
	
	 @Test
	    public void testConsultBalanceNullCard() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException {
	    	assertThrows(NotRegisteredException.class, ()->{
				this.manager.consultBalance(1, "1234");
			});
	    }

	    @Test
	    public void testConsultBalanceIncorrectPin() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException {
	    	assertThrows(IncorrectPinException.class, ()->{
	    		this.manager.consultBalance(this.card.getNumber(), null);
			});
	    }

	    @Test
	    public void testConsultBalanceExpiredDate() throws NotRegisteredException, 
		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
	    	assertThrows(ExpiredCardException.class, ()->{
	    		String pin = new HashPin("1234").getHashPin();
	    		Card card = new Card(null,"Daniel", "Morgera", pin, 0, "01-01-1990");
	    		this.cardsList.add(card);
	    		this.init();
				this.manager.consultBalance(card.getNumber(), "1234");
			});
	    }
}

package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import es.upm.pproject.tdd.exceptions.*;

public class ChargeMoneyTest {
	private CardOperations manager;
	private List <Card> cardsList = new ArrayList<Card>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException{
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null, "Victor", "Nieves", pin, 0, null);
		this.cardsList.add(card);
		this.manager = new CardOperations(this.cardsList);
		this.card = this.manager.getCard(card.getNumber()); 
	}
	
    @Test
    public void testChargeMoneyOk_1() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException, 
    		InvalidAmountException {
        this.manager.chargeMoney(this.card.getNumber(), 10, "1234");
        assertEquals(10, this.card.getBalance());
    }

    @Test
    public void testChargeMoneyOk_2() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException,
    		InvalidAmountException{
		String pin = new HashPin("1234").getHashPin();
    	Card card = new Card(null,"Daniel", "Morgera", pin, 0, null);
		this.cardsList.add(card);
		this.init();
		this.card = this.manager.getCard(card.getNumber());
        this.manager.chargeMoney(this.card.getNumber(), 20, "1234");
        assertEquals(20, this.card.getBalance());
    }

    @Test
    public void testChargeMoneyNullCard() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException {
    	assertThrows(NotRegisteredException.class, ()->{
			this.manager.chargeMoney(12, 10, "1234");
		});
    }

    @Test
    public void testChargeMoneyIncorrectPin() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException {
    	assertThrows(IncorrectPinException.class, ()->{
			this.manager.chargeMoney(this.card.getNumber(), 10, "ae22");
		});
    }

    @Test
    public void testChargeMoneyExpiredDate() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException, IncorrectPinFormatException {
    	assertThrows(ExpiredCardException.class, ()->{
    		String pin = new HashPin("1234").getHashPin();
    		Card card = new Card(null,"Daniel", "Morgera", pin, 0, "01-01-1990");
    		this.cardsList.add(card);
    		this.init();
			this.manager.chargeMoney(card.getNumber(), 10, card.getPin());
		});
    }
}

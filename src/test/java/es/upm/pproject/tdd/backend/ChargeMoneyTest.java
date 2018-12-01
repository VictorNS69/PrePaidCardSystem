package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ChargeMoneyTest {
	private Manager manager;
	private List <Card> cardsList = new ArrayList<Card>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException{
		Card card = new Card(null, "Victor", "Nieves", "1234", 0, null);
		this.cardsList.add(card);
		this.manager = new Manager(this.cardsList);
		this.card = this.manager.getCard(card.getNumber());
	}
	
    @Test
    public void testChargeMoneyOk_1() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException {
        this.manager.chargeMoney(this.card.getNumber(), 10, this.card.getPin());
        assertEquals(10, this.card.getBalance());
    }

    @Test
    public void testChargeMoneyOk_2() throws NotRegisteredException, 
    		IncorrectPinException, ExpiredCardException, IncorrectPinFormatException{
    	Card card = new Card(null,"Daniel", "Morgera", "1234", 0, null);
		this.cardsList.add(card);
		this.init();
		this.card = this.manager.getCard(card.getNumber());
        this.manager.chargeMoney(this.card.getNumber(), 20, this.card.getPin());
        assertEquals(20, this.card.getBalance());
    }

    @Test
    public void testChargeMoneyNullCard() throws NotRegisteredException, 
	IncorrectPinException, ExpiredCardException {
    	assertThrows(NotRegisteredException.class, ()->{
			this.manager.chargeMoney(12, 10, this.card.getPin());
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
    	Card card = new Card(null,"Daniel", "Morgera", "1234", 0, "01-01-1990");
		this.cardsList.add(card);
		this.init();
    	assertThrows(ExpiredCardException.class, ()->{
			this.manager.chargeMoney(card.getNumber(), 10, card.getPin());
		});
    }
}

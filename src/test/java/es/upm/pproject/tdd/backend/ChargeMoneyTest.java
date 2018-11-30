package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import es.upm.pproject.tdd.backend.*;

public class ChargeMoneyTest {
	private Manager manager;
	private List <Card> cardsList = new ArrayList<Card>();
	private Card card;
	
	@BeforeEach
	private void init() {
		Card card = new Card("Victor", "Nieves", "1234", 0);
		this.cardsList.add(card);
		this.manager = new Manager(this.cardsList);
		this.card = this.manager.getCard(card.getNumber());
	}
	
    @Test
    public void testChargeMoneyOk_1() throws NotRegisteredException, 
    		IncorrectPINException, ExpiredCardException {
        manager.chargeMoney(this.card.getNumber(), 10, this.card.getPin());
        assertEquals(10, this.card.getBalance());
    }
    
    @Test
    public void testChargeMoneyOk_2() throws NotRegisteredException, 
    		IncorrectPINException, ExpiredCardException {
    	Card card = new Card("Daniel", "Morgera", "1234", 0);
		this.cardsList.add(card);
		this.init();
		this.card = this.manager.getCard(card.getNumber());
        manager.chargeMoney(this.card.getNumber(), 20, this.card.getPin());
        assertEquals(20, this.card.getBalance());
    }
    
    @Test
    public void testChargeMoneyNullCard() throws NotRegisteredException, 
	IncorrectPINException, ExpiredCardException {
    	assertThrows(NotRegisteredException.class, ()->{
			this.manager.chargeMoney(12, 10, this.card.getPin());
		});
    }
    
    @Test
    public void testChargeMoneyIncorrectPin() throws NotRegisteredException, 
	IncorrectPINException, ExpiredCardException {
    	assertThrows(IncorrectPINException.class, ()->{
			this.manager.chargeMoney(this.card.getNumber(), 10, "ae22");
		});
    }
    
    @Disabled
    @Test
    public void testChargeMoneyExpiredDate() throws NotRegisteredException, 
	IncorrectPINException, ExpiredCardException {
    	assertThrows(ExpiredCardException.class, ()->{
			this.manager.chargeMoney(this.card.getNumber(), 10, this.card.getPin());
		});
    }
}

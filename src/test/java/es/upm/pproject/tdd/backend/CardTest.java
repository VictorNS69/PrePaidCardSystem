package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;


public class CardTest {
		
    @Test
    public void testCardOk_1() throws IncorrectPinException, IncorrectPinFormatException, ExpiredCardException {
    	String pin = new HashPin("0001").getHashPin();
    	Card card = new Card (null, "Victor", "Nieves", pin, 0, null);
    	long number = card.getNumber();
    	String dateE = card.getPrettyExpirationDate();
    	assertEquals(number +" "+ "Victor"+ " "+ "Nieves" +" "+  card.getPin() +" " + 0.0 +" " +dateE,  card.toString());
    }
    
    @Test
    public void testCardOk_2() throws IncorrectPinException, IncorrectPinFormatException, ExpiredCardException {
    	long number =Long.valueOf("123456789123").longValue();
    	String pin = new HashPin("0001").getHashPin();
    	Card card = new Card (number, "Victor", "Nieves", pin, 0, null);
    	String dateE = card.getPrettyExpirationDate();
    	assertEquals(number +" "+ "Victor"+ " "+ "Nieves" +" "+ card.getPin() +" " + 0.0 +" " +dateE,  card.toString());
    }
    
    @Test
    public void testCardOk_3() throws IncorrectPinException, IncorrectPinFormatException, ExpiredCardException {
    	String pin = new HashPin("0001").getHashPin();
    	long number =Long.valueOf("123456789123").longValue();
    	Card card = new Card (number, "Victor", "Nieves", pin, 0, "11-11-3999");
    	assertEquals(number +" "+ "Victor"+ " "+ "Nieves" +" "+ card.getPin() +" " + 0.0 +" " +"11-11-3999",  card.toString());
    }
    
    @Test
    public void testCardOk_4() throws IncorrectPinException, IncorrectPinFormatException, ExpiredCardException {
    	String pin = new HashPin("0001").getHashPin();
    	Card card = new Card (null, "Victor", "Nieves", pin, 0, "11-11-3999");
    	long number = card.getNumber();
    	assertEquals(number +" "+ "Victor"+ " "+ "Nieves" +" "+ card.getPin() +" " + 0.0 +" " +"11-11-3999",  card.toString());
    }
    
    @Test 
    public void testCardWrongFormatPin() {
    	assertThrows(IncorrectPinException.class, ()->{
    		new Card (null, "Victor", "Nieves", null, 0, null);
    	});
    }
    
    @Test 
    public void testCardWrongDate() {
    	assertThrows(ExpiredCardException.class, ()->{
    		String pin = new HashPin("0001").getHashPin();
    		new Card (null, "Victor", "Nieves", pin, 0, "11-11-1000");
    	});
    }

    @Test 
    public void testAmount() throws IncorrectPinException, IncorrectPinFormatException, ExpiredCardException  {
    	String pin = new HashPin("0001").getHashPin();
    	Card card = new Card (null, "Victor", "Nieves", pin, 0, "11-11-3999");
    	long number = card.getNumber();
    	assertEquals(0,  card.getAmount());
    }

}


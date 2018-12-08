package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class CardTest {
		
    @Test
    public void testCardOk_1() throws IncorrectPinException, IncorrectPinFormatException, ExpiredCardException {
    	Card card = new Card (null, "Victor", "Nieves", "0001", 0, null);
    	long number = card.getNumber();
    	String pin = card.getPin();
    	String dateE = card.getPrettyExpirationDate();
    	assertEquals(number +" "+ "Victor"+ " "+ "Nieves" +" "+  pin +" " + 0.0 +" " +dateE,  card.toString());
    }
    
    @Test
    public void testCardOk_2() throws IncorrectPinException, IncorrectPinFormatException, ExpiredCardException {
    	long number =Long.valueOf("123456789123").longValue();
    	Card card = new Card (number, "Victor", "Nieves", "0001", 0, null);
    	String pin = card.getPin();
    	String dateE = card.getPrettyExpirationDate();
    	assertEquals(number +" "+ "Victor"+ " "+ "Nieves" +" "+ pin +" " + 0.0 +" " +dateE,  card.toString());
    }
    
    @Test
    public void testCardOk_3() throws IncorrectPinException, IncorrectPinFormatException, ExpiredCardException {
    	long number =Long.valueOf("123456789123").longValue();
    	Card card = new Card (number, "Victor", "Nieves", "0001", 0, "11-11-3999");
    	String pin = card.getPin();
    	assertEquals(number +" "+ "Victor"+ " "+ "Nieves" +" "+ pin +" " + 0.0 +" " +"11-11-3999",  card.toString());
    }
    
    @Test
    public void testCardOk_4() throws IncorrectPinException, IncorrectPinFormatException, ExpiredCardException {
    	Card card = new Card (null, "Victor", "Nieves", "0001", 0, "11-11-3999");
    	long number = card.getNumber();
    	String pin = card.getPin();
    	assertEquals(number +" "+ "Victor"+ " "+ "Nieves" +" "+ pin +" " + 0.0 +" " +"11-11-3999",  card.toString());
    }
    
    @Test 
    public void testCardWrongFormatPin_1() {
    	assertThrows(IncorrectPinException.class, ()->{
    		new Card (null, "Victor", "Nieves", null, 0, null);
    	});
    }
    
    @Test 
    public void testCardWrongFormatPin_2() {
    	assertThrows(IncorrectPinFormatException.class, ()->{
    		new Card (null, "Victor", "Nieves", "0", 0, null);
    	});
    }
    
    @Test 
    public void testCardWrongFormatPin_3() {
    	assertThrows(IncorrectPinFormatException.class, ()->{
    		new Card (null, "Victor", "Nieves", "12345", 0, null);
    	});
    }
    
    @Test 
    public void testCardWrongDate() {
    	assertThrows(ExpiredCardException.class, ()->{
    		new Card (null, "Victor", "Nieves", "1234", 0, "11-11-1000");
    	});
    }
}

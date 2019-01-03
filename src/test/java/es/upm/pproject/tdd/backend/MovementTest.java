package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;

public class MovementTest {
	
	@Test
	public void movementSub_1() throws InvalidMovementException, 
	InvalidAmountException {
		Movement mv = new Movement (0, 33.33);
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' HH:mm:ss");
		String expected = ft.format(today)+"   "+ "-"+ 33.33 +"€";
		assertEquals(expected, mv.toString());
	}
	
	@Test
	public void movementSub_2() throws InvalidMovementException ,
	InvalidAmountException{
		Movement mv = new Movement (0, 1);
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' HH:mm:ss");
		String expected = ft.format(today)+"   "+"-"+ 1.0 +"€";
		assertEquals(expected, mv.toString());
	}
	
	@Test
	public void movementAdd_1() throws InvalidMovementException, 
	InvalidAmountException {
		Movement mv = new Movement (1, 33.33);
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' HH:mm:ss");
		String expected = ft.format(today)+"   "+ "+"+ 33.33 +"€";
		assertEquals(expected, mv.toString());
	}
	
	@Test
	public void movementAdd_2() throws InvalidMovementException ,
	InvalidAmountException{
		Movement mv = new Movement (1, 1);
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' HH:mm:ss");
		String expected = ft.format(today)+"   "+"+"+ 1.0 +"€";
		assertEquals(expected, mv.toString());
	}
	
	@Test
	public void movementInvalidAmount_1() throws InvalidMovementException,
	InvalidAmountException{
		assertThrows(InvalidAmountException.class, ()->{
    		new Movement (1,0);
		});
	}
	
	@Test
	public void movementInvalidAmount_2() throws InvalidMovementException,
	InvalidAmountException{
		assertThrows(InvalidAmountException.class, ()->{
    		new Movement (1,-20);
		});
	}
	
	@Test
	public void movementInvalidAmount_3() throws InvalidMovementException,
	InvalidAmountException{
		assertThrows(InvalidAmountException.class, ()->{
    		new Movement (1,-3000);
		});
	}
	
	@Test
	public void movementInvalidSign_1() throws InvalidMovementException,
	InvalidAmountException{
		assertThrows(InvalidMovementException.class, ()->{
    		new Movement (2,10);
		});
	}
	
	@Test
	public void movementInvalidSign_2() throws InvalidMovementException,
	InvalidAmountException{
		assertThrows(InvalidMovementException.class, ()->{
    		new Movement (100,10);
		});
	}
	
	@Test
	public void movementInvalidSign_3() throws InvalidMovementException,
	InvalidAmountException{
		assertThrows(InvalidMovementException.class, ()->{
    		new Movement (-1,10);
		});
	}
	
	
}

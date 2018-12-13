package es.upm.pproject.tdd.backend;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;


public class HashPinTest {
	
	@Test
	public void lessThan4DigitsTest() {
		assertThrows(IncorrectPinFormatException.class, ()->{
			new  HashPin("1");
		});
	}
	
	@Test
	public void moreThan4DigitsTest() {
		assertThrows(IncorrectPinFormatException.class, ()->{
			new  HashPin("12345");
		});
	}
	
	@Test
	public void nullPinTest() {
		assertThrows(IncorrectPinFormatException.class, ()->{
			new  HashPin(null);
		});
	}
	
	@Test
	public void okPinTest_1() throws IncorrectPinFormatException {
		HashPin hp = new HashPin("0001");
		String pw = "888b19a43b151683c87895f6211d9f8640f97bdc8ef32f03dbe057c8f5e56d32";
		assertEquals(pw, hp.getHashPin());
	}
	
	@Test
	public void okPinTest_2() throws IncorrectPinFormatException {
		HashPin hp = new HashPin("1000");
		String pw = "40510175845988f13f6162ed8526f0b09f73384467fa855e1e79b44a56562a58";
		assertEquals(pw, hp.getHashPin());
	}
}



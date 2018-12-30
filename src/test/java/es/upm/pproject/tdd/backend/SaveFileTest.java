package es.upm.pproject.tdd.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;

public class SaveFileTest {
	private CardOperations manager;
	private Map <Long, Card> map = new HashMap<>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException,
	ExpiredCardException{
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null, "Victor", "Nieves", pin, 100, null);
		this.map.put(card.getNumber(), card);
		this.manager = new CardOperations(this.map);
		this.card = this.manager.getCard(card.getNumber());
	}

	@AfterEach
	private void removeFile() {
		File file = new File("src/assets/test.txt");
		file.delete();
	}
	
	@Test
	public void SaveFileOk_1() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException, AlreadyRegisteredException, FileNotFoundException {
		Path path = FileSystems.getDefault().getPath("src/assets/test.txt").toAbsolutePath();
		new FileOperations().saveFile(path, this.map);
		assertTrue(new File ("src/assets/test.txt").exists());
		assertNotEquals(0, new File ("src/assets/test.txt").length() );
	}
	
	@Test
	public void SaveFileOk_2() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException, AlreadyRegisteredException, FileNotFoundException {
		String pin = new HashPin("1333").getHashPin();
		Card card = new Card(null, "Daniel", "Morgera", pin, 10, null);
		this.map.put(card.getNumber(), card);
		this.manager = new CardOperations(this.map);
		this.card = this.manager.getCard(card.getNumber());
		Path path = FileSystems.getDefault().getPath("src/assets/test.txt").toAbsolutePath();
		new FileOperations().saveFile(path, this.map);
		assertTrue(new File ("src/assets/test.txt").exists());
		assertNotEquals(0, new File ("src/assets/test.txt").length() );
	}
	
	@Test 
	public void SaveFileOk_3()throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException, AlreadyRegisteredException, FileNotFoundException {
		for (int i = 0; i< 100;i++) {
			String pin = new HashPin("1234").getHashPin();
			Card card = new Card(null, "name", "surname", pin, 10*i, null);
			this.map.put(card.getNumber(), card);
		}
		this.manager = new CardOperations(this.map);
		this.card = this.manager.getCard(card.getNumber());
		Path path = FileSystems.getDefault().getPath("src/assets/test.txt").toAbsolutePath();
		new FileOperations().saveFile(path, this.map);
		assertTrue(new File ("src/assets/test.txt").exists());
		assertNotEquals(0, new File ("src/assets/test.txt").length() );
	}
	
	@Test
	public void SaveFileNullMap()throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException, AlreadyRegisteredException, FileNotFoundException {
		Map <Long, Card> mapNull = new HashMap<>();
		Path path = FileSystems.getDefault().getPath("src/assets/test.txt").toAbsolutePath();
		new FileOperations().saveFile(path, mapNull);
		assertEquals(0, new File ("src/assets/test.txt").length() );
	}
}

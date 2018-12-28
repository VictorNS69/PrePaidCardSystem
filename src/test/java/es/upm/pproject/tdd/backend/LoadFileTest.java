package es.upm.pproject.tdd.backend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.pproject.tdd.exceptions.*;

public class LoadFileTest {
	private Manager manager;
	private List <Card> cardsList = new ArrayList<Card>();
	private Map <Long, Card> map = new HashMap<>();
	private Card card;
	
	@BeforeEach
	private void init() throws IncorrectPinFormatException, IncorrectPinException,
	ExpiredCardException, FileNotFoundException{
		String pin = new HashPin("1234").getHashPin();
		Card card = new Card(null, "Victor", "Nieves", pin, 100, null);
		this.cardsList.add(card);
		this.manager = new Manager(this.cardsList);
		this.card = this.manager.getCard(card.getNumber());
		this.map.put(this.card.getNumber(), this.card);
		Path path = FileSystems.getDefault().getPath("src/assets/test.txt").toAbsolutePath();
		new FileOperations().saveFile(path, this.map);
	}
	
	@Test
	public void LoadFileOk_1() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException, AlreadyRegisteredException, IOException {
		Path path = FileSystems.getDefault().getPath("src/assets/test.txt").toAbsolutePath();
		Map <Long, Card> mapLoad = new FileOperations().loadFile(path);
		assertEquals(this.map.toString(), mapLoad.toString());
	}
	
	@Test
	public void LoadFileOk_2() throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException, AlreadyRegisteredException, IOException {
		Path path = FileSystems.getDefault().getPath("src/assets/test.txt").toAbsolutePath();
		for (int i = 0; i< 100;i++) {
			String pin = new HashPin("1234").getHashPin();
			Card card = new Card(null, "name", "surname", pin, 10*i, null);
			this.cardsList.add(card);
			this.manager = new Manager(this.cardsList);
			this.card = this.manager.getCard(card.getNumber());
			this.map.put(this.card.getNumber(), this.card);			
		}
		new FileOperations().saveFile(path, this.map);
		Map <Long, Card> mapLoad = new FileOperations().loadFile(path);
		assertEquals(this.map.toString(), mapLoad.toString());
	}
	
	@Test
	public void LoadFileNullMap()throws IncorrectPinFormatException, IncorrectPinException, 
	ExpiredCardException, AlreadyRegisteredException, IOException {
		Path path = FileSystems.getDefault().getPath("src/assets/test.txt").toAbsolutePath();
		Map <Long, Card> mapNull = new HashMap<>();
		new FileOperations().saveFile(path, mapNull);
		Map <Long, Card> mapLoad = new FileOperations().loadFile(path);
		assertEquals(mapNull.toString(), mapLoad.toString() );
	}
}
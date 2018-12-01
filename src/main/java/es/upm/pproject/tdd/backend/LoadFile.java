package es.upm.pproject.tdd.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LoadFile {
	private Queue <List>attributes = new LinkedList<List>();
	private Map <Long, Card> cards = new HashMap <>();
	
	public LoadFile(Path path) throws IOException, IncorrectPinFormatException {
		//Path path = FileSystems.getDefault().getPath("src/assets/doc.txt").toAbsolutePath();
		FileReader file = new FileReader(path.toString());
		String line;
		BufferedReader in = new BufferedReader(file);
		while((line = in.readLine()) != null){
		    this.attributes.add(this.makeAttributes(line));
		}
		in.close();
		this.makeCards();
	}
	
	private List<?> makeAttributes(String line) {
		List<String> list = new ArrayList();
		String[] parts = line.split("@");
		for (int i = 0; i < parts.length; i++) {
			list.add(parts[i]);
		}
		return list;
	}
	
	private void makeCards() throws IncorrectPinFormatException{
		List list;
		while ((list = this.attributes.poll()) != null) {
			Card card = new Card(Long.valueOf(list.get(0).toString()).longValue(), list.get(1).toString(), list.get(2).toString(), 
					list.get(3).toString(), Float.valueOf(list.get(4).toString()).floatValue(), list.get(5).toString());
			this.cards.put(card.getNumber(), card);
		}
	}
	
	public Map<Long, Card> getCards(){
		return this.cards;
	}
}

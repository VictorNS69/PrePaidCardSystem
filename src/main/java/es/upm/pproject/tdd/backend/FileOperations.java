package es.upm.pproject.tdd.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import es.upm.pproject.tdd.exceptions.*;

public class FileOperations {
	@SuppressWarnings("rawtypes")
	private Queue <List>attributes = new LinkedList<>();
	private Map <Long, Card> cards = new HashMap <>();
	
	/**Load a file from a path. This file must be well written
	 * to load correctly the cards.
	 * @param path
	 * @throws IOException
	 * @throws IncorrectPinException
	 * @throws ExpiredCardException
	 */
	public Map<Long, Card> loadFile(Path path) throws IOException, 
	IncorrectPinException, ExpiredCardException {
		FileReader file = new FileReader(path.toString());
		String line;
		try (BufferedReader in = new BufferedReader(file)){
			while((line = in.readLine()) != null){
			    this.attributes.add(this.makeAttributes(line));
			}
		}
		this.makeCards();
		return this.cards;
	}
	
	/** Saves all the cards from a Map type data into a 
	 * file in the path.
	 * @param path
	 * @param map
	 * @throws FileNotFoundException
	 */
	public void saveFile(Path path, Map <Long, Card> map) throws FileNotFoundException{
		this.cards = map;
		String file = new File(path.toString()).toString();
		PrintWriter writer = new PrintWriter(file);
		for (Map.Entry<Long, Card> entry :this.cards.entrySet()) {
			String card = entry.getValue().toString();
		    writer.write(card.replace(" ", "@"));
		    writer.write("\n");
		}
		writer.close();
	}
	
	@SuppressWarnings("rawtypes")
	private List<?> makeAttributes(String line) {
		@SuppressWarnings("unchecked")
		List<String> list = new ArrayList();
		String[] parts = line.split("@");
		for (int i = 0; i < parts.length; i++) {
			list.add(parts[i]);
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	private void makeCards() throws IncorrectPinException, ExpiredCardException{
		List list;
		while ((list = this.attributes.poll()) != null) {
			Card card = new Card(Long.parseLong(list.get(0).toString()), 
					list.get(1).toString(), list.get(2).toString(), 
					list.get(3).toString(), Float.parseFloat(list.get(4).toString()), 
					list.get(5).toString());
			this.cards.put(card.getNumber(), card);
		}
	}
}

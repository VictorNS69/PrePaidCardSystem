package es.upm.pproject.tdd.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SaveFile {
	private Map <Long, Card> cards = new HashMap <>();
	
	/** Saves all the cards from a Map type data into a 
	 * file in the path.
	 * @param path
	 * @param map
	 * @throws FileNotFoundException
	 */
	public SaveFile(Path path, Map <Long, Card> map) throws FileNotFoundException{
		this.cards = map;
		String file = new File(path.toString()).toString();
		PrintWriter writer = new PrintWriter(file);
		for (Map.Entry<Long, Card> entry :this.cards.entrySet()) {
			String card = entry.getValue().toString();
		    writer.write(card.replace(" ", "@"));
		}
		writer.close();
	}
}

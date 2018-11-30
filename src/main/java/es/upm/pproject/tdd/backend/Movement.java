package es.upm.pproject.tdd.backend;

import java.text.SimpleDateFormat;
import java.util.*;

public class Movement {
	private Calendar date;
	private float amount;
	
	public Movement (float amount) {
		this.date = Calendar.getInstance();
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		Date date = this.date.getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' HH:mm:ss");
		return ft.format(date)+"    "+ this.amount;
	}
}

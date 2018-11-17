package es.upm.pproject.tdd;

import java.text.SimpleDateFormat;
import java.util.*;

public class Movement {
	private Calendar date;
	private float amount;
	private SimpleDateFormat ft;
	
	public Movement (float amount) {
		this.date = Calendar.getInstance();
		this.amount = amount;
		this.ft = new SimpleDateFormat ("E dd.MM.yyyy 'at' hh:mm:ss");
	}
	
	public String toString() {
		return this.ft.format(date)+"    "+ this.amount;
	}
}

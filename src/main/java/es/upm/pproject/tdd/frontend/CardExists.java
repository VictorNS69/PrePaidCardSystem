package es.upm.pproject.tdd.frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import es.upm.pproject.tdd.backend.*;
import es.upm.pproject.tdd.exceptions.*;

public class CardExists extends JFrame {
	JButton ok;
	JTextField fieldCard, fieldPin;
	private Map <Long, Card> cards = new HashMap <>();
	
	public void centre() {
		int ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int alto=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setSize(500,500);
		setLocation((ancho/2)-(this.getWidth()/2),(alto/2)-(this.getHeight()/2));
	}
	
public CardExists() {
	//super("CardExists");

	setLayout(new GridLayout(0,1));
	add(new JLabel("Card Number: "));
	fieldCard = new JTextField(5);
	add(fieldCard);
	add(new JLabel("Pin Card: "));
	fieldPin = new JTextField(5);
	add(fieldPin);

	ok = new JButton("OK");
	add(ok);
	ok.addActionListener(new Handler());
	centre();
	setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}

class Handler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String card = fieldCard.getText();
	}
}

}
package es.upm.pproject.tdd.frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import es.upm.pproject.tdd.backend.*;
import es.upm.pproject.tdd.exceptions.*;
import es.upm.pproject.tdd.frontend.NewCardScreen.Handler;

public class CardExists extends JFrame {
	JButton ok, back;
	JTextField fieldCard;
	JPasswordField fieldPin;
	String error;
	public void centreWindow(){
        int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((ancho/2)-(getWidth()/2),(alto/2)-(getHeight()/2),600,350);
        setLocation((ancho/2)-(getWidth()/2),(alto/2)-(getHeight()/2));
    }
	
public CardExists() {
	super("Card Exists");
	back = new JButton("Go back");
    ok = new JButton("Ok");
    fieldCard = new JTextField(5);
	fieldPin = new JPasswordField(5);
	add(new JLabel("Card Number "));
	add(fieldCard);
	add(new JLabel("Pin Card "));
	add(fieldPin);
	add(back);
	add(ok);
	centreWindow();
    ok.addActionListener(new Handler());
    setLayout(new GridLayout(4,2));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
}



class Handler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String card = fieldCard.getText();
		if(card.length()!=12)
		JOptionPane.showMessageDialog(new JFrame(), "Error: Card Invalid", "Dialog", JOptionPane.ERROR_MESSAGE);
		char[] pass = fieldPin.getPassword();
		String password="";
		for (int i=0;i<pass.length;i++)
		password+= pass[i];
		HashPin code = null;
		try {
			code = new HashPin(password);
		} catch (IncorrectPinFormatException e1) {
			// TODO Auto-generated catch block
			error= e1.getMessage();
			JOptionPane.showMessageDialog(new JFrame(), error, "Dialog", JOptionPane.ERROR_MESSAGE);
		}
		password= code.getHashPin();
		
	}
}

}
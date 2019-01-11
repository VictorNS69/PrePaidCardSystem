package es.upm.pproject.tdd.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.*;
import es.upm.pproject.tdd.backend.*;
import es.upm.pproject.tdd.exceptions.*;

public class MainWindow extends JFrame {
	
	// controller
	private ViewController controller;
	
	// logger stuff
	private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
	private static final String ERR = "Error";

	// main panel
	private JPanel contentPanel;
	
	// button stuff
	private JButton buyNewCardB = new JButton("Buy New Card");
	private JButton payB = new JButton("Pay");
	private JButton chargeMoneyB = new JButton("Charge Money");
	private JButton changePinB = new JButton("Change Pin");
	private JButton consultBalanceB = new JButton("Consult Balance");
	private JButton consultMovementsB = new JButton("Consult Movements");
	private JButton exitB = new JButton("Exit");
	private JButton goBackB = new JButton("Go Back");
	private JButton okB = new JButton("OK");
	
	// action listeners
	private ActionListener okA;

	// text fields and passwords
	private JTextField nameT = new JTextField();
	private JTextField surnameT = new JTextField();
	private JTextField amountT = new JTextField();
	private JPasswordField pinP = new JPasswordField();
	private JPasswordField newPinP = new JPasswordField();
	private JPasswordField confirmPinP = new JPasswordField();
	private JTextField cardNumberT = new JTextField();
	
	// labels
	private JLabel nameL = new JLabel("Name");
	private JLabel surnameL = new JLabel("Surname");
	private JLabel amountL = new JLabel("Amount");
	private JLabel pinL = new JLabel("PIN");
	private JLabel confirmPinL = new JLabel("Confirm PIN");
	private JLabel newPinL = new JLabel("New PIN");
	private JLabel cardNumberL = new JLabel("Card number");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					LOGGER.log(Level.INFO, ERR, e);				}
			}
		});
	}

	
	/**
	 * This main is going to be exclusively focused on putting settings on the window.
	 * It will load the data, bring up the buttons and other elements in an invisible stay.
	 * It will call the main function to initialize the application
	 * @throws ExpiredCardException
	 * @throws IOException
	 * @throws IncorrectPinException
	 */
	public MainWindow() throws ExpiredCardException, IOException, IncorrectPinException {
		setTitle("PrePaid System");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(700, 480);
		this.setResizable(false);
		Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((window.width / 2)-(this.getWidth() / 2),(window.height/2)-(this.getHeight()/2));
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		controller = new ViewController(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent x) {
				controller.close();
			}
		});
		// we load the file with the saved cards
		
		// buttons 
		contentPanel.add(buyNewCardB);
		contentPanel.add(payB);
		contentPanel.add(changePinB);
		contentPanel.add(consultBalanceB);
		contentPanel.add(chargeMoneyB);
		contentPanel.add(consultMovementsB);
		contentPanel.add(exitB);
		contentPanel.add(goBackB);
		contentPanel.add(okB);
		
		// text fields
		contentPanel.add(nameT);
		contentPanel.add(surnameT);
		contentPanel.add(amountT);
		contentPanel.add(pinP);
		contentPanel.add(newPinP);
		contentPanel.add(confirmPinP);
		contentPanel.add(cardNumberT);
		
		// labels
		contentPanel.add(nameL);
		contentPanel.add(surnameL);
		contentPanel.add(amountL);
		contentPanel.add(pinL);
		contentPanel.add(confirmPinL);
		contentPanel.add(newPinL);
		contentPanel.add(cardNumberL);


		// button positions
		buyNewCardB.setBounds(80, 50, 250, 70);
		changePinB.setBounds(370, 50, 250, 70);
		payB.setBounds(80, 140, 250, 70);
		consultBalanceB.setBounds(370, 140, 250, 70);
		chargeMoneyB.setBounds(80, 230, 250, 70);
		consultMovementsB.setBounds(370, 230, 250, 70);
		exitB.setBounds(225, 340, 250, 70);
		goBackB.setBounds(105, 340, 200, 70);
		okB.setBounds(395, 340, 200, 70);

		// everything invisible
		buyNewCardB.setVisible(false);
		changePinB.setVisible(false);
		payB.setVisible(false);
		consultBalanceB.setVisible(false);
		chargeMoneyB.setVisible(false);
		consultMovementsB.setVisible(false);
		exitB.setVisible(false);
		goBackB.setVisible(false);
		okB.setVisible(false);
		nameT.setVisible(false);
		surnameT.setVisible(false);
		amountT.setVisible(false);
		pinP.setVisible(false);
		newPinP.setVisible(false);
		confirmPinP.setVisible(false);
		cardNumberT.setVisible(false);
		nameL.setVisible(false);
		surnameL.setVisible(false);
		amountL.setVisible(false);
		pinL.setVisible(false);
		confirmPinL.setVisible(false);
		cardNumberL.setVisible(false);
		
		// we load the data from the file
		controller.loadData();
		
		// action for the buyCard button
		buyNewCardB.addActionListener(event -> {
				mainButtonsVisible(false);
				buyNewCard();
		});
		// action for the pay button
		payB.addActionListener(event -> {
				mainButtonsVisible(false);
				pay();
		});
		// action for the changePin button
		changePinB.addActionListener(event -> {
				mainButtonsVisible(false);
				changePin();
		});
		// action for the consultBalance button
		consultBalanceB.addActionListener(event -> {
				mainButtonsVisible(false);
				consultBalance();
		});
		//accion boton chargeMoneyB
		chargeMoneyB.addActionListener(event -> {
				mainButtonsVisible(false);
				chargeMoney();
		});
		// action for the consultMovements button
		consultMovementsB.addActionListener(event -> {
				mainButtonsVisible(false);
				consultMovements();
		});
		// action for the exit button
		exitB.addActionListener(event -> {
				controller.close();
		});
		// action for the goBack button
		goBackB.addActionListener(event -> {
				goBackB.setVisible(false);
				okB.setVisible(false);
				nameT.setVisible(false);
				surnameT.setVisible(false);
				amountT.setVisible(false);
				pinP.setVisible(false);
				confirmPinP.setVisible(false);
				nameL.setVisible(false);
				surnameL.setVisible(false);
				amountL.setVisible(false);
				pinL.setVisible(false);
				confirmPinL.setVisible(false);
				newPinL.setVisible(false);
				cardNumberL.setVisible(false);
				cardNumberT.setVisible(false);
				newPinP.setVisible(false);
				
				nameT.setText("");
				surnameT.setText("");
				amountT.setText("");
				cardNumberT.setText("");
				pinP.setText("");
				confirmPinP.setText("");
				newPinP.setText("");
				
				okB.removeActionListener(okA);
				
				start();
		});
		start();
	}

	// starting function for the main screen
	public void start() {
		setTitle("Prepaid Card System");
		mainButtonsVisible(true);	
	}

	public void buyNewCard() {
		setTitle("New Card");
		nameT.setBounds(80, 50, 250, 70);
		nameL.setBounds(80, 5, 250, 70);
		surnameT.setBounds(370, 50, 250, 70);
		surnameL.setBounds(370, 5, 250, 70);
		amountT.setBounds(80, 140, 250, 70);
		amountL.setBounds(80, 95, 250, 70);
		pinP.setBounds(80, 230, 250, 70);
		pinL.setBounds(80, 185, 250, 70);
		confirmPinP.setBounds(370, 230, 250, 70);
		confirmPinL.setBounds(370, 185, 250, 70);
		
		goBackB.setVisible(true);
		okB.setVisible(true);
		nameT.setVisible(true);
		surnameT.setVisible(true);
		amountT.setVisible(true);
		pinP.setVisible(true);
		confirmPinP.setVisible(true);
		nameL.setVisible(true);
		surnameL.setVisible(true);
		amountL.setVisible(true);
		pinL.setVisible(true);
		confirmPinL.setVisible(true);
		
		
		okB.addActionListener(okA = event -> {
				String name = nameT.getText();
				String surname = surnameT.getText();
				String amount = amountT.getText();
				String pin = new String(pinP.getPassword());
				String confirmPin = new String(confirmPinP.getPassword());
				controller.buyNewCard(name, surname, amount, pin, confirmPin);
		});
	}
	
	public void changePin() {
		setTitle("Change PIN");
		cardNumberT.setBounds(80, 50, 250, 70);
		cardNumberL.setBounds(80, 5, 250, 70);
		pinP.setBounds(370, 50, 250, 70);
		pinL.setBounds(370, 5, 250, 70);
		newPinP.setBounds(80, 230, 250, 70);
		newPinL.setBounds(80, 185, 250, 70);
		confirmPinP.setBounds(370, 230, 250, 70);
		confirmPinL.setBounds(370, 185, 250, 70);
		
		cardNumberT.setVisible(true);
		pinP.setVisible(true);
		newPinP.setVisible(true);
		confirmPinP.setVisible(true);
		goBackB.setVisible(true);
		okB.setVisible(true);
		cardNumberL.setVisible(true);
		pinL.setVisible(true);
		newPinL.setVisible(true);
		confirmPinL.setVisible(true);
		
		okB.addActionListener(okA = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cardNumber = cardNumberT.getText();
				String pin = new String(pinP.getPassword());
				String newPin = new String(newPinP.getPassword());
				String confirmPin = new String(confirmPinP.getPassword());
				controller.changePin(cardNumber, pin, confirmPin, newPin);
			}
		});
	}
	
	public void pay() {
		setTitle("Pay");
		
		cardNumberT.setBounds(80, 50, 250, 70);
		cardNumberL.setBounds(80, 5, 250, 70);
		pinP.setBounds(370, 50, 250, 70);
		pinL.setBounds(370, 5, 250, 70);
		amountT.setBounds(80, 230, 250, 70);
		amountL.setBounds(80, 185, 250, 70);
		
		cardNumberT.setVisible(true);
		cardNumberL.setVisible(true);
		amountT.setVisible(true);
		amountL.setVisible(true);
		pinP.setVisible(true);
		pinL.setVisible(true);
		goBackB.setVisible(true);
		okB.setVisible(true);
		okB.addActionListener(okA = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amount = amountT.getText();
				String pin = new String(pinP.getPassword());
				String cardNumber = cardNumberT.getText();
				controller.pay(amount, pin, cardNumber);
			}
		});
	}
	
	public void consultBalance() {
		setTitle("Consult balance");
		
		cardNumberT.setBounds(80, 140, 250, 70);
		cardNumberL.setBounds(80, 95, 250, 70);
		pinP.setBounds(370, 140, 250, 70);
		pinL.setBounds(370, 95, 250, 70);
		cardNumberT.setVisible(true);
		cardNumberL.setVisible(true);
		pinP.setVisible(true);
		pinL.setVisible(true);
		goBackB.setVisible(true);
		okB.setVisible(true);
		okB.addActionListener(okA = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pin = new String(pinP.getPassword());
				String cardNumber = cardNumberT.getText();
				controller.consultBalance(pin, cardNumber);
			}
		});
	}

	public void consultMovements() {
		setTitle("Consult movements");
		cardNumberT.setBounds(80, 140, 250, 70);
		cardNumberL.setBounds(80, 95, 250, 70);
		pinP.setBounds(370, 140, 250, 70);
		pinL.setBounds(370, 95, 250, 70);
		
		cardNumberT.setVisible(true);
		cardNumberL.setVisible(true);
		pinP.setVisible(true);
		pinL.setVisible(true);
		goBackB.setVisible(true);
		okB.setVisible(true);
		
		okB.addActionListener(okA = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cardNumber = cardNumberT.getText();
				String pin = new String(pinP.getPassword());
				controller.consultMovements(pin, cardNumber);
			}
		});
	}

	public void chargeMoney() {
		setTitle("Charge money");
		cardNumberT.setBounds(80, 50, 250, 70);
		cardNumberL.setBounds(80, 5, 250, 70);
		pinP.setBounds(370, 50, 250, 70);
		pinL.setBounds(370, 5, 250, 70);
		amountT.setBounds(80, 230, 250, 70);
		amountL.setBounds(80, 185, 250, 70);
		
		cardNumberT.setVisible(true);
		cardNumberL.setVisible(true);
		amountT.setVisible(true);
		amountL.setVisible(true);
		pinP.setVisible(true);
		pinL.setVisible(true);
		goBackB.setVisible(true);
		okB.setVisible(true);
		okB.addActionListener(okA = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amount = amountT.getText();
				String pin = new String(pinP.getPassword());
				String cardNumber = cardNumberT.getText();
				controller.chargeMoney(amount, pin, cardNumber);
			}
		});
	}

	private void mainButtonsVisible (boolean option) {
		buyNewCardB.setVisible(option);
		changePinB.setVisible(option);
		payB.setVisible(option);
		consultBalanceB.setVisible(option);
		chargeMoneyB.setVisible(option);
		consultMovementsB.setVisible(option);
		exitB.setVisible(option);
	}
	public void showErrorWindow(String msg) {
		JOptionPane.showMessageDialog(contentPanel, msg, ERR,
				JOptionPane.ERROR_MESSAGE);
		LOGGER.log(Level.INFO, ERR, msg);
	}
	public void showDialog(String msg, String title) {
		JOptionPane.showMessageDialog(contentPanel, msg, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
}


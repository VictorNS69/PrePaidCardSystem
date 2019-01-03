package es.upm.pproject.tdd.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.*;
import es.upm.pproject.tdd.backend.*;
import es.upm.pproject.tdd.exceptions.*;

public class MainFrontend extends JFrame {

	// logger stuff
	private final static Logger LOGGER = Logger.getLogger(MainFrontend.class.getName());
	private final static String ERR = "Error";
	
	// data and backend stuff
	private Card  actualCard;
	private final Path path = FileSystems.getDefault().getPath("src/assets/data.dat").toAbsolutePath();
	private Map<Long, Card> map = new HashMap<>();
	private FileOperations fileops = new FileOperations();
	private CardOperations ops;

	// main panel
	private JPanel contentPanel;
	
	// buttons
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
	private ActionListener goBackA;
	
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
					MainFrontend frame = new MainFrontend();
					frame.setVisible(true);
				} catch (Exception e) {
					LOGGER.log(Level.INFO, ERR, e);				}
			}
		});
	}

	
	/**
	 * This main is going to be exclusively focused on putting settings on the window.
	 * It will load the data, bring up the buttons and other elements in an invisible stay.
	 * It will call the main function to initialize the aplication
	 * @throws ExpiredCardException
	 * @throws IOException
	 * @throws IncorrectPinException
	 */
	public MainFrontend() throws ExpiredCardException, IOException, IncorrectPinException {
		setTitle("PrePaid System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 480);
		this.setResizable(false);
		Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((window.width / 2)-(this.getWidth() / 2),(window.height/2)-(this.getHeight()/2));
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		// we load the file with the saved cards
		try {
			map = fileops.loadFile(path);
			ops = new CardOperations(map);
		} catch (ExpiredCardException | IOException | IncorrectPinException e) {
			LOGGER.log(Level.INFO, ERR, e);
		}
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
		
		// action for the buyCard button
		buyNewCardB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainButtonsVisible(false);
				buyNewCard();
			}
		});
		// action for the pay button
		payB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainButtonsVisible(false);
				pay();
			}
		});
		// action for the changePin button
		changePinB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainButtonsVisible(false);
				changePin();
			}
		});
		// action for the consultBalance button
		consultBalanceB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainButtonsVisible(false);
				consultBalance();
			}
		});
		//accion boton chargeMoneyB
		chargeMoneyB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainButtonsVisible(false);
				chargeMoney();
			}
		});
		// action for the consultMovements button
		consultMovementsB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainButtonsVisible(false);
				consultMovements();
			}
		});
		// action for the exit button
		exitB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		// action for the goBack button
		goBackB.addActionListener(goBackA = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			}
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
		
		
		okB.addActionListener(okA = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = nameT.getText();
				String surname = surnameT.getText();
				String amount = amountT.getText();
				String pin = new String(pinP.getPassword());
				String confirmPin = new String(confirmPinP.getPassword());
				if(name.isEmpty()||surname.isEmpty()||amount.isEmpty()||pin.isEmpty()||confirmPin.isEmpty())
					JOptionPane.showMessageDialog(contentPanel, "There is a field that is empty", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				else if (!Pattern.matches("^[\\p{L} .-]+$", name)|| !Pattern.matches("^[\\p{L} .-]+$", surname))
					JOptionPane.showMessageDialog(contentPanel, "The name and surname can only contain letters", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if(!Pattern.matches("[0-9]+(\\.[0-9]{1,2})?$", amount)) 
					JOptionPane.showMessageDialog(contentPanel, "The amount can only contain positive numbers with two decimals", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if(pin.length()!=4)
					JOptionPane.showMessageDialog(contentPanel, "The size of the pin should be four digits", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if(!Pattern.matches("[0-9]+", pin))
					JOptionPane.showMessageDialog(contentPanel, "The password can only have digits", ERR,
							JOptionPane.ERROR_MESSAGE);				
				else if(!(pin.equals(confirmPin)))
					JOptionPane.showMessageDialog(contentPanel, "Both pin fields must match", ERR,
							JOptionPane.ERROR_MESSAGE);
				else {
					try {
						long cardNumber = ops.buyCard(name, surname, pin, Double.valueOf(amount));
						JOptionPane.showMessageDialog(contentPanel, "Dear "+name+" "+surname+
								", your card has been successfully created.\nAMOUNT: "+amount+
								"€\nCARD NUMBER: "+cardNumber+"\nBALANCE: "+amount+"€\nThanks for using our system!",
								"Success!", JOptionPane.INFORMATION_MESSAGE);
					} 
					catch (NumberFormatException | AlreadyRegisteredException | IncorrectPinException |
							IncorrectPinFormatException | ExpiredCardException | InvalidAmountException e) {
						JOptionPane.showMessageDialog(contentPanel, e.getMessage(),
								ERR, JOptionPane.ERROR_MESSAGE);
						LOGGER.log(Level.INFO, ERR, e);
					}
				}
			}
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
				if(cardNumber.isEmpty()||pin.isEmpty()||newPin.isEmpty()||confirmPin.isEmpty())
					JOptionPane.showMessageDialog(contentPanel, "There is a field that is empty", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				else if(cardNumber.length()!=12)
					JOptionPane.showMessageDialog(contentPanel, "The card number must have 12 digits", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if (!Pattern.matches("[0-9]+", cardNumber))
					JOptionPane.showMessageDialog(contentPanel, "The card number can only have digits", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if(pin.length()!=4||newPin.length()!=4)
					JOptionPane.showMessageDialog(contentPanel, "The size of the pin should be four digits", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if(!Pattern.matches("[0-9]+", pin)||!Pattern.matches("[0-9]+", newPin))
					JOptionPane.showMessageDialog(contentPanel, "The password can only have digits", ERR,
							JOptionPane.ERROR_MESSAGE);				
				else if(!(newPin.equals(confirmPin)))
					JOptionPane.showMessageDialog(contentPanel, "New Pin and Confirm Pin fields must match", ERR,
							JOptionPane.ERROR_MESSAGE);
				else {
				actualCard = ops.getCard(Long.valueOf(cardNumberT.getText()));
				try {
					ops.changePIN(Long.valueOf(cardNumber), pin, newPin);
					JOptionPane.showMessageDialog(contentPanel, "Dear "+actualCard.getName()+" "+actualCard.getSurname()+
							"\nPIN CHANGED SUCESSFULY\nThanks for using our system",
							"Success!", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (NumberFormatException | NotRegisteredException | IncorrectPinException | ExpiredCardException
						| IncorrectPinFormatException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(),
							ERR, JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.INFO, ERR, e);
				}
				}
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
				
				if(cardNumber.isEmpty()||amount.isEmpty()||pin.isEmpty())
					JOptionPane.showMessageDialog(contentPanel, "There is a field that is empty", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				else if (cardNumber.length()!=12)
					JOptionPane.showMessageDialog(contentPanel, "The card number must have 12 digits", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if (!Pattern.matches("[0-9]+", cardNumber))
					JOptionPane.showMessageDialog(contentPanel, "The card number can only have digits", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if(!Pattern.matches("[0-9]+(\\.[0-9]{1,2})?$", amount)) 
					JOptionPane.showMessageDialog(contentPanel, "The amount can only contain positive numbers with two decimals", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if(pin.length()!=4 )
					JOptionPane.showMessageDialog(contentPanel, "The size of the pin should be four digits", ERR,
							JOptionPane.ERROR_MESSAGE);
				else if(!Pattern.matches("[0-9]+", pin))
					JOptionPane.showMessageDialog(contentPanel, "The password can only have digits", ERR,
							JOptionPane.ERROR_MESSAGE);				
				else {
						actualCard = ops.getCard(Long.valueOf(cardNumber));
						try {
							ops.pay(Long.valueOf(cardNumber), Double.valueOf(amount), pin);
							JOptionPane.showMessageDialog(contentPanel, "Dear "+actualCard.getName()+" "+actualCard.getSurname()+
									"\nCARD NUMBER: "+cardNumber+"\nBALANCE: "+actualCard.getBalance()+"€\nThanks for using our system!",
									"Success!", JOptionPane.INFORMATION_MESSAGE);
						} catch (NumberFormatException | NotRegisteredException | IncorrectPinException
								| NotEnoughMoneyException | ExpiredCardException | IncorrectPinFormatException
								| InvalidAmountException | InvalidMovementException e) {
							JOptionPane.showMessageDialog(contentPanel, e.getMessage(),
									ERR, JOptionPane.ERROR_MESSAGE);
							LOGGER.log(Level.INFO, ERR, e);
						}		
				}
			}	
		});
	}
	
	public void consultBalance() {
		setTitle("Consult balance");

		// TODO
	}

	public void consultMovements() {
		setTitle("Consult movements");

		// TODO
	}

	public void chargeMoney() {
		setTitle("Charge money");

		// TODO
	}

	public void mainButtonsVisible (boolean option) {
		buyNewCardB.setVisible(option);
		changePinB.setVisible(option);
		payB.setVisible(option);
		consultBalanceB.setVisible(option);
		chargeMoneyB.setVisible(option);
		consultMovementsB.setVisible(option);
		exitB.setVisible(option);
	}
}

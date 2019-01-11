package es.upm.pproject.tdd.frontend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import es.upm.pproject.tdd.backend.*;
import es.upm.pproject.tdd.exceptions.AlreadyRegisteredException;
import es.upm.pproject.tdd.exceptions.ExpiredCardException;
import es.upm.pproject.tdd.exceptions.IncorrectPinException;
import es.upm.pproject.tdd.exceptions.IncorrectPinFormatException;
import es.upm.pproject.tdd.exceptions.InvalidAmountException;
import es.upm.pproject.tdd.exceptions.InvalidMovementException;
import es.upm.pproject.tdd.exceptions.NotEnoughMoneyException;
import es.upm.pproject.tdd.exceptions.NotRegisteredException;

public class ViewController {
	
	private MainWindow view;
	private FileOperations fileops;
	private CardOperations ops;
	private final Path path;
	private Map<Long, Card> map;
	private Card actualCard;
	private DecimalFormat df;
	
	public ViewController(MainWindow frame) {
		view = frame;
		fileops = new FileOperations();	
		path = FileSystems.getDefault().getPath("src/assets/data.dat").toAbsolutePath();
		map = new HashMap<>();
		df = new DecimalFormat("#.00");
	}
	
	public void loadData() {
		try {
			map = fileops.loadFile(path);
			ops = new CardOperations(map);
		} catch (ExpiredCardException | IOException | IncorrectPinException e) {
			view.showErrorWindow(e.getMessage());
		  }
	}
	
	public void buyNewCard(String name, String surname, String amount, String pin, String confirmPin) {
		if(name.isEmpty()||surname.isEmpty()||amount.isEmpty()||pin.isEmpty()||confirmPin.isEmpty())
			view.showErrorWindow("There is a field that is empty");
		else if (!Pattern.matches("^[\\p{L} .-]+$", name)|| !Pattern.matches("^[\\p{L} .-]+$", surname))
			view.showErrorWindow("The name and surname can only contain letters");
		else if(!Pattern.matches("[0-9]+(\\.[0-9]{1,2})?$", amount)) 
			view.showErrorWindow("The amount can only contain positive numbers with two decimals");
		else if(pin.length()!=4)
			view.showErrorWindow("The size of the pin should be four digits");
		else if(!Pattern.matches("[0-9]+", pin))
			view.showErrorWindow("The password can only have digits");
		else if(!(pin.equals(confirmPin)))
			view.showErrorWindow("Both pin fields must match");
		else {
			try {
				double amountD = Double.parseDouble(amount);
				ops = new CardOperations(map);
				long cardNumber = ops.buyCard(name, surname, pin, Double.valueOf(amount));
				String s = "Dear "+name+" "+surname+", your card has been successfully created."
						+"\nAMOUNT: "+df.format(amountD)
						+"€\nCARD NUMBER: "+cardNumber
						+"\nBALANCE: "+df.format(amountD)
						+"€\nThanks for using our system!";
				view.showDialog(s, "Success!");
			} 
			catch (NumberFormatException | AlreadyRegisteredException | IncorrectPinException |
					IncorrectPinFormatException | ExpiredCardException | InvalidAmountException e) {
				view.showErrorWindow(e.getMessage());
			}
		}
	}
	
	public void changePin(String cardNumber, String pin, String confirmPin, String newPin) {
		if(cardNumber.isEmpty()||pin.isEmpty()||newPin.isEmpty()||confirmPin.isEmpty())
			view.showErrorWindow("There is a field that is empty");
		else if(cardNumber.length()!=12)
			view.showErrorWindow("The card number must have 12 digits");
		else if (!Pattern.matches("[0-9]+", cardNumber))
			view.showErrorWindow("The card number can only have digits");
		else if(pin.length()!=4||newPin.length()!=4)
			view.showErrorWindow("The size of the pin should be four digits");
		else if(!Pattern.matches("[0-9]+", pin)||!Pattern.matches("[0-9]+", newPin))
			view.showErrorWindow("The password can only have digits");				
		else if(!(newPin.equals(confirmPin)))
			view.showErrorWindow("Both pin fields must match");
		else {
		actualCard = ops.getCard(Long.valueOf(cardNumber));
		try {
			ops.changePIN(Long.valueOf(cardNumber), pin, newPin);
			view.showDialog(
					"Dear "+actualCard.getName()+" "+actualCard.getSurname()+
					"\nPIN CHANGED SUCCESSFULLY"
					+"\nThanks for using our system!",
					"Success!");
		} catch (NumberFormatException | NotRegisteredException | IncorrectPinException | ExpiredCardException
				| IncorrectPinFormatException e) {
			view.showErrorWindow(e.getMessage());
		  }
		}
	}
	
	public void pay(String amount, String pin, String cardNumber) {
		if(cardNumber.isEmpty()||amount.isEmpty()||pin.isEmpty())
			view.showErrorWindow("There is a field that is empty");
		else if (cardNumber.length()!=12)
			view.showErrorWindow("The card number must have 12 digits");
		else if (!Pattern.matches("[0-9]+", cardNumber))
			view.showErrorWindow("The card number can only have digits");
		else if(!Pattern.matches("[0-9]+(\\.[0-9]{1,2})?$", amount)) 
			view.showErrorWindow("The amount can only contain positive numbers with two decimals");
		else if(pin.length()!=4 )
			view.showErrorWindow("The size of the pin should be four digits");
		else if(!Pattern.matches("[0-9]+", pin))
			view.showErrorWindow("The password can only have digits");				
		else {
				actualCard = ops.getCard(Long.valueOf(cardNumber));
				try {
					ops.pay(Long.valueOf(cardNumber), Double.valueOf(amount), pin);
					char [] buf= new char[4];
					cardNumber.getChars(8, 12, buf, 0);
					cardNumber= "XXXXXXXX"+new String(buf);
					view.showDialog( 
							"Dear "+actualCard.getName()+" "+actualCard.getSurname()+
							"\nCARD NUMBER: "+cardNumber
							+"\nBALANCE: "+df.format(actualCard.getBalance())
							+"€\nThanks for using our system!",
							"Success!");
				} catch (NumberFormatException | NotRegisteredException | IncorrectPinException
						| NotEnoughMoneyException | IncorrectPinFormatException
						| InvalidAmountException | InvalidMovementException e) {
					view.showErrorWindow(e.getMessage());
				  }		
		}
	}
	
	public void consultBalance(String pin, String cardNumber) {
		if(cardNumber.isEmpty()||pin.isEmpty())
			view.showErrorWindow("There is a field that is empty");
		else if (cardNumber.length()!=12)
			view.showErrorWindow("The card number must have 12 digits");
		else if (!Pattern.matches("[0-9]+", cardNumber))
			view.showErrorWindow("The card number can only have digits");
		else if(pin.length()!=4 )
			view.showErrorWindow("The size of the pin should be four digits");
		else if(!Pattern.matches("[0-9]+", pin))
			view.showErrorWindow("The password can only have digits");	
		else {
			actualCard = ops.getCard(Long.valueOf(cardNumber));
			try {
				
				double balance=ops.consultBalance(Long.valueOf(cardNumber), pin);
				char [] buf= new char[4];
				cardNumber.getChars(8, 12, buf, 0);
				cardNumber= "XXXXXXXX"+new String(buf);
				view.showDialog(
						"Dear "+actualCard.getName()+" "+actualCard.getSurname()
						+"€\nCARD NUMBER: "+cardNumber
						+"\nBALANCE: "+df.format(balance)
						+"€\nThanks for using our system!",
						"Success!");
			} catch (NumberFormatException | NotRegisteredException | IncorrectPinException
					 | IncorrectPinFormatException e) {
				view.showErrorWindow(e.getMessage());
			}
		}
	}
	
	public void consultMovements(String pin, String cardNumber) {
		if(cardNumber.isEmpty()||pin.isEmpty())
			view.showErrorWindow("There is a field that is empty");
		else if (cardNumber.length()!=12)
			view.showErrorWindow("The card number must have 12 digits");
		else if (!Pattern.matches("[0-9]+", cardNumber))
			view.showErrorWindow("The card number can only have digits");
		else if(pin.length()!=4 )
			view.showErrorWindow("The size of the pin should be four digits");
		else if(!Pattern.matches("[0-9]+", pin))
			view.showErrorWindow("The password can only have digits");	
		else {
			actualCard = ops.getCard(Long.valueOf(cardNumber));
			try {
				List<Movement> mvmnt = ops.consultMovements(actualCard.getNumber(), pin);
				String movs = "";
				for (Movement m : mvmnt) {
					movs = movs + m.toString() + "\n";
				}
				char [] buf= new char[4];
				cardNumber.getChars(8, 12, buf, 0);
				cardNumber= "XXXXXXXX"+new String(buf);
				view.showDialog( 
						"Dear "+actualCard.getName()+" "+actualCard.getSurname()
						+"\nCARD NUMBER: "+cardNumber
						+"\nBALANCE: "+df.format(actualCard.getBalance())
						+"€\nMOVEMENTS: \n"+movs
						+"\nThanks for using our system!",
						"Success!");
			} catch (NotRegisteredException | IncorrectPinException | IncorrectPinFormatException e) {
				view.showErrorWindow(e.getMessage());
			}
		}
	}
	
	public void chargeMoney(String amount, String pin, String cardNumber) {
		if(cardNumber.isEmpty()||amount.isEmpty()||pin.isEmpty())
			view.showErrorWindow("There is a field that is empty");
		else if (cardNumber.length()!=12)
			view.showErrorWindow("The card number must have 12 digits");
		else if (!Pattern.matches("[0-9]+", cardNumber))
			view.showErrorWindow("The card number can only have digits");
		else if(!Pattern.matches("[0-9]+(\\.[0-9]{1,2})?$", amount)) 
			view.showErrorWindow("The amount can only contain positive numbers with two decimals");
		else if(pin.length()!=4 )
			view.showErrorWindow("The size of the pin should be four digits");
		else if(!Pattern.matches("[0-9]+", pin))
			view.showErrorWindow("The password can only have digits");				
		else {
				actualCard = ops.getCard(Long.valueOf(cardNumber));
				try {
					ops.chargeMoney(Long.valueOf(cardNumber), Double.valueOf(amount), pin);
					char [] buf= new char[4];
					cardNumber.getChars(8, 12, buf, 0);
					cardNumber= "XXXXXXXX"+new String(buf);
					view.showDialog( 
							"Dear "+actualCard.getName()+" "+actualCard.getSurname()+
							"\nCARD NUMBER: "+cardNumber
							+"\nBALANCE: "+df.format(actualCard.getBalance())
							+"€\nThanks for using our system!",
							"Success!");
				} catch (NumberFormatException | NotRegisteredException | IncorrectPinException | IncorrectPinFormatException
						| InvalidAmountException | InvalidMovementException e) {
					view.showErrorWindow(e.getMessage());
				}		
		}
	}	

	public void close() {
		try {
			fileops.saveFile(path, map);
		} catch (FileNotFoundException e) {
			view.showErrorWindow(e.getMessage());
		}
		System.exit(0);
	}
}


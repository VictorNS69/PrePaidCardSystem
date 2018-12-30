package es.upm.pproject.tdd.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.sql.Time;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import es.upm.pproject.tdd.backend.*;
import es.upm.pproject.tdd.exceptions.*;

public class MainFrontend extends JFrame {

	// apartado carga de datos
	Card card;
	private final Path path = FileSystems.getDefault().getPath("src/assets/data.dat").toAbsolutePath();
	private Map<Long, Card> map = new HashMap<>();

	// apartado botones y demas
	private JPanel contentPanel;
	// dejo los botones ya inicializados para asi solo tener que hacerlos visibles o
	// no.
	private JButton buyNewCardB = new JButton("Buy New Card");
	private JButton payB = new JButton("Pay");
	private JButton chargeMoneyB = new JButton("Charge Money");
	private JButton changePinB = new JButton("Change Pin");
	private JButton consultBalanceB = new JButton("Consult Balance");
	private JButton consultMovementsB = new JButton("Consult Movements");
	private JButton exitB = new JButton("Exit");
	private JButton goBackB = new JButton("Go Back");
	private JButton okB = new JButton("OK");
	//cuadros de texto
	private JTextField nameT = new JTextField();
	private JTextField surnameT = new JTextField();
	private JTextField amountT = new JTextField();
	private JPasswordField pinP = new JPasswordField();
	private JPasswordField newPinP = new JPasswordField();
	private JPasswordField confirmPinP = new JPasswordField();
	private JTextField cardNumberT = new JTextField();
	//labels?? esto a lo mejor no lo usamos
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
					// esto hay que modificarlo, IMPORTANTE, si no se cambia por un logger
					// el sonar dice que el proyecto no es evaluable
					e.printStackTrace();
				}
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
		// titulo del frame
		setTitle("PrePaidSystem");
		// modo de salida del frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// tamanio
		this.setSize(700, 480);
		// centrado
		Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((window.width / 2)-(this.getWidth() / 2),(window.height/2)-(this.getHeight()/2));
		// el contenedor
		contentPanel = new JPanel();
		// pongo el panel en la ventana??
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		// cargo el mapa
		try {
			map = new FileOperations().loadFile(path);
		} catch (ExpiredCardException | IOException | IncorrectPinException e) {
			e.printStackTrace();
		}



		// hacemos aqui los "add" de esta forma estan ya los botones (quiza haya que
		// hacerlos invisibles todos segun empieza
		// porque haya un bug). el motivo de hacer esto aqui es que se buguea segun
		// inicia el programa, y el primer boton
		// lo pinta pero el segundo se queda oculto hasta que pasas el raton por encima.
		// es un bug
		//botones
		contentPanel.add(buyNewCardB);
		contentPanel.add(payB);
		contentPanel.add(changePinB);
		contentPanel.add(consultBalanceB);
		contentPanel.add(chargeMoneyB);
		contentPanel.add(consultMovementsB);
		contentPanel.add(exitB);
		contentPanel.add(goBackB);
		contentPanel.add(okB);
		
		//textos
		contentPanel.add(nameT);
		contentPanel.add(surnameT);
		contentPanel.add(amountT);
		contentPanel.add(pinP);
		contentPanel.add(newPinP);
		contentPanel.add(confirmPinP);
		contentPanel.add(cardNumberT);
		
		//labels
		contentPanel.add(nameL);
		contentPanel.add(surnameL);
		contentPanel.add(amountL);
		contentPanel.add(pinL);
		contentPanel.add(confirmPinL);
		contentPanel.add(newPinL);
		contentPanel.add(cardNumberL);


		//posicion botones inamovibles
		buyNewCardB.setBounds(80, 50, 250, 70);
		changePinB.setBounds(370, 50, 250, 70);
		payB.setBounds(80, 140, 250, 70);
		consultBalanceB.setBounds(370, 140, 250, 70);
		chargeMoneyB.setBounds(80, 230, 250, 70);
		consultMovementsB.setBounds(370, 230, 250, 70);
		exitB.setBounds(225, 340, 250, 70);
		goBackB.setBounds(105, 340, 200, 70);
		okB.setBounds(395, 340, 200, 70);

		//inicializacion visibilidad todo a false
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
		//accion boton buyNewCard
		buyNewCardB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonVisible(false);
				buyNewCard();
			}
		});
		//accion boton payB
		payB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonVisible(false);
				pay();
			}
		});
		//accion boton changePinB
		changePinB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonVisible(false);
				changePin();
			}
		});
		//accion boton consultBalanceB
		consultBalanceB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonVisible(false);
				consultBalance();
			}
		});
		//accion boton chargeMoneyB
		chargeMoneyB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonVisible(false);
				chargeMoney();
			}
		});
		//accion boton consultMovementsB
		consultMovementsB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonVisible(false);
				consultMovements();
			}
		});
		//accion boton exitB
		exitB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		goBackB.addActionListener(new ActionListener() {
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
				start();
			}
		});

		// llamo a la funcion donde va a empezar todo.
		start();

	}

	// funcion donde empieza todo
	public void start() {
		setTitle("Prepaid Card System");

		// aparecen los botones
		buttonVisible(true);
		
	}

	public void buyNewCard() {
		// TODO
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
	}
	
	public void changePin() {
		// TODO
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
		
	}
	
	public void pay() {
		setTitle("Pay");
		// esta funcion se encarga de pintar un nuevo boton en una posicion X (este
		// boton tendra luego sus acciones)
		payB.setVisible(true);
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

	public void buttonVisible (boolean option) {
		buyNewCardB.setVisible(option);
		changePinB.setVisible(option);
		payB.setVisible(option);
		consultBalanceB.setVisible(option);
		chargeMoneyB.setVisible(option);
		consultMovementsB.setVisible(option);
		exitB.setVisible(option);
	}
}

package es.upm.pproject.tdd.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystem;
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
	private JButton buyNewCard = new JButton("Buy New Card");
	private JButton pay = new JButton("Pay");
	private JButton chargeMoney = new JButton("Charge Money");
	private JButton changePin = new JButton("Change Pin");
	private JButton consultBalance = new JButton("Consult Balance");
	private JButton consultMovements = new JButton("Consult Movements");
	private JButton exit = new JButton("Exit");
	private JButton goBack = new JButton("Go Back");
	private JButton ok = new JButton("OK");
	//cuadros de texto
	private JTextField name = new JTextField("Name");
	private JTextField surname = new JTextField("Surname");
	private JTextField amount = new JTextField("Amount");
	private JPasswordField pin = new JPasswordField("Pin");
	private JPasswordField newPin = new JPasswordField("New Pin");
	private JPasswordField confirmPin = new JPasswordField("Confirm Pin");
	private JTextField cardNumbre = new JTextField("Card Number");
	//labels?? esto a lo mejor no lo usamos
	private JLabel text1 = new JLabel();
	private JLabel text2 = new JLabel();
	private JLabel text3 = new JLabel();
	private JLabel text4 = new JLabel();
	private JLabel text5 = new JLabel();
	

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
		this.setSize(500, 500);
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
		contentPanel.add(buyNewCard);
		contentPanel.add(pay);
		pay.setVisible(false);
		buyNewCard.setVisible(false);
		// llamo a la funcion donde va a empezar todo.
		start();

	}

	// funcion donde empieza todo
	public void start() {
		// aparece el boton
		buyNewCard.setVisible(true);
		// posicion del boton
		buyNewCard.setBounds(100, 110, 100, 100);
		// le doy la accion
		buyNewCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// esta linea de codigo sirve para probar que el mapa se carga correctamente.
				// resultado exitoso
				// JOptionPane.showMessageDialog(contentPanel,map.values().toString());
				// hago que cuando se pulse el boton, este se haga invisible
				buyNewCard.setVisible(false);
				// y llame a la funcion newButton
				pay();

			}
		});
	}

	public void pay() {
		// esta funcion se encarga de pintar un nuevo boton en una posicion X (este
		// boton tendra luego sus acciones)
		pay.setVisible(true);
		pay.setBounds(10, 11, 100, 100);
		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Toolkit.getDefaultToolkit().beep();
				// hago que cuando se pulse el boton, este se haga invisible
				pay.setVisible(false);
				// y llame a la funcion newButton
				start();

			}
		});

	}

	public void buyNewCard() {
		// TODO
	}

	public void changePin() {
		// TODO
	}

	public void consultBalance() {
		// TODO
	}

	public void consultMovements() {
		// TODO
	}

	public void chargeMoney() {
		// TODO
	}

}

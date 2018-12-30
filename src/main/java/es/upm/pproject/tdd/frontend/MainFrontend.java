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
	private JButton pruebaDialog = new JButton("Dialog");
	private JButton pruebaBeep = new JButton("Beep");

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
		contentPanel.add(pruebaDialog);
		contentPanel.add(pruebaBeep);
		pruebaBeep.setVisible(false);
		pruebaDialog.setVisible(false);
		// llamo a la funcion donde va a empezar todo.
		Main();

	}

	// funcion donde empieza todo
	public void Main() {
		// aparece el boton
		pruebaDialog.setVisible(true);
		// posicion del boton
		pruebaDialog.setBounds(100, 110, 100, 100);
		// le doy la accion
		pruebaDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// esta linea de codigo sirve para probar que el mapa se carga correctamente.
				// resultado exitoso
				// JOptionPane.showMessageDialog(contentPanel,map.values().toString());
				// hago que cuando se pulse el boton, este se haga invisible
				pruebaDialog.setVisible(false);
				// y llame a la funcion newButton
				Pay();

			}
		});
	}

	public void Pay() {
		// esta funcion se encarga de pintar un nuevo boton en una posicion X (este
		// boton tendra luego sus acciones)
		pruebaBeep.setVisible(true);
		pruebaBeep.setBounds(10, 11, 100, 100);
		pruebaBeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Toolkit.getDefaultToolkit().beep();
				// hago que cuando se pulse el boton, este se haga invisible
				pruebaBeep.setVisible(false);
				// y llame a la funcion newButton
				Main();

			}
		});

	}

	public void BuyNewCard() {
		// TODO
	}

	public void ChangePin() {
		// TODO
	}

	public void ConsultBalance() {
		// TODO
	}

	public void ConsultMovements() {
		// TODO
	}

	public void ChargeMoney() {
		// TODO
	}

}

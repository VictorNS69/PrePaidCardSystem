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

public class MainFrontend extends JFrame{

	//apartado carga de datos
	Card card;
	public final Path path = FileSystems.getDefault().getPath("src/assets/data.dat").toAbsolutePath();
	Map<Long, Card> map = new HashMap<>();

	//apartado botones y demas
	private JPanel contentPanel;
	//dejo los botones ya inicializados para asi solo tener que hacerlos visibles o no.
	private JButton pruebaDialog = new JButton("Dialog");
	private JButton pruebaBeep = new JButton("Beep");

	public static void main (String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					MainFrontend frame = new MainFrontend();
					frame.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
				);
	}

	//este main se va a dedicar exclusivamente a poner las settings de la ventana, cargar los datos
	//cargar todos los botones y demas y dejarlos invisibles y una vez hecho eso, llamara a la funcion 
	//MainMenu para que empiece el programa
	public MainFrontend() throws ExpiredCardException, IOException, IncorrectPinException{
		//titulo del frame
		setTitle("PrePaidSystem");
		//modo de salida del frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//posicion
		setBounds(100,100,500,500);
		//el contenedor
		contentPanel = new JPanel();
		//pongo el panel en la ventana??
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		//cargo el mapa
		try{
			map = new FileOperations().loadFile(path);
		}
		catch(ExpiredCardException | IOException | IncorrectPinException e) {
			e.printStackTrace();
		}
		//hacemos aqui los "add" de esta forma estan ya los botones (quiza haya que hacerlos invisibles todos segun empieza 
		// porque haya un bug). el motivo de hacer esto aqui es que se buguea segun inicia el programa, y el primer boton
		//lo pinta pero el segundo se queda oculto hasta que pasas el raton por encima. es un bug
		contentPanel.add(pruebaDialog);
		contentPanel.add(pruebaBeep);
		pruebaBeep.setVisible(false);
		pruebaDialog.setVisible(false);
		//llamo a la funcion donde va a empezar todo.
		MainMenu();



	}

	//funcion donde empieza todo
	public void MainMenu() {
		//aparece el boton
		pruebaDialog.setVisible(true);
		//posicion del boton
		pruebaDialog.setBounds(100, 110, 100, 100);
		//le doy la accion
		pruebaDialog.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//esta linea de codigo sirve para probar que el mapa se carga correctamente. resultado exitoso
				//JOptionPane.showMessageDialog(contentPanel,map.values().toString());
				//hago que cuando se pulse el boton, este se haga invisible
				pruebaDialog.setVisible(false);
				//y llame a la funcion newButton
				newButton();

			}	
		}
				);
	}

	public void newButton () {
		//esta funcion se encarga de pintar un nuevo boton en una posicion X (este boton tendra luego sus acciones)
		pruebaBeep.setVisible(true);
		pruebaBeep.setBounds(10, 11, 100, 100);
		pruebaBeep.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Toolkit.getDefaultToolkit().beep();
				//hago que cuando se pulse el boton, este se haga invisible
				pruebaBeep.setVisible(false);
				//y llame a la funcion newButton
				MainMenu();

			}	
		}
				);

	}
	//esto puede ser util porque podemos hacer un main bien hecho y desde ahi ir editando funciones que lleven 
	//de una ventana del menu a otra y viceversa
}

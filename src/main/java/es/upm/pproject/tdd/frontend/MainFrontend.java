package es.upm.pproject.tdd.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MainFrontend extends JFrame{
	
	private JPanel contentPanel;
	private JButton pruebaDialog;
	private JButton pruebaBeep;

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
public MainFrontend(){
	//titulo del frame
	setTitle("PrePaidSystem");
	//modo de salida del frame
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//posicion
	setBounds(100,100,450,433);
	//el contenedor
	contentPanel = new JPanel();
	//borde del panel
	contentPanel.setBorder(new EmptyBorder(5,5,5,5));
	//pongo el panel en la ventana???
	setContentPane(contentPanel);
	contentPanel.setLayout(null);
	//creo 1 boton
	pruebaDialog = new JButton("Dialog");
	pruebaDialog.setBounds(10, 11, 100, 100);
	//le doy la accion
	pruebaDialog.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			//creo un dialog de ejemplo
			JDialog hola = new JDialog();
			hola.setBounds(100,100,300,300);
			hola.setVisible(true);
			//hago que cuando se pulse el boton, este se haga invisible
			pruebaDialog.setVisible(false);
			//y llame a la funcion newButton
			newButton();
			
		}	
	}
);
	contentPanel.add(pruebaDialog);
	
	
}
public void newButton () {
	//esta funcion se encarga de pintar un nuevo boton en una posicion X (este boton tendra luego sus acciones)
	pruebaBeep = new JButton("Beep");
	pruebaBeep.setBounds(10, 11, 100, 100);
	pruebaBeep.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Toolkit.getDefaultToolkit().beep();
			//hago que cuando se pulse el boton, este se haga invisible
			pruebaBeep.setVisible(false);
			//y llame a la funcion newButton
			pruebaDialog.setVisible(true);
			
		}	
	}
);
	contentPanel.add(pruebaBeep);
}
//esto puede ser util porque podemos hacer un main bien hecho y desde ahi ir editando funciones que lleven 
//de una ventana del menu a otra y viceversa
}

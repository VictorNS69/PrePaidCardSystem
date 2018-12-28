package es.upm.pproject.tdd.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MainFrontend extends JFrame{
	
private JPanel contentpanel;

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
	contentpanel = new JPanel();
	//borde del panel
	contentpanel.setBorder(new EmptyBorder(5,5,5,5));
	//pongo el panel en la ventana???
	setContentPane(contentpanel);
	contentpanel.setLayout(null);
	//creo 1 boton
	JButton prueba = new JButton("prueba");
	//le doy la accion
	prueba.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			//creo un dialog de ejemplo
			JDialog hola = new JDialog();
			hola.setBounds(100,100,300,300);
			hola.setVisible(true);
			//hago que cuando se pulse el boton, este se haga invisible
			prueba.setVisible(false);
			//y llame a la funcion nuevobotton
			nuevobotton();
			
		}	
	}
);
	//doy tamaño y añado el boton a la ventana
	prueba.setBounds(10, 11, 100, 100);
	contentpanel.add(prueba);
	
	
}
public void nuevobotton () {
	//esta funcion se encarga de pintar un nuevo boton en una posicion X (este boton tendra luego sus acciones)
	JButton prueba2 = new JButton("prueba2");
	prueba2.setBounds(10, 11, 100, 100);
	contentpanel.add(prueba2);
}
//esto puede ser util porque podemos hacer un main bien hecho y desde ahi ir editando funciones que lleven 
//de una ventana del menu a otra y viceversa
}

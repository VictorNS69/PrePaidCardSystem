package es.upm.pproject.tdd.frontend;
import es.upm.pproject.tdd.backend.*;
import es.upm.pproject.tdd.exceptions.AlreadyRegisteredException;
import es.upm.pproject.tdd.exceptions.ExpiredCardException;
import es.upm.pproject.tdd.exceptions.IncorrectPinException;
import es.upm.pproject.tdd.exceptions.IncorrectPinFormatException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class NewCardScreen extends JFrame {
    private JButton back;
    private JButton ok;
    private JTextField name, surname, pin, pin2, amount;
    static StartScreen ventana = new StartScreen();

    public NewCardScreen(){
        super("Create new card");
        back = new JButton("Go back");
        ok = new JButton("Ok");
        centreWindow();
        name = new JTextField(10);
        surname = new JTextField(10);
        pin = new JTextField(4);
        pin2 = new JTextField(4);
        amount = new JTextField(6);
        add(new JLabel("Name "));
        add(name);
        add(new JLabel("Surname "));
        add(surname);
        add(new JLabel("PIN "));
        add(pin);
        add(new JLabel("Confirm PIN "));
        add(pin2);
        add(new JLabel("Amount "));
        add(amount);
        add(back);
        add(ok);
        ok.addActionListener(new Handler());
        setLayout(new GridLayout(6,2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void centreWindow(){
        int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((ancho/2)-(getWidth()/2),(alto/2)-(getHeight()/2),600,350);
        setLocation((ancho/2)-(getWidth()/2),(alto/2)-(getHeight()/2));
    }
    class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch (s){
                case "Ok":
                    String n = name.getText();
                    String sur = surname.getText();
                    String p = pin.getText();
                    String p2 = pin2.getText();
                    String a = amount.getText();
                    float aF = Float.valueOf(a).floatValue();
                    if (p == p2){
                       Path path = FileSystems.getDefault().getPath("src/assets/doc.txt").toAbsolutePath();
                       ArrayList l = new ArrayList();
                       Manager manager = new Manager(l);
                       try {
						manager.buyCard(n, sur, p, aF);
					} catch (AlreadyRegisteredException | IncorrectPinFormatException | IncorrectPinException
							| ExpiredCardException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                       try {
						new FileOperations().SaveFile(path, manager.getMap());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    }
                    break;
                case "Go back":
                    EventQueue.invokeLater(() -> {
                        ventana.setVisible(true);
                        dispose();
                    });
                    break;
                default:
                    break;
            }
        }
    }
}

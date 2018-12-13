package es.upm.pproject.tdd.frontend;
import es.upm.pproject.tdd.backend.*;
import javax.swing.*;
import java.awt.*;

public class NewCardScreen extends JFrame {
    private JButton back;
    private JButton ok;
    private JTextField name, surname, pin, pin2, amount;

    public NewCardScreen(){
        super("Create new card");
        back = new JButton("Go back");
        ok = new JButton("Ok");
        centreWindow();
        back.addActionListener(new ButtonListener());
        ok.addActionListener(new ButtonListener());
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
        setLayout(new GridLayout(6,2));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void centreWindow(){
        int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((ancho/2)-(getWidth()/2),(alto/2)-(getHeight()/2),600,350);
        setLocation((ancho/2)-(getWidth()/2),(alto/2)-(getHeight()/2));
    }
}

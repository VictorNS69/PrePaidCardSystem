package es.upm.pproject.tdd.frontend;
import es.upm.pproject.tdd.backend.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {
    private JButton yesCard;
    private JButton noCard;
    public StartScreen(){
        super("Welcome!");
        yesCard = new JButton("I already have a card");
        noCard = new JButton("I don't have a card");
        yesCard.setBounds(150, 68, 300, 60);
        noCard.setBounds(150, 180, 300, 60);
        add(yesCard);
        add(noCard);
        centreWindow();
        yesCard.addActionListener(new ButtonListener());
        noCard.addActionListener(new ButtonListener());
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void centreWindow(){
        int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((ancho/2)-(getWidth()/2),(alto/2)-(getHeight()/2),600,350);
        setLocation((ancho/2)-(getWidth()/2),(alto/2)-(getHeight()/2));
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() ->{
            StartScreen ventana = new StartScreen();
            ventana.setVisible(true);
        });

    }
}

class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
            switch (s){
                case "I already have a card":
                    break;
                case "I don't have a card":
                    NewCardScreen ventanaNueva = new NewCardScreen();
                    break;
                case "Ok":
                    Toolkit.getDefaultToolkit().beep();
                    break;
                case "Go back":
                    break;
                default:
                    break;
            }

    }
}
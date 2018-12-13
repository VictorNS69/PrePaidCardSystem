package es.upm.pproject.tdd.frontend;
import es.upm.pproject.tdd.backend.*;
import es.upm.pproject.tdd.exceptions.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartScreen extends JFrame {
    private JButton yesCard;
    private JButton noCard;
    static StartScreen ventana = new StartScreen();

    public StartScreen() {
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

    public void centreWindow() {
        int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((ancho / 2) - (getWidth() / 2), (alto / 2) - (getHeight() / 2), 600, 350);
        setLocation((ancho / 2) - (getWidth() / 2), (alto / 2) - (getHeight() / 2));
    }

    public static void main(String[] args) throws IOException, IncorrectPinFormatException, NumberFormatException,
            IncorrectPinException, ExpiredCardException {
        Path path = FileSystems.getDefault().getPath("src/assets/doc.txt").toAbsolutePath();
        LoadFile load = new LoadFile(path);
        ArrayList list = new ArrayList<>(load.getCards().values());
        Manager manager = new Manager(list);
        EventQueue.invokeLater(() -> {
            ventana.setVisible(true);
        });

    }


    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch (s) {
                case "I already have a card":
                    EventQueue.invokeLater(() -> {
                        CardExists ventanaNueva = new CardExists();
                        ventanaNueva.setVisible(true);
                    });
                    break;
                case "I don't have a card":
                    EventQueue.invokeLater(() -> {
                        NewCardScreen ventanaNueva = new NewCardScreen();
                        ventanaNueva.setVisible(true);
                        dispose();
                    });
                    break;
            }
        }
    }
}
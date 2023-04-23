import GUI.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MainMenu mainMenu = new MainMenu();
        JFrame frame = new JFrame("Main Menu");
        frame.setContentPane(mainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

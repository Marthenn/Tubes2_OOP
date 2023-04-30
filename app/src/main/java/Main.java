import javax.swing.*;
import GUI.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World! GUI STARTED!");
        MainMenu mainMenu = new MainMenu();
        JFrame frame = new JFrame("ObjectEnjoyer");
        frame.setContentPane(mainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

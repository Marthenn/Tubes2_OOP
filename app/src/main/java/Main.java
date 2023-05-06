import GUI.MainMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello World! GUI STARTED!");
        MainMenu mainMenu = MainMenu.getInstance();
        JFrame frame = new JFrame("ObjectEnjoyer");
        frame.setContentPane(mainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

import GUI.LoadingScreen;
import GUI.MainMenu;
import Plugins.Plugin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World! GUI STARTED!");
        Plugin.initialLoad();
        LoadingScreen loadingScreen = new LoadingScreen();
        JFrame loadingFrame = new JFrame("Loading Data..");
        loadingFrame.setContentPane(loadingScreen.getPanel());
        loadingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadingFrame.pack();
        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setResizable(false);
        loadingFrame.setVisible(true);

        while (!loadingScreen.isFinished()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Close loading screen and show main menu
        loadingFrame.dispose();
        MainMenu mainMenu = MainMenu.getInstance();
        JFrame mainFrame = new JFrame("ObjectEnjoyer");
        mainFrame.setContentPane(mainMenu);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }
}

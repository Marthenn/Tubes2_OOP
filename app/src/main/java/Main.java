import Core.DataStore.DataStore;
import Core.Settings.Settings;
import GUI.LoadingScreen;
import GUI.MainMenu;
import Plugins.Plugin;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

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
                Settings.getInstance().loadPath();
                Settings.getInstance().loadFileType();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error loading settings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(Settings.getInstance().getPath() != null){
                try {
                    DataStore ds = DataStore.getInstance();
                    HashMap<String, Integer> retMap = ds.load();
                    String message = String.join(", ", retMap.keySet().stream().filter(key -> retMap.get(key) == 0).toList());
                    if (!message.equals("")) {
                        JOptionPane.showMessageDialog(null, message + " wasn't loaded");
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            loadingScreen.setFinished(true);
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

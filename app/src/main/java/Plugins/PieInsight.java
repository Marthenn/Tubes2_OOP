package Plugins;

import GUI.MainMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class PieInsight implements Plugin {
    private MainMenu mainMenu = MainMenu.getInstance();

    public void load() {
        System.out.println("Loading PieChart based insight");

        // add PieChart menuItem
        Field menuBarField = findField(MainMenu.class, "menuBar");
        menuBarField.setAccessible(true);
        Field tabbedPaneField = findField(MainMenu.class, "tabbedPane");
        tabbedPaneField.setAccessible(true);
        JMenuBar menuBar = null;
        try {
            menuBar = (JMenuBar) menuBarField.get(mainMenu);
            JMenuItem pieChart = new JMenuItem("PieChart");
            pieChart.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    if(tabbedPaneField != null){
                        JTabbedPane tabbedPane = null;
                        try {
                            tabbedPane = (JTabbedPane) tabbedPaneField.get(mainMenu);
                            if(tabbedPane.indexOfTab("PieChart") == -1){
                                tabbedPane.addTab("PieChart", new PieInsightGUI());
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            // add PieChart menuItem at the second from last index
            menuBar.add(pieChart, menuBar.getMenuCount() - 1);
            addToSetting("PieChart", null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void unload() {
        System.out.println("Unloading PieChart based insight");
    }

    public ArrayList<String> getItems() {
        ArrayList<String> items = new ArrayList<String>();
        items.add("PieChart");
        return items;
    }

}

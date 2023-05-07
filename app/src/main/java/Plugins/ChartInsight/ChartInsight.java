package Plugins.ChartInsight;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.QuantifiableItem;
import GUI.MainMenu;
import Plugins.Plugin;

import java.awt.event.MouseAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class ChartInsight implements Plugin {
    private MainMenu mainMenu = MainMenu.getInstance();
    private static DataStore ds = DataStore.getInstance();
    private static JMenuItem chartInsight = new JMenuItem("ChartInsight");

    public void load(){
        System.out.println("Loading ChartInsight");

        Field menuBarField = findField(MainMenu.class, "menuBar");
        menuBarField.setAccessible(true);
        Field tabbedPaneField = findField(MainMenu.class, "tabbedPane");
        tabbedPaneField.setAccessible(true);
        JMenuBar menuBar = null;
        try {
            menuBar = (JMenuBar) menuBarField.get(mainMenu);
            chartInsight.addMouseListener((new MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    if(tabbedPaneField != null){
                        JTabbedPane tabbedPane = null;
                        try {
                            tabbedPane = (JTabbedPane) tabbedPaneField.get(mainMenu);
                            if(tabbedPane.indexOfTab("ChartInsight") == -1){
                                tabbedPane.addTab("ChartInsight", new ChartInsightGUI());
                            }
                            tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("ChartInsight"));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }));

            menuBar.add(chartInsight, menuBar.getMenuCount() - 1);
            addToSetting("ChartInsight", null);
            System.out.println("Successfully added ChartInsight menuItem");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getItems() {
        ArrayList<String> items = new ArrayList<String>();
        items.add("ChartInsight");
        return items;
    }

    public void unload(){
        System.out.println("Unloading ChartInsight");
        Field menuBarField = findField(MainMenu.class, "menuBar");
        menuBarField.setAccessible(true);
        JMenuBar menuBar = null;
        try {
            menuBar = (JMenuBar) menuBarField.get(mainMenu);
            menuBar.remove(chartInsight);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        removeFromSetting("ChartInsight");
    }

    public static Map<Integer, Integer> getSoldItems(){
        Map<Integer, Integer> soldItems = new HashMap<>();
        for (Customer cs : ds.getCustomers()){
            for (FixedBill fb : cs.getHistory()){
                for (QuantifiableItem qi : fb.getItems()) {
                    System.out.println(qi.getID() + " " + qi.getQuantity());
                    if (soldItems.containsKey(qi.getID())) {
                        soldItems.put(qi.getID(), soldItems.get(qi.getID()) + qi.getQuantity());
                    } else {
                        soldItems.put(qi.getID(), qi.getQuantity());
                    }
                }
            }
        }
        for (PremiumCustomer pc : ds.getPremiumCustomers()){
            for (FixedBill fb : pc.getHistory()){
                for (QuantifiableItem qi : fb.getItems()) {
                    if (soldItems.containsKey(qi.getID())) {
                        soldItems.put(qi.getID(), soldItems.get(qi.getID()) + qi.getQuantity());
                    } else {
                        soldItems.put(qi.getID(), qi.getQuantity());
                    }
                }
            }
        }
        return soldItems;
    }
}

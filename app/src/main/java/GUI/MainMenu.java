/*
 * Created by JFormDesigner on Sun Apr 23 04:42:03 WIB 2023
 */

package GUI;

import Core.DataStore.DataStore;
import Core.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * @author Marthen
 */
public class MainMenu extends JPanel {
    private static MainMenu instance = null;
    public static MainMenu getInstance(){
        if (MainMenu.instance == null){
            MainMenu.instance = new MainMenu();
        }
        return MainMenu.instance;
    }
    private MainMenu() {
        initComponents();
        Dimension size = new Dimension(800, 600);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        // add home tab
        tabbedPane.addTab("Home", new Home());

        // date clock threading
        Thread clockThread = new Thread(() -> {
            try {
                while (true) {
                    dateTime.setText(new java.util.Date().toString());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        clockThread.start();
    }

    private void exitMenuMousePressed(MouseEvent e) {
        try {
            DataStore.getInstance().saveBill();
            DataStore.getInstance().saveImage();
            DataStore.getInstance().saveItem();
            DataStore.getInstance().saveCustomers();
            DataStore.getInstance().savePremiumCustomers();
        } catch (Exception error) {
            System.out.println(error);
        }
//        // save settings_config
//        try {
//            Settings.getInstance().savePath();
//            Settings.getInstance().saveFileType();
//        } catch (IOException er) {
//            throw new RuntimeException(er);
//        }
        System.exit(0);
    }

    private void closeTabMiddleMouse(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON2){
            int tabIdx = tabbedPane.indexAtLocation(e.getX(),e.getY());
            if (tabIdx >= 0) {
                tabbedPane.removeTabAt(tabIdx);
            }
        }
    }

    private void homeMenuMousePressed(MouseEvent e) {
        if(tabbedPane.indexOfTab("Home") == -1){
            tabbedPane.addTab("Home", new Home());
        }
        tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Home"));
    }

    private void cashierMenuMousePressed(MouseEvent e) {
        tabbedPane.addTab("Cashier", new Cashier(tabbedPane));
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
    }

    private void inventoryMenuMousePressed(MouseEvent e) {
        if(tabbedPane.indexOfTab("Inventory") == -1){
            tabbedPane.addTab("Inventory", new Inventory());
        }
        tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Inventory"));
    }

    private void registerMenuMousePressed(MouseEvent e) {
        if(tabbedPane.indexOfTab("Register") == -1){
            tabbedPane.addTab("Register", new Register());
        }
        tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Register"));
    }

    private void updateMenuMousePressed(MouseEvent e) {
        if(tabbedPane.indexOfTab("Update") == -1){
            tabbedPane.addTab("Update", new Update());
        }
        tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Update"));
    }

    private void settingMenuMousePressed(MouseEvent e) {
        if(tabbedPane.indexOfTab("Setting") == -1){
            tabbedPane.addTab("Setting", Setting.getInstance());
        }
        tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Setting"));
    }

    private void historyMenuMousePressed(MouseEvent e) {
        if(tabbedPane.indexOfTab("History") == -1){
            tabbedPane.addTab("History", new History());
        }
        tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("History"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
        menuBar = new JMenuBar();
        homeMenu = new JMenu();
        storeMenu = new JMenu();
        cashierMenu = new JMenuItem();
        inventoryMenu = new JMenuItem();
        historyMenu = new JMenuItem();
        memberMenu = new JMenu();
        registerMenu = new JMenuItem();
        updateMenu = new JMenuItem();
        settingMenu = new JMenu();
        exitMenu = new JMenu();
        tabbedPane = new JTabbedPane();
        dateTime = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(800, 600));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
        ( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border
        . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
        propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( )
        ; }} );
        setLayout(new BorderLayout());

        //======== menuBar ========
        {

            //======== homeMenu ========
            {
                homeMenu.setText("Home");
                homeMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        homeMenuMousePressed(e);
                    }
                });
            }
            menuBar.add(homeMenu);

            //======== storeMenu ========
            {
                storeMenu.setText("Store");

                //---- cashierMenu ----
                cashierMenu.setText("Cashier");
                cashierMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        cashierMenuMousePressed(e);
                    }
                });
                storeMenu.add(cashierMenu);

                //---- inventoryMenu ----
                inventoryMenu.setText("Inventory");
                inventoryMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        inventoryMenuMousePressed(e);
                    }
                });
                storeMenu.add(inventoryMenu);

                //---- historyMenu ----
                historyMenu.setText("History");
                historyMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        historyMenuMousePressed(e);
                    }
                });
                storeMenu.add(historyMenu);
            }
            menuBar.add(storeMenu);

            //======== memberMenu ========
            {
                memberMenu.setText("Member");

                //---- registerMenu ----
                registerMenu.setText("Register");
                registerMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        registerMenuMousePressed(e);
                    }
                });
                memberMenu.add(registerMenu);

                //---- updateMenu ----
                updateMenu.setText("Update");
                updateMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        updateMenuMousePressed(e);
                    }
                });
                memberMenu.add(updateMenu);


            }
            menuBar.add(memberMenu);

            //======== settingMenu ========
            {
                settingMenu.setText("Setting");
                settingMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        settingMenuMousePressed(e);
                    }
                });
            }
            menuBar.add(settingMenu);

            //======== exitMenu ========
            {
                exitMenu.setText("Exit");
                exitMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        exitMenuMousePressed(e);
                    }
                });
            }
            menuBar.add(exitMenu);
        }
        add(menuBar, BorderLayout.NORTH);

        //======== tabbedPane ========
        {
            tabbedPane.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    closeTabMiddleMouse(e);
                }
            });
        }
        add(tabbedPane, BorderLayout.CENTER);

        //---- dateTime ----
        dateTime.setText("text");
        dateTime.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(dateTime, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JMenu storeMenu;
    private JMenuItem cashierMenu;
    private JMenuItem inventoryMenu;
    private JMenuItem historyMenu;
    private JMenu memberMenu;
    private JMenuItem registerMenu;
    private JMenuItem updateMenu;
    private JMenu settingMenu;
    private JMenu exitMenu;
    private JTabbedPane tabbedPane;
    private JLabel dateTime;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

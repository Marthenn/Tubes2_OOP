/*
 * Created by JFormDesigner on Sun Apr 23 04:42:03 WIB 2023
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Marthen
 */
public class MainMenu extends JPanel {
    public MainMenu() {
        initComponents();
        Dimension size = new Dimension(800, 600);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        // add home tab
        tabbedPane.addTab("Home", new Home());

        // date clock threading
        Thread clockThread = new Thread() {
            public void run() {
                try {
                    while (true) {
                        dateTime.setText(new java.util.Date().toString());
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        clockThread.start();
    }

    private void exitMenuMousePressed(MouseEvent e) {
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
        // restore home tab if closed
        if(tabbedPane.indexOfTab("Home") == -1){
            tabbedPane.addTab("Home", new Home());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
        menuBar = new JMenuBar();
        homeMenu = new JMenu();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menu2 = new JMenu();
        exitMenu = new JMenu();
        tabbedPane = new JTabbedPane();
        dateTime = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(800, 600));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
        0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
        . BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
        red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
        beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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

            //======== menu1 ========
            {
                menu1.setText("Member");

                //---- menuItem1 ----
                menuItem1.setText("Register");
                menu1.add(menuItem1);
            }
            menuBar.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("text");
            }
            menuBar.add(menu2);

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
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenu menu2;
    private JMenu exitMenu;
    private JTabbedPane tabbedPane;
    private JLabel dateTime;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

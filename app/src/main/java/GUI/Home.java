/*
 * Created by JFormDesigner on Sun Apr 23 13:09:32 WIB 2023
 */

package GUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author Marthen
 */
public class Home extends JPanel {
    public Home() {
        initComponents();
        Dimension size = new Dimension(800, 600);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        
        // set logo
        logo.setIcon(new ImageIcon("img\\placeholder.jpg"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
        appName = new JLabel();
        logo = new JLabel();
        namaKelompok = new JLabel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
        ( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border
        . TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
        propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( )
        ; }} );
        setLayout(new BorderLayout());

        //---- appName ----
        appName.setText("ObjectEnjoyer - HEY");
        add(appName, BorderLayout.NORTH);
        add(logo, BorderLayout.CENTER);

        //---- namaKelompok ----
        namaKelompok.setText("13521060 - Fatih | 13521088 - Puti | 13521091 - Fakih | 13521130 - Althaaf | 13521144 - Marthen");
        add(namaKelompok, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
    private JLabel appName;
    private JLabel logo;
    private JLabel namaKelompok;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

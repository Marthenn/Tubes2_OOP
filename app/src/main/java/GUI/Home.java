/*
 * Created by JFormDesigner on Sun Apr 23 13:09:32 WIB 2023
 */

package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Home extends JPanel {
    public Home() {
        initComponents();
        Dimension size = new Dimension(800, 600);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        // set logo
        String path = "app" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "GUI" + File.separator + "ObjectEnjoyer_Logo.png";
        logo.setIcon(new ImageIcon(path));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
        appName = new JLabel();
        logo = new JLabel();
        namaKelompok = new JLabel();

        //======== this ========
        setLayout(new GridBagLayout());

        //---- appName ----
        appName.setText("ObjectEnjoyer - HEY");
        appName.setHorizontalAlignment(SwingConstants.CENTER);
        add(appName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

        //---- logo ----
        add(logo, new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

        //---- namaKelompok ----
        namaKelompok.setText("<html><br>13521060 - Fatih Nararya Rashadyfa I&nbsp;| " +
                "13521088 - Puti Nabilla Aidira<br>" +
                "13521091 - Fakih Anugerah Pratama &nbsp;&nbsp;| " +
                "13521130 - Althaaf Khasyi Atisomya<br>" +
                "13521144 - Bintang Dwi Marthen</html>");
        namaKelompok.setHorizontalAlignment(SwingConstants.CENTER);
        add(namaKelompok, new GridBagConstraints(0, 2, 1, 2, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
    private JLabel appName;
    private JLabel logo;
    private JLabel namaKelompok;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

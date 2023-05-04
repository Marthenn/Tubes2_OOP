/*
 * Created by JFormDesigner on Fri May 05 05:56:14 ICT 2023
 */

package GUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author Fakih A
 */
public class CashierItemAdd extends JDialog {
    public CashierItemAdd() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        this.setSize(1000, 1200);
        this.setTitle("add new item");
        this.setLocationRelativeTo(null);

        initComponents();

        this.setVisible(true);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(5, 4, 5, 5));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

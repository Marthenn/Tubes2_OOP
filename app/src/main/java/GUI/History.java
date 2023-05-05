/*
 * Created by JFormDesigner on Mon Apr 24 01:02:36 WIB 2023
 */

package GUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author Marthen
 */
public class History extends JPanel {
    public History() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
        DefaultComboBoxModel memberListModel = new DefaultComboBoxModel();
        


        NameLabel = new JLabel();
        Name = new JComboBox(memberListModel);
        MembershipLabel = new JLabel();
        Membership = new JLabel();
        SearchBox = new JTextField();
        HistoryTablePane = new JScrollPane();
        HistoryTable = new JTable();

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.7, 1.0, 1.0, 1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- NameLabel ----
        NameLabel.setText("Name");
        add(NameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- Name ----
        add(Name, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- MembershipLabel ----
        MembershipLabel.setText("Membership Status");
        add(MembershipLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- Membership ----
        Membership.setText("DefaultMembership");
        add(Membership, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- SearchBox ----
        SearchBox.setText("Search by");
        add(SearchBox, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== HistoryTablePane ========
        {
            HistoryTablePane.setViewportView(HistoryTable);
        }
        add(HistoryTablePane, new GridBagConstraints(0, 3, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    private JLabel NameLabel;
    private JComboBox Name;
    private JLabel MembershipLabel;
    private JLabel Membership;
    private JTextField SearchBox;
    private JScrollPane HistoryTablePane;
    private JTable HistoryTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

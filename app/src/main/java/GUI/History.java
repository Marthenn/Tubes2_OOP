/*
 * Created by JFormDesigner on Mon Apr 24 01:02:36 WIB 2023
 */

package GUI;

import Core.Customer.Customer;
import Core.Customer.MembershipState.MembershipStateName;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.DataStore.Exception.CustomerNotExistException;
import Core.DataStore.Exception.PromotedCustomerAlreadyExist;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Marthen
 */
public class History extends JPanel {
    DataStore dataStore;
    Customer selectedCustomer;
    public History() {
        this.dataStore = DataStore.getInstance();

        //DEBUG POPULATING//
        dataStore.createNewCustomer();
        dataStore.createNewCustomer();
        dataStore.createNewCustomer();
//        try {
////
//            dataStore.promoteCustomer(1, "Bembi", "10", "11", MembershipStateName.MEMBER);
//
//        } catch (CustomerNotExistException e) {
//            System.out.println("a");
//        } catch (PromotedCustomerAlreadyExist e) {
//            System.out.println("b");
//        }
        // HAPUS KALO GA DIPAKE //


        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
        DefaultComboBoxModel customerListModel = new DefaultComboBoxModel();

        List<? extends Customer> listOfAllCustomers = Stream.of(dataStore.getCustomers(), dataStore.getPremiumCustomers())
                                                        .flatMap(x -> x.stream())
                                                        .collect(Collectors.toList());

        for (Customer customer : listOfAllCustomers) {
            customerListModel.addElement(customer.getID());
        }

        NameLabel = new JLabel();
        Name = new JComboBox(customerListModel);
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


        Name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                updateCustomerData((int) Name.getSelectedItem());

            }
        });


        // SET DEFAULT VALUE
        if (listOfAllCustomers.size() != 0) {
            updateCustomerData((int)customerListModel.getSelectedItem());
        }
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

    Boolean isMember(Customer customer) {
        return customer.getClass() == PremiumCustomer.class;
    }

    void updateCustomerData(int customerID) {
        // TODO : gabungin
        try {
            selectedCustomer = dataStore.getCustomerWithID(customerID);
        } catch (SearchedItemNotExist e) {

        }

        try {
            selectedCustomer = dataStore.getPremiumCustomerWithID(customerID);
        } catch (SearchedItemNotExist e) {

        }

        if (!isMember(selectedCustomer)) {
            Membership.setText("Customer");
        } else {
            PremiumCustomer _premiumCustomer = (PremiumCustomer) selectedCustomer;
            Membership.setText(_premiumCustomer.getStatus().getName());
        }
    }
}

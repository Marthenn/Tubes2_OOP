/*
 * Created by JFormDesigner on Mon Apr 24 01:02:36 WIB 2023
 */

package GUI;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.FullReportPrinter;
import Core.DataStore.StorerData.StorerDataListener;
import Core.IDAble.IDAbleListener;
import Core.Item.Bill.Exception.ItemInBillNotExist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Marthen
 */
public class History extends JPanel implements IDAbleListener<Customer>, StorerDataListener {
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

        DataStore.getInstance().listenToCustomer(this);
        DataStore.getInstance().listenToCustomerStore(this);

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
        PrintFullReport = new JButton();

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

        //---- PrintFullReport ----
        PrintFullReport.setText("Print Full Sales Report");
        add(PrintFullReport, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        PrintFullReport.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {PrintFullReportPressed(e);
                } catch (SearchedItemNotExist | ItemInBillNotExist ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
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

    private void PrintFullReportPressed(MouseEvent e) throws SearchedItemNotExist, ItemInBillNotExist {
        pathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = pathChooser.showOpenDialog(History.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = pathChooser.getSelectedFile();
            FullReportPrinter fullReportPrinter = new FullReportPrinter(selectedFile.getAbsolutePath() + "/full_report-" + new Date() + ".pdf");
            Thread reportThread = new Thread(fullReportPrinter::printFullReport);
            reportThread.start();
        }
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
    private JButton PrintFullReport;
    private JFileChooser pathChooser = new JFileChooser();
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

    @Override
    public void onItemWithIDChange(Customer item) {

    }

    @Override
    public void onStorerDataChange(String storerName) {

    }
}

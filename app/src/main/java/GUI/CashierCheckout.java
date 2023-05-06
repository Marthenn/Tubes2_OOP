/*
 * Created by JFormDesigner on Sat May 06 14:53:39 ICT 2023
 */

package GUI;

import Core.Customer.Customer;
import Core.Customer.MembershipState.MembershipStateName;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.DataStore.Exception.CustomerNotExistException;
import Core.DataStore.Exception.PromotedCustomerAlreadyExist;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Fakih A
 */
public class CashierCheckout extends JPanel {
    JTabbedPane parentTabbedPane;
    Cashier parentCashier;
    public CashierCheckout(JTabbedPane parentTabbedPane, Cashier parentCashier) {
        this.parentTabbedPane = parentTabbedPane;
        this.parentCashier = parentCashier;

        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama

        //data//
        try {
            DataStore.getInstance().promoteCustomer(DataStore.getInstance().createNewCustomer().getID(), "lmao", "123456789012", "asd@gmail.com", MembershipStateName.VIP );
            DataStore.getInstance().promoteCustomer(DataStore.getInstance().createNewCustomer().getID(), "likalat", "123456789012", "asd@gmail.com", MembershipStateName.MEMBER );

            System.out.println("custard" + DataStore.getInstance().getCustomers().toString());
        } catch (CustomerNotExistException e) {
            throw new RuntimeException(e);
        } catch (PromotedCustomerAlreadyExist e) {
            throw new RuntimeException(e);
        }
        //data//

        Title = new JLabel();
        DaftarPembelian = new JScrollPane();
        TabelPembelian = new JTable();
        LabelCustomer = new JLabel();
        NamaCustomer = new CashierComboBox(DataStore.getInstance().getPremiumCustomers());
        DaftarDetail = new JScrollPane();
        TabelDetail = new JTable();
        LabelTotalPembelian = new JLabel();
        NilaiTotalPembelian = new JLabel();
        Checkout = new JButton();

        NamaCustomer.setEditable(true);

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.6, 0.6, 2.0, 1.0, 0.2, 1.0E-4};

        //---- Title ----
        Title.setText("Checkout");
        add(Title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== DaftarPembelian ========
        {
            DaftarPembelian.setViewportView(TabelPembelian);
        }
        add(DaftarPembelian, new GridBagConstraints(3, 0, 2, 3, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- LabelCustomer ----
        LabelCustomer.setText("Nama");
        add(LabelCustomer, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(NamaCustomer, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== DaftarDetail ========
        {
            DaftarDetail.setViewportView(TabelDetail);
        }
        add(DaftarDetail, new GridBagConstraints(0, 2, 3, 3, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- LabelTotalPembelian ----
        LabelTotalPembelian.setText("Total");
        add(LabelTotalPembelian, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- NilaiTotalPembelian ----
        NilaiTotalPembelian.setText("1000");
        add(NilaiTotalPembelian, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- Checkout ----
        Checkout.setText("Checkout");
        add(Checkout, new GridBagConstraints(3, 4, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        CashierCheckout thisCashierCheckout = this;
        Checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // function finish checkout

                // hide this page (TODO: destroy or recycle instead)
                parentTabbedPane.setComponentAt(parentTabbedPane.getSelectedIndex(), parentCashier);
                parentTabbedPane.repaint();
                thisCashierCheckout.removeAll();
            }
        });

        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    private JLabel Title;
    private JScrollPane DaftarPembelian;
    private JTable TabelPembelian;
    private JLabel LabelCustomer;
    private CashierComboBox NamaCustomer;
    private JScrollPane DaftarDetail;
    private JTable TabelDetail;
    private JLabel LabelTotalPembelian;
    private JLabel NilaiTotalPembelian;
    private JButton Checkout;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

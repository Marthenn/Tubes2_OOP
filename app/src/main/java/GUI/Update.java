/*
 * Created by JFormDesigner on Mon Apr 24 01:02:49 WIB 2023
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import Core.Customer.Customer;
import Core.Customer.MembershipState.MembershipStateName;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.DataStore.Exception.CustomerNotExistException;
import Core.DataStore.Exception.PromotedCustomerAlreadyExist;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;

/**
 * @author Marthen
 */
public class Update extends JPanel {

    private DataStore ds = DataStore.getInstance();
    private ArrayList<PremiumCustomer> premiumCustomers = new ArrayList<>();

    public Update() {
        initComponents();
        for (PremiumCustomer pc : premiumCustomers) {
            String name = pc.getName();
            Integer id = pc.getID();
            memberDropDown.addItem(name + " (" + id + ")");
        }
        Thread updateThread = new Thread(() -> {
            try {
                while(true){
                    ArrayList<PremiumCustomer> tempCustomer = ds.getPremiumCustomers();

                    for (PremiumCustomer pc : tempCustomer) {
                        if (!premiumCustomers.contains(pc)) {
                            premiumCustomers.add(pc);
                            String name = pc.getName();
                            Integer id = pc.getID();
                            memberDropDown.addItem(name + " (" + id + ")");
                        } else{
                            // find the index of the pc in drop down by id
                            int index = -1;
                            for (int i = 0 ; i < memberDropDown.getItemCount() ; i++){
                                if (memberDropDown.getItemAt(i).toString().contains(pc.getID().toString())){
                                    String name = "";
                                    for(String s : memberDropDown.getItemAt(i).toString().split(" ")){
                                        if (s.contains("(")){
                                            break;
                                        }
                                        name += s + " ";
                                    }
                                    // remove the last space
                                    name = name.substring(0, name.length() - 1);
                                    if (!name.equals(pc.getName())){
                                        memberDropDown.removeItemAt(i);
                                        memberDropDown.addItem(pc.getName() + " (" + pc.getID() + ")");
                                    }
                                }
                            }
                        }
                    }

                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        updateThread.start();
    }

    private void nameFieldKeyReleased(KeyEvent e) {
        String input = nameField.getText();
        // check if alphabet using regex
        if (!input.matches("[a-zA-Z ]*")) {
            nameField.setText(input.substring(0, input.length() - 1));
        }
        if (input.length() > 0) {
            nameVerifLabel.setText("VALID");
        } else {
            nameVerifLabel.setText("INVALID");
        }
    }

    private void nameFieldFocusGained(FocusEvent e) {
        if(nameField.getText().isEmpty()){
            nameVerifLabel.setText("INVALID");
        }
    }

    private void phoneFieldFocusGained(FocusEvent e) {
        if(phoneField.getText().equals("must be 12 digits")){
            phoneField.setText("");
            phoneVerifLabel.setText("INVALID");
            if(phoneField.getText().length() == 12){
                for (int i = 0; i < phoneField.getText().length(); i++) {
                    if(!Character.isDigit(phoneField.getText().charAt(i))){
                        phoneVerifLabel.setText("INVALID");
                        break;
                    }
                }
                phoneVerifLabel.setText("VALID");
            }
        }
    }

    private void phoneFieldFocusLost(FocusEvent e) {
        if (phoneField.getText().length() == 0){
            phoneField.setText("must be 12 digits");
        }
    }

    private void phoneFieldKeyReleased(KeyEvent e) {
        String input = phoneField.getText();
        if(!Character.isDigit(input.charAt(input.length()-1))){
            phoneField.setText(input.substring(0, input.length()-1));
        }
        if(input.length() > 12){
            phoneField.setText(input.substring(0, 12));
        }
        if(input.length() >= 12){
            phoneVerifLabel.setText("VALID");
        } else {
            phoneVerifLabel.setText("INVALID");
        }
    }

    private void emailFieldKeyReleased(KeyEvent e) {
        String input = emailField.getText();
        // check validity of email using regex
        if(input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            emailVerifLabel.setText("VALID");
        } else {
            emailVerifLabel.setText("INVALID");
        }
    }

    private void emailFieldFocusGained(FocusEvent e) {
        if(emailField.getText().length() == 0){
            emailVerifLabel.setText("INVALID");
        }
    }

    private void cancelButtonMousePressed(MouseEvent e) {
        memberDropDown.setSelectedIndex(-1);
    }


    private Integer getSelectedID(){
        String selected = memberDropDown.getSelectedItem().toString();
        String[] split = selected.split(" ");
        return Integer.parseInt(split[split.length-1].substring(1, split[split.length-1].length()-1));
    }

    private void updateButtonMousePressed(MouseEvent e) {
        // pause the update thread
        if (memberDropDown.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please select a member");
        } else if(nameField.getText().equals("") || phoneField.getText().equals("") || emailField.getText().equals("") || phoneField.getText().equals("must be 12 digits")){
            JOptionPane.showMessageDialog(null, "Please fill all fields");
        } else if (nameVerifLabel.getText().equals("INVALID") || phoneVerifLabel.getText().equals("INVALID") || emailVerifLabel.getText().equals("INVALID")){
            JOptionPane.showMessageDialog(null, "Please fill all fields correctly");
        } else {
            try{
                Integer id = getSelectedID();
                System.out.println(id);
                String name = nameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                MembershipStateName status;
                if (regularRadio.isSelected()){
                    status = MembershipStateName.MEMBER;
                } else if (vipRadio.isSelected()){
                    status = MembershipStateName.VIP;
                } else {
                    status = MembershipStateName.DEACTIVATED;
                }
                PremiumCustomer pc = ds.getPremiumCustomerWithID(id);
                pc.setName(name);
                pc.setPhoneNumber(phone);
                pc.setEmail(email);
                pc.transitionToState(status);
            } catch (SearchedItemNotExist ex) {
                throw new RuntimeException(ex);
            }
            nameField.setText("");
            phoneField.setText("must be 12 digits");
            emailField.setText("");
            regularRadio.setSelected(true);
            nameVerifLabel.setText("");
            phoneVerifLabel.setText("");
            emailVerifLabel.setText("");
            memberDropDown.setSelectedIndex(-1);
            JOptionPane.showMessageDialog(null, "Update Success");
        }
    }

    private void memberDropDown(ActionEvent e) {
        // TODO: change to get from id instead of index
        if (memberDropDown.getSelectedIndex() == -1){
            nameField.setText("");
            phoneField.setText("must be 12 digits");
            emailField.setText("");
            regularRadio.setSelected(true);
            nameVerifLabel.setText("");
            phoneVerifLabel.setText("");
            emailVerifLabel.setText("");
            return;
        }
        nameField.setText(ds.getPremiumCustomers().get(memberDropDown.getSelectedIndex()).getName());
        phoneField.setText(ds.getPremiumCustomers().get(memberDropDown.getSelectedIndex()).getPhoneNumber());
        emailField.setText(ds.getPremiumCustomers().get(memberDropDown.getSelectedIndex()).getEmail());
        if(ds.getPremiumCustomers().get(memberDropDown.getSelectedIndex()).getStatus().equals(MembershipStateName.MEMBER)){
            regularRadio.setSelected(true);
        } else if (ds.getPremiumCustomers().get(memberDropDown.getSelectedIndex()).getStatus().equals(MembershipStateName.VIP)){
            vipRadio.setSelected(true);
        } else {
            offRadio.setSelected(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
        headerLabel = new JLabel();
        panel1 = new JPanel();
        namelabel = new JLabel();
        phoneLabel = new JLabel();
        emailLabel = new JLabel();
        vipRadio = new JRadioButton();
        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        cancelButton = new JButton();
        updateButton = new JButton();
        membershipLabel = new JLabel();
        regularRadio = new JRadioButton();
        nameVerifLabel = new JLabel();
        phoneVerifLabel = new JLabel();
        emailVerifLabel = new JLabel();
        memberLabel = new JLabel();
        memberDropDown = new JComboBox();
        offRadio = new JRadioButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
        border.EmptyBorder(0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax.swing.border.TitledBorder.CENTER
        ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069al\u006fg",java.awt.Font
        .BOLD,12),java.awt.Color.red), getBorder())); addPropertyChangeListener(
        new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062or\u0064er"
        .equals(e.getPropertyName()))throw new RuntimeException();}});
        setLayout(new BorderLayout());

        //---- headerLabel ----
        headerLabel.setText("Update Member");
        headerLabel.setFont(new Font("Verdana", Font.BOLD, 48));
        add(headerLabel, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- namelabel ----
            namelabel.setText("Name");
            namelabel.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(namelabel);
            namelabel.setBounds(50, 100, 250, 60);

            //---- phoneLabel ----
            phoneLabel.setText("Phone");
            phoneLabel.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(phoneLabel);
            phoneLabel.setBounds(50, 165, 250, 60);

            //---- emailLabel ----
            emailLabel.setText("Email");
            emailLabel.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(emailLabel);
            emailLabel.setBounds(50, 235, 250, 60);

            //---- vipRadio ----
            vipRadio.setText("VIP");
            vipRadio.setFont(new Font("Verdana", Font.BOLD, 34));
            panel1.add(vipRadio);
            vipRadio.setBounds(530, 310, 105, 40);

            //---- nameField ----
            nameField.setFont(new Font("Verdana", Font.PLAIN, 30));
            nameField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    nameFieldKeyReleased(e);
                }
            });
            nameField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    nameFieldFocusGained(e);
                }
            });
            panel1.add(nameField);
            nameField.setBounds(245, 100, 440, 60);

            //---- phoneField ----
            phoneField.setFont(new Font("Verdana", Font.PLAIN, 30));
            phoneField.setText("must be 12 digits");
            phoneField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    phoneFieldFocusGained(e);
                }
                @Override
                public void focusLost(FocusEvent e) {
                    phoneFieldFocusLost(e);
                }
            });
            phoneField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    phoneFieldKeyReleased(e);
                }
            });
            panel1.add(phoneField);
            phoneField.setBounds(245, 165, 440, 60);

            //---- emailField ----
            emailField.setFont(new Font("Verdana", Font.PLAIN, 30));
            emailField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    emailFieldKeyReleased(e);
                }
            });
            emailField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    emailFieldFocusGained(e);
                }
            });
            panel1.add(emailField);
            emailField.setBounds(245, 235, 440, 60);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setFont(new Font("Verdana", Font.BOLD, 30));
            cancelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    cancelButtonMousePressed(e);
                }
            });
            panel1.add(cancelButton);
            cancelButton.setBounds(155, 365, 185, 55);

            //---- updateButton ----
            updateButton.setText("Update");
            updateButton.setFont(new Font("Verdana", Font.BOLD, 30));
            updateButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    updateButtonMousePressed(e);
                }
            });
            panel1.add(updateButton);
            updateButton.setBounds(460, 365, 185, 55);

            //---- membershipLabel ----
            membershipLabel.setText("Membership");
            membershipLabel.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(membershipLabel);
            membershipLabel.setBounds(50, 300, 250, 60);

            //---- regularRadio ----
            regularRadio.setText("Regular");
            regularRadio.setFont(new Font("Verdana", Font.BOLD, 34));
            regularRadio.setSelected(true);
            panel1.add(regularRadio);
            regularRadio.setBounds(new Rectangle(new Point(320, 305), regularRadio.getPreferredSize()));

            //---- nameVerifLabel ----
            nameVerifLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
            panel1.add(nameVerifLabel);
            nameVerifLabel.setBounds(690, 110, 90, 38);

            //---- phoneVerifLabel ----
            phoneVerifLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
            panel1.add(phoneVerifLabel);
            phoneVerifLabel.setBounds(690, 175, 90, 38);

            //---- emailVerifLabel ----
            emailVerifLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
            panel1.add(emailVerifLabel);
            emailVerifLabel.setBounds(690, 245, 90, 38);

            //---- memberLabel ----
            memberLabel.setText("Member");
            memberLabel.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(memberLabel);
            memberLabel.setBounds(45, 25, 250, 60);

            //---- memberDropDown ----
            memberDropDown.setFont(new Font("Verdana", Font.PLAIN, 30));
            memberDropDown.addActionListener(e -> memberDropDown(e));
            panel1.add(memberDropDown);
            memberDropDown.setBounds(245, 30, 440, 60);

            //---- offRadio ----
            offRadio.setText("Off");
            offRadio.setFont(new Font("Verdana", Font.BOLD, 34));
            panel1.add(offRadio);
            offRadio.setBounds(660, 310, 105, 40);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        add(panel1, BorderLayout.WEST);

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(vipRadio);
        buttonGroup1.add(regularRadio);
        buttonGroup1.add(offRadio);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
    private JLabel headerLabel;
    private JPanel panel1;
    private JLabel namelabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JRadioButton vipRadio;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JButton cancelButton;
    private JButton updateButton;
    private JLabel membershipLabel;
    private JRadioButton regularRadio;
    private JLabel nameVerifLabel;
    private JLabel phoneVerifLabel;
    private JLabel emailVerifLabel;
    private JLabel memberLabel;
    private JComboBox memberDropDown;
    private JRadioButton offRadio;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

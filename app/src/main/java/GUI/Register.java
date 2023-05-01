/*
 * Created by JFormDesigner on Mon Apr 24 01:02:44 WIB 2023
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.DocumentListener;

/**
 * @author Marthen
 */
public class Register extends JPanel {
    public Register() {
        initComponents();
    }

    private void cancelButtonMousePressed(MouseEvent e) {
        // TODO add your code here
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        regularRadio.setSelected(true);
        nameVerifLabel.setText("");
        phoneVerifLabel.setText("");
        emailVerifLabel.setText("");
    }

    private void registerButtonMousePressed(MouseEvent e) {
        // TODO add your code here
        if(nameField.getText().equals("") || phoneField.getText().equals("") || emailField.getText().equals("") || phoneField.getText().equals("must be 12 digits")){
            JOptionPane.showMessageDialog(null, "Please fill all fields");
        } else if (nameVerifLabel.getText().equals("INVALID") || phoneVerifLabel.getText().equals("INVALID") || emailVerifLabel.getText().equals("INVALID")){
            JOptionPane.showMessageDialog(null, "Please fill all fields correctly");
        } else {
            JOptionPane.showMessageDialog(null, "Register Success");
        }
    }

    private void phoneFieldFocusGained(FocusEvent e) {
        // TODO add your code here
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
        // TODO add your code here
        if (phoneField.getText().length() == 0){
            phoneField.setText("must be 12 digits");
        }
    }

    private void nameFieldKeyReleased(KeyEvent e) {
        // TODO add your code here
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

    private void phoneFieldKeyReleased(KeyEvent e) {
        // TODO add your code here
        String input = phoneField.getText();
        if(!Character.isDigit(input.charAt(input.length()-1))){
            phoneField.setText(input.substring(0, input.length()-1));
        }
        if(input.length() > 12){
            phoneField.setText(input.substring(0, 12));
        }
        if(input.length() == 12){
            phoneVerifLabel.setText("VALID");
        } else {
            phoneVerifLabel.setText("INVALID");
        }
    }

    private void emailFieldKeyReleased(KeyEvent e) {
        // TODO add your code here
        String input = emailField.getText();
        // check validity of email using regex
        if(input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            emailVerifLabel.setText("VALID");
        } else {
            emailVerifLabel.setText("INVALID");
        }
    }

    private void nameFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        if(nameField.getText().length() == 0){
            nameVerifLabel.setText("INVALID");
        }
    }

    private void emailFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        if(emailField.getText().length() == 0){
            emailVerifLabel.setText("INVALID");
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
        registerButton = new JButton();
        membershipLabel = new JLabel();
        regularRadio = new JRadioButton();
        nameVerifLabel = new JLabel();
        phoneVerifLabel = new JLabel();
        emailVerifLabel = new JLabel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
        border.EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER
        ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font
        .BOLD,12),java.awt.Color.red), getBorder())); addPropertyChangeListener(
        new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r"
        .equals(e.getPropertyName()))throw new RuntimeException();}});
        setLayout(new BorderLayout());

        //---- headerLabel ----
        headerLabel.setText("Register New Member");
        headerLabel.setFont(new Font("Verdana", Font.BOLD, 48));
        add(headerLabel, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- namelabel ----
            namelabel.setText("Name");
            namelabel.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(namelabel);
            namelabel.setBounds(50, 65, 250, 60);

            //---- phoneLabel ----
            phoneLabel.setText("Phone");
            phoneLabel.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(phoneLabel);
            phoneLabel.setBounds(50, 130, 250, 60);

            //---- emailLabel ----
            emailLabel.setText("Email");
            emailLabel.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(emailLabel);
            emailLabel.setBounds(50, 200, 250, 60);

            //---- vipRadio ----
            vipRadio.setText("VIP");
            vipRadio.setFont(new Font("Verdana", Font.BOLD, 34));
            panel1.add(vipRadio);
            vipRadio.setBounds(530, 270, 105, 40);

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
            nameField.setBounds(245, 65, 440, 60);

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
            phoneField.setBounds(245, 130, 440, 60);

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
            emailField.setBounds(245, 200, 440, 60);

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
            cancelButton.setBounds(154, 360, 185, 55);

            //---- registerButton ----
            registerButton.setText("Register");
            registerButton.setFont(new Font("Verdana", Font.BOLD, 30));
            registerButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    registerButtonMousePressed(e);
                }
            });
            panel1.add(registerButton);
            registerButton.setBounds(461, 360, 185, 55);

            //---- membershipLabel ----
            membershipLabel.setText("Membership");
            membershipLabel.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(membershipLabel);
            membershipLabel.setBounds(50, 260, 250, 60);

            //---- regularRadio ----
            regularRadio.setText("Regular");
            regularRadio.setFont(new Font("Verdana", Font.BOLD, 34));
            regularRadio.setSelected(true);
            panel1.add(regularRadio);
            regularRadio.setBounds(new Rectangle(new Point(320, 265), regularRadio.getPreferredSize()));

            //---- nameVerifLabel ----
            nameVerifLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
            panel1.add(nameVerifLabel);
            nameVerifLabel.setBounds(690, 75, 90, 38);

            //---- phoneVerifLabel ----
            phoneVerifLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
            panel1.add(phoneVerifLabel);
            phoneVerifLabel.setBounds(690, 140, 90, 38);

            //---- emailVerifLabel ----
            emailVerifLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
            panel1.add(emailVerifLabel);
            emailVerifLabel.setBounds(690, 210, 90, 38);

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
    private JButton registerButton;
    private JLabel membershipLabel;
    private JRadioButton regularRadio;
    private JLabel nameVerifLabel;
    private JLabel phoneVerifLabel;
    private JLabel emailVerifLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

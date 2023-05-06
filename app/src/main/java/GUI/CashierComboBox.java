package GUI;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CashierComboBox extends JComboBox {
    ArrayList<PremiumCustomer>  customerList = new ArrayList<>();
    JTextField textField = new JTextField();
    DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    BasicComboBoxEditor comboBoxEditor = new BasicComboBoxEditor();
    public CashierComboBox(ArrayList<PremiumCustomer> customerList){
        this.customerList = customerList;

        this.setEditable(true);

        initComponents();

        updateComboModel();
    }

    public void initComponents(){
        this.setEditor(comboBoxEditor);
        this.setModel(comboBoxModel);


        textField = (JTextField) comboBoxEditor.getEditorComponent();

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
//                showPopup();
                if (e.getSource() instanceof JTextField){
                    updateComboModel();
                    hidePopup();
                    showPopup();
                }
            }


        });
    }

    public void updateComboModel(){
        // TODO: MAKE SURE OF THIS
        String lastInput = textField.getText().substring(0, textField.getText().length() >= 16 ?
                                                                16
                                                                :
                                                                textField.getText().length()
                                                        );
        comboBoxModel.removeAllElements();
        comboBoxModel.addElement(lastInput);

        for (PremiumCustomer pCustomer : customerList) {
            if (pCustomer.getName().contains(lastInput)) {
                        comboBoxModel.addElement(pCustomer.getName());
            }
        }
    }




}

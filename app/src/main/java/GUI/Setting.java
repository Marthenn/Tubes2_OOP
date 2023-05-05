/*
 * Created by JFormDesigner on Mon Apr 24 01:02:27 WIB 2023
 */

package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Marthen
 */
public class Setting extends JPanel {
    private JButton saveButton;
    private JFileChooser fileChooser;
    private JRadioButton objRadioButton, xmlRadioButton, jsonRadioButton;
    private JLabel fileFormat;

    public Setting() {
        initComponents();
    }

    private void initComponents() {
        // create the JFileChooser component
        fileChooser = new JFileChooser();

        // create label for file format
        JLabel fileFormat = new JLabel("File Format:");

        // create the radio button group
        ButtonGroup buttonGroup = new ButtonGroup();

        // create the radio buttons
        JRadioButton objRadioButton = new JRadioButton("Object");
        JRadioButton xmlRadioButton = new JRadioButton("XML");
        JRadioButton jsonRadioButton = new JRadioButton("JSON");

        // add the radio buttons to the group
        buttonGroup.add(objRadioButton);
        buttonGroup.add(xmlRadioButton);
        buttonGroup.add(jsonRadioButton);

        // create the button to trigger the file chooser
        saveButton = new JButton("Configure Save Location");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open the file chooser dialog
                int result = fileChooser.showSaveDialog(Setting.this);

                // TODO: check if file > 1 -> folder
                if (result == JFileChooser.APPROVE_OPTION) {
                    // get the selected file
                    File selectedFile = fileChooser.getSelectedFile();

                    // TODO: truly saving file
                }
            }
        });

        // add the radio buttons and save button to the panel
        add(fileFormat);
        add(objRadioButton);
        add(xmlRadioButton);
        add(jsonRadioButton);
        add(saveButton);
        // TODO: plugin setting
    }
}


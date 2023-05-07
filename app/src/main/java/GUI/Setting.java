/*
 * Created by JFormDesigner on Mon Apr 24 01:02:27 WIB 2023
 */

package GUI;

import Core.Settings;
import Plugins.Plugin;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarInputStream;

import Core.DataStore.DataStore;
import Core.Settings;

/**
 * @author Marthen
 */
public class Setting extends JPanel {
    private Settings settings = Settings.getInstance();

    private static Setting instance = null;
//    private JButton saveButton;
    private JFileChooser fileChooser = new JFileChooser();
//    private JRadioButton objRadioButton, xmlRadioButton, jsonRadioButton;
//    private JLabel fileFormat;

    private Setting() {
        initComponents();

        Thread settingThread = new Thread(() -> {
            while (true) {
                if (settings.getPath() != null) {
                    directoryLabel.setText(settings.getPath());
                }
                if(settings.getFileType().get("OBJ") == null){
                    objBox.setSelected(false);
                } else if (settings.getFileType().get("OBJ")) {
                    objBox.setSelected(true);
                } else {
                    objBox.setSelected(false);
                }
                if(settings.getFileType().get("XML") == null){
                    xmlBox.setSelected(false);
                } else if (settings.getFileType().get("XML")) {
                    xmlBox.setSelected(true);
                } else {
                    xmlBox.setSelected(false);
                }
                if(settings.getFileType().get("JSON") == null){
                    jsonBox.setSelected(false);
                } else if (settings.getFileType().get("JSON")) {
                    jsonBox.setSelected(true);
                } else {
                    jsonBox.setSelected(false);
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        settingThread.start();
    }

    public static Setting getInstance() {
        if (instance == null) {
            instance = new Setting();
        }
        return instance;
    }

    private void dirButtonMousePressed(MouseEvent e) {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(Setting.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            settings.setPath(selectedFile.getAbsolutePath());
            // save settings_config
            try {
                Settings.getInstance().savePath();
            } catch (IOException er) {
                throw new RuntimeException(er);
            }
        }
    }

    @SneakyThrows
    private void loadButtonMousePressed(MouseEvent e) {
        // TODO: handle loading of plugins
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("JAR File", "jar"));
        int result = fileChooser.showOpenDialog(Setting.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            settings.addPlugin(selectedFile.getAbsolutePath());
        }
    }

    private void resetButtonMousePressed(MouseEvent e) {
        for (Plugin plugin : settings.getPlugins()) {
            plugin.unload();
        }
    }

    private void objBoxItemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            settings.getFileType().put("OBJ", true);
        } else {
            settings.getFileType().put("OBJ", false);
        }
        // save settings_config
        try {
            Settings.getInstance().saveFileType();
        } catch (IOException er) {
            throw new RuntimeException(er);
        }
    }

    private void jsonBoxItemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            settings.getFileType().put("JSON", true);
        } else {
            settings.getFileType().put("JSON", false);
        }
        // save settings_config
        try {
            Settings.getInstance().saveFileType();
        } catch (IOException er) {
            throw new RuntimeException(er);
        }
    }

    private void xmlBoxItemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            settings.getFileType().put("XML", true);
        } else {
            settings.getFileType().put("XML", false);
        }
        // save settings_config
        try {
            Settings.getInstance().saveFileType();
        } catch (IOException er) {
            throw new RuntimeException(er);
        }
    }

    public void removePlugin(String name){
        for (Component component : viewportPanel.getComponents()) {
            JPanel panel = (JPanel) component;
            JLabel label = (JLabel) panel.getComponent(0);
            if (label.getText().equals(name)) {
                viewportPanel.remove(panel);
                break;
            }
        }
        viewportPanel.revalidate();
        viewportPanel.repaint();
        pluginPanes.revalidate();
        pluginPanes.repaint();
    }

    public void addPlugin(String name, ArrayList<String> items){
        if (items == null) {
            items = new ArrayList<>();
        }
        // Create a new JComboBox with the items ArrayList
        JComboBox<String> dropdown = new JComboBox<>(items.toArray(new String[0]));
        if (items.size() == 0) {
            dropdown.addItem("No items");
        }
        dropdown.setSelectedIndex(0);

        // Create a new JPanel with a BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Create a new JLabel for the plugin name and add it to the left of the JPanel
        JLabel nameLabel = new JLabel(name);
        panel.add(nameLabel, BorderLayout.WEST);

        // Add the JComboBox to the right of the JPanel
        panel.add(dropdown, BorderLayout.CENTER);

        // Add the JPanel to the viewportPanel
        viewportPanel.add(panel);

        // Repaint the viewportPanel to update its content
        viewportPanel.revalidate();
        viewportPanel.repaint();
        pluginPanes.revalidate();
        pluginPanes.repaint();

        System.out.println("added plugin to pane");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
        label1 = new JLabel();
        panel1 = new JPanel();
        loadButton = new JButton();
        label3 = new JLabel();
        label2 = new JLabel();
        objBox = new JCheckBox();
        jsonBox = new JCheckBox();
        xmlBox = new JCheckBox();
        pluginPanes = new JScrollPane();
        viewportPanel = new JPanel();
        resetButton = new JButton();
        dirButton = new JButton();
        directoryLabel = new JLabel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
        . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
        . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
        Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );
        setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("Settings");
        label1.setFont(new Font("Verdana", Font.BOLD, 48));
        add(label1, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- loadButton ----
            loadButton.setText("Load");
            loadButton.setFont(new Font("Verdana", Font.BOLD, 36));
            loadButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    loadButtonMousePressed(e);
                }
            });
            panel1.add(loadButton);
            loadButton.setBounds(205, 115, 150, 45);

            //---- label3 ----
            label3.setText("Plugins");
            label3.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(15, 115), label3.getPreferredSize()));

            //---- label2 ----
            label2.setText("File Saving Formats");
            label2.setFont(new Font("Verdana", Font.BOLD, 36));
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(10, 15), label2.getPreferredSize()));

            //---- objBox ----
            objBox.setText("OBJ");
            objBox.setFont(new Font("Verdana", Font.BOLD, 30));
            objBox.addItemListener(e -> objBoxItemStateChanged(e));
            panel1.add(objBox);
            objBox.setBounds(new Rectangle(new Point(420, 15), objBox.getPreferredSize()));

            //---- jsonBox ----
            jsonBox.setText("JSON");
            jsonBox.setFont(new Font("Verdana", Font.BOLD, 30));
            jsonBox.addItemListener(e -> jsonBoxItemStateChanged(e));
            panel1.add(jsonBox);
            jsonBox.setBounds(525, 15, 120, 43);

            //---- xmlBox ----
            xmlBox.setText("XML");
            xmlBox.setFont(new Font("Verdana", Font.BOLD, 30));
            xmlBox.addItemListener(e -> xmlBoxItemStateChanged(e));
            panel1.add(xmlBox);
            xmlBox.setBounds(655, 15, 150, 43);

            //======== pluginPanes ========
            {

                //======== viewportPanel ========
                {
                    viewportPanel.setLayout(new BoxLayout(viewportPanel, BoxLayout.Y_AXIS));
                }
                pluginPanes.setViewportView(viewportPanel);
            }
            panel1.add(pluginPanes);
            pluginPanes.setBounds(0, 170, 800, 300);

            //---- resetButton ----
            resetButton.setText("Reset");
            resetButton.setFont(new Font("Verdana", Font.BOLD, 36));
            resetButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    resetButtonMousePressed(e);
                }
            });
            panel1.add(resetButton);
            resetButton.setBounds(375, 115, 150, 45);

            //---- dirButton ----
            dirButton.setText("Path");
            dirButton.setFont(new Font("Verdana", Font.BOLD, 36));
            dirButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    dirButtonMousePressed(e);
                }
            });
            panel1.add(dirButton);
            dirButton.setBounds(10, 65, 150, 45);

            //---- directoryLabel ----
            directoryLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            panel1.add(directoryLabel);
            directoryLabel.setBounds(175, 65, 396, 45);

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
        add(panel1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
    private JLabel label1;
    private JPanel panel1;
    private JButton loadButton;
    private JLabel label3;
    private JLabel label2;
    private JCheckBox objBox;
    private JCheckBox jsonBox;
    private JCheckBox xmlBox;
    private JScrollPane pluginPanes;
    private JPanel viewportPanel;
    private JButton resetButton;
    private JButton dirButton;
    private JLabel directoryLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}


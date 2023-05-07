package GUI;

import Core.DataStore.DataStore;
import Core.Settings;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class LoadingScreen {
    private JProgressBar progressBar = new JProgressBar();
    private boolean finished = false;

    public LoadingScreen() {
        initComponents();
    }

    private void initComponents() {
        progressBar.setStringPainted(true);
        new Thread(() -> {
            int progress = 0;
            while (!finished) {
                progressBar.setValue(progress);
                progress = progress + 1;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Settings.getInstance().loadPath();
                Settings.getInstance().loadFileType();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error loading settings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                DataStore ds = DataStore.getInstance();
                int retcode = ds.load();
                if(retcode == 1){
                    JOptionPane.showMessageDialog(null, "Image wasn't loaded");
                } else if(retcode == 2){
                    JOptionPane.showMessageDialog(null, "Item wasn't loaded");
                } else if(retcode == 3){
                    JOptionPane.showMessageDialog(null, "Customer data wasn't loaded");
                } else if(retcode == 4){
                    JOptionPane.showMessageDialog(null, "Premium customer data wasn't loaded");
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            finished = true;
        }).start();
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(400, 50));
        return panel;
    }

    public boolean isFinished() {
        return finished;
    }
}

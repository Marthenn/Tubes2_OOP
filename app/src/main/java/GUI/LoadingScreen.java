package GUI;

import Core.DataStore.DataStore;
import Core.Settings;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

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
                HashMap<String, Integer> retMap = ds.load();
                String message = String.join(", ", retMap.keySet().stream().filter(key -> retMap.get(key) == 0).toList());
                JOptionPane.showMessageDialog(null, message + " wasn't loaded");
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

package GUI;

import Core.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
            try {
                Settings.getInstance().loadPath();
                Settings.getInstance().loadFileType();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error loading settings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            progress = 100;
            int finalProgress = progress;
            SwingUtilities.invokeLater(() -> {
                progressBar.setValue(finalProgress);
                finished = true;
            });
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

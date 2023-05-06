package GUI;

import Core.DataStore.DataStore;

import javax.swing.*;
import java.awt.*;

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
            while (progress < 100) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress += 10;
                int finalProgress = progress;
                SwingUtilities.invokeLater(() -> {
                    progressBar.setValue(finalProgress);
                });
            }
            DataStore.getInstance();
            SwingUtilities.invokeLater(() -> {
                progressBar.setValue(100);
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

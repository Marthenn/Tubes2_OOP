package GUI;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen {
    private JProgressBar progressBar = new JProgressBar();
    @Setter @Getter
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
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            progressBar.setValue(100);
        }).start();
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(400, 50));
        return panel;
    }

}

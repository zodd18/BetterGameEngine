package view;

import javax.swing.*;
import java.awt.*;

public class GFrame extends JFrame {

    private JPanel  mainPanel;

    public GFrame() {
        setTitle("BetterEngine");
        setLayout(new BorderLayout());
        setDefaultConfiguration();
    }

    public GFrame(JPanel mainPanel) {
        this();

        this.mainPanel = mainPanel;
        add(mainPanel);
        setContentPane(mainPanel);

        setDefaultConfiguration();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Screen getScreen() {
        if(mainPanel instanceof Screen)
            return (Screen) mainPanel;

        try {
            throw new ScreenException();
        } catch (ScreenException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void setDefaultConfiguration() {
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setMainPanel(JPanel p) {
        removeAll();
        mainPanel = p;
        add(mainPanel);
        setSize(mainPanel.getSize());
    }
}

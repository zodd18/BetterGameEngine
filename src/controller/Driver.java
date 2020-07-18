package controller;

import view.GFrame;
import view.GPanel;
import view.Screen;

import javax.swing.*;

public class Driver {
    public Driver(int width, int height) {
        SwingUtilities.invokeLater(() -> {
            Screen s = new GPanel(width, height);
            GFrame gameFrame = new GFrame((JPanel) s);
            new GController(gameFrame);
        });
    }
}

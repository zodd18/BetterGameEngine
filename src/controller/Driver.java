package controller;

import view.GFrame;
import view.GPanel;
import view.Screen;

import javax.swing.*;

public class Driver {
    // resolution = size of JFrame
    // width/height = virtual size

    public Driver(int resolutionWidth, int resolutionHeight, int width, int height) {
        SwingUtilities.invokeLater(() -> {
            Screen s = new GPanel(resolutionWidth, resolutionHeight, width, height);
            GFrame gameFrame = new GFrame((JPanel) s);
            new GController(gameFrame);
        });
    }
}

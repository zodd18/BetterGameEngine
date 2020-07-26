package view;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public interface Screen {

    void refresh();

    Graphics2D getGraphics();

    BufferedImage getImg();

    JLabel getLabel();

    int getWidth();

    int getRealWidth();

    int getHeight();

    int getRealHeight();

    void addMouseListener(GMouseAdapter gMouseAdapter);

    void addMouseMotionListener(GMouseAdapter gMouseAdapter);

    void addKeyListener(GKeyboardAdapter gKeyboardAdapter);
}

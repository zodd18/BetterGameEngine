package view;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GPanel extends JPanel implements Screen {

    private int WIDTH, HEIGHT;

    private JLabel imgLabel;
    private BufferedImage img;
    private Graphics2D graphics;

    public GPanel(int width, int height) {
        super(new BorderLayout());

        this.WIDTH = width;
        this.HEIGHT = height;

        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics = (Graphics2D) img.getGraphics();

        imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon(img));
        imgLabel.setSize(img.getWidth(), img.getHeight());

        add(imgLabel);
        setSize(imgLabel.getSize());
    }

    @Override
    public void refresh() {
        Component p = getParent();
        if (p != null) p.repaint();
    }

    @Override
    public JLabel getLabel() {
        return imgLabel;
    }

    @Override
    public BufferedImage getImg() {
        return img;
    }

    @Override
    public Graphics2D getGraphics() {
        return graphics;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public void addMouseListener(GMouseAdapter gMouseAdapter) {
        super.addMouseListener(gMouseAdapter);
    }

    @Override
    public void addMouseMotionListener(GMouseAdapter gMouseAdapter) {
        super.addMouseMotionListener(gMouseAdapter);
    }

    @Override
    public void addKeyListener(GKeyboardAdapter gKeyboardAdapter) {
        super.addKeyListener(gKeyboardAdapter);

        super.setFocusable(true);
        super.requestFocus();
//        super.grabFocus();
    }
}

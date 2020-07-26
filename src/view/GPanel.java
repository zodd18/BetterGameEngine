package view;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.utils.GLog;
import model.utils.GraphicsUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GPanel extends JPanel implements Screen {

    private final int resolutionWidth;
    private final int resolutionHeight;

    private int WIDTH;
    private int HEIGHT;

    private JLabel imgLabel;
    private BufferedImage realImg, img;
    private Graphics2D realGraphics, graphics;

    public GPanel(int realWidth, int realHeight, int width, int height) {
        setLayout(new BorderLayout());

        this.resolutionWidth = realWidth;
        this.resolutionHeight = realHeight;

        this.WIDTH = width;
        this.HEIGHT = height;

        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        realImg = new BufferedImage(realWidth, realHeight, BufferedImage.TYPE_INT_ARGB);

        realGraphics = (Graphics2D) realImg.getGraphics();
        graphics = (Graphics2D) img.getGraphics();

        imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon(realImg));
        imgLabel.setSize(realWidth, realHeight);

        add(imgLabel);
        setSize(imgLabel.getSize());
    }

    @Override
    public void refresh() {
        realGraphics.drawImage(img, 0, 0, getRealWidth(), getRealHeight(), null);

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
    public int getRealWidth() {
        return resolutionWidth;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public int getRealHeight() {
        return resolutionHeight;
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
    }
}

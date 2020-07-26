package model.builtInGComponents.graphics;

import model.components.GComponent;
import model.settings.builtIn.PSSettings;
import model.settings.builtIn.PositionSizeSettings;
import model.utils.GLog;
import model.utils.GraphicsUtil;
import model.utils.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Sprite extends GComponent {

    private String imgPath;

    private transient BufferedImage img;

    private transient Graphics2D graphics;

    private PSSettings data;

    public Sprite(Vector2D position, Dimension size, String imgPath) {
        this.imgPath = imgPath;
        this.data = new PositionSizeSettings(position, size);

        loadImage();
    }

    public void loadImage() {
        try {
            GLog.log("loading: " + imgPath + " ...");
            URL url = getClass().getResource(imgPath);

            img = ImageIO.read(url);
            graphics = (Graphics2D) img.getGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PSSettings getData() {
        return data;
    }

    @Override
    public void render(Graphics2D g) {
        if (img == null) loadImage();

        if (img != null) {
            g.drawImage(img, data.getX(), data.getY(), data.getWidth(), data.getHeight(), null);
        } else {
            try {
                throw new SpriteException(String.format("img: \"%s\" is not loaded yet", imgPath));
            } catch (SpriteException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }
}

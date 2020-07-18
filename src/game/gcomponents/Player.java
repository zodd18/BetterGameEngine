package game.gcomponents;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.components.GComponent;
import model.scenes.Scene;
import model.utils.Hourglass;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GComponent {

    private int posX, posY;

    private int width;
    private int height;
    private int speed;

    private Hourglass timer;

    public Player(int x, int y, int w, int h, int speed) {
        this.posX = x;
        this.posY = y;
        this.width = w;
        this.height = h;
        this.speed = speed;

        this.timer = new Hourglass(20);
    }

    @Override
    public void update() {
        super.update();

        timer.update();
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        g.setColor(Color.RED);
        g.fillRect(posX, posY, width, height);
    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {
        super.input(m, k);

        if (k.isPressed(KeyEvent.VK_D)) {
            posX += speed;
        } else if (k.isPressed(KeyEvent.VK_A)) {
            posX -= speed;
        }

        if (k.isPressed(KeyEvent.VK_W)) {
            posY -= speed;
        } else if (k.isPressed(KeyEvent.VK_S)) {
            posY += speed;
        }

        if (timer.finished() && k.isPressed(KeyEvent.VK_SPACE)) {
            getParent().enqueueGComponent(new Player(posX - width - 40, posY, width, height, speed));

            timer.reset();
            timer.stop();
        }
    }
}

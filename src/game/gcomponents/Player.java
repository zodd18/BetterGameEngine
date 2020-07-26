package game.gcomponents;

import model.Game;
import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.builtInGComponents.collisions.CollisionBox;
import model.components.GComponent;
import model.settings.builtIn.PositionSizeSettings;
import model.utils.GLog;
import model.utils.Hourglass;
import model.settings.builtIn.PSSettings;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GComponent {

    private Hourglass spaceTimer, hitBoxVisionTimer;
    private PSSettings data;

    public Player(int x, int y, int w, int h, int speed) {
        data = new PositionSizeSettings(x, y, w, h);
        data.addSetting("SPEED", speed);
        data.addSetting("COLOR", Color.RED);
        spaceTimer = new Hourglass(40);
        hitBoxVisionTimer = new Hourglass(20);
    }

    @Override
    public void additionRoutine() {
        super.additionRoutine();

        CollisionBox collisionBox = new CollisionBox(data.getPosition(), data.getSize(), 0, 0, 0, 0);
        collisionBox.setRenderingColor(Color.WHITE);

        addGExtension(getParentScene().get("COLLISIONS"), collisionBox);
    }

    @Override
    public void update() {
        super.update();

        spaceTimer.update();
        hitBoxVisionTimer.update();
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        g.setColor((Color) data.get("COLOR").value());
        g.fillRect(data.getX(), data.getY(), data.getWidth(), data.getHeight());
    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {
        super.input(m, k);

        if (k.isPressed(KeyEvent.VK_D)) {
            data.incX(data.get("SPEED").valueAsInt());
        } else if (k.isPressed(KeyEvent.VK_A)) {
            data.incX(-data.get("SPEED").valueAsInt());
        }

        if (k.isPressed(KeyEvent.VK_W)) {
            data.incY(-data.get("SPEED").valueAsInt());
        } else if (k.isPressed(KeyEvent.VK_S)) {
            data.incY(data.get("SPEED").valueAsInt());
        }

        if (spaceTimer.finished() && k.isPressed(KeyEvent.VK_SPACE)) {
            Player clone = new Player(data.getX() - data.getWidth() - 40, data.getY(), data.getWidth(), data.getHeight(), data.get("SPEED").valueAsInt());
            addGExtension("CLONE", clone);

            spaceTimer.reset();
            spaceTimer.stop();
        }

        if (hitBoxVisionTimer.finished() && k.isPressed(KeyEvent.VK_H)) {
            if (GLog.getSelectedLevel() == GLog.ALL) {
                GLog.setSelectedLevel(GLog.MAX);
            } else GLog.setSelectedLevel(GLog.ALL);

            hitBoxVisionTimer.reset();
        }

        if (k.isPressed(KeyEvent.VK_R)) {
            destroyGExtension("CLONE");
            spaceTimer.reset();
        }

        if (data.getX() + data.getWidth() > Game.getScreen().getWidth()) data.setX(Game.getScreen().getWidth() - data.getWidth() - 1);
        else if (data.getX() < 0) data.setX(0);
        if (data.getY() + data.getHeight() > Game.getScreen().getHeight()) data.setY(Game.getScreen().getHeight() -data.getHeight() - 1);
        else if (data.getY() < 0) data.setY(0);
    }

    public PSSettings getData() {
        return data;
    }
}
